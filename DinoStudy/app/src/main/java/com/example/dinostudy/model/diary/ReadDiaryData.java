package com.example.dinostudy.model.diary;

import com.google.gson.annotations.SerializedName;

public class ReadDiaryData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public ReadDiaryData(String username, String date) {
        this.username = username;
        this.date = date;
    }
}