package com.example.dinostudy.model.watch;

import com.google.gson.annotations.SerializedName;

public class EditSubjectData {

    @SerializedName("position")
    private int position;

    @SerializedName("subject")
    private String subject;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public EditSubjectData(int position, String subject, String username, String date) {
        this.position = position;
        this.subject = subject;
        this.username = username;
        this.date = date;
    }
}