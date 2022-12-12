package com.example.dinostudy.model.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadPostResponse {
    @Expose
    @SerializedName("result")
    private List<Post> result;

    @SerializedName("code")
    private int code;

    public List<Post> getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }
}
