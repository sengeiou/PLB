package com.example.administrator.plb.fragment.operating_fragment.customer;

import android.content.Context;
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
 * 近7天
 */
public class customer_frag2 extends Fragment {
    private View view;
    public Context mContext;
    /**
     * 2017.12.04-12.10
     */
    private TextView mCustomerTime2;
    /**
     * 188
     */
    private TextView mNumberofOrders2;
    private ImageView mNumberofOrdersImg2;
    /**
     * 55
     */
    private TextView mNumberofOrdersPeople2;
    /**
     * 人
     */
    private TextView mNumbersOrders2;
    private LineChart mCustomerLineChart2;
    /**
     * 110
     */
    private TextView mNewPeople2;
    private ImageView mNewPeopleImg2;
    /**
     * 29
     */
    private TextView mNewChangePeople2;
    /**
     * 人
     */
    private TextView mNewcustomer12;
    /**
     * 占58.51%
     */
    private TextView mPercentageNew2;
    private ProgressBar mNewcustomProgressbar2;
    /**
     * 78
     */
    private TextView mOldPeople2;
    private ImageView mOldPeopleImg2;
    /**
     * 26
     */
    private TextView mOldChangePeople2;
    /**
     * 人
     */
    private TextView mOldcustomer12;
    /**
     * 占41.49%
     */
    private TextView mPercentageOld2;
    private ProgressBar mOldcustomProgressbar2;
    private double sum1, sum2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_customer_frag2, container, false);
        initView(view);
        setChart();
        setData();
        setmProgressbar();
        return view;
    }

    private void initView(View view) {
        mCustomerTime2 = (TextView) view.findViewById(R.id.customer_time2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        mCustomerTime2.setText(format.format(System.currentTimeMillis())+"."+getOldDate(-7)+"—"+getOldDate(0));
        mNumberofOrders2 = (TextView) view.findViewById(R.id.numberof_orders2);
        mNumberofOrdersImg2 = (ImageView) view.findViewById(R.id.numberof_orders_img2);
        mNumberofOrdersPeople2 = (TextView) view.findViewById(R.id.numberof_orders_people2);
        mNumbersOrders2 = (TextView) view.findViewById(R.id.numbers_orders2);
        mCustomerLineChart2 = (LineChart) view.findViewById(R.id.customer_lineChart2);
        mNewPeople2 = (TextView) view.findViewById(R.id.new_people2);
        mNewPeopleImg2 = (ImageView) view.findViewById(R.id.new_people_img2);
        mNewChangePeople2 = (TextView) view.findViewById(R.id.new_change_people2);
        mNewcustomer12 = (TextView) view.findViewById(R.id.newcustomer12);
        mPercentageNew2 = (TextView) view.findViewById(R.id.percentage_new2);
        mNewcustomProgressbar2 = (ProgressBar) view.findViewById(R.id.newcustom_progressbar2);
        mOldPeople2 = (TextView) view.findViewById(R.id.old_people2);
        mOldPeopleImg2 = (ImageView) view.findViewById(R.id.old_people_img2);
        mOldChangePeople2 = (TextView) view.findViewById(R.id.old_change_people2);
        mOldcustomer12 = (TextView) view.findViewById(R.id.oldcustomer12);
        mPercentageOld2 = (TextView) view.findViewById(R.id.percentage_old2);
        mOldcustomProgressbar2 = (ProgressBar) view.findViewById(R.id.oldcustom_progressbar2);
    }

    /**
     * 获取前n天日期、后n天日期
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
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

    public void setData() {
        int counts;//下单人数
        int num1 = Integer.parseInt(mNewPeople2.getText() + "");
        int num2 = Integer.parseInt(mOldPeople2.getText() + "");
        counts = num1 + num2;
        sum1 = (num1/(double)counts)*100;
        String.format("%.2f", sum1);
        sum2 = (num2/(double)counts)*100;
        String.format("%.2f", sum2);
        Log.e("num",""+num1+"===>"+num2);
        Log.e("counts",""+counts);
        Log.e("sum",String.format("%.2f", sum1)+"--------------"+String.format("%.2f", (num1/(double)counts)*100));
        mNumberofOrders2.setText(counts + "");
        mPercentageNew2.setText("占"+String.format("%.2f", sum1)+"%");
        mPercentageOld2.setText("占"+String.format("%.2f", sum2)+"%");
        int count1;//mNumberofOrdersPeople
        int num3 = Integer.parseInt(mNewChangePeople2.getText().toString().trim());
        int num4 = Integer.parseInt(mOldChangePeople2.getText().toString().trim());
        count1 = num3 + num4;
        mNumberofOrdersPeople2.setText(count1 + "");
        //mCustomerTime
    }

    public void setmProgressbar() {
        mNewcustomProgressbar2.setProgress((int) sum1);
        mOldcustomProgressbar2.setProgress((int) sum2);
    }

    public void setChart() {
        mCustomerLineChart2.setDrawBorders(false);//边界线
        // mCustomerLineChart.setBackgroundColor(Color.LTGRAY);
        //mCustomerLineChart.setDrawGridBackground(true);
        XAxis xAxis=mCustomerLineChart2.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis=mCustomerLineChart2.getAxisLeft();
        YAxis rightAxis=mCustomerLineChart2.getAxisRight();

        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);

        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 80));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "顾客");
        LineData data = new LineData(lineDataSet);
        mCustomerLineChart2.setData(data);
    }
}
