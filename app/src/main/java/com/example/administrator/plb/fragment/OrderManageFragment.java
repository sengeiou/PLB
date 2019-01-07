package com.example.administrator.plb.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.FragmentAdapter;
import com.example.administrator.plb.fragment.orderManage_fragment.CancelFragment;
import com.example.administrator.plb.fragment.orderManage_fragment.CompletedFragment;
import com.example.administrator.plb.fragment.orderManage_fragment.OnGoingFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OrderManageFragment extends Fragment implements View.OnClickListener{

    private TextView mTitle,time;
    private Toolbar mToolbar;
    private TabLayout mTab;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manager,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mToolbar=view.findViewById(R.id.toolbar);
        mTab=view.findViewById(R.id.tab);
        mViewPager=view.findViewById(R.id.viewPager);
        time=view.findViewById(R.id.time);
        initView();
    }

    private void initView() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        time.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));
        time.setOnClickListener(this);
        List<Fragment> fragments=new ArrayList<>();
        List<String> titles=new ArrayList<>();
        fragments.add(new OnGoingFragment());//进行中
        fragments.add(new CompletedFragment());//已完成
        fragments.add(new CancelFragment());//已取消

        titles.add("进行中");
        titles.add("已完成");
        titles.add("已取消");
        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments,titles));
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        Calendar calendar= Calendar.getInstance();
        switch (v.getId()){
            case R.id.time:
                DatePickerDialog dialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
        }
    }
}
