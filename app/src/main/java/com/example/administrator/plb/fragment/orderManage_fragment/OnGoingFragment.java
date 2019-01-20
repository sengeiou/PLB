package com.example.administrator.plb.fragment.orderManage_fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.MainThread;
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
import com.example.administrator.plb.adapter.OrderCancelAdapter;
import com.example.administrator.plb.adapter.OrderCompletedAdapter;
import com.example.administrator.plb.adapter.OrderOnGoindAdapter;
import com.example.administrator.plb.entity.OrderBean;
import com.example.administrator.plb.until.CacheUntil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 取消订单
 */
public class OnGoingFragment extends Fragment {
    private OrderBean orderBean;
    private OrderOnGoindAdapter adapter;
    private ListView list;
    private LocalBroadcastManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_new,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=view.findViewById(R.id.list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
    }

    private void reash(String order) {
       orderBean=new Gson().fromJson(order,OrderBean.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        //当Gson封装的JavaBean不为空时,添加到适配器进行设置
        if(orderBean!=null){
            adapter=new OrderOnGoindAdapter(getActivity(),orderBean,1);
            list.setAdapter(adapter);
        }
    }

    //添加subscribe的注解，进行EventBus的订阅，进行消息的接收
    @Subscribe
    public void Updata(String result){
        reash(result);
        onStart();
    }

    @Override
    public void onDestroy() {
        //注销EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
