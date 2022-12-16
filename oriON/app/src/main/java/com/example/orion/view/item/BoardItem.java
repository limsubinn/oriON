package com.example.orion.view.item;

public class BoardItem {
    private int id;
    private String username;
    private String title;
    private String date;
    private String content;
    private int comment;

    public BoardItem() {
    }

    public BoardItem(int id, String username, String title, String date, String content, int comment) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.date = date;
        this.content = content;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
