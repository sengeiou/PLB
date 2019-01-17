package com.example.administrator.plb.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.ClassInfoBean;
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<ClassInfoBean.ClassBean>list;
    public GoodsListAdapter(Context context, List<ClassInfoBean.ClassBean>list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getCommodityListBeans().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_goods_list_parent, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.className.setText(list.get(groupPosition).getClassificationName());
        if(isExpanded){
            viewHolder.arrow.setRotation(180);
        }else{
            viewHolder.arrow.setRotation(0);
        }
        return viewHolder.rootView;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView className;
        public ImageView arrow;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.className = (TextView) rootView.findViewById(R.id.className);
            this.arrow = rootView.findViewById(R.id.arrow);
        }

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder2 viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_goods_list_child, null);
            viewHolder = new ViewHolder2(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder2) convertView.getTag();
        }
        UserInformBean.CommodityListBean goodsBean= list.get(groupPosition).getCommodityListBeans().get(childPosition);
        viewHolder.goodsName.setText(goodsBean.getGoodsName());
        viewHolder.inventory.setText(goodsBean.getStocks()+"");
        viewHolder.minCount.setText(goodsBean.getMinNum()+"元/"+goodsBean.getUnit());
        viewHolder.sellingTime.setText(goodsBean.getShelfLife());
        viewHolder.price.setText("￥"+goodsBean.getWholesalePrice()+"元/"+goodsBean.getUnit());
        Glide.with(context).load(goodsBean.getImage())
                .error(R.mipmap.logo)
                .into(viewHolder.goodsImage);
        return viewHolder.rootView;
    }

    class ViewHolder2 {
        public View rootView;
        public TextView goodsName;
        public ImageView goodsImage;
        public TextView inventory;
        public TextView minCount;
        public TextView sellingTime;
        public TextView price;

        public ViewHolder2(View rootView) {
            this.rootView = rootView;
            this.goodsName = (TextView) rootView.findViewById(R.id.goodsName);
            this.goodsImage = (ImageView) rootView.findViewById(R.id.goodsImage);
            this.inventory = (TextView) rootView.findViewById(R.id.inventory);
            this.minCount = (TextView) rootView.findViewById(R.id.minCount);
            this.sellingTime = (TextView) rootView.findViewById(R.id.sellingTime);
            this.price = (TextView) rootView.findViewById(R.id.price);
        }

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




}
