package com.example.traveldiary.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiaryResponse {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<Diary> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Diary> getData() {
        return data;
    }


    public DiaryResponse(List<Diary> data) {
        super();
        this.data = data;
    }


    public void setData(List<Diary> data) {
        this.data = data;
    }

}
