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
import com.example.administrator.plb.fragment.operating_fragment.customer.customer_frag2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * create by csy 1/2
 * 营业统计
 * 近七天
 * */
public class business_frag2 extends Fragment {
    private View view;
    public Context context;
    /**
     * 近7日
     */
    private TextView mBusinessTime2;
    /**
     * ￥1,872.5
     */
    private TextView mAmountOfBusiness2;
    /**
     * 53
     */
    private TextView mEffectiveOrderQuantity2;
    /**
     * 客单价￥35.33
     */
    private TextView mGuestUnitPrice2;
    /**
     * ￥73
     */
    private TextView mMerchantsSubsidies2;
    /**
     * 0
     */
    private TextView mInvalidOrderQuantity2;
    /**
     * 损失营业额约￥0
     */
    private TextView mLostTurnover2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_business_frag2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBusinessTime2 = (TextView) view.findViewById(R.id.business_time2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        mBusinessTime2.setText(format.format(System.currentTimeMillis())+"."+getOldDate(-7)+"—"+getOldDate(0));
        mAmountOfBusiness2 = (TextView) view.findViewById(R.id.amount_of_business2);
        mEffectiveOrderQuantity2 = (TextView) view.findViewById(R.id.Effective_order_quantity2);
        mGuestUnitPrice2 = (TextView) view.findViewById(R.id.guest_unit_price2);
        mMerchantsSubsidies2 = (TextView) view.findViewById(R.id.Merchants_subsidies2);
        mInvalidOrderQuantity2 = (TextView) view.findViewById(R.id.Invalid_order_quantity2);
        mLostTurnover2 = (TextView) view.findViewById(R.id.Lost_turnover2);
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
