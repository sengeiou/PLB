package com.example.administrator.plb.fragment.operating.operating_data_activity;

import android.content.Context;
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
import com.example.administrator.plb.fragment.operating.operating_data_fragment.traffic.traffic_frag1;
import com.example.administrator.plb.fragment.operating.operating_data_fragment.traffic.traffic_frag2;
import com.example.administrator.plb.fragment.operating.operating_data_fragment.traffic.traffic_frag3;
/*
 * create by csy 1/2
 * 流量分析
 * */

public class Traffic_analysis extends AppCompatActivity implements View.OnClickListener {

    private ImageView mClose3;
    /**
     * 昨天
     */
    private TextView mTraffic1;
    /**
     * 近7天
     */
    private TextView mTraffic2;
    /**
     * 近30天
     */
    private TextView mTraffic3;
    private FrameLayout mTrafficFrag;
    private FragmentManager manager;
    private traffic_frag1 f1,f2,f3;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_analysis);
        initView();
        manager=getSupportFragmentManager();
        mTraffic1.performClick();
    }

    private void initView() {
        mClose3 = (ImageView) findViewById(R.id.close3);
        mClose3.setOnClickListener(this);
        mTraffic1 = (TextView) findViewById(R.id.traffic1);
        mTraffic1.setOnClickListener(this);
        mTraffic2 = (TextView) findViewById(R.id.traffic2);
        mTraffic2.setOnClickListener(this);
        mTraffic3 = (TextView) findViewById(R.id.traffic3);
        mTraffic3.setOnClickListener(this);
        mTrafficFrag = (FrameLayout) findViewById(R.id.traffic_frag);
    }

    private void selected(){
        mTraffic1.setSelected(false);
        mTraffic1.setBackgroundResource(R.drawable.status_bg2);
        mTraffic1.setTextColor(getResources().getColor(R.color.data));
        mTraffic2.setSelected(false);
        mTraffic2.setBackgroundResource(R.drawable.status_bg2);
        mTraffic2.setTextColor(getResources().getColor(R.color.data));
        mTraffic3.setSelected(false);
        mTraffic3.setBackgroundResource(R.drawable.status_bg2);
        mTraffic3.setTextColor(getResources().getColor(R.color.data));
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
            case R.id.close3:
                Traffic_analysis.this.finish();
                break;
            case R.id.traffic1:
                selected();
                mTraffic1.setSelected(true);
                mTraffic1.setBackgroundResource(R.drawable.status_bg1);
                mTraffic1.setTextColor(Color.BLACK);
                if(f1==null){
                    traffic_frag1 mTraffic1=new traffic_frag1();
                    mTraffic1.mContext=mContext;
                    Transaction.replace(R.id.traffic_frag,mTraffic1);
                }else{
                    Transaction.show(f1);
                }
                break;
            case R.id.traffic2:
                selected();
                mTraffic2.setSelected(true);
                mTraffic2.setBackgroundResource(R.drawable.status_bg1);
                mTraffic2.setTextColor(Color.BLACK);
                if(f2==null){
                    traffic_frag2 mTraffic2=new traffic_frag2();
                    mTraffic2.mContext=mContext;
                    Transaction.replace(R.id.traffic_frag,mTraffic2);
                }else{
                    Transaction.show(f2);
                }
                break;
            case R.id.traffic3:
                selected();
                mTraffic3.setSelected(true);
                mTraffic3.setBackgroundResource(R.drawable.status_bg1);
                mTraffic3.setTextColor(Color.BLACK);
                if(f3==null){
                    traffic_frag3 mTraffic3=new traffic_frag3();
                    mTraffic3.mContext=mContext;
                    Transaction.replace(R.id.traffic_frag,mTraffic3);
                }else{
                    Transaction.show(f3);
                }
                break;
        }
        Transaction.commit();
    }


}
