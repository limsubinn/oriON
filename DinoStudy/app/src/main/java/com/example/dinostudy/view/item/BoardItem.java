package com.example.dinostudy.view.item;

public class BoardItem {
    private String username;
    private String title;
    private String date;
    private String content;
    private int comment;

    public BoardItem() {
    }

    public BoardItem(String username, String title, String date, String content, int comment) {
        this.username = username;
        this.title = title;
        this.date = date;
        this.content = content;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Data_Board{" +
                "username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
