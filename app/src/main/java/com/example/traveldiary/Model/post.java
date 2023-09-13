package com.example.traveldiary.Model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

public class post {
    @SerializedName("fullname")
    @Nullable
    private String fullname;

    @SerializedName("email")
    @Nullable
    private String email;


    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

}

