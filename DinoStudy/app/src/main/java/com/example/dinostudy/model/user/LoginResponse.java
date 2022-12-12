package com.example.dinostudy.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("coin")
    private int coin;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getCoin() { return coin; }
}