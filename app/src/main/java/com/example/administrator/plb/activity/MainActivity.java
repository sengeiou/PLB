package com.example.administrator.plb.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.FragmentAdapter;
import com.example.administrator.plb.fragment.MyFragment;
import com.example.administrator.plb.fragment.OperatingFragment;
import com.example.administrator.plb.fragment.OrderFragment;
import com.example.administrator.plb.fragment.OrderManageFragment;
import com.example.administrator.plb.until.GDLocation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ViewPager viewPager;
    private BottomNavigationView bottomView;
    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        /**
         * 定位
         */
        //location();
    }

    private void location() {
        GDLocation gdLocation = new GDLocation(this);
        gdLocation.getLocation();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomView = (BottomNavigationView) findViewById(R.id.bottomView);
    }

    private void initData() {
        List<Fragment>list=new ArrayList<>();
        list.add(new OrderFragment());
        list.add(new OrderManageFragment());
        list.add(new OperatingFragment());
        list.add(new MyFragment());
        adapter=new FragmentAdapter(getSupportFragmentManager(),list,null);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(listener);
        bottomView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.inform:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.contacts:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.video:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.action:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
        }
    };


    private ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    bottomView.setSelectedItemId(R.id.inform);
                    break;
                case 1:
                    bottomView.setSelectedItemId(R.id.contacts);
                    break;
                case 2:
                    bottomView.setSelectedItemId(R.id.video);
                    break;
                case 3:
                    bottomView.setSelectedItemId(R.id.action);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

}
