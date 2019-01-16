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
 * 近三十天
 */
public class customer_frag3 extends Fragment {
    private View view;
    public Context mContext;
    /**
     * 2017.12.04-12.10
     */
    private TextView mCustomerTime3;
    /**
     * 188
     */
    private TextView mNumberofOrders3;
    private ImageView mNumberofOrdersImg3;
    /**
     * 55
     */
    private TextView mNumberofOrdersPeople3;
    /**
     * 人
     */
    private TextView mNumbersOrders3;
    private LineChart mCustomerLineChart3;
    /**
     * 310
     */
    private TextView mNewPeople3;
    private ImageView mNewPeopleImg3;
    /**
     * 59
     */
    private TextView mNewChangePeople3;
    /**
     * 人
     */
    private TextView mNewcustomer13;
    /**
     * 占58.51%
     */
    private TextView mPercentageNew3;
    private ProgressBar mNewcustomProgressbar3;
    /**
     * 178
     */
    private TextView mOldPeople3;
    private ImageView mOldPeopleImg3;
    /**
     * 56
     */
    private TextView mOldChangePeople3;
    /**
     * 人
     */
    private TextView mOldcustomer13;
    /**
     * 占41.49%
     */
    private TextView mPercentageOld3;
    private ProgressBar mOldcustomProgressbar3;
    private double sum1,sum2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_customer_frag3, container, false);
        initView(view);
        setChart();
        setData();
        setmProgressbar();
        return view;
    }

    private void initView(View view) {
        mCustomerTime3 = (TextView) view.findViewById(R.id.customer_time3);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        mCustomerTime3.setText(format.format(System.currentTimeMillis())+"."+getOldDate(-30)+"—"+getOldDate(0));
        mNumberofOrders3 = (TextView) view.findViewById(R.id.numberof_orders3);
        mNumberofOrdersImg3 = (ImageView) view.findViewById(R.id.numberof_orders_img3);
        mNumberofOrdersPeople3 = (TextView) view.findViewById(R.id.numberof_orders_people3);
        mNumbersOrders3 = (TextView) view.findViewById(R.id.numbers_orders3);
        mCustomerLineChart3 = (LineChart) view.findViewById(R.id.customer_lineChart3);
        mNewPeople3 = (TextView) view.findViewById(R.id.new_people3);
        mNewPeopleImg3 = (ImageView) view.findViewById(R.id.new_people_img3);
        mNewChangePeople3 = (TextView) view.findViewById(R.id.new_change_people3);
        mNewcustomer13 = (TextView) view.findViewById(R.id.newcustomer13);
        mPercentageNew3 = (TextView) view.findViewById(R.id.percentage_new3);
        mNewcustomProgressbar3 = (ProgressBar) view.findViewById(R.id.newcustom_progressbar3);
        mOldPeople3 = (TextView) view.findViewById(R.id.old_people3);
        mOldPeopleImg3 = (ImageView) view.findViewById(R.id.old_people_img3);
        mOldChangePeople3 = (TextView) view.findViewById(R.id.old_change_people3);
        mOldcustomer13 = (TextView) view.findViewById(R.id.oldcustomer13);
        mPercentageOld3 = (TextView) view.findViewById(R.id.percentage_old3);
        mOldcustomProgressbar3 = (ProgressBar) view.findViewById(R.id.oldcustom_progressbar3);
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
        Log.e("近30天==","" + dft.format(endDate));
        return dft.format(endDate);
    }

    public void setData() {
        int counts;//下单人数
        int num1 = Integer.parseInt(mNewPeople3.getText() + "");
        int num2 = Integer.parseInt(mOldPeople3.getText() + "");
        counts = num1 + num2;
        sum1 = (num1/(double)counts)*100;
        String.format("%.2f", sum1);
        sum2 = (num2/(double)counts)*100;
        String.format("%.2f", sum2);
        Log.e("num",""+num1+"===>"+num2);
        Log.e("counts",""+counts);
        Log.e("sum",String.format("%.2f", sum1)+"--------------"+String.format("%.2f", (num1/(double)counts)*100));
        mNumberofOrders3.setText(counts + "");
        mPercentageNew3.setText("占"+String.format("%.2f", sum1)+"%");
        mPercentageOld3.setText("占"+String.format("%.2f", sum2)+"%");
        int count1;//mNumberofOrdersPeople
        int num3 = Integer.parseInt(mNewChangePeople3.getText().toString().trim());
        int num4 = Integer.parseInt(mOldChangePeople3.getText().toString().trim());
        count1 = num3 + num4;
        mNumberofOrdersPeople3.setText(count1 + "");
        //mCustomerTime
    }

    public void setmProgressbar() {
        mNewcustomProgressbar3.setProgress((int) sum1);
        mOldcustomProgressbar3.setProgress((int) sum2);
    }

    public void setChart() {
        mCustomerLineChart3.setDrawBorders(false);//边界线
        // mCustomerLineChart.setBackgroundColor(Color.LTGRAY);
        //mCustomerLineChart.setDrawGridBackground(true);
        XAxis xAxis=mCustomerLineChart3.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis=mCustomerLineChart3.getAxisLeft();
        YAxis rightAxis=mCustomerLineChart3.getAxisRight();

        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);

        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 80));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "顾客");
        LineData data = new LineData(lineDataSet);
        mCustomerLineChart3.setData(data);
    }


}
