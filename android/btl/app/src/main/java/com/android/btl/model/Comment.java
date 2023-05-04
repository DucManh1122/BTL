package com.android.btl.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comment(int id, String username, String content, String date, int id_post) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.date = date;
        this.id_post = id_post;
    }

    private String username, content, date;
    private int id_post;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public Comment(String username, String content, String date, int id_post) {
        this.username = username;
        this.content = content;
        this.date = date;
        this.id_post = id_post;
    }
}
