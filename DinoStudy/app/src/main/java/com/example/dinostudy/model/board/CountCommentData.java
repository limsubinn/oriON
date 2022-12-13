package com.example.dinostudy.model.board;

import com.google.gson.annotations.SerializedName;

public class CountCommentData {

    @SerializedName("post_id")
    private int post_id;

    @SerializedName("n")
    private int n;

    public CountCommentData(int post_id, int n) {
        this.post_id = post_id;
        this.n = n;
    }
}