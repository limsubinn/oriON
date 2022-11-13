package com.example.dinostudy.model.watch;

import com.google.gson.annotations.SerializedName;

public class AddWatchData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("subject")
    private String subject;

    @SerializedName("time")
    private String time;

    @SerializedName("n")
    private int n;

    public AddWatchData(String username, String date, String subject, String time, int n) {
        this.username = username;
        this.date = date;
        this.subject = subject;
        this.time = time;
        this.n = n;
    }
}