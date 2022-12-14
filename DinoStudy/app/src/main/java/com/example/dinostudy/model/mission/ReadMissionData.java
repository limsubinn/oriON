package com.example.dinostudy.model.mission;

import com.google.gson.annotations.SerializedName;

public class ReadMissionData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public ReadMissionData(String username, String date) {
        this.username = username;
        this.date = date;
    }
}