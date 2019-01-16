package com.example.administrator.plb.fragment.operating_fragment.customer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create by csy
 * 顾客分析
 * 昨天
 */
public class customer_frag1 extends Fragment {
    private View view;
    public Context mContext;
    /**
     * 2017.12.04-12.10
     */
    private TextView mCustomerTime;
    /**
     * 188
     */
    private TextView mNumberofOrders;
    private ImageView mNumberofOrdersImg;
    /**
     * 55
     */
    private TextView mNumberofOrdersPeople;
    /**
     * 人
     */
    private TextView mNumbersOrders;
    private LineChart mCustomerLineChart;
    /**
     * 110
     */
    private TextView mNewPeople;
    private ImageView mNewPeopleImg;
    /**
     * 29
     */
    private TextView mNewChangePeople;
    /**
     * 人
     */
    private TextView mNewcustomer1;
    private ProgressBar mNewcustomProgressbar;
    /**
     * 78
     */
    private TextView mOldPeople;
    private ImageView mOldPeopleImg;
    /**
     * 26
     */
    private TextView mOldChangePeople;
    /**
     * 人
     */
    private TextView mOldcustomer1;
    private ProgressBar mOldcustomProgressbar;
    private double sum1, sum2;
    /**
     * 占58.51%
     */
    private TextView mPercentageNew;
    /**
     * 占41.49%
     */
    private TextView mPercentageOld;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_customer_frag1, container, false);
        initView(view);
        setChart();
        setData();
        setmProgressbar();
        return view;
    }

    private void initView(View view) {
        mCustomerTime = (TextView) view.findViewById(R.id.customer_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        mCustomerTime.setText(format.format(System.currentTimeMillis())+"");
        mNumberofOrders = (TextView) view.findViewById(R.id.numberof_orders);
        mNumberofOrdersImg = (ImageView) view.findViewById(R.id.numberof_orders_img);
        mNumberofOrdersPeople = (TextView) view.findViewById(R.id.numberof_orders_people);
        mNumbersOrders = (TextView) view.findViewById(R.id.numbers_orders);
        mCustomerLineChart = (LineChart) view.findViewById(R.id.customer_lineChart);
        mNewPeople = (TextView) view.findViewById(R.id.new_people);
        mNewPeopleImg = (ImageView) view.findViewById(R.id.new_people_img);
        mNewChangePeople = (TextView) view.findViewById(R.id.new_change_people);
        mNewcustomer1 = (TextView) view.findViewById(R.id.newcustomer1);
        mNewcustomProgressbar = (ProgressBar) view.findViewById(R.id.newcustom_progressbar);
        mOldPeople = (TextView) view.findViewById(R.id.old_people);
        mOldPeopleImg = (ImageView) view.findViewById(R.id.old_people_img);
        mOldChangePeople = (TextView) view.findViewById(R.id.old_change_people);
        mOldcustomer1 = (TextView) view.findViewById(R.id.oldcustomer1);
        mOldcustomProgressbar = (ProgressBar) view.findViewById(R.id.oldcustom_progressbar);
        mPercentageNew = (TextView) view.findViewById(R.id.percentage_new);
        mPercentageOld = (TextView) view.findViewById(R.id.percentage_old);
    }

    public void setData() {
        int counts;//下单人数
        int num1 = Integer.parseInt(mNewPeople.getText() + "");
        int num2 = Integer.parseInt(mOldPeople.getText() + "");
        counts = num1 + num2;
        sum1 = (num1/(double)counts)*100;
        String.format("%.2f", sum1);
        sum2 = (num2/(double)counts)*100;
        String.format("%.2f", sum2);
        Log.e("num",""+num1+"===>"+num2);
        Log.e("counts",""+counts);
        Log.e("sum",String.format("%.2f", sum1)+"--------------"+String.format("%.2f", (num1/(double)counts)*100));
        mNumberofOrders.setText(counts + "");
        mPercentageNew.setText("占"+String.format("%.2f", sum1)+"%");
        mPercentageOld.setText("占"+String.format("%.2f", sum2)+"%");
        int count1;//mNumberofOrdersPeople
        int num3 = Integer.parseInt(mNewChangePeople.getText().toString().trim());
        int num4 = Integer.parseInt(mOldChangePeople.getText().toString().trim());
        count1 = num3 + num4;
        mNumberofOrdersPeople.setText(count1 + "");
        //mCustomerTime
    }

    public void setmProgressbar() {
        mNewcustomProgressbar.setProgress((int) sum1);
        mOldcustomProgressbar.setProgress((int) sum2);
    }

    public void setChart() {
        mCustomerLineChart.setDrawBorders(false);//边界线
       // mCustomerLineChart.setBackgroundColor(Color.LTGRAY);
        //mCustomerLineChart.setDrawGridBackground(true);
        XAxis xAxis=mCustomerLineChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis=mCustomerLineChart.getAxisLeft();
        YAxis rightAxis=mCustomerLineChart.getAxisRight();

        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);

        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 80));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "顾客");
        LineData data = new LineData(lineDataSet);
        mCustomerLineChart.setData(data);
    }

}
