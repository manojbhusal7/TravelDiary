package com.example.traveldiary.remote;

import com.example.traveldiary.Model.ApiResponse;
import com.example.traveldiary.Model.ForgetPassword;
import com.example.traveldiary.Model.LoginResponse;
import com.example.traveldiary.Model.MyResponseModel;
import com.example.traveldiary.Model.OtpVerificationResponse;
import com.example.traveldiary.Model.RegisterResponse;
import com.example.traveldiary.Model.RegistrationModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> registerUser(@Query("fullname") String fullname,  @Query("email")String email,@Query("password") String password, @Query("confirm_password") String cpassword);
    @POST("login")
    Call<LoginResponse> validateUser(@Query("email") String email, @Query("password") String password);



    @FormUrlEncoded
    @POST("verify")
    Call<ApiResponse> verifyOtp(
            @Field("email") String email,
            @Field("otp") int otp);

    @FormUrlEncoded
    @POST("forgot") // Adjust the URL to match your Laravel endpoint
    Call<ApiResponse> forgetPassword(
            @Field("email") String email);
    @FormUrlEncoded
    @POST("resetpassword") // Replace with the actual endpoint URL
    Call<MyResponseModel> resetPassword(
            @Field("email") String email,
            @Field("password") String password);


    @POST("resendOtp")
    Call<ApiResponse> resendOtp(@Query("email") String email);

}
