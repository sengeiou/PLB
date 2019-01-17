package com.example.administrator.plb.activity.operating_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.fragment.operating_fragment.customer.customer_frag1;
import com.example.administrator.plb.fragment.operating_fragment.customer.customer_frag2;
import com.example.administrator.plb.fragment.operating_fragment.customer.customer_frag3;

/**
 * 客户分析
 */
public class Customer_analysis extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private ImageView mClose2;
    private customer_frag1 f1, f2, f3;
    private FragmentManager manager;
    /**
     * 今天
     */
    private TextView mCustomer1;
    /**
     * 近7天
     */
    private TextView mCustomer2;
    /**
     * 近30天
     */
    private TextView mCustomer3;
    private FrameLayout mCustomerFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_analysis);
        initView();
        manager=getSupportFragmentManager();
        mCustomer1.performClick();
        initData();
    }

    private void initView() {
        mClose2 = (ImageView) findViewById(R.id.close2);
        mClose2.setOnClickListener(this);
        mCustomer1 = (TextView) findViewById(R.id.customer1);
        mCustomer1.setOnClickListener(this);
        mCustomer2 = (TextView) findViewById(R.id.customer2);
        mCustomer2.setOnClickListener(this);
        mCustomer3 = (TextView) findViewById(R.id.customer3);
        mCustomer3.setOnClickListener(this);
        mCustomerFrag = (FrameLayout) findViewById(R.id.customer_frag);

    }
    private void selected(){
        mCustomer1.setSelected(false);
        mCustomer1.setBackgroundResource(R.drawable.status_bg2);
        mCustomer1.setTextColor(getResources().getColor(R.color.data));
        mCustomer2.setSelected(false);
        mCustomer2.setBackgroundResource(R.drawable.status_bg2);
        mCustomer2.setTextColor(getResources().getColor(R.color.data));
        mCustomer3.setSelected(false);
        mCustomer3.setBackgroundResource(R.drawable.status_bg2);
        mCustomer3.setTextColor(getResources().getColor(R.color.data));
    }

    private void sethindAll(FragmentTransaction fragmentTransaction){
        if(f1!=null)fragmentTransaction.show(f1);
        if(f2!=null)fragmentTransaction.show(f2);
        if(f3!=null)fragmentTransaction.show(f3);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction Transaction=manager.beginTransaction();
        sethindAll(Transaction);
        switch (v.getId()) {
            default:
                break;
            case R.id.close2:
                Customer_analysis.this.finish();
                break;
            case R.id.customer1:
                selected();
                mCustomer1.setSelected(true);
                mCustomer1.setBackgroundResource(R.drawable.status_bg1);
                mCustomer1.setTextColor(Color.BLACK);
                if(f1==null){
                    customer_frag1 mCustomer1=new customer_frag1();
                    mCustomer1.mContext=mContext;
                    Transaction.replace(R.id.customer_frag,mCustomer1);
                }else{
                    Transaction.show(f1);
                }
                break;
            case R.id.customer2:
                selected();
                mCustomer2.setSelected(true);
                mCustomer2.setBackgroundResource(R.drawable.status_bg1);
                mCustomer2.setTextColor(Color.BLACK);
                if(f2==null){
                    customer_frag2 mCustomer2=new customer_frag2();
                    mCustomer2.mContext=mContext;
                    Transaction.replace(R.id.customer_frag,mCustomer2);
                }else{
                    Transaction.show(f2);
                }
                break;
            case R.id.customer3:
                selected();
                mCustomer3.setSelected(true);
                mCustomer3.setBackgroundResource(R.drawable.status_bg1);
                mCustomer3.setTextColor(Color.BLACK);
                if(f3==null){
                    customer_frag3 mCustomer3=new customer_frag3();
                    mCustomer3.mContext=mContext;
                    Transaction.replace(R.id.customer_frag,mCustomer3);
                }else{
                    Transaction.show(f3);
                }
                break;
        }
        Transaction.commit();
    }

    private void initData() {
        Intent intent=getIntent();
        int item= intent.getIntExtra("type",0);
        if(item==0){
            mCustomer1.performClick();
        }else {
            mCustomer2.performClick();
        }
    }

}
