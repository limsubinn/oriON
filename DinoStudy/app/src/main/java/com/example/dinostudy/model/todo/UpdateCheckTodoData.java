package com.example.dinostudy.model.todo;

import com.google.gson.annotations.SerializedName;

public class UpdateCheckTodoData {

    @SerializedName("position")
    private int n;

    @SerializedName("check")
    private int check;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public UpdateCheckTodoData(int n, int check, String username, String date) {
        this.n = n;
        this.check = check;
        this.username = username;
        this.date = date;
    }
}