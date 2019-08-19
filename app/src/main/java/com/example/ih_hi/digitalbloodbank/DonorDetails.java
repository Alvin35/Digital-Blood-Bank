package com.example.ih_hi.digitalbloodbank;


public class DonorDetails {

    String dName,dMobile,dLoca,dBldgrp,dAge,dLastDate,dWeight,dpass,dEmail;
    int _id;
    public DonorDetails(int id,String name, String Mobile,String email, String loca,String pass,String bldgrp, String age,String weight, String last)
    {
        _id = id;
        dName = name;
        dMobile = Mobile;
        dEmail = email;

        dLoca = loca;
        dpass = pass;
        dBldgrp = bldgrp;
        dAge = age;
        dWeight = weight;
        dLastDate = last;
    }

    public int get_id() {
        return _id;
    }

    public String getdEmail() {
        return dEmail;
    }

    public void setdEmail(String dEmail) {
        this.dEmail = dEmail;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDpass() {
        return dpass;
    }

    public void setDpass(String dpass) {
        this.dpass = dpass;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdMobile() {
        return dMobile;
    }

    public void setdMobile(String dMobile) {
        this.dMobile = dMobile;
    }

    public String getdLoca() {
        return dLoca;
    }

    public void setdLoca(String dLoca) {
        this.dLoca = dLoca;
    }

    public String getdAge() {
        return dAge;
    }

    public void setdAge(String dAge) {
        this.dAge = dAge;
    }

    public String getdBldgrp() {
        return dBldgrp;
    }

    public void setdBldgrp(String dBldgrp) {
        this.dBldgrp = dBldgrp;
    }

    public String getdLastDate() {
        return dLastDate;
    }

    public void setdLastDate(String dLastDate) {
        this.dLastDate = dLastDate;
    }

    public String getdWeight() {
        return dWeight;
    }

    public void setdWeight(String dWeight) {
        this.dWeight = dWeight;
    }
}

