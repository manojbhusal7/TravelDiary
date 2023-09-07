package com.example.traveldiary.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveldiary.Model.RegisterResponse;
import com.example.traveldiary.Model.RegistrationModel;
import com.example.traveldiary.R;
import com.example.traveldiary.SearchActivity;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextView tvLogin =  findViewById(R.id.tvLogin);
        Button btRegister = findViewById(R.id.btRegister);



//        SharedPreferences shp = getSharedPreferences("CLUB",MODE_PRIVATE);
//        Boolean isLoggedIn = shp.getBoolean("LOGIN",false);
//        if (isLoggedIn){
//            Intent i = new Intent(RegistrationActivity.this, SearchActivity.class);
//            startActivity(i);
//            finish();


        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPass = findViewById(R.id.etPass);
        EditText etCpass = findViewById(R.id.etCpass);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etName.getText().toString();

                String email = etEmail.getText().toString();

                String password = etPass.getText().toString();
//                String cpassword = etCpass.getText().toString();

                boolean check = validateinfo(fullname, email, password);
                RegisterService registerService = APIUtil.getUserservices();
                Call<RegisterResponse> userRegisterCall = registerService.registerUser(etName.getText().toString(), etEmail.getText().toString(), etPass.getText().toString(), etCpass.getText().toString());
                userRegisterCall.enqueue(new Callback<RegisterResponse>() {


                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.code() == 200) {

                            Toast.makeText(RegistrationActivity.this, "OTP has been sent to your email. Please verify.", Toast.LENGTH_SHORT).show();
                            RegisterResponse userRegister = response.body();
                            String tokenRegister = userRegister.getAccessToken();


                            //save token
                            SharedPreferences shp = getSharedPreferences("USERREGISTER", MODE_PRIVATE);
                            shp.edit().putString("TOKENREGISTER", tokenRegister).apply();
                            shp.edit().putBoolean("USERREGISTER", true).apply();
                            if (check == true) {
                                Intent i = new Intent(RegistrationActivity.this, EmailVerificationActivity.class);
                                startActivity(i);
                                i.putExtra("user_email",email );
                                i.putExtra("source_activity", "signup");
                            }
                        } else if (response.code() == 422) {
                            Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Error on Server", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            private boolean validateinfo(String fullname,  String email, String password) {
                if (fullname.length() == 0) {
                    etName.requestFocus();
                    etName.setError("Field cannot be empty");
                    return false;

                } else if (email.length() == 0) {
                    etEmail.requestFocus();
                    etEmail.setError("Field cannot be empty");
                    return false;} else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etEmail.requestFocus();
                    etEmail.setError("Enter valid email address");
                    return false;

                }


                else if (password.length() < 8) {
                    etPass.requestFocus();
                    etPass.setError("Minimum 8 characters long");
                    return false;

                }

//else if (!password.equals(etCpass)) {
//                    Toast.makeText(RegistrationActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
//                    return false;
//                }


                 else {
                    return true;
                }

            }
        });




            }

    }

