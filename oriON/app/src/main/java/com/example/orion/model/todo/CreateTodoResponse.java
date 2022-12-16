package com.example.orion.model.todo;

import com.google.gson.annotations.SerializedName;

public class CreateTodoResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}