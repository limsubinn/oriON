package com.example.dinostudy.model;

import com.google.gson.annotations.SerializedName;

public class CheckEmailData {
    @SerializedName("userEmail")
    private String userEmail;

    public CheckEmailData(String userEmail) {
        this.userEmail = userEmail;
    }
}