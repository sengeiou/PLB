package com.example.administrator.plb.activity.operating_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TextView shop_name;
    private TextView shop_address;
    private TextView shop_master;
    private TextView shop_registered_time;
    private TextView license_startTime;
    private TextView license_endTime;
    private Button btn_update;
    private ImageView back;

    private List<ImageView> list=new ArrayList<>();
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<ImageView>();
        for (int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(-1, -1);
            imageView.setLayoutParams(lp);
            this.list.add(imageView);
        }
        List<Integer>integerList=new ArrayList<>();
        integerList.add(R.drawable.license);
        integerList.add(R.drawable.shop);
        adapter=new MyPagerAdapter(this,list,integerList);
        viewPager.setAdapter(adapter);
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        shop_name = (TextView) findViewById(R.id.shop_name);
        shop_address = (TextView) findViewById(R.id.shop_address);
        shop_master = (TextView) findViewById(R.id.shop_master);
        shop_registered_time = (TextView) findViewById(R.id.shop_registered_time);
        license_startTime = (TextView) findViewById(R.id.license_startTime);
        license_endTime = (TextView) findViewById(R.id.license_endTime);
        btn_update = (Button) findViewById(R.id.btn_update);
        back = findViewById(R.id.back);

        btn_update.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
