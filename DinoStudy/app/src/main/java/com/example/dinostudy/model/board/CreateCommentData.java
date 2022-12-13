package com.example.dinostudy.model.board;

import com.google.gson.annotations.SerializedName;

public class CreateCommentData {
    @SerializedName("username")
    private String username;

    @SerializedName("curdate")
    private String curdate;

    @SerializedName("post_id")
    private int post_id;

    @SerializedName("content")
    private String content;

    public CreateCommentData(String username, String curdate, int post_id, String content) {
        this.username = username;
        this.curdate = curdate;
        this.post_id = post_id;
        this.content = content;
    }
}