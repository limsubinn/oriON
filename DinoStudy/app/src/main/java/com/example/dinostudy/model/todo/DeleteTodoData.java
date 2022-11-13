package com.example.dinostudy.model.todo;

import com.google.gson.annotations.SerializedName;

public class DeleteTodoData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("content1")
    private String content1;

    @SerializedName("content2")
    private String content2;

    @SerializedName("content3")
    private String content3;

    @SerializedName("content4")
    private String content4;

    @SerializedName("content5")
    private String content5;

    @SerializedName("content6")
    private String content6;

    @SerializedName("content7")
    private String content7;

    @SerializedName("content8")
    private String content8;

    @SerializedName("content9")
    private String content9;

    @SerializedName("content10")
    private String content10;

    public DeleteTodoData(String username, String date, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10) {
        this.username = username;
        this.date = date;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
        this.content5 = content5;
        this.content6 = content6;
        this.content7 = content7;
        this.content8 = content8;
        this.content9 = content9;
        this.content10 = content10;
    }
}