package com.example.orion.model.user;

import com.google.gson.annotations.SerializedName;

public class ReadMyPostData {

    @SerializedName("username")
    private String username;

    public ReadMyPostData(String username) {
        this.username = username;
    }
}
