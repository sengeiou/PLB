package com.example.administrator.plb.entity;

import java.util.List;

public class ClassInfoBean {
    private List<ClassBean> classBeanList;

    public ClassInfoBean(List<ClassBean> classBeanList) {
        this.classBeanList = classBeanList;
    }

    public List<ClassBean> getClassBeanList() {
        return classBeanList;
    }

    public void setClassBeanList(List<ClassBean> classBeanList) {
        this.classBeanList = classBeanList;
    }

    public static class ClassBean{
        private int classificationId;
        private String classificationName;
        private int parentId;
        private int type;
        private Object typeDescribe;
        private int storeId;
        private List<UserInformBean.CommodityListBean>commodityListBeans;

        public ClassBean(int classificationId, String classificationName, int parentId, int type, Object typeDescribe, int storeId, List<UserInformBean.CommodityListBean> commodityListBeans) {
            this.classificationId = classificationId;
            this.classificationName = classificationName;
            this.parentId = parentId;
            this.type = type;
            this.typeDescribe = typeDescribe;
            this.storeId = storeId;
            this.commodityListBeans = commodityListBeans;
        }

        public List<UserInformBean.CommodityListBean> getCommodityListBeans() {
            return commodityListBeans;
        }

        public void setCommodityListBeans(List<UserInformBean.CommodityListBean> commodityListBeans) {
            this.commodityListBeans = commodityListBeans;
        }

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
}
