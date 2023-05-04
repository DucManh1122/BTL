package com.android.btl.model;

import java.io.Serializable;

public class RViewPostItem implements Serializable {
    private int id;

    public RViewPostItem() {
    }

    private String image,title,content,date,user,views,category,video;
    private float rtbar;

    public RViewPostItem(Post post,float rt,int luotxem){
        this.image = post.getImage();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.date = post.getDate();
        this.user = post.getUsername();
        this.category = post.getCategory();
        this.rtbar = rt;
        this.views = String.valueOf(luotxem);
        this.video = post.getVideo();
        this.id = post.getId();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RViewPostItem(String image, String title, String content, String date, String user, String view, String category, float rtbar) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.date = date;
        this.user = user;
        this.views = view;
        this.category = category;
        this.rtbar = rtbar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String view) {
        this.views = view;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRtbar() {
        return rtbar;
    }

    public void setRtbar(float rtbar) {
        this.rtbar = rtbar;
    }
}
