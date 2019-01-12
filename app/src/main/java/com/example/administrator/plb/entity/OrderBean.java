package com.example.administrator.plb.entity;

import java.util.List;

public class OrderBean {
    private int id;
    private String way;
    private String username;
    private String phone;
    private String time;
    private int count;
    private int total;
    private String orderNumber;
    private String address;
    private List<ShoppingBean> list;

    public OrderBean(int id, String way, String username, String phone, String time, int count, int total, String orderNumber, String address, List<ShoppingBean> list) {
        this.id = id;
        this.way = way;
        this.username = username;
        this.phone = phone;
        this.time = time;
        this.count = count;
        this.total = total;
        this.orderNumber = orderNumber;
        this.address = address;
        this.list = list;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ShoppingBean> getList() {
        return list;
    }

    public void setList(List<ShoppingBean> list) {
        this.list = list;
    }

    public static class ShoppingBean{
        private String name;
        private int number;
        private int price;

        public ShoppingBean(String name, int number, int price) {
            this.name = name;
            this.number = number;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
