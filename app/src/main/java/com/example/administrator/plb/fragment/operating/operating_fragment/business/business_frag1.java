package com.example.administrator.plb.fragment.operating.operating_fragment.business;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.plb.R;

/*
 * create by csy 1/2
 * 营业统计
 * 今天
 * */
public class business_frag1 extends Fragment {
    private View view;
    public Context context;

    private TextView mNum1;

    private TextView mNum2;

    private TextView mNum3;

    private TextView mNum4;

    private TextView mNum5;

    private TextView mNum6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_business_frag1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mNum1 = (TextView) view.findViewById(R.id.num1);
        mNum2 = (TextView) view.findViewById(R.id.num2);
        mNum3 = (TextView) view.findViewById(R.id.num3);
        mNum4 = (TextView) view.findViewById(R.id.num4);
        mNum5 = (TextView) view.findViewById(R.id.num5);
        mNum6 = (TextView) view.findViewById(R.id.num6);
    }


}
