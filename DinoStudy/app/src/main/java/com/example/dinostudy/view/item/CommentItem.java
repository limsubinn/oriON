package com.example.dinostudy.view.item;

public class CommentItem {

    private String nickname;
    private String comment_date;
    private String comment_content;


    public CommentItem(String nickname, String comment_date, String comment_content) {
        this.nickname = nickname;
        this.comment_date = comment_date;
        this.comment_content = comment_content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
