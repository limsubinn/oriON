package com.example.dinostudy.model.todo;

import com.google.gson.annotations.SerializedName;

public class ReadTodoData {

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;


    public ReadTodoData(String username, String date) {
        this.username = username;
        this.date = date;

    }
}