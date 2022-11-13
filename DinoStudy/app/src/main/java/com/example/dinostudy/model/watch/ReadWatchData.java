package com.example.dinostudy.model.watch;

import com.google.gson.annotations.SerializedName;

public class ReadWatchData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public ReadWatchData(String username, String date) {
        this.username = username;
        this.date = date;
    }
}