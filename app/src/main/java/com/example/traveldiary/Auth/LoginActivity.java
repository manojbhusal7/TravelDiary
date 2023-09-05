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

import com.example.traveldiary.HomeActivity;
import com.example.traveldiary.Model.LoginResponse;
import com.example.traveldiary.R;
import com.example.traveldiary.SearchActivity;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        SharedPreferences shp = getSharedPreferences("LOGIN",MODE_PRIVATE);
//        Boolean isLoggedIn = shp.getBoolean("LOGIN",false);

        //check if user is logged in or not
        // if true send to home screen so that everytime user will
        // not be asked for login
//        if (isLoggedIn){
//            Intent i = new Intent(LoginActivity.this, SearchActivity.class);
//            startActivity(i);
//            finish();
//        }


        // 2 x 3


        Button btnLogin = findViewById(R.id.btnlogin);
        EditText etEmail = findViewById(R.id.etUser);
        EditText etPassword = findViewById(R.id.etPass);

        TextView tvRegisternow =findViewById(R.id.tvRegisternow);
        tvRegisternow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            RegisterService registerService = APIUtil.getUserservices();

                                            Call<LoginResponse> loginResponseCall =registerService.validateUser(etEmail.getText().toString(),etPassword.getText().toString());
                                            loginResponseCall.enqueue(new Callback<LoginResponse>(){

                                                @Override
                                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                                                    if(response.code() == 200) {
                                                        Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                                        LoginResponse loginResponse = response.body();
                                                        String token = loginResponse.getAccessToken();

                                                        //save token if you need token in future api call
                                                        SharedPreferences shp = getSharedPreferences("USER",MODE_PRIVATE);
                                                        shp.edit().putString("TOKEN",token).apply();

                                                        //save login true if you want to save that user have logged in
                                                        // and don't want user to go on login screen everytime user open
                                                        // app
                                                        shp.edit().putBoolean("LOGIN",true).apply();

                                                        Intent i = new Intent(LoginActivity.this,SearchActivity.class);
                                                        startActivity(i);
                                                        finish();


                                                    }else if (response.code() == 401){
                                                        Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(LoginActivity.this,"Error on Server",Toast.LENGTH_SHORT).show();
                                                    }


                                                }

                                                @Override
                                                public void onFailure(Call<LoginResponse> call, Throwable t) {
                                                    Toast.makeText(LoginActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                        }

                                    });



        TextView forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(i);
            }
        });

    }}