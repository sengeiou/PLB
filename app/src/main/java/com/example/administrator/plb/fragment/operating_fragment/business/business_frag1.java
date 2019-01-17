package com.example.administrator.plb.fragment.operating_fragment.business;

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

import java.text.SimpleDateFormat;

/*
 * create by csy 1/2
 * 营业统计
 * 今天
 * */
public class business_frag1 extends Fragment {
    private View view;
    public Context context;
    /**
     * 今日
     */
    private TextView mBusinessTime1;
    /**
     * ￥1,872.5
     */
    private TextView mAmountOfBusiness1;
    /**
     * 53
     */
    private TextView mEffectiveOrderQuantity1;
    /**
     * 客单价￥35.33
     */
    private TextView mGuestUnitPrice1;
    /**
     * ￥73
     */
    private TextView mMerchantsSubsidies1;
    /**
     * 0
     */
    private TextView mInvalidOrderQuantity1;
    /**
     * 损失营业额约￥0
     */
    private TextView mLostTurnover1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_business_frag1, container, false);
        initView(view);

        return view;
    }


    private void initView(View view) {
        mBusinessTime1 = (TextView) view.findViewById(R.id.business_time1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        mBusinessTime1.setText(format.format(System.currentTimeMillis())+"");
        mAmountOfBusiness1 = (TextView) view.findViewById(R.id.amount_of_business1);
        mEffectiveOrderQuantity1 = (TextView) view.findViewById(R.id.Effective_order_quantity1);
        mGuestUnitPrice1 = (TextView) view.findViewById(R.id.guest_unit_price1);
        mMerchantsSubsidies1 = (TextView) view.findViewById(R.id.Merchants_subsidies1);
        mInvalidOrderQuantity1 = (TextView) view.findViewById(R.id.Invalid_order_quantity1);
        mLostTurnover1 = (TextView) view.findViewById(R.id.Lost_turnover1);
    }
}
