package com.example.administrator.plb.fragment.operating.operating_activity;

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

import com.example.administrator.plb.R;
import com.example.administrator.plb.fragment.operating.operating_fragment.product.product_frag1;
import com.example.administrator.plb.fragment.operating.operating_fragment.product.product_frag2;


public class Product_analysis extends AppCompatActivity implements View.OnClickListener {

    private ImageView mClose4;
    private TabLayout mTablayoutPro;
    private ViewPager mProViewpager;
    private Fragment[] mFragmentArrays = new Fragment[2];
    private String[] mTabTitles = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_analysis);
        initView();
    }

    private void initView() {
        mClose4 = (ImageView) findViewById(R.id.close4);
        mClose4.setOnClickListener(this);

        mTablayoutPro = (TabLayout) findViewById(R.id.tablayout_pro);
        mProViewpager = (ViewPager) findViewById(R.id.pro_viewpager);

        mTabTitles[0] = "商品销售";
        mTabTitles[1] = "商圈热销";
        mTablayoutPro.setTabMode(TabLayout.MODE_FIXED);
        mFragmentArrays[0] =new product_frag1();
        //mFragmentArrays[1] = TabFragment.newInstance();
        mFragmentArrays[1] = new product_frag2();
        PagerAdapter pagerAdapter = new Product_analysis.MyViewPagerAdapter(getSupportFragmentManager());
        mProViewpager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        mTablayoutPro.setupWithViewPager(mProViewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.close4:
                Product_analysis.this.finish();
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
