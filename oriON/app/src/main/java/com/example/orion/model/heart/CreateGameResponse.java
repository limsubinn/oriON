package com.example.orion.model.heart;

import com.google.gson.annotations.SerializedName;

public class CreateGameResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
