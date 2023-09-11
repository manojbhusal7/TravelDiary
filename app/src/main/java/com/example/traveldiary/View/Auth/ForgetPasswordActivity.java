package com.example.traveldiary.View.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveldiary.remote.ApiResponse;
import com.example.traveldiary.R;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        EditText etEmail = findViewById(R.id.etEmail);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                boolean check = validateemail(email);
                RegisterService registerService = APIUtil.getUserservices();
                Call<ApiResponse> ApiResponseCall = registerService.forgot(email);

                ApiResponseCall.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.code() == 200){
//                            ApiResponse ApiResponse = response.body();
//                            SharedPreferences shp = getSharedPreferences("FORGOTPASS", MODE_PRIVATE);
//                            shp.edit().putBoolean("email", true).apply();
                            Toast.makeText(ForgetPasswordActivity.this, "Otp has been sent. Please verify to reset password.", Toast.LENGTH_SHORT).show();
                            if (check == true) {
                            Intent i = new Intent(ForgetPasswordActivity.this,EmailVerificationActivity.class);
                            i.putExtra("user_email",email );
                            i.putExtra("source_activity", "forgot_password");
                            startActivity(i);}
                        } else if (response.code() == 401) {
                            Toast.makeText(ForgetPasswordActivity.this, "Email not matched with our records.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this, "Error on Server", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(ForgetPasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
            private boolean validateemail(String email){
                if (email.length() == 0) {
                    etEmail.requestFocus();
                    etEmail.setError("Field cannot be empty");
                    return false;} else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etEmail.requestFocus();
                    etEmail.setError("Enter valid email address");
                    return false;

                }
                else {
                    return true;
                }
            }
        });
    }







    }
