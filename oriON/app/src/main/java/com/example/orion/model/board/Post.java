package com.example.orion.model.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
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
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("n")
    private int n;

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getCurdate() {
        return curdate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getN() {
        return n;
    }
}
