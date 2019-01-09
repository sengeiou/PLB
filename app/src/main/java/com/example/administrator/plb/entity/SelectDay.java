package com.example.administrator.plb.entity;

/**
 * Created by xlj on 2019.1.3.
 */
public class SelectDay {
    private String day;
    private Boolean isCk;

    public void SelectDay(){

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getCk() {
        return isCk;
    }

    public void setCk(Boolean ck) {
        isCk = ck;
    }

    public SelectDay(String day, Boolean isCk) {
        this.day = day;
        this.isCk = isCk;
    }
}
