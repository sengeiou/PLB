package com.example.administrator.plb.fragment.order_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.OrderFragmentAdapter;
import com.example.administrator.plb.adapter.ReminderFragmentAdapter;
import com.example.administrator.plb.entity.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 催单
 */
public class ReminderFragment extends Fragment {
    private ListView mList;
    private ReminderFragmentAdapter adapter;
    private List<OrderBean> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_new,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mList=view.findViewById(R.id.list);

        list=new ArrayList<>();
        List<OrderBean.ShoppingBean> beans=new ArrayList<>();
        beans.add(new OrderBean.ShoppingBean("杜蕾斯",1,40));
        list.add(new OrderBean(1,"立即送达","隔壁老王","1748899174","2019-04-04",1,40,"123456789","天马山",beans));
        adapter=new ReminderFragmentAdapter(getActivity(),list);
        mList.setAdapter(adapter);
    }
}
