package com.example.administrator.plb.fragment.operating_fragment.businessdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.activity.operating_activity.Customer_analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create by csy
 * 近7日 经营数据
 */
public class Businessdata_frg extends Fragment implements View.OnClickListener {
    private View view;
    /**  */
    private TextView mCountTime2;
    /**
     * 303
     */
    private TextView mAccessTraffic2;
    private ImageView mTrafficaccessTopImg2;
    /**
     * 18
     */
    private TextView mTrafficaccessTopPeople2;
    /**
     * 人
     */
    private TextView mTrafficpeople12;
    /**
     * 42
     */
    private TextView mOrderTraffic2;
    private ImageView mTrafficorderBlowImg2;
    /**
     * 18
     */
    private TextView mTrafficorderBlowPeople2;
    /**
     * 人
     */
    private TextView mTrafficpeople22;
    /**
     * 13.86%
     */
    private TextView mTrafficCountAvg2;
    private ImageView mTrafficCountImg2;
    /**
     * 3.68%
     */
    private TextView mTrafficCount2;
    /**
     * 详情
     */
    private TextView mClickTo;
    /**
     * 78
     */
    private TextView mAccessCustomer2;
    private ImageView mAccessTopImg2;
    /**
     * 18
     */
    private TextView mAccessTopPeople2;
    /**
     * 人
     */
    private TextView mPeople12;
    /**
     * 110
     */
    private TextView mOrderCustomer2;
    private ImageView mOrderBlowImg2;
    /**
     * 18
     */
    private TextView mOrderBlowPeople2;
    /**
     * 人
     */
    private TextView mPeople22;
    /**
     * 188人
     */
    private TextView mCountAvg2;
    private ImageView mCountImg2;
    /**
     * 55人
     */
    private TextView mCount2;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.businessdata_frg, container, false);
        initView(view);
        setData();
        return view;
    }

    private void initView(View view) {
        mCountTime2 = (TextView) view.findViewById(R.id.count_time2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        mCountTime2.setText(format.format(System.currentTimeMillis())+"."+getOldDate(-7)+"—"+getOldDate(0));
        mAccessTraffic2 = (TextView) view.findViewById(R.id.access_traffic2);
        mTrafficaccessTopImg2 = (ImageView) view.findViewById(R.id.trafficaccess_top_img2);
        mTrafficaccessTopPeople2 = (TextView) view.findViewById(R.id.trafficaccess_top_people2);
        mTrafficpeople12 = (TextView) view.findViewById(R.id.trafficpeople12);
        mOrderTraffic2 = (TextView) view.findViewById(R.id.order_traffic2);
        mTrafficorderBlowImg2 = (ImageView) view.findViewById(R.id.trafficorder_blow_img2);
        mTrafficorderBlowPeople2 = (TextView) view.findViewById(R.id.trafficorder_blow_people2);
        mTrafficpeople22 = (TextView) view.findViewById(R.id.trafficpeople22);
        mTrafficCountAvg2 = (TextView) view.findViewById(R.id.traffic_count_avg2);
        mTrafficCountImg2 = (ImageView) view.findViewById(R.id.traffic_count_img2);
        mTrafficCount2 = (TextView) view.findViewById(R.id.traffic_count2);
        mClickTo = (TextView) view.findViewById(R.id.click_to);
        mClickTo.setOnClickListener(this);
        mAccessCustomer2 = (TextView) view.findViewById(R.id.access_customer2);
        mAccessTopImg2 = (ImageView) view.findViewById(R.id.access_top_img2);
        mAccessTopPeople2 = (TextView) view.findViewById(R.id.access_top_people2);
        mPeople12 = (TextView) view.findViewById(R.id.people12);
        mOrderCustomer2 = (TextView) view.findViewById(R.id.order_customer2);
        mOrderBlowImg2 = (ImageView) view.findViewById(R.id.order_blow_img2);
        mOrderBlowPeople2 = (TextView) view.findViewById(R.id.order_blow_people2);
        mPeople22 = (TextView) view.findViewById(R.id.people22);
        mCountAvg2 = (TextView) view.findViewById(R.id.count_avg2);
        mCountImg2 = (ImageView) view.findViewById(R.id.count_img2);
        mCount2 = (TextView) view.findViewById(R.id.count2);
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

    public void setData(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.click_to:
                intent= new Intent(getActivity(), Customer_analysis.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
        }
    }



}
