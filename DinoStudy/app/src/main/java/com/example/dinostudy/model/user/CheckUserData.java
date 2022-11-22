package com.example.dinostudy.model.user;

import com.google.gson.annotations.SerializedName;

public class CheckUserData {

    @SerializedName("nickname")
    private String nickname;

    public CheckUserData(String nickname) {
        this.nickname = nickname;
    }
}