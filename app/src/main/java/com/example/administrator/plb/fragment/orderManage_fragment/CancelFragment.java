package com.example.administrator.plb.fragment.orderManage_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.administrator.plb.entity.OrderBean;
import com.example.administrator.plb.until.CacheUntil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 取消订单
 */
public class CancelFragment extends Fragment {
    private OrderBean orderBean;
    private OrderCancelAdapter adapter;
    private ListView list;

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
        EventBus.getDefault().register(this);
    }

    private void reash(String order) {
        orderBean=new Gson().fromJson(order,OrderBean.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(orderBean!=null){
            adapter=new OrderCancelAdapter(getActivity(),orderBean,0);
            list.setAdapter(adapter);
        }
    }

    @Subscribe
    public void Updata(String result){
        reash(result);
        onStart();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
