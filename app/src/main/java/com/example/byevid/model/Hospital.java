package com.example.byevid.model;

public class Hospital {
    private String name;
    private String phone;
    private double _lang;
    private double _long;
    private String address;
    private String imgUrl;

    public Hospital(String name, String phone, double _lang, double _long, String address, String imgUrl) {
        this.name = name;
        this.phone = phone;
        this._lang = _lang;
        this._long = _long;
        this.address = address;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double get_lang() {
        return _lang;
    }

    public void set_lang(double _lang) {
        this._lang = _lang;
    }

    public double get_long() {
        return _long;
    }

    public void set_long(double _long) {
        this._long = _long;
    }
}
