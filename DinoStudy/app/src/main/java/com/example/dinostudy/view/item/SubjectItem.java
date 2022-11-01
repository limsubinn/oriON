package com.example.dinostudy.view.item;

//item_list_subject에 들어갈 데이터

public class SubjectItem {

    private String tv_subject, tv_subject_time;

    public SubjectItem(String tv_subject, String tv_subject_time) {
        this.tv_subject = tv_subject;
        this.tv_subject_time = tv_subject_time;
    }

    public String getTv_subject() {
        return tv_subject;
    }

    public void setTv_subject(String tv_subject) {
        this.tv_subject = tv_subject;
    }

    public String getTv_subject_time() {
        return tv_subject_time;
    }

    public void setTv_subject_time(String tv_subject_time) {
        this.tv_subject_time = tv_subject_time;
    }
}
