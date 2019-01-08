package com.example.administrator.plb.activity.operating_activity;

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
import com.example.administrator.plb.fragment.operating_fragment.business.business_frag1;
import com.example.administrator.plb.fragment.operating_fragment.business.business_frag2;
import com.example.administrator.plb.fragment.operating_fragment.business.business_frag3;
import com.example.administrator.plb.fragment.operating_fragment.business.business_frag4;
/*
 * create by csy 1/2
 * 营业统计
 * */
public class Business_statistics extends AppCompatActivity implements View.OnClickListener {
    private Context mcontext;
    private ImageView mClose1;
    /**
     * 今天
     */
    private TextView mToday;
    /**
     * 近7天
     */
    private TextView mToday2;
    /**
     * 近30天
     */
    private TextView mToday3;
    /**
     * 自定义
     */
    private TextView mToday4;
    private FragmentManager manager;
    private FrameLayout mBusinessFrag;
    private business_frag1 f1,f2,f3,f4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_statistics);
        initView();
        manager=getSupportFragmentManager();
        mToday.performClick();

    }

    private void initView() {
        mClose1 = (ImageView) findViewById(R.id.close1);
        mClose1.setOnClickListener(this);
        mToday = (TextView) findViewById(R.id.today);
        mToday.setOnClickListener(this);
        mToday2 = (TextView) findViewById(R.id.today2);
        mToday2.setOnClickListener(this);
        mToday3 = (TextView) findViewById(R.id.today3);
        mToday3.setOnClickListener(this);
        mToday4 = (TextView) findViewById(R.id.today4);
        mToday4.setOnClickListener(this);
        mBusinessFrag = (FrameLayout) findViewById(R.id.business_frag);
    }

    private void selected(){
        mToday.setSelected(false);
        mToday.setBackgroundResource(R.drawable.status_bg2);
        mToday.setTextColor(getResources().getColor(R.color.data));
        mToday2.setSelected(false);
        mToday2.setBackgroundResource(R.drawable.status_bg2);
        mToday2.setTextColor(getResources().getColor(R.color.data));
        mToday3.setSelected(false);
        mToday3.setBackgroundResource(R.drawable.status_bg2);
        mToday3.setTextColor(getResources().getColor(R.color.data));
        mToday4.setSelected(false);
        mToday4.setBackgroundResource(R.drawable.status_bg2);
        mToday4.setTextColor(getResources().getColor(R.color.data));

    }

    private void sethindAll(FragmentTransaction fragmentTransaction){
        if(f1!=null)fragmentTransaction.show(f1);
        if(f2!=null)fragmentTransaction.show(f2);
        if(f3!=null)fragmentTransaction.show(f3);
        if(f4!=null)fragmentTransaction.show(f4);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction Transactions=manager.beginTransaction();
        sethindAll(Transactions);
        switch (v.getId()) {
            default:
                break;
            case R.id.close1:
                Business_statistics.this.finish();
                break;
            case R.id.today:
                selected();
                mToday.setSelected(true);
                mToday.setBackgroundResource(R.drawable.status_bg1);
                mToday.setTextColor(Color.BLACK);
                if(f1==null){
                    business_frag1 mToday=new business_frag1();
                    mToday.context=mcontext;
                    Transactions.replace(R.id.business_frag,mToday);

                }else{
                    Transactions.show(f1);
                }
                break;
            case R.id.today2:
                selected();
                mToday2.setSelected(true);
                mToday2.setBackgroundResource(R.drawable.status_bg1);
                mToday2.setTextColor(Color.parseColor("#101010"));
                if(f2==null){
                    business_frag2 mToday2=new business_frag2();
                    mToday2.context=mcontext;
                    Transactions.replace(R.id.business_frag,mToday2);

                }else{
                    Transactions.show(f2);
                }
                break;
            case R.id.today3:
                selected();
                mToday3.setSelected(true);
                mToday3.setBackgroundResource(R.drawable.status_bg1);
                mToday3.setTextColor(getResources().getColor(R.color.black));
                if(f3==null){
                    business_frag3 mToday3=new business_frag3();
                    mToday3.context=mcontext;
                    Transactions.replace(R.id.business_frag,mToday3);

                }else{
                    Transactions.show(f3);
                }
                break;
            case R.id.today4:
                selected();
                mToday4.setSelected(true);
                mToday4.setBackgroundResource(R.drawable.status_bg1);
                mToday4.setTextColor(getResources().getColor(R.color.black));
                if(f4==null){
                    business_frag4 mToday4=new business_frag4();
                    mToday4.context=mcontext;
                    Transactions.replace(R.id.business_frag,mToday4);
                }else{
                    Transactions.show(f4);
                }
                break;
        }
        Transactions.commit();
    }
}
