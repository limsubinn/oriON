package com.example.dinostudy.model.mission;

import com.google.gson.annotations.SerializedName;

public class ReadMissionResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("mission1")
    private int mission1;

    @SerializedName("mission2")
    private int mission2;

    @SerializedName("mission3")
    private int mission3;

    public int getCode() {
        return code;
    }

    public int getMission1() {
        return mission1;
    }

    public int getMission2() {
        return mission2;
    }

    public int getMission3() {
        return mission3;
    }
}
