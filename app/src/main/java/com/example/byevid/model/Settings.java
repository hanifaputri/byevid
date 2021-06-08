package com.example.byevid.model;

import android.graphics.drawable.Drawable;

public class Settings {
    private String title;
    private Drawable icon;
    private Class<?> to;

    public Settings() {

    }

    public Settings(String title, Drawable icon, Class<?> to) {
        this.title = title;
        this.icon = icon;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Class<?> getTo() {
        return to;
    }

    public void setTo(Class<?> to) {
        this.to = to;
    }

}
