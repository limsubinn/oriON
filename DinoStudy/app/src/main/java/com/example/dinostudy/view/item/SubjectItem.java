package com.example.dinostudy.view.item;

//item_list_subject에 들어갈 데이터

public class SubjectItem {

    private String subject, time;

    public SubjectItem(String subject, String time) {
        this.subject = subject;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
