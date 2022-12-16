package com.example.orion.model.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("writer")
    private String writer;

    @Expose
    @SerializedName("curdate")
    private String curdate;

    @Expose
    @SerializedName("post_id")
    private String post_id;

    @Expose
    @SerializedName("content")
    private String content;

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getCurdate() {
        return curdate;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getContent() {
        return content;
    }
}
