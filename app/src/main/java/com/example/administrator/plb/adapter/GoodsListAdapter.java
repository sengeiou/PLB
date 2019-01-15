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
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.until.FileUtils;

public class GoodsListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private GoodsListBean listBean;

    public GoodsListAdapter(Context context, GoodsListBean listBean) {
        this.context = context;
        this.listBean = listBean;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return listBean.getList().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listBean
                .getList()
                .get(groupPosition)
                .getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listBean.getList().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listBean.getList().get(groupPosition).getList().get(childPosition);
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
        viewHolder.className.setText(listBean.getList().get(groupPosition).getClassName());
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
        GoodsListBean.GoodsBean goodsBean= listBean.getList().get(groupPosition).getList().get(childPosition);
        viewHolder.goodsName.setText(goodsBean.getGoodsName());
        viewHolder.inventory.setText(goodsBean.getInventory()+"");
        viewHolder.minCount.setText(goodsBean.getMinCount()+"元/"+goodsBean.getGoodsUnit());
        viewHolder.sellingTime.setText(goodsBean.getSellingTime());
        viewHolder.price.setText("￥"+goodsBean.getGoodsPrice()+"元/"+goodsBean.getGoodsUnit());
        Glide.with(context).load(goodsBean.getGoodsImage())
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
