package com.example.dinostudy.model.board;

import com.google.gson.annotations.SerializedName;

public class EditCommentData {
    @SerializedName("commentId")
    private int commentId;

    @SerializedName("content")
    private String content;

    public EditCommentData(int commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}