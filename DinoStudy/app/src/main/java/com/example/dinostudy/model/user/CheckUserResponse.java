package com.example.dinostudy.model.user;

import com.google.gson.annotations.SerializedName;

public class CheckUserResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() { return code; }

    public String getMessage() { return message; }
}
