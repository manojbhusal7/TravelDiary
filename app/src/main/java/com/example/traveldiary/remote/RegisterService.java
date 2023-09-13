package com.example.traveldiary.remote;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> registerUser(@Query("fullname") String fullname,  @Query("email")String email,@Query("password") String password, @Query("confirm_password") String cpassword);
    @POST("login")
    Call<LoginResponse> validateUser(@Query("email") String email, @Query("password") String password);
    @POST("verify")
    Call<ApiResponse> verifyOtp(@Query("email") String email, @Query("otp") String otp);


    @POST("resend")
    Call<ApiResponse> resend(@Query("email") String email);

    @POST("forgot")
    Call<ApiResponse> forgot(@Query("email") String email);

    @POST("reset")
    Call<ApiResponse> reset(@Query("email") String email,@Query("new_password") String new_password,@Query("new_password_confirmation") String new_password_confirmation);



    @POST("resendOtp")
    Call<ApiResponse> resendOtp(@Query("email") String email);

}
