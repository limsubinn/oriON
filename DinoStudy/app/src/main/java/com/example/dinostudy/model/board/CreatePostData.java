package com.example.dinostudy.model.board;

import com.google.gson.annotations.SerializedName;

public class CreatePostData {
    @SerializedName("username")
    private String username;

    @SerializedName("curdate")
    private String curdate;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public CreatePostData(String username, String curdate, String title, String content) {
        this.username = username;
        this.curdate = curdate;
        this.title = title;
        this.content = content;
    }
}