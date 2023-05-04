package com.android.btl.model;

import java.io.Serializable;

public class Rating implements Serializable{
    private  String username;
    private int id;

    public Rating(int id, String username, int id_post, float rating) {
        this.username = username;
        this.id = id;
        this.id_post = id_post;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id_post;
    private float rating;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Rating(String username, int id_post, float rating) {
        this.username = username;
        this.id_post = id_post;
        this.rating = rating;
        this.id = 0;
    }
}
