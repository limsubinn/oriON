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

    @SerializedName("gender")
    private String gender;

    @SerializedName("birth")
    private String birth;

    @SerializedName("username")
    private String username;

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

    public String getGender() { return gender; }

    public String getBirth() { return birth; }

    public String getUsername() {
        return username;
    }
}