package com.example.traveldiary.remote;

public class APIUtil {
    public static final String baseURL = "http://traveldiary.oxfordcollege.edu.np/api/";
    public static RegisterService getUserservices(){
        return RetrofitClient.getUser(baseURL).create(RegisterService.class);
    }
}
