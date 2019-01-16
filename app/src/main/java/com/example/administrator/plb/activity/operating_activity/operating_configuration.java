package com.example.administrator.plb.activity.operating_activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.activity.operating_activity.configuration_fragment.Created_Activities;
import com.example.administrator.plb.activity.operating_activity.configuration_fragment.New_Activities;
import com.example.administrator.plb.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlj on 2019.1.16.
 */
public class operating_configuration extends AppCompatActivity implements View.OnClickListener{
    private TabLayout mTab;
    private ViewPager mViewPager;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_configuration);

        init();
        initData();
    }

    private void init() {
        mTab = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.mViewPager);
        back = findViewById(R.id.img_back);
        back.setOnClickListener(this);
    }


    private void initData() {
        List<String> titil = new ArrayList<>();
        titil.add("新建活动");
        titil.add("已创建活动");
        List<Fragment> fragments = new ArrayList<>();
        Fragment f1 = new New_Activities();
        Fragment f2 = new Created_Activities();
        fragments.add(f1);
        fragments.add(f2);
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments,titil));
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
