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

    @SerializedName("n")
    private int n;

    public CreatePostData(String username, String curdate, String title, String content, int n) {
        this.username = username;
        this.curdate = curdate;
        this.title = title;
        this.content = content;
        this.n = n;
    }
}