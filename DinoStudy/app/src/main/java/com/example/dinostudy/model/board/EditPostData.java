package com.example.dinostudy.model.board;

import com.google.gson.annotations.SerializedName;

public class EditPostData {

    @SerializedName("id")
    private int id;

    @SerializedName("after_title")
    private String after_title;

    @SerializedName("after_content")
    private String after_content;

    public EditPostData(int id, String after_title, String after_content) {
        this.id = id;
        this.after_title = after_title;
        this.after_content = after_content;
    }
}
