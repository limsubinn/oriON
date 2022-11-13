package com.example.dinostudy.model.todo;

import com.google.gson.annotations.SerializedName;

public class AddTodoData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("content")
    private String content;

    @SerializedName("n")
    private int n;



    public AddTodoData(String username, String date, String content, int n) {
        this.username = username;
        this.date = date;
        this.content = content;
        this.n = n;
    }
}