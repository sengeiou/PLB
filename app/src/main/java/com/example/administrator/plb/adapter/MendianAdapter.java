package com.example.administrator.plb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.MenDianSetting;

import java.util.List;

/**
 * Created by xlj on 2019.1.2.
 */
public class MendianAdapter extends BaseAdapter {
    private List<MenDianSetting>list;
    private Context context;
    public MendianAdapter(Context context,List<MenDianSetting>list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenDianSetting md = list.get(position);
        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.mendian_list_item,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) view.findViewById(R.id.text1);
            viewHolder.text2 = (TextView) view.findViewById(R.id.text2);
            viewHolder.img = (ImageView) view.findViewById(R.id.img1);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text1.setText(md.getStr1());
        viewHolder.text2.setText(md.getStr2());
        if (position == 0){
            viewHolder.text2.setTextColor(Color.parseColor("#FF7400"));
        }
        viewHolder.img.setImageResource(md.getImg1());
        return view;
    }


    class ViewHolder{
        TextView text1;
        TextView text2;
        ImageView img;
    }
}
