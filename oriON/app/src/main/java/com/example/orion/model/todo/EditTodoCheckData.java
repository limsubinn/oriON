package com.example.orion.model.todo;

import com.google.gson.annotations.SerializedName;

public class EditTodoCheckData {

    @SerializedName("position")
    private int n;

    @SerializedName("check")
    private int check;

    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    public EditTodoCheckData(int n, int check, String username, String date) {
        this.n = n;
        this.check = check;
        this.username = username;
        this.date = date;
    }
}