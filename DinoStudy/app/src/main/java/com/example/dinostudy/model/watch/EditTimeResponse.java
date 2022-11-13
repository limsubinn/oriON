package com.example.dinostudy.model.watch;

import com.google.gson.annotations.SerializedName;

public class EditTimeResponse {
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
