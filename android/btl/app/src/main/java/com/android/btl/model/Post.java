package com.android.btl.model;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String username, title, image, category, content, date,video;

    public Post(String username, String title, String image,String video, String category, String content, String date) {
        this.username = username;
        this.title = title;
        this.image = image;
        this.category = category;
        this.content = content;
        this.date = date;
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Post(int id, String username, String title, String image,String video, String category, String content, String date) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.image = image;
        this.category = category;
        this.content = content;
        this.date = date;
        this.video = video;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
