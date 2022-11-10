package com.example.dinostudy.model;

import com.google.gson.annotations.SerializedName;

public class EditWatchData {

    @SerializedName("before")
    private String before;

    @SerializedName("after")
    private String after;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public EditWatchData(String before, String after, String username, String date) {
        this.before = before;
        this.after = after;
        this.username = username;
        this.date = date;
    }
}