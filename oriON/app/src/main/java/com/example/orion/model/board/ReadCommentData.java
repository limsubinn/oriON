package com.example.orion.model.board;

import com.google.gson.annotations.SerializedName;

public class ReadCommentData {
    @SerializedName("postId")
    private int postId;

    public ReadCommentData(int postId) {
        this.postId = postId;
    }
}