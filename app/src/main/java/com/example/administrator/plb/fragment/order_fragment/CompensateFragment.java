package com.example.administrator.plb.fragment.order_fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.CompensateFragmentAdapter;
import com.example.administrator.plb.adapter.OrderFragmentAdapter;
import com.example.administrator.plb.entity.OrderBean;
import com.example.administrator.plb.until.CacheUntil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 新订单
 */
public class CompensateFragment extends Fragment {


    private ListView mList;
    private CompensateFragmentAdapter adapter;
    private List<OrderBean> list;
    private OrderBean orderBean;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_new,null);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mList=view.findViewById(R.id.list);
        list=new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "start", Toast.LENGTH_SHORT).show();
        String order=CacheUntil.getString(getActivity(),"order","");
        if(!TextUtils.isEmpty(order)){
            orderBean=new Gson().fromJson(order,OrderBean.class);
            adapter=new CompensateFragmentAdapter(getActivity(),orderBean);
            mList.setAdapter(adapter);
        }

    }
}
