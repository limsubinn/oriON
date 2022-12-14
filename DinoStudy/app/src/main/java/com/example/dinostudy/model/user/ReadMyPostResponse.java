package com.example.dinostudy.model.user;

import com.example.dinostudy.model.board.Post;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadMyPostResponse {

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
