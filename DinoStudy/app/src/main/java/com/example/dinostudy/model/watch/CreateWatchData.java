package com.example.dinostudy.model.watch;

import com.google.gson.annotations.SerializedName;

public class CreateWatchData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("subject")
    private String subject;

    @SerializedName("time")
    private String time;

    public CreateWatchData(String username, String date, String subject, String time) {
        this.username = username;
        this.date = date;
        this.subject = subject;
        this.time = time;
    }
}