package com.example.traveldiary.Model;

public class LocationCategoryModel {
    int image;
    String placeName;

    public LocationCategoryModel(int image, String placeName){
        this.image = image;
        this.placeName= placeName;
    }

    public int getImage() {
        return image;
    }

    public String getPlaceName() {
        return placeName;
    }
}
