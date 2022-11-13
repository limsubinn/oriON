package com.example.dinostudy.model.diary;

import com.google.gson.annotations.SerializedName;

public class ReadDiaryResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("good")
    private String good;

    @SerializedName("bad")
    private String bad;

    public int getCode() {
        return code;
    }

    public String getGood() {
        return good;
    }

    public String getBad() {
        return bad;
    }
}
