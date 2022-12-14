package com.example.dinostudy.model.mission;

import com.google.gson.annotations.SerializedName;

public class EditMissionData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("mission")
    private String mission;

    @SerializedName("value")
    private boolean value;

    public EditMissionData(String username, String date, String mission, boolean value) {
        this.username = username;
        this.date = date;
        this.mission = mission;
        this.value = value;
    }
}