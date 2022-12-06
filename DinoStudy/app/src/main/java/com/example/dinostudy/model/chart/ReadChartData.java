package com.example.dinostudy.model.chart;

import com.google.gson.annotations.SerializedName;

public class ReadChartData {
    @SerializedName("username")
    private String username;

    @SerializedName("curdate")
    private String curdate;

    public ReadChartData(String username, String curdate) {
        this.username = username;
        this.curdate = curdate;
    }
}