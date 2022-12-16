package com.example.orion.model.mission;

import com.google.gson.annotations.SerializedName;

public class CreateMissionResponse {
    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }
}
