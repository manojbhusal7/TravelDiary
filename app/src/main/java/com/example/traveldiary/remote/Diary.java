package com.example.traveldiary.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Diary {

    @SerializedName("diary_id")
    @Expose
    private Integer diaryId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user")
    @Expose
    private User user;

    // Getter for user
    public User getUser() {
        return user;
    }




    public Diary(Integer diary_id, String title, String description, String images, Integer user_id, String location,String privacy,String start_date, String end_date) {
        super();
        this.diaryId = diary_id;
        this.title = title;
        this.description = description;
        this.images = images;
        this.userId = user_id;
        this.location = location;
        this.privacy = privacy;
        this.startDate = start_date;
        this.endDate = end_date;

    }

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }





//    public String getProfile() {
//        return user.getPhoto();
//    }
//
//    public void setPhoto(String photo) {
//        if (user == null) {
//            user = new User();
//        }
//        user.setPhoto(photo);
//    }
}
