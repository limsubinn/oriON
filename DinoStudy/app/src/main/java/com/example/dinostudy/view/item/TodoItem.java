package com.example.dinostudy.view.item;
public class TodoItem {

    private Boolean cb_todo;
    private String tv_todo;

    //Alt + Insert = Constructor
//    public Data_Todo(CheckBox cb_todo, String tv_todo) {
//        this.cb_todo = cb_todo;
//        this.tv_todo = tv_todo;
//    }

    public TodoItem(Boolean cb_todo, String tv_todo) {
        this.cb_todo = cb_todo;
        this.tv_todo = tv_todo;
    }


    //Alt + Insert = Getter and Setter

    public Boolean getCb_todo() {
        return cb_todo;
    }

    public void setCb_todo(Boolean cb_todo) {
        this.cb_todo = cb_todo;
    }

    public String getTv_todo() {
        return tv_todo;
    }

    public void setTv_todo(String tv_todo) {
        this.tv_todo = tv_todo;
    }

//    public CheckBox getCb_todo() {
//        return cb_todo;
//    }
//
//    public void setCb_todo(CheckBox cb_todo) {
//        this.cb_todo = cb_todo;
//    }
//
//    public String getTv_todo() {
//        return tv_todo;
//    }
//
//    public void setTv_todo(String tv_todo) {
//        this.tv_todo = tv_todo;
//    }
}