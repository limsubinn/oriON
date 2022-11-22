package com.example.dinostudy.model.user;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("email")
    private String email;

    @SerializedName("coin")
    private int coin;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birth")
    private String birth;

    public JoinData(String nickname, String email, int coin, String gender, String birth) {
        this.nickname = nickname;
        this.email = email;
        this.coin = coin;
        this.gender = gender;
        this.birth = birth;
    }
}