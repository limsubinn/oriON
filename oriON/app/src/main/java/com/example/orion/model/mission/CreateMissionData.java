package com.example.orion.model.mission;

import com.google.gson.annotations.SerializedName;

public class CreateMissionData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("mission1")
    private boolean mission1;

    @SerializedName("mission2")
    private boolean mission2;

    @SerializedName("mission3")
    private boolean mission3;

    public CreateMissionData(String username, String date, boolean mission1, boolean mission2, boolean mission3) {
        this.username = username;
        this.date = date;
        this.mission1 = mission1;
        this.mission2 = mission2;
        this.mission3 = mission3;
    }
}