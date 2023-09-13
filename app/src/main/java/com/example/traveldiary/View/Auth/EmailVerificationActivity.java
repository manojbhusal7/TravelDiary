package com.example.traveldiary.View.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveldiary.R;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.ApiResponse;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailVerificationActivity extends AppCompatActivity {
    private String userEmail; // Declare userEmail as a class member

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        Button btnSubmit = findViewById(R.id.btSubmit);
        EditText etOTP = findViewById(R.id.etOTP);
        TextView btnResend = findViewById(R.id.tvResendOtp);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterService registerService = APIUtil.getUserservices();
                Bundle extras = getIntent().getExtras();
                String email = extras.getString("user_email");
                String otp = etOTP.getText().toString();
                String source = extras.getString("source_activity");

                    Call<ApiResponse> ApiResponseCall = registerService.verifyOtp(email,otp);

                    ApiResponseCall.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.code() == 200) {
//                                ApiResponse ApiResponse = response.body();
//                                String message = ApiResponse.getMessage();
//                                SharedPreferences shp = getSharedPreferences("Email Verify", MODE_PRIVATE);
//
//                                // Save login true if you want to save that user has logged in
//                                // and don't want the user to go to the login screen every time they open the app
//                                shp.edit().putBoolean("otp", true).apply();

                                if ("register".equals(source)) {
                                    Intent i = new Intent(EmailVerificationActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    Toast.makeText(EmailVerificationActivity.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                } else if ("forgot_password".equals(source)) {
                                    // User came from forgot password, go to ResetPasswordActivity
                                    Intent i = new Intent(EmailVerificationActivity.this, ResetPassActivity.class);
                                    i.putExtra("user_email",email );
                                    startActivity(i);
                                    Toast.makeText(EmailVerificationActivity.this, "Successfully Verified", Toast.LENGTH_SHORT).show();
                                }
                            } else if (response.code() == 401) {
                                Toast.makeText(EmailVerificationActivity.this, "Otp doesn't match", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EmailVerificationActivity.this, "Error on Server", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(EmailVerificationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

            }
        });

        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterService registerService = APIUtil.getUserservices();
                Bundle extras = getIntent().getExtras();
                String email = extras.getString("user_email");

                Call<ApiResponse> ApiResponseCall = registerService.resend(email);
                ApiResponseCall.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(EmailVerificationActivity.this, "Otp has been sent Again.", Toast.LENGTH_SHORT).show();
                        } else if (response.code()==401){
                            Toast.makeText(EmailVerificationActivity.this, "Error!! Please try after about 5 minutes.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(EmailVerificationActivity.this, "Error on Server!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(EmailVerificationActivity.this, "Failed to resend!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
