package com.example.administrator.plb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.plb.R;

import java.util.List;

/**
 * Created by tlf on 2019/1/2.
 */

public class NewGoodAdapter extends BaseAdapter{

    private Context mContext;
    private List<String> mList;

    public NewGoodAdapter(Context context, List<String> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView;

        mView = LayoutInflater.from(mContext).inflate(R.layout.newgoodadapter, parent, false);

        return mView;
    }
}
