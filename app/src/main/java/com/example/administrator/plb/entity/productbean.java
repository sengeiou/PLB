package com.example.administrator.plb.entity;
/*
 * create by csy
 * 商品销售
 * */
public class productbean {
    private int stortimg;
    private String start_name;
    private int shop_money;
    private int shop_good;
    private String shop_evaluation;
    private int shop_num;
    private int shop_bad;

    public productbean() {
        super();
    }

    public productbean(int stortimg, String start_name, int shop_money, int shop_good, String shop_evaluation, int shop_num, int shop_bad) {
        this.stortimg = stortimg;
        this.start_name = start_name;
        this.shop_money = shop_money;
        this.shop_good = shop_good;
        this.shop_evaluation = shop_evaluation;
        this.shop_num = shop_num;
        this.shop_bad = shop_bad;
    }

    public int getStortimg() {
        return stortimg;
    }

    public void setStortimg(int stortimg) {
        this.stortimg = stortimg;
    }

    public String getStart_name() {
        return start_name;
    }

    public void setStart_name(String start_name) {
        this.start_name = start_name;
    }

    public int getShop_money() {
        return shop_money;
    }

    public void setShop_money(int shop_money) {
        this.shop_money = shop_money;
    }

    public int getShop_good() {
        return shop_good;
    }

    public void setShop_good(int shop_good) {
        this.shop_good = shop_good;
    }

    public String getShop_evaluation() {
        return shop_evaluation;
    }

    public void setShop_evaluation(String shop_evaluation) {
        this.shop_evaluation = shop_evaluation;
    }

    public int getShop_num() {
        return shop_num;
    }

    public void setShop_num(int shop_num) {
        this.shop_num = shop_num;
    }

    public int getShop_bad() {
        return shop_bad;
    }

    public void setShop_bad(int shop_bad) {
        this.shop_bad = shop_bad;
    }
}
