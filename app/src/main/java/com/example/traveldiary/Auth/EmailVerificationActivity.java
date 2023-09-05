package com.example.traveldiary.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveldiary.Model.LoginResponse;
import com.example.traveldiary.Model.OtpVerificationResponse;
import com.example.traveldiary.R;
import com.example.traveldiary.SearchActivity;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailVerificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        EditText etOTP = findViewById(R.id.etOTP);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterService registerService = APIUtil.getUserservices();

                Call<OtpVerificationResponse> otpVerificationResponseCall = registerService.verify(etOTP.getText().toString());

                otpVerificationResponseCall.enqueue(new Callback<OtpVerificationResponse>() {
                    @Override
                    public void onResponse(Call<OtpVerificationResponse> call, Response<OtpVerificationResponse> response) {
                        if (response.code() == 200) {
                            OtpVerificationResponse otpVerificationResponse = response.body();
                            String message = otpVerificationResponse.getMessage();
//                            Boolean status = otpVerificationResponse.getStatus();


                            Toast.makeText(EmailVerificationActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(EmailVerificationActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();


                        } else if (response.code() == 401) {
                            Toast.makeText(EmailVerificationActivity.this, "Otp doesn't match", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EmailVerificationActivity.this, "Error on Server", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpVerificationResponse> call, Throwable t) {
                        Toast.makeText(EmailVerificationActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

    }
}
