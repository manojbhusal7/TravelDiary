package com.example.traveldiary.remote;

import com.example.traveldiary.Model.LoginResponse;
import com.example.traveldiary.Model.OtpVerificationResponse;
import com.example.traveldiary.Model.RegisterResponse;
import com.example.traveldiary.Model.RegistrationModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> registerUser(@Query("fullname") String fullname,  @Query("email")String email,@Query("password") String password, @Query("confirm_password") String cpassword);
    @POST("login")
    Call<LoginResponse> validateUser(@Query("email") String email, @Query("password") String password);

    @POST("verify")
    Call<OtpVerificationResponse> verify(@Query("otp") String otp);

}
