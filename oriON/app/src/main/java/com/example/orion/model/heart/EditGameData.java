package com.example.orion.model.heart;

import com.google.gson.annotations.SerializedName;

public class EditGameData {
    @SerializedName("username")
    private String username;

    @SerializedName("gamedata")
    private String gamedata;

    public EditGameData(String username, String gamedata) {
        this.username = username;
        this.gamedata = gamedata;
    }
}