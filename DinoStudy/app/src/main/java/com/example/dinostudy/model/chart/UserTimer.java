package com.example.dinostudy.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTimer {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("curdate")
    private String curdate;

    @Expose
    @SerializedName("subject1")
    private String subject1;

    @Expose
    @SerializedName("time1")
    private String time1;

    @Expose
    @SerializedName("subject2")
    private String subject2;

    @Expose
    @SerializedName("time2")
    private String time2;

    @Expose
    @SerializedName("subject3")
    private String subject3;

    @Expose
    @SerializedName("time3")
    private String time3;

    @Expose
    @SerializedName("subject4")
    private String subject4;

    @Expose
    @SerializedName("time4")
    private String time4;

    @Expose
    @SerializedName("subject5")
    private String subject5;

    @Expose
    @SerializedName("time5")
    private String time5;

    @Expose
    @SerializedName("subject6")
    private String subject6;

    @Expose
    @SerializedName("time6")
    private String time6;

    @Expose
    @SerializedName("subject7")
    private String subject7;

    @Expose
    @SerializedName("time7")
    private String time7;

    @Expose
    @SerializedName("subject8")
    private String subject8;

    @Expose
    @SerializedName("time8")
    private String time8;

    @Expose
    @SerializedName("subject9")
    private String subject9;

    @Expose
    @SerializedName("time9")
    private String time9;

    @Expose
    @SerializedName("subject10")
    private String subject10;

    @Expose
    @SerializedName("time10")
    private String time10;

    public String getSubject1() {
        return subject1;
    }

    public String getTime1() {
        return time1;
    }

    public String getSubject2() {
        return subject2;
    }

    public String getTime2() {
        return time2;
    }

    public String getSubject3() {
        return subject3;
    }

    public String getTime3() {
        return time3;
    }

    public String getSubject4() {
        return subject4;
    }

    public String getTime4() {
        return time4;
    }

    public String getSubject5() {
        return subject5;
    }

    public String getTime5() {
        return time5;
    }

    public String getSubject6() {
        return subject6;
    }

    public String getTime6() {
        return time6;
    }

    public String getSubject7() {
        return subject7;
    }

    public String getTime7() {
        return time7;
    }

    public String getSubject8() {
        return subject8;
    }

    public String getTime8() {
        return time8;
    }

    public String getSubject9() {
        return subject9;
    }

    public String getTime9() {
        return time9;
    }

    public String getSubject10() {
        return subject10;
    }

    public String getTime10() {
        return time10;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCurdate() {
        return curdate;
    }

    public String getTime() {
        int hour1 = 0, min1 = 0, sec1 = 0;
        int hour2 = 0, min2 = 0, sec2 = 0;
        int hour3 = 0, min3 = 0, sec3 = 0;
        int hour4 = 0, min4 = 0, sec4 = 0;
        int hour5 = 0, min5 = 0, sec5 = 0;
        int hour6 = 0, min6 = 0, sec6 = 0;
        int hour7 = 0, min7 = 0, sec7 = 0;
        int hour8 = 0, min8 = 0, sec8 = 0;
        int hour9 = 0, min9 = 0, sec9 = 0;
        int hour10 = 0, min10 = 0, sec10 = 0;


        if (time1 != null) {
            hour1 = Integer.parseInt(time1.substring(0, 2));
            min1 = Integer.parseInt(time1.substring(3, 5));
            sec1 = Integer.parseInt(time1.substring(6, 8));
        }

        if (!time2.equals("")) {
            hour2 = Integer.parseInt(time2.substring(0, 2));
            min2 = Integer.parseInt(time2.substring(3, 5));
            sec2 = Integer.parseInt(time2.substring(6, 8));
        }
        if (!time3.equals("")) {
            hour3 = Integer.parseInt(time3.substring(0, 2));
            min3 = Integer.parseInt(time3.substring(3, 5));
            sec3 = Integer.parseInt(time3.substring(6, 8));
        }

        if (!time4.equals("")) {
            hour4 = Integer.parseInt(time4.substring(0, 2));
            min4 = Integer.parseInt(time4.substring(3, 5));
            sec4 = Integer.parseInt(time4.substring(6, 8));
        }

        if (!time5.equals("")) {
            hour5 = Integer.parseInt(time5.substring(0, 2));
            min5 = Integer.parseInt(time5.substring(3, 5));
            sec5 = Integer.parseInt(time5.substring(6, 8));
        }

        if (!time6.equals("")) {
            hour6 = Integer.parseInt(time6.substring(0, 2));
            min6 = Integer.parseInt(time6.substring(3, 5));
            sec6 = Integer.parseInt(time6.substring(6, 8));
        }

        if (!time7.equals("")) {
            hour7 = Integer.parseInt(time7.substring(0, 2));
            min7 = Integer.parseInt(time7.substring(3, 5));
            sec7 = Integer.parseInt(time7.substring(6, 8));
        }

        if (!time8.equals("")) {
            hour8 = Integer.parseInt(time8.substring(0, 2));
            min8 = Integer.parseInt(time8.substring(3, 5));
            sec8 = Integer.parseInt(time8.substring(6, 8));
        }

        if (!time9.equals("")) {
            hour9 = Integer.parseInt(time9.substring(0, 2));
            min9 = Integer.parseInt(time9.substring(3, 5));
            sec9 = Integer.parseInt(time9.substring(6, 8));
        }

        if (!time10.equals("")) {
            hour10 = Integer.parseInt(time10.substring(0, 2));
            min10 = Integer.parseInt(time10.substring(3, 5));
            sec10 = Integer.parseInt(time10.substring(6, 8));
        }

        int hour = hour1 + hour2 + hour3 + hour4 + hour5 + hour6 + hour7 + hour8 + hour9 + hour10;
        int min = min1 + min2 + min3 + min4 + min5 + min6 + min7 + min8 + min9 + min10;
        int sec = sec1 + sec2 + sec3 + sec4 + sec5 + sec6 + sec7 + sec8 + sec9 + sec10;

        min += sec / 60;
        sec = sec % 60;
        hour += min / 60;
        min = min % 60;

        String time = String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
        return time;
    }
}
