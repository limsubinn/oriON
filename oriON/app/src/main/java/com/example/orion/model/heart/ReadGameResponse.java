package com.example.orion.model.heart;

import com.google.gson.annotations.SerializedName;

public class ReadGameResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("username")
    private String username;

    @SerializedName("gamedata")
    private String gamedata;

    public int getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public String getGamedata() {
        return gamedata;
    }
}
