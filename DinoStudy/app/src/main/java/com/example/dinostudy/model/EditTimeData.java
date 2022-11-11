package com.example.dinostudy.model;

import com.google.gson.annotations.SerializedName;

public class EditTimeData {

    @SerializedName("position")
    private int position;

    @SerializedName("time")
    private String time;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public EditTimeData(int position, String time, String username, String date) {
        this.position = position;
        this.time = time;
        this.username = username;
        this.date = date;
    }
}