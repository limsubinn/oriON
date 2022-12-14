package com.example.dinostudy.model.user;

import com.google.gson.annotations.SerializedName;

public class EditCoinData {
    @SerializedName("username")
    private String username;

    @SerializedName("coin")
    private int coin;

    public EditCoinData(String username, int coin) {
        this.username = username;
        this.coin = coin;
    }
}
