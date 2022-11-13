package com.example.dinostudy.model.todo;

import com.google.gson.annotations.SerializedName;

public class EditTodoData {
    @SerializedName("position")
    private int position;

    @SerializedName("content")
    private String content;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public EditTodoData(int position, String content, String username, String date) {
        this.position = position;
        this.content = content;
        this.username = username;
        this.date = date;
    }
}