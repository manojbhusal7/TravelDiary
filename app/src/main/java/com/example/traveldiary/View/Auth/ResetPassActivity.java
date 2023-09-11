package com.example.traveldiary.View.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveldiary.R;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.ApiResponse;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        EditText newpass = findViewById(R.id.etNewPass);
        EditText cnewpass = findViewById(R.id.etCNewPass);
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterService registerService = APIUtil.getUserservices();
                Bundle extras = getIntent().getExtras();
                String email = extras.getString("user_email");
                String new_password = newpass.getText().toString();
                String new_password_confirmation = cnewpass.getText().toString();

                boolean check = validatepass(new_password,new_password_confirmation);


                if (check == true) {
                Call<ApiResponse> ApiResponseCall = registerService.reset(email, new_password, new_password_confirmation);

                ApiResponseCall.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.code() == 200) {

                                Toast.makeText(ResetPassActivity.this, "Password Reset Successful.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ResetPassActivity.this,LoginActivity.class);
                                startActivity(i);

                        } else {
                            Toast.makeText(ResetPassActivity.this, "Failed!! Error on server.", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(ResetPassActivity.this, "Failed!!", Toast.LENGTH_SHORT).show();

                    }
                });}
            }


            private boolean validatepass(String newPassword, String confirmNewPassword) {
                if (newPassword.length() < 8) {
                    newpass.setError("Minimum 8 characters long");
                    newpass.requestFocus();
                    return false;
                }

                if (!newPassword.equals(confirmNewPassword)) {
                    newpass.setError("Passwords don't match");
                    cnewpass.setError("Passwords don't match");
                    newpass.requestFocus();
                    return false;
                }

                return true;
            }
        });
    }

}
