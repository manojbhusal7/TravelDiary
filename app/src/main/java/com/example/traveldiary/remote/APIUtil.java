package com.example.traveldiary.remote;

public class APIUtil {
    public static final String baseURL = "http://192.168.18.6:8000/api/";

//    public static final String baseURL = "http://traveldiary.oxfordcollege.edu.np/api/";
    public static RegisterService getUserservices(){
        return RetrofitClient.getUser(baseURL).create(RegisterService.class);
    }


}
