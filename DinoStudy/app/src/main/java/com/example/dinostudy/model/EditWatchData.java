package com.example.dinostudy.model;

import com.google.gson.annotations.SerializedName;

public class EditWatchData {

    @SerializedName("position")
    private int position;

    @SerializedName("subject")
    private String subject;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public EditWatchData(int position, String subject, String username, String date) {
        this.position = position;
        this.subject = subject;
        this.username = username;
        this.date = date;
    }
}