package com.example.administrator.plb.fragment.operating_fragment.business;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.plb.R;
/*
 * create by csy 1/2
 * 营业统计
 * 近七天
 * */
public class business_frag2 extends Fragment {
    private View view;
    public Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_business_frag2,container,false);
        return view;
    }

}
