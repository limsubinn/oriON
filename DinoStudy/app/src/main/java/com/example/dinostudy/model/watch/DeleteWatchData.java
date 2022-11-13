package com.example.dinostudy.model.watch;

import com.google.gson.annotations.SerializedName;

public class DeleteWatchData {
    @SerializedName("username")
    private String username;

    @SerializedName("date")
    private String date;

    @SerializedName("subject1")
    private String subject1;

    @SerializedName("time1")
    private String time1;

    @SerializedName("subject2")
    private String subject2;

    @SerializedName("time2")
    private String time2;

    @SerializedName("subject3")
    private String subject3;

    @SerializedName("time3")
    private String time3;

    @SerializedName("subject4")
    private String subject4;

    @SerializedName("time4")
    private String time4;

    @SerializedName("subject5")
    private String subject5;

    @SerializedName("time5")
    private String time5;

    @SerializedName("subject6")
    private String subject6;

    @SerializedName("time6")
    private String time6;

    @SerializedName("subject7")
    private String subject7;

    @SerializedName("time7")
    private String time7;

    @SerializedName("subject8")
    private String subject8;

    @SerializedName("time8")
    private String time8;

    @SerializedName("subject9")
    private String subject9;

    @SerializedName("time9")
    private String time9;

    @SerializedName("subject10")
    private String subject10;

    @SerializedName("time10")
    private String time10;

    public DeleteWatchData(String username, String date, String subject1, String time1,
                           String subject2, String time2, String subject3, String time3,
                           String subject4, String time4, String subject5, String time5,
                           String subject6, String time6, String subject7, String time7,
                           String subject8, String time8, String subject9, String time9,
                           String subject10, String time10) {
        this.username = username;
        this.date = date;

        this.subject1 = subject1;
        this.time1 = time1;

        this.subject2 = subject2;
        this.time2 = time2;

        this.subject3 = subject3;
        this.time3 = time3;

        this.subject4 = subject4;
        this.time4 = time4;

        this.subject5 = subject5;
        this.time5 = time5;

        this.subject6 = subject6;
        this.time6 = time6;

        this.subject7 = subject7;
        this.time7 = time7;

        this.subject8 = subject8;
        this.time8 = time8;

        this.subject9 = subject9;
        this.time9 = time9;

        this.subject10 = subject10;
        this.time10 = time10;
    }
}