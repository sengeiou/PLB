package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.plb.R;
import com.example.administrator.plb.fragment.operating_fragment.businessdata.Businessdaily_frg;
import com.example.administrator.plb.fragment.operating_fragment.businessdata.Businessdata_frg;


/*
* create by csy 1/2
*经营数据
*
* */
public class operating_data extends AppCompatActivity implements View.OnClickListener {

    private ImageView mQuit;
    private LinearLayout mBusinessStatistics;
    private LinearLayout mCustomerAnalysis;
    private LinearLayout mTrafficAnalysis;
    private LinearLayout mProductAnalysis;
    private LinearLayout mMerchantsExperience;
    private TabLayout mTablayout;
    private ViewPager mTabViewpager;
    private Fragment[] mFragmentArrays = new Fragment[2];
    private String[] mTabTitles = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_data);
        initView();
    }

    private void initView() {
        mQuit = (ImageView) findViewById(R.id.close1);
        mQuit.setOnClickListener(this);
        mBusinessStatistics = (LinearLayout) findViewById(R.id.Business_statistics);
        mBusinessStatistics.setOnClickListener(this);
        mCustomerAnalysis = (LinearLayout) findViewById(R.id.Customer_analysis);
        mCustomerAnalysis.setOnClickListener(this);
        mTrafficAnalysis = (LinearLayout) findViewById(R.id.Traffic_analysis);
        mTrafficAnalysis.setOnClickListener(this);
        mProductAnalysis = (LinearLayout) findViewById(R.id.Product_analysis);
        mProductAnalysis.setOnClickListener(this);
        mMerchantsExperience = (LinearLayout) findViewById(R.id.Merchants_experience);
        mMerchantsExperience.setOnClickListener(this);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mTabViewpager = (ViewPager) findViewById(R.id.tab_viewpager);
        mTabTitles[0] = "昨日 经营日报";
        mTabTitles[1] = "近7日 经营数据";
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mFragmentArrays[0] =new Businessdaily_frg();
        //mFragmentArrays[1] = TabFragment.newInstance();
        mFragmentArrays[1] = new Businessdata_frg();
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mTabViewpager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        mTablayout.setupWithViewPager(mTabViewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.close1:
                operating_data.this.finish();
                //startActivity(new Intent(operating_data.this, MainActivity.class));
                break;
            case R.id.Business_statistics:
                /*营业统计*/
                startActivity(new Intent(operating_data.this, Business_statistics.class));
                break;
            case R.id.Customer_analysis:
                /*顾客分析*/
                startActivity(new Intent(operating_data.this, Customer_analysis.class));
                break;
            case R.id.Traffic_analysis:
                /*流量分析*/
                startActivity(new Intent(operating_data.this, Traffic_analysis.class));
                break;
            case R.id.Product_analysis:
                /*商品分析*/
                startActivity(new Intent(operating_data.this, Product_analysis.class));
                break;
            case R.id.Merchants_experience:
                /*商家体验*/
                startActivity(new Intent(operating_data.this, Merchants_experience.class));
                break;
        }
    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }

}
