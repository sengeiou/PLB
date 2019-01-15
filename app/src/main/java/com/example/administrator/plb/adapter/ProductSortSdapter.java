package com.example.administrator.plb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.productbean;

import java.util.LinkedList;
/*
* create by csy
* 商品销售排序
* 适配器
* */
public class ProductSortSdapter extends BaseAdapter {
    private LinkedList<productbean> list;
    private Context context;
    private int[] imgs={R.mipmap.sort1,R.mipmap.sort2, R.mipmap.sort3,R.mipmap.sort4,R.mipmap.sort5,
            R.mipmap.sort6,R.mipmap.sort7, R.mipmap.sort8,R.mipmap.sort9,R.mipmap.sort10,R.mipmap.sort};

    public ProductSortSdapter(LinkedList<productbean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;//复用ConvertView以及自定义ViewHolder 减少findViewById()的调用
        if(convertView==null){
            holder=new ViewHolder();
            convertView=convertView.inflate(context, R.layout.product_list_item,null);
            holder.sort_img=convertView.findViewById(R.id.sort_img);
            holder.start_name=convertView.findViewById(R.id.start_name);
            holder.shop_money=convertView.findViewById(R.id.shop_money);
            holder.shop_good=convertView.findViewById(R.id.shop_good);
            holder.shop_evaluation=convertView.findViewById(R.id.shop_evaluation);
            holder.shop_num=convertView.findViewById(R.id.shop_num);
            holder.shop_bad=convertView.findViewById(R.id.shop_bad);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        if(position<10) {
            holder.sort_img.setImageResource(imgs[position]);
        }else{
            holder.sort_img.setImageResource(imgs[10]);
        }
        holder.start_name.setText(list.get(position).getStart_name());
        holder.shop_money.setText(list.get(position).getShop_money()+"");
        holder.shop_good.setText(list.get(position).getShop_good()+"");
        holder.shop_evaluation.setText(list.get(position).getShop_evaluation());
        holder.shop_num.setText(list.get(position).getShop_num()+"");
        holder. shop_bad.setText(list.get(position).getShop_bad()+"");
        return convertView;
    }

    static class ViewHolder{
        ImageView sort_img;
        TextView start_name;
        TextView shop_money;
        TextView shop_good;
        TextView shop_evaluation;
        TextView shop_num;
        TextView shop_bad;
    }

    public void add(productbean product) {
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(product);
        notifyDataSetChanged();
    }

}
