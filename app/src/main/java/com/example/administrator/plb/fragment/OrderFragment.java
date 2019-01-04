package com.example.administrator.plb.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.FragmentAdapter;
import com.example.administrator.plb.fragment.order_fragment.CompensateFragment;
import com.example.administrator.plb.fragment.order_fragment.NewOrderFragment;
import com.example.administrator.plb.fragment.order_fragment.RefundFragment;
import com.example.administrator.plb.fragment.order_fragment.ReminderFragment;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    private TextView mTitle;
    private Toolbar mToolbar;
    private TabLayout mTab;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTitle=view.findViewById(R.id.title);
        mToolbar=view.findViewById(R.id.toolbar);
        mTab=view.findViewById(R.id.tab);
        mViewPager=view.findViewById(R.id.viewPager);

        initView();
    }

    private void initView() {
        mTitle.setText("待处理");
        List<Fragment> fragments=new ArrayList<>();
        List<String> titles=new ArrayList<>();
        fragments.add(new NewOrderFragment());//新订单
        fragments.add(new ReminderFragment());//催单
        fragments.add(new RefundFragment());//退款
        fragments.add(new CompensateFragment());//赔付
        titles.add("新订单");
        titles.add("催单");
        titles.add("退款");
        titles.add("赔付");
        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments,titles));
        mTab.setupWithViewPager(mViewPager);
//        setupWithViewPager方法会清除Tab，再重新创建
        mTab.getTabAt(0).setIcon(R.drawable.xin_selector);
        mTab.getTabAt(1).setIcon(R.drawable.cui_selector);
        mTab.getTabAt(2).setIcon(R.drawable.tui_selector);
        mTab.getTabAt(3).setIcon(R.drawable.pei_selector);
    }
}
