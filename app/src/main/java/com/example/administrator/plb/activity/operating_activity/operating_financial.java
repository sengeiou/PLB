package com.example.administrator.plb.activity.operating_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;

/**
 * 财务对账
 */
public class operating_financial extends AppCompatActivity {

    private ImageView close1;
    private TextView tvFinancialMoney;
    private TextView tvFinancialTixian;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_financial);
        initView();
    }

    private void initView() {
        close1 = (ImageView) findViewById(R.id.close1);
        tvFinancialMoney = (TextView) findViewById(R.id.tv_financial_money);
        tvFinancialTixian = (TextView) findViewById(R.id.tv_financial_tixian);
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
}
