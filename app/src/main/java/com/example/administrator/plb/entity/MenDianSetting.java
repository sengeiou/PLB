package com.example.administrator.plb.entity;

/**
 * Created by xlj on 2019.1.2.
 */
public class MenDianSetting {
    private String str1;
    private String str2;
    private int img1;

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public MenDianSetting(String str1, String str2, int img1) {
        this.str1 = str1;
        this.str2 = str2;
        this.img1 = img1;
    }

    public void MenDianSetting(){

    }
}
