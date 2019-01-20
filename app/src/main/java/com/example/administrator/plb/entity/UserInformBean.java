package com.example.administrator.plb.entity;

import java.util.List;

public class UserInformBean {


    /**
     * userInfo : {"id":1,"address":"1111111","identity":"11111111","name":"测试001","phone":"11111111","userid":1}
     * commodityTypeList : [{"classificationId":1,"classificationName":"副食品","parentId":0,"type":1,"typeDescribe":null,"storeId":1},{"classificationId":2,"classificationName":"干果","parentId":0,"type":1,"typeDescribe":null,"storeId":1},{"classificationId":4,"classificationName":"牛奶","parentId":1,"type":2,"typeDescribe":null,"storeId":1},{"classificationId":5,"classificationName":"麻辣","parentId":1,"type":2,"typeDescribe":null,"storeId":1}]
     * orderList : [{"id":1,"address":"湖南衡阳科学城B2栋","consignee":"张三","freightInsurance":0,"leave_message":"","money":200,"orderid":100000001,"orderTime":"2019-01-11T02:05:36.000+0000","phone":"15773411484","state":2,"userId":1,"storeId":1,"orderitemVO":[{"orderitemId":5,"number":3,"subtotal":168,"commodityid":1,"goodsName":"伊利牛奶","unit":"箱","info":"伊利 高钙低脂奶 250ml*24盒 高钙低脂营养早餐纯牛奶","image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/20181228-008.jpg"},{"orderitemId":6,"number":4,"subtotal":268,"commodityid":2,"goodsName":"卫龙辣条","unit":"包","info":"卫龙辣条","image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/004.jpg"}]},{"id":2,"address":"湖南衡阳科学城B2栋","consignee":"李四","freightInsurance":0,"leave_message":"","money":250,"orderid":100000002,"orderTime":"2019-01-11T02:07:18.000+0000","phone":"15233341484","state":2,"userId":2,"storeId":1,"orderitemVO":[{"orderitemId":7,"number":5,"subtotal":158.6,"commodityid":3,"goodsName":"好丽友","unit":"\n包","info":"好丽友饼干","image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/20190109-010.jpg"},{"orderitemId":8,"number":6,"subtotal":138.2,"commodityid":4,"goodsName":"娃哈哈AD钙奶","unit":"件","info":"娃哈哈 AD钙奶220g*24瓶/箱儿童含乳饮料情怀饮品新老包装随机发","image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/002.jpg"}]}]
     * store : {"storeId":1,"address":"1111111","dpjydUrl":"http://192.168.43.49:8080/RetailManager/static/images/store/20181228-002.jpg","dzsfzUrl":"http://192.168.43.49:8080/RetailManager/static/images/store/20181228-003.jpg","license":"000000000","marketId":1,"ownerName":"测试001","phone":"111111111","storeName":"测试001批发店铺","storeType":"个体经营","type":2,"yxzsUrl":"http://192.168.43.49:8080/RetailManager/static/images/store/20181228-001.jpg","yyxkz":"http://192.168.43.49:8080/RetailManager/static/images/store/20181228-004.jpg","logoUrl":"http://192.168.43.49:8080/RetailManager/static/images/store/20190111-001.jpg","slogan":"掌上生活，疯狂特惠","monday":0,"tuesday":0,"wednesday":0,"thursday":0,"friday":0,"sunday":0,"saturday":0,"handheIdURL":null,"reverseIdURL":null,"userId":null,"closeTime":"20:00","openTime":"9:00","state":1,"shtg":1}
     * user : {"id":1,"password":"123","roleid":2,"storeId":1,"userName":"test001"}
     * commodityList : [{"id":1,"goodsName":"伊利牛奶","wholesalePrice":56.2,"retailPrice":62.5,"marketPrice":62.5,"image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/20181228-008.jpg","detailedurl":"http://192.168.43.49:8080/RetailManager/static/images/commodity/20190108-001.jpg","unit":"箱","minNum":2,"shelfLife":"1年","brand":"伊利","secondtypeId":4,"imported":0,"info":"伊利 高钙低脂奶 250ml*24盒 高钙低脂营养早餐纯牛奶","firsttypeId":1,"storeId":1,"stocks":100,"isShelfs":0},{"id":2,"goodsName":"卫龙辣条","wholesalePrice":2,"retailPrice":3,"marketPrice":3,"image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/004.jpg","detailedurl":"http://192.168.43.49:8080/RetailManager/static/images/commodity/005.jpg","unit":"包","minNum":2,"shelfLife":"1年","brand":"卫龙","secondtypeId":10,"imported":0,"info":"卫龙辣条","firsttypeId":1,"storeId":1,"stocks":100,"isShelfs":1},{"id":3,"goodsName":"好丽友","wholesalePrice":4,"retailPrice":5,"marketPrice":5,"image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/20190109-010.jpg","detailedurl":"images","unit":"\n包","minNum":2,"shelfLife":"1年","brand":"卡夫","secondtypeId":10,"imported":0,"info":"好丽友饼干","firsttypeId":1,"storeId":1,"stocks":100,"isShelfs":1},{"id":4,"goodsName":"娃哈哈AD钙奶","wholesalePrice":38.5,"retailPrice":41.5,"marketPrice":41.5,"image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/002.jpg","detailedurl":"http://192.168.43.49:8080/RetailManager/static/images/commodity/003.jpg","unit":"件","minNum":2,"shelfLife":"1年","brand":"娃哈哈","secondtypeId":4,"imported":0,"info":"娃哈哈 AD钙奶220g*24瓶/箱儿童含乳饮料情怀饮品新老包装随机发","firsttypeId":1,"storeId":1,"stocks":100,"isShelfs":1}]
     */

    private UserInfoBean userInfo;
    private StoreBean store;
    private UserBean user;
    private List<CommodityTypeListBean> commodityTypeList;
    private List<OrderListBean> orderList;
    private List<CommodityListBean> commodityList;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<CommodityTypeListBean> getCommodityTypeList() {
        return commodityTypeList;
    }

    public void setCommodityTypeList(List<CommodityTypeListBean> commodityTypeList) {
        this.commodityTypeList = commodityTypeList;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public List<CommodityListBean> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListBean> commodityList) {
        this.commodityList = commodityList;
    }

    public static class UserInfoBean {
        /**
         * id : 1
         * address : 1111111
         * identity : 11111111
         * name : 测试001
         * phone : 11111111
         * userid : 1
         */

        private int id;
        private String address;
        private String identity;
        private String name;
        private String phone;
        private int userid;

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

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }

    public static class StoreBean {
        /**
         * storeId : 1
         * address : 1111111
         * dpjydUrl : http://192.168.43.49:8080/RetailManager/static/images/store/20181228-002.jpg
         * dzsfzUrl : http://192.168.43.49:8080/RetailManager/static/images/store/20181228-003.jpg
         * license : 000000000
         * marketId : 1
         * ownerName : 测试001
         * phone : 111111111
         * storeName : 测试001批发店铺
         * storeType : 个体经营
         * type : 2
         * yxzsUrl : http://192.168.43.49:8080/RetailManager/static/images/store/20181228-001.jpg
         * yyxkz : http://192.168.43.49:8080/RetailManager/static/images/store/20181228-004.jpg
         * logoUrl : http://192.168.43.49:8080/RetailManager/static/images/store/20190111-001.jpg
         * slogan : 掌上生活，疯狂特惠
         * monday : 0
         * tuesday : 0
         * wednesday : 0
         * thursday : 0
         * friday : 0
         * sunday : 0
         * saturday : 0
         * handheIdURL : null
         * reverseIdURL : null
         * userId : null
         * closeTime : 20:00
         * openTime : 9:00
         * state : 1
         * shtg : 1
         */

        private int storeId;
        private String address;
        private String dpjydUrl;
        private String dzsfzUrl;
        private String license;
        private int marketId;
        private String ownerName;
        private String phone;
        private String storeName;
        private String storeType;
        private int type;
        private String yxzsUrl;
        private String yyxkz;
        private String logoUrl;
        private String slogan;
        private int monday;
        private int tuesday;
        private int wednesday;
        private int thursday;
        private int friday;
        private int sunday;
        private int saturday;
        private Object handheIdURL;
        private Object reverseIdURL;
        private Object userId;
        private String closeTime;
        private String openTime;
        private int state;
        private int shtg;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDpjydUrl() {
            return dpjydUrl;
        }

        public void setDpjydUrl(String dpjydUrl) {
            this.dpjydUrl = dpjydUrl;
        }

        public String getDzsfzUrl() {
            return dzsfzUrl;
        }

        public void setDzsfzUrl(String dzsfzUrl) {
            this.dzsfzUrl = dzsfzUrl;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public int getMarketId() {
            return marketId;
        }

        public void setMarketId(int marketId) {
            this.marketId = marketId;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreType() {
            return storeType;
        }

        public void setStoreType(String storeType) {
            this.storeType = storeType;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getYxzsUrl() {
            return yxzsUrl;
        }

        public void setYxzsUrl(String yxzsUrl) {
            this.yxzsUrl = yxzsUrl;
        }

        public String getYyxkz() {
            return yyxkz;
        }

        public void setYyxkz(String yyxkz) {
            this.yyxkz = yyxkz;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public int getMonday() {
            return monday;
        }

        public void setMonday(int monday) {
            this.monday = monday;
        }

        public int getTuesday() {
            return tuesday;
        }

        public void setTuesday(int tuesday) {
            this.tuesday = tuesday;
        }

        public int getWednesday() {
            return wednesday;
        }

        public void setWednesday(int wednesday) {
            this.wednesday = wednesday;
        }

        public int getThursday() {
            return thursday;
        }

        public void setThursday(int thursday) {
            this.thursday = thursday;
        }

        public int getFriday() {
            return friday;
        }

        public void setFriday(int friday) {
            this.friday = friday;
        }

        public int getSunday() {
            return sunday;
        }

        public void setSunday(int sunday) {
            this.sunday = sunday;
        }

        public int getSaturday() {
            return saturday;
        }

        public void setSaturday(int saturday) {
            this.saturday = saturday;
        }

        public Object getHandheIdURL() {
            return handheIdURL;
        }

        public void setHandheIdURL(Object handheIdURL) {
            this.handheIdURL = handheIdURL;
        }

        public Object getReverseIdURL() {
            return reverseIdURL;
        }

        public void setReverseIdURL(Object reverseIdURL) {
            this.reverseIdURL = reverseIdURL;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getShtg() {
            return shtg;
        }

        public void setShtg(int shtg) {
            this.shtg = shtg;
        }
    }

    public static class UserBean {
        /**
         * id : 1
         * password : 123
         * roleid : 2
         * storeId : 1
         * userName : test001
         */

        private int id;
        private String password;
        private int roleid;
        private int storeId;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getRoleid() {
            return roleid;
        }

        public void setRoleid(int roleid) {
            this.roleid = roleid;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class CommodityTypeListBean {
        /**
         * classificationId : 1
         * classificationName : 副食品
         * parentId : 0
         * type : 1
         * typeDescribe : null
         * storeId : 1
         */

        private int classificationId;
        private String classificationName;
        private int parentId;
        private int type;
        private Object typeDescribe;
        private int storeId;

        public int getClassificationId() {
            return classificationId;
        }

        public void setClassificationId(int classificationId) {
            this.classificationId = classificationId;
        }

        public String getClassificationName() {
            return classificationName;
        }

        public void setClassificationName(String classificationName) {
            this.classificationName = classificationName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getTypeDescribe() {
            return typeDescribe;
        }

        public void setTypeDescribe(Object typeDescribe) {
            this.typeDescribe = typeDescribe;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
    }

    public static class OrderListBean {
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
         * orderitemVO : [{"orderitemId":5,"number":3,"subtotal":168,"commodityid":1,"goodsName":"伊利牛奶","unit":"箱","info":"伊利 高钙低脂奶 250ml*24盒 高钙低脂营养早餐纯牛奶","image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/20181228-008.jpg"},{"orderitemId":6,"number":4,"subtotal":268,"commodityid":2,"goodsName":"卫龙辣条","unit":"包","info":"卫龙辣条","image":"http://192.168.43.49:8080/RetailManager/static/images/commodity/004.jpg"}]
         */

        private int id;
        private String address;
        private String consignee;
        private int freightInsurance;
        private String leave_message;
        private int money;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
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
             * image : http://192.168.43.49:8080/RetailManager/static/images/commodity/20181228-008.jpg
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

    public static class CommodityListBean {
        /**
         * id : 1
         * goodsName : 伊利牛奶
         * wholesalePrice : 56.2
         * retailPrice : 62.5
         * marketPrice : 62.5
         * image : http://192.168.43.49:8080/RetailManager/static/images/commodity/20181228-008.jpg
         * detailedurl : http://192.168.43.49:8080/RetailManager/static/images/commodity/20190108-001.jpg
         * unit : 箱
         * minNum : 2
         * shelfLife : 1年
         * brand : 伊利
         * secondtypeId : 4
         * imported : 0
         * info : 伊利 高钙低脂奶 250ml*24盒 高钙低脂营养早餐纯牛奶
         * firsttypeId : 1
         * storeId : 1
         * stocks : 100
         * isShelfs : 0
         */

        private int id;
        private String goodsName;
        private double wholesalePrice;
        private double retailPrice;
        private double marketPrice;
        private String unit;
        private int minNum;
        private String shelfLife;
        private String brand;
        private int secondtypeId;
        private int imported;
        private String info;
        private int firsttypeId;
        private int storeId;
        private int stocks;
        private int isShelfs;
        private String image;
        private String detailedurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getWholesalePrice() {
            return wholesalePrice;
        }

        public void setWholesalePrice(double wholesalePrice) {
            this.wholesalePrice = wholesalePrice;
        }

        public double getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(double retailPrice) {
            this.retailPrice = retailPrice;
        }

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDetailedurl() {
            return detailedurl;
        }

        public void setDetailedurl(String detailedurl) {
            this.detailedurl = detailedurl;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getMinNum() {
            return minNum;
        }

        public void setMinNum(int minNum) {
            this.minNum = minNum;
        }

        public String getShelfLife() {
            return shelfLife;
        }

        public void setShelfLife(String shelfLife) {
            this.shelfLife = shelfLife;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getSecondtypeId() {
            return secondtypeId;
        }

        public void setSecondtypeId(int secondtypeId) {
            this.secondtypeId = secondtypeId;
        }

        public int getImported() {
            return imported;
        }

        public void setImported(int imported) {
            this.imported = imported;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getFirsttypeId() {
            return firsttypeId;
        }

        public void setFirsttypeId(int firsttypeId) {
            this.firsttypeId = firsttypeId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStocks() {
            return stocks;
        }

        public void setStocks(int stocks) {
            this.stocks = stocks;
        }

        public int getIsShelfs() {
            return isShelfs;
        }

        public void setIsShelfs(int isShelfs) {
            this.isShelfs = isShelfs;
        }
    }
}
