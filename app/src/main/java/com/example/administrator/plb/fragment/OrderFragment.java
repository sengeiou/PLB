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
import android.widget.ImageView;
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
        List<Integer> image=new ArrayList<>();
        fragments.add(new NewOrderFragment());//新订单
        fragments.add(new ReminderFragment());//催单
        fragments.add(new RefundFragment());//退款
        fragments.add(new CompensateFragment());//赔付
        titles.add("新订单");
        titles.add("催单");
        titles.add("退款");
        titles.add("赔付");
        image.add(R.drawable.xin_selector);
        image.add(R.drawable.cui_selector);
        image.add(R.drawable.tui_selector);
        image.add(R.drawable.pei_selector);
        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments,titles));
        mTab.setupWithViewPager(mViewPager);
        //setupWithViewPager方法会清除Tab，再重新创建
        for(int i=0;i<titles.size();i++){
            TabLayout.Tab tab=mTab.getTabAt(i);
            tab.setCustomView(R.layout.fragment_order_tab);
            if (i==0) {
                View view1=tab.getCustomView();
                view1.findViewById(R.id.image).setSelected(true);
                view1.findViewById(R.id.title).setSelected(true);
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.title);
            ImageView imageView =  tab.getCustomView().findViewById(R.id.image);
            textView.setText(titles.get(i));
            imageView.setBackgroundResource(image.get(i));
        }
        mTab.addOnTabSelectedListener(listener);
    }
    private TabLayout.OnTabSelectedListener listener=new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            tab.getCustomView().findViewById(R.id.title).setSelected(true);
            tab.getCustomView().findViewById(R.id.image).setSelected(true);
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            tab.getCustomView().findViewById(R.id.title).setSelected(false);
            tab.getCustomView().findViewById(R.id.image).setSelected(false);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
