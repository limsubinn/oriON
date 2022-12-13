package com.example.dinostudy.model.board;

import com.example.dinostudy.model.chart.UserTimer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadCommentResponse {
    @Expose
    @SerializedName("result")
    private List<Comment> result;

    @Expose
    @SerializedName("code")
    private int code;

    public List<Comment> getResult() {
        return result;
    }

    public int getCode() { return code; }

}
