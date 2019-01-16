package com.example.administrator.plb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.SelectDay;

import java.util.List;

/**
 * Created by xlj on 2019.1.3.
 */
public class SelectDayAdapter extends BaseAdapter {
    private List mList;
    private Context mContext;
    private CompoundButton.OnCheckedChangeListener  onCheckedChangeListener;
    public SelectDayAdapter(Context context, List<SelectDay> list, CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        this.mList = list;
        this.mContext = context;
        this.onCheckedChangeListener=onCheckedChangeListener;
    }
    @Override
    public int getCount() {
        return mList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        SelectDay sd = (SelectDay) mList.get(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.select_day_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text = view.findViewById(R.id.tv_day);
            viewHolder.ck = view.findViewById(R.id.ck_select);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text.setText(sd.getDay());
        viewHolder.ck.setChecked(sd.getCk());
        viewHolder.ck.setOnCheckedChangeListener(onCheckedChangeListener);

        return view;
    }

    class ViewHolder{
        TextView text;
        CheckBox ck;
    }
}
