
package com.example.traveldiary.Model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegistrationModel {

    @SerializedName("fullname")
    @Expose
    private List<String> fullname;
    @SerializedName("email")
    @Expose
    private List<String> email;
    @SerializedName("password")
    @Expose
    private List<String> password;
    @SerializedName("confirm_password")
    @Expose
    private List<String> confirmPassword;

    public List<String> getFullname() {
        return fullname;
    }

    public void setFullname(List<String> fullname) {
        this.fullname = fullname;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(List<String> confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
