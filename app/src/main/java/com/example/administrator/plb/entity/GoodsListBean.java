package com.example.administrator.plb.entity;

import java.io.Serializable;
import java.util.List;

public class GoodsListBean {

    public static class ClassBean{
        private int classificationId;
        private String classificationName;
        private int parentId;
        private int type;
        private Object typeDescribe;
        private int storeId;
        private List<UserInformBean.CommodityListBean>list;

        public List<UserInformBean.CommodityListBean> getList() {
            return list;
        }

        public void setList(List<UserInformBean.CommodityListBean> list) {
            this.list = list;
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
