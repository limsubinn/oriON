package com.example.orion.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadChartResponse {
    @Expose
    @SerializedName("result")
    private List<UserTimer> result;

    @Expose
    @SerializedName("curdate")
    private String curdate;

    public List<UserTimer> getResult() {
        return result;
    }

    public String getCurdate() {
        return curdate;
    }

}
