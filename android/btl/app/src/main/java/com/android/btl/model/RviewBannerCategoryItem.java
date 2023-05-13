package com.android.btl.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class RviewBannerCategoryItem implements Serializable {

    public RviewBannerCategoryItem() {
    }

    private String title;
    private int image,bg;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public RviewBannerCategoryItem(String title, int image, int bg) {
        this.title = title;
        this.image = image;
        this.bg = bg;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }
}
