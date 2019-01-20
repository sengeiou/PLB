package com.example.administrator.plb.entity;

import java.util.List;

public class OrderBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * address : 湖南衡阳科学城B2栋
         * consignee : 张三
         * freightInsurance : 0
         * leave_message :
         * money : 200
         * orderid : 100000001
         * orderTime : 2019-01-11T02:05:36.000+0000
         * phone : 15773411484
         * state : 2
         * userId : 1
         * storeId : 1
         * orderitemVO : [{"orderitemId":5,"number":3,"subtotal":168,"commodityid":1,"goodsName":"伊利牛奶","unit":"箱","info":"伊利 高钙低脂奶 250ml*24盒 高钙低脂营养早餐纯牛奶","image":"http://39.98.68.40:8080/RetailManager/static/images/commodity/20181228-008.jpg"}]
         */

        private int id;
        private String address;
        private String consignee;
        private int freightInsurance;
        private String leave_message;
        private double money;
        private int orderid;
        private String orderTime;
        private String phone;
        private int state;
        private int userId;
        private int storeId;
        private List<OrderitemVOBean> orderitemVO;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public int getFreightInsurance() {
            return freightInsurance;
        }

        public void setFreightInsurance(int freightInsurance) {
            this.freightInsurance = freightInsurance;
        }

        public String getLeave_message() {
            return leave_message;
        }

        public void setLeave_message(String leave_message) {
            this.leave_message = leave_message;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public List<OrderitemVOBean> getOrderitemVO() {
            return orderitemVO;
        }

        public void setOrderitemVO(List<OrderitemVOBean> orderitemVO) {
            this.orderitemVO = orderitemVO;
        }

        public static class OrderitemVOBean {
            /**
             * orderitemId : 5
             * number : 3
             * subtotal : 168
             * commodityid : 1
             * goodsName : 伊利牛奶
             * unit : 箱
             * info : 伊利 高钙低脂奶 250ml*24盒 高钙低脂营养早餐纯牛奶
             * image : http://39.98.68.40:8080/RetailManager/static/images/commodity/20181228-008.jpg
             */

            private int orderitemId;
            private int number;
            private double subtotal;
            private int commodityid;
            private String goodsName;
            private String unit;
            private String info;
            private String image;

            public int getOrderitemId() {
                return orderitemId;
            }

            public void setOrderitemId(int orderitemId) {
                this.orderitemId = orderitemId;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public double getSubtotal() {
                return subtotal;
            }

            public void setSubtotal(double subtotal) {
                this.subtotal = subtotal;
            }

            public int getCommodityid() {
                return commodityid;
            }

            public void setCommodityid(int commodityid) {
                this.commodityid = commodityid;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
