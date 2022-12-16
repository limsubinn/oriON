package com.example.orion.model.board;

import com.google.gson.annotations.SerializedName;

public class DeletePostData {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    public DeletePostData(int id, String username) {
        this.id = id;
        this.username = username;
    }
}
