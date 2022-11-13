package com.example.dinostudy.model.todo;

import com.google.gson.annotations.SerializedName;

public class CreateTodoData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;


    public CreateTodoData(String username, String date) {
        this.username = username;
        this.date = date;

    }
}