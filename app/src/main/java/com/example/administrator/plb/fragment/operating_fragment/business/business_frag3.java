package com.example.administrator.plb.fragment.operating_fragment.business;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.plb.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * create by csy 1/2
 * 营业统计
 * 近三十天
 * */
public class business_frag3 extends Fragment {
    private View view;
    public Context context;
    /**
     * 近30日
     */
    private TextView mBusinessTime3;
    /**
     * ￥1,872.5
     */
    private TextView mAmountOfBusiness3;
    /**
     * 53
     */
    private TextView mEffectiveOrderQuantity3;
    /**
     * 客单价￥35.33
     */
    private TextView mGuestUnitPrice3;
    /**
     * ￥73
     */
    private TextView mMerchantsSubsidies3;
    /**
     * 0
     */
    private TextView mInvalidOrderQuantity3;
    /**
     * 损失营业额约￥0
     */
    private TextView mLostTurnover3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_business_frag3, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBusinessTime3 = (TextView) view.findViewById(R.id.business_time3);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        mBusinessTime3.setText(format.format(System.currentTimeMillis())+"."+getOldDate(-30)+"—"+getOldDate(0));
        mAmountOfBusiness3 = (TextView) view.findViewById(R.id.amount_of_business3);
        mEffectiveOrderQuantity3 = (TextView) view.findViewById(R.id.Effective_order_quantity3);
        mGuestUnitPrice3 = (TextView) view.findViewById(R.id.guest_unit_price3);
        mMerchantsSubsidies3 = (TextView) view.findViewById(R.id.Merchants_subsidies3);
        mInvalidOrderQuantity3 = (TextView) view.findViewById(R.id.Invalid_order_quantity3);
        mLostTurnover3 = (TextView) view.findViewById(R.id.Lost_turnover3);
    }

    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("MM.dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("近7天==","" + dft.format(endDate));
        return dft.format(endDate);
    }
}
