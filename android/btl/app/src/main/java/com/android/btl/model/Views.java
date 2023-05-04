package com.android.btl.model;

import java.io.Serializable;

public class Views implements Serializable {
    private String username;
    private int id_post;

    public Views(int id, String username, int id_post) {
        this.username = username;
        this.id_post = id_post;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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

    public Views(String username, int id_post) {
        this.username = username;
        this.id_post = id_post;
    }
}
