package com.example.orion.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("userEmail")
    private String userEmail;

    public LoginData(String userEmail) {
        this.userEmail = userEmail;
    }
}