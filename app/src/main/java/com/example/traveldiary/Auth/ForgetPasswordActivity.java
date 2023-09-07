package com.example.traveldiary.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveldiary.Model.ApiResponse;
import com.example.traveldiary.Model.ForgetPassword;
import com.example.traveldiary.R;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetPasswordActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_password);
//
//        EditText etEmail = findViewById(R.id.etEmail);
//        Button btnSubmit = findViewById(R.id.btnSubmit);
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create an instance of the Retrofit service
//                RegisterService registerService = APIUtil.getUserservices();
//
//                // Make a Retrofit API call to initiate the password reset
//                Call<ApiResponse> forgetPasswordCall = registerService.forgetPassword(etEmail.getText().toString());
//                forgetPasswordCall.enqueue(new Callback<ApiResponse>() {
//
//                    @Override
//                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                        if (response.code() == 200) {
//                            ApiResponse forgetPassword = response.body();
//                            if (forgetPassword != null) {
//                                if (forgetPassword.isSuccess()) {
//                                    // Password reset initiated successfully
//                                    String message = forgetPassword.getMessage();
//                                    // You can display the message or handle it as needed
//                                    Toast.makeText(ForgetPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
//
//                                    // Proceed to the verification activity or perform other actions
//                                    Intent i = new Intent(ForgetPasswordActivity.this, EmailVerificationActivity.class);
//                                    startActivity(i);
//                                } else {
//                                    // Password reset failed
//                                    String message = forgetPassword.getMessage();
//                                    Toast.makeText(ForgetPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                // Handle the case where the response body is null
//                                Toast.makeText(ForgetPasswordActivity.this, "Invalid response", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            // Handle other response codes here
//                            Toast.makeText(ForgetPasswordActivity.this, "Password reset failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//
//                    @Override
//                    public void onFailure(Call<ForgetPassword> call, Throwable t) {
//                        // Handle network or API call failure here
//                        Toast.makeText(ForgetPasswordActivity.this, "Password reset request failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }






    Button button;
    RegisterService apiService;
    EditText emailEditText; // Declare the EditText variable for email input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://traveldiary.oxfordcollege.edu.np/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(RegisterService.class);



        button = findViewById(R.id.btnSubmit);
        emailEditText = findViewById(R.id.etEmail); // Initialize the EditText view

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the email entered by the user
                String userEmail = emailEditText.getText().toString().trim();

                // Check if the email is empty or invalid (you can add more validation here)

                if (userEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    Toast.makeText(ForgetPasswordActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Make the API call to initiate the "forget password" process
                Call<ApiResponse> call = apiService.forgetPassword(userEmail);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            ApiResponse apiResponse = response.body();
                            // Handle the response, which may contain a success message or error message
                            // You can navigate to the OTP verification screen if needed
                            Intent intent = new Intent(ForgetPasswordActivity.this, EmailVerificationActivity.class);
                            intent.putExtra("user_email", userEmail);
                            intent.putExtra("source_activity", "forgot_password");
                            startActivity(intent);
                        } else {
                            // Handle the error response here
                            Toast.makeText(ForgetPasswordActivity.this, "Failed to initiate password reset.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // Handle network or other errors here
                        Toast.makeText(ForgetPasswordActivity.this, "Network error.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
