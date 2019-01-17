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
 * 昨日 经营日报
 */
public class Businessdaily_frg extends Fragment implements View.OnClickListener {
    private View view;
    /**  */
    private TextView mCountTime;
    /**
     * 详情
     */
    private TextView mClick;
    /**
     * 303
     */
    private TextView mAccessTraffic;
    private ImageView mTrafficaccessTopImg;
    /**
     * 18
     */
    private TextView mTrafficaccessTopPeople;
    /**
     * 人
     */
    private TextView mTrafficpeople1;
    /**
     * 42
     */
    private TextView mOrderTraffic;
    private ImageView mTrafficorderBlowImg;
    /**
     * 18
     */
    private TextView mTrafficorderBlowPeople;
    /**
     * 人
     */
    private TextView mTrafficpeople2;
    /**
     * 13.86%
     */
    private TextView mTrafficCountAvg;
    private ImageView mTrafficCountImg;
    /**
     * 3.68%
     */
    private TextView mTrafficCount;
    /**
     * 78
     */
    private TextView mAccessCustomer;
    private ImageView mAccessTopImg;
    /**
     * 18
     */
    private TextView mAccessTopPeople;
    /**
     * 人
     */
    private TextView mPeople1;
    /**
     * 110
     */
    private TextView mOrderCustomer;
    private ImageView mOrderBlowImg;
    /**
     * 18
     */
    private TextView mOrderBlowPeople;
    /**
     * 人
     */
    private TextView mPeople2;
    /**
     * 188
     */
    private TextView mCountAvg;
    private ImageView mCountImg;
    /**
     * 55
     */
    private TextView mCount;
    private int num1,num2;
    private double sum;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.businessdaily_frg, container, false);
        initView(view);
        setData();
        return view;
    }

    private void initView(View view) {
        mCountTime = (TextView) view.findViewById(R.id.count_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        mCountTime.setText(format.format(System.currentTimeMillis())+"");
        mClick = (TextView) view.findViewById(R.id.click);
        mClick.setOnClickListener(this);
        mAccessTraffic = (TextView) view.findViewById(R.id.access_traffic);
        mTrafficaccessTopImg = (ImageView) view.findViewById(R.id.trafficaccess_top_img);
        mTrafficaccessTopPeople = (TextView) view.findViewById(R.id.trafficaccess_top_people);
        mTrafficpeople1 = (TextView) view.findViewById(R.id.trafficpeople1);
        mOrderTraffic = (TextView) view.findViewById(R.id.order_traffic);
        mTrafficorderBlowImg = (ImageView) view.findViewById(R.id.trafficorder_blow_img);
        mTrafficorderBlowPeople = (TextView) view.findViewById(R.id.trafficorder_blow_people);
        mTrafficpeople2 = (TextView) view.findViewById(R.id.trafficpeople2);
        mTrafficCountAvg = (TextView) view.findViewById(R.id.traffic_count_avg);
        mTrafficCountImg = (ImageView) view.findViewById(R.id.traffic_count_img);
        mTrafficCount = (TextView) view.findViewById(R.id.traffic_count);
        mAccessCustomer = (TextView) view.findViewById(R.id.access_customer);
        mAccessTopImg = (ImageView) view.findViewById(R.id.access_top_img);
        mAccessTopPeople = (TextView) view.findViewById(R.id.access_top_people);
        mPeople1 = (TextView) view.findViewById(R.id.people1);
        mOrderCustomer = (TextView) view.findViewById(R.id.order_customer);
        mOrderBlowImg = (ImageView) view.findViewById(R.id.order_blow_img);
        mOrderBlowPeople = (TextView) view.findViewById(R.id.order_blow_people);
        mPeople2 = (TextView) view.findViewById(R.id.people2);
        mCountAvg = (TextView) view.findViewById(R.id.count_avg);
        mCountImg = (ImageView) view.findViewById(R.id.count_img);
        mCount = (TextView) view.findViewById(R.id.count);
    }

    public void setData(){
        mAccessTraffic.setText("303");
        //mTrafficaccessTopImg.setImageResource(R.mipmap.top);
        mAccessTopPeople.setText("18");
        mOrderTraffic.setText("42");
        mTrafficorderBlowPeople.setText("8");
        num1=Integer.parseInt(mOrderTraffic.getText().toString().trim());
        num2=Integer.parseInt(mAccessTraffic.getText().toString().trim());
       sum=( num1/(double)num2)*100;
        mTrafficCountAvg.setText(""+String.format("%.2f",sum)+"%");
        mTrafficCount.setText("3.68%");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.click:
                intent= new Intent(getActivity(), Customer_analysis.class);
                intent.putExtra("type",0);
                startActivity(intent);
                break;
        }
    }
}
