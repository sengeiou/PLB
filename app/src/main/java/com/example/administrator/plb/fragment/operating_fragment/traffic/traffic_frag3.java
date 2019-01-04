package com.example.administrator.plb.fragment.operating_fragment.traffic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.plb.R;


/**
 * 流量分析近30天
 */
public class traffic_frag3 extends Fragment {
    private View view;
    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_traffic_frag3,container,false);
        return view;
    }
}
