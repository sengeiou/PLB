package com.example.administrator.plb.activity.operating_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.plb.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 财务对账
 */
public class operating_financial extends Activity implements View.OnClickListener{

    private ImageView close1;
    private TextView tvFinancialMoney;
    private TextView tvFinancialTixian;
    private LinearLayout llFinancialUser;
    private LinearLayout llFinancialPredictMoney;
    private TextView tvFinancialTime;
    private TextView tvFinancialPredictMoney;
    private TextView tvFinancialOrderName;
    private TextView tvFinancialOrderNum;
    private TextView tvFinancialOrderMoney;
    private TextView tvFinancialOldMinTime;
    private TextView tvFinancialOldMaxTime;
    private TextView tvFinancialOldMoney;
    private TextView tvFinancialOldTime3;
    private TextView tvFinancialOldMoney3;
    private TextView tvFinancialOldTime2;
    private TextView tvFinancialOldMoney2;
    private TextView tvFinancialOldTime1;
    private TextView tvFinancialOldMoney1;
    private Date today;
    private SimpleDateFormat formatter;
    private String strToDay;
    private Date yesterday;
    private String strYesterday;
    private Date lastDay;
    private String strLastDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_financial);
        initView();
        setDate();

    }

    /**
     * 初始化文本的时间
     */
    private void setDate() {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        today = new Date(System.currentTimeMillis());
        strToDay = formatter.format(today);
        /**
         * 昨天
         */
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(today); //设置时间为当前时间
        ca.add(Calendar.DATE, -1); //天数减1
        yesterday = ca.getTime();
        strYesterday = formatter.format(yesterday);
        /**
         * 前天
         */
        ca.setTime(today);
        ca.add(Calendar.DATE, -2);
        lastDay = ca.getTime();
        strLastDay =  formatter.format(lastDay);


        /**
         * 预计收入设置当前时间
         */
        tvFinancialTime.setText(strToDay);
        /**
         * 设置历史账单时间
         */
        tvFinancialOldMaxTime.setText(strToDay);
        tvFinancialOldMinTime.setText(strLastDay);
        tvFinancialOldTime3.setText(strToDay);
        tvFinancialOldTime2.setText(strYesterday);
        tvFinancialOldTime1.setText(strLastDay);
    }

    private void initView() {
        close1 = (ImageView) findViewById(R.id.close1);
        tvFinancialMoney = (TextView) findViewById(R.id.tv_financial_money);
        tvFinancialTixian = (TextView) findViewById(R.id.tv_financial_tixian);
        tvFinancialTixian.setOnClickListener(this);
        llFinancialPredictMoney = (LinearLayout) findViewById(R.id.ll_financial_predict_money);
        llFinancialPredictMoney.setOnClickListener(this);
        llFinancialUser = (LinearLayout) findViewById(R.id.ll_financial_user);

        tvFinancialTime = (TextView) findViewById(R.id.tv_financial_time);

        tvFinancialPredictMoney = (TextView) findViewById(R.id.tv_financial_predict_money);
        tvFinancialOrderName = (TextView) findViewById(R.id.tv_financial_order_name);
        tvFinancialOrderNum = (TextView) findViewById(R.id.tv_financial_order_num);
        tvFinancialOrderMoney = (TextView) findViewById(R.id.tv_financial_order_money);
        tvFinancialOldMinTime = (TextView) findViewById(R.id.tv_financial_old_min_time);
        tvFinancialOldMaxTime = (TextView) findViewById(R.id.tv_financial_old_max_time);
        tvFinancialOldMoney = (TextView) findViewById(R.id.tv_financial_old_money);
        tvFinancialOldTime3 = (TextView) findViewById(R.id.tv_financial_old_time3);
        tvFinancialOldMoney3 = (TextView) findViewById(R.id.tv_financial_old_money3);
        tvFinancialOldTime2 = (TextView) findViewById(R.id.tv_financial_old_time2);
        tvFinancialOldMoney2 = (TextView) findViewById(R.id.tv_financial_old_money2);
        tvFinancialOldTime1 = (TextView) findViewById(R.id.tv_financial_old_time1);
        tvFinancialOldMoney1 = (TextView) findViewById(R.id.tv_financial_old_money1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_financial_user:
                Intent intent = new Intent(operating_financial.this,DepositActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ll_financial_predict_money:
                intent = new Intent(operating_financial.this,DayMoneyActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
