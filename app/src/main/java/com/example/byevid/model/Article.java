package com.example.byevid.model;

import java.io.Serializable;

public class Article implements Serializable {
    private String title;
    private String duration;
    private String imgUrl;
    private String url;

    public Article(String title, String duration, String imgUrl, String url) {
        this.title = title;
        this.duration = duration;
        this.imgUrl = imgUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
