package com.example.orion.model.heart;

import com.google.gson.annotations.SerializedName;

public class ReadGameData {
    @SerializedName("username")
    private String username;

    public ReadGameData(String username) {
        this.username = username;
    }
}