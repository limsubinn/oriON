package com.example.orion.model.heart;

import com.google.gson.annotations.SerializedName;

public class ReadGameResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("username")
    private String username;

    @SerializedName("coin")
    private int coin;

    @SerializedName("gamedata")
    private String gamedata;

    @SerializedName("email")
    private String email;

    public int getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public int getCoin() { return coin; }

    public String getGamedata() {
        return gamedata;
    }

    public String getEmail() {
        return email;
    }
}
