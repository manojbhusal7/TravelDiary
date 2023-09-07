package com.example.traveldiary.Auth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.traveldiary.Model.ApiResponse;
import com.example.traveldiary.Model.OtpVerificationResponse;
import com.example.traveldiary.R;
import com.example.traveldiary.remote.RegisterService;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailVerificationActivity extends AppCompatActivity {


    Button button;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);


        TextView resendButton = findViewById(R.id.tvResendOtp);
        resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOtp(); // Call the method to resend OTP
            }
        });

        button = findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp(); // Call the OTP verification method
            }
        });
    }
    private void resendOtp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://traveldiary.oxfordcollege.edu.np/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterService api = retrofit.create(RegisterService.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("user_email");
            String sourceActivity = extras.getString("source_activity");}
        // Assuming userEmail is already initialized in your class
        Call<ApiResponse> call = api.resendOtp(userEmail); // Adjust the API call according to your server's endpoint

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EmailVerificationActivity.this, "OTP resent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        String errorMessage = "Failed to resend OTP: " + errorBody;
                        Log.d("VerifyOTP", errorMessage);
                        Toast.makeText(EmailVerificationActivity.this, "Failed to resend OTP: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(EmailVerificationActivity.this, "Failed to resend OTP: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyOtp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://traveldiary.oxfordcollege.edu.np/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterService api = retrofit.create(RegisterService.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("user_email");
            String sourceActivity = extras.getString("source_activity");

            if (sourceActivity != null) {
                EditText otpInputEditText = findViewById(R.id.etOTP);
                String otpStr = otpInputEditText.getText().toString();

                if (!otpStr.isEmpty()) {
                    Log.d("VerifyOTP", "User Email: " + userEmail);
                    Log.d("VerifyOTP", "OTP: " + otpStr);

                    if (sourceActivity.equals("signup")|| sourceActivity.equals("login")) {
                        Call<ApiResponse> call = api.verifyOtp(userEmail, Integer.parseInt(otpStr));
                        call.enqueue(new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(EmailVerificationActivity.this, "OTP code verified Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EmailVerificationActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    try {
                                        String errorBody = response.errorBody().string();
                                        String errorMessage = "OTP verification failed: " + errorBody;
                                        Log.d("VerifyOTP", errorMessage);
                                        Toast.makeText(EmailVerificationActivity.this, "OTP verification failed: " + errorBody, Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(EmailVerificationActivity.this, "OTP verification failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (sourceActivity.equals("forgot_password")) {
                        Call<ApiResponse> call = api.verifyOtp(userEmail, Integer.parseInt(otpStr));
                        call.enqueue(new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(EmailVerificationActivity.this, "OTP code verified Successfully", Toast.LENGTH_SHORT).show();

                                    // Make the "forget password" API call
                                    Call<ApiResponse> forgetPasswordCall = api.forgetPassword(userEmail);
                                    forgetPasswordCall.enqueue(new Callback<ApiResponse>() {
                                        @Override
                                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(EmailVerificationActivity.this, "Password reset request sent", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(EmailVerificationActivity.this,ResetPassActivity.class);
                                                intent.putExtra("user_email", userEmail); // Pass the user's email if needed
                                                startActivity(intent);

                                            } else {
                                                Toast.makeText(EmailVerificationActivity.this, "Failed to initiate password reset.", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                                            t.printStackTrace();
                                            Toast.makeText(EmailVerificationActivity.this, "Failed to initiate password reset: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    try {
                                        String errorBody = response.errorBody().string();
                                        String errorMessage = "OTP verification failed: " + errorBody;
                                        Log.d("VerifyOTP", errorMessage);
                                        Toast.makeText(EmailVerificationActivity.this, "OTP verification failed: " + errorBody, Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(EmailVerificationActivity.this, "OTP verification failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(EmailVerificationActivity.this, "OTP field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}