package com.example.orion.model.board;

import com.google.gson.annotations.SerializedName;

public class EditPostResponse {

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
