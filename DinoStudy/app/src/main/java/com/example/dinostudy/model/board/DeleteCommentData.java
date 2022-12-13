package com.example.dinostudy.model.board;

import com.google.gson.annotations.SerializedName;

public class DeleteCommentData {
    @SerializedName("commentId")
    private int commentId;

    public DeleteCommentData(int commentId) {
        this.commentId = commentId;
    }
}