package com.example.traveldiary.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpVerificationResponse {





        @SerializedName("message")
        @Expose
        private String message;





        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
