package com.example.administrator.plb.entity;

import java.util.List;

public class GoodsListBean {

    private List<ClassBean> list;

    public List<ClassBean> getList() {
        return list;
    }

    public void setList(List<ClassBean> list) {
        this.list = list;
    }

    public static class ClassBean{
        private String className;
        private String note;
        private List<GoodsBean>list;

        public ClassBean(String className, String note, List<GoodsBean> list) {
            this.className = className;
            this.note = note;
            this.list = list;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public List<GoodsBean> getList() {
            return list;
        }

        public void setList(List<GoodsBean> list) {
            this.list = list;
        }
    }
    public static class GoodsBean{
        private String GoodsName; //商品名称
        private String GoodsClass;//商品分类
        private String GoodsImage;//商品图片
        private int GoodsPrice;//商品单价
        private String GoodsUnit;//商品单位
        private int inventory;//库存
        private int minCount;//最低数量
        private String sellingTime;//销售时间

        public GoodsBean(String goodsName, String goodsClass, String goodsImage, int goodsPrice, String goodsUnit, int inventory, int minCount, String sellingTime) {
            GoodsName = goodsName;
            GoodsClass = goodsClass;
            GoodsImage = goodsImage;
            GoodsPrice = goodsPrice;
            GoodsUnit = goodsUnit;
            this.inventory = inventory;
            this.minCount = minCount;
            this.sellingTime = sellingTime;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String goodsName) {
            GoodsName = goodsName;
        }

        public String getGoodsClass() {
            return GoodsClass;
        }

        public void setGoodsClass(String goodsClass) {
            GoodsClass = goodsClass;
        }

        public String getGoodsImage() {
            return GoodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            GoodsImage = goodsImage;
        }

        public int getGoodsPrice() {
            return GoodsPrice;
        }

        public void setGoodsPrice(int goodsPrice) {
            GoodsPrice = goodsPrice;
        }

        public String getGoodsUnit() {
            return GoodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            GoodsUnit = goodsUnit;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public int getMinCount() {
            return minCount;
        }

        public void setMinCount(int minCount) {
            this.minCount = minCount;
        }

        public String getSellingTime() {
            return sellingTime;
        }

        public void setSellingTime(String sellingTime) {
            this.sellingTime = sellingTime;
        }
    }
}
