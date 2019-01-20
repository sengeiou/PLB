package com.example.administrator.plb.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.FragmentAdapter;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.fragment.MyFragment;
import com.example.administrator.plb.fragment.OperatingFragment;
import com.example.administrator.plb.fragment.OrderFragment;
import com.example.administrator.plb.fragment.OrderManageFragment;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.GDLocation;
import com.example.administrator.plb.until.HttpUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ViewPager viewPager;
    private BottomNavigationView bottomView;
    private FragmentAdapter adapter;
    private LinearLayout linearLayout;
    private String today;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHttpData();
        initView();
        initData();
        /**
         * 定位
         */
        //location();

    }

    private void initHttpData() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        today  = sdf.format(ca.getTime());
        ca.setTime(ca.getTime()); //设置时间为当前时间
        ca.add(Calendar.DATE, 1); //天数减1
        String tomorrow= sdf.format(ca.getTime());
        UserInformBean userInformBean = new Gson().fromJson(CacheUntil.getString(this, "infoJson", ""), UserInformBean.class);
        new HttpUtil("http://39.98.68.40:8080/RetailManager/getOrderByTime?" +
                "storeId="+userInformBean.getStore().getStoreId()+
                "&startTime="+today+
                "&endTime="+tomorrow,handler,0).openConn();
    }

    private void location() {
        GDLocation gdLocation = new GDLocation(this);
        gdLocation.getLocation();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomView = (BottomNavigationView) findViewById(R.id.bottomView);
        linearLayout = findViewById(R.id.prb_login);
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

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            linearLayout.setVisibility(View.GONE);
            switch (msg.what){
                case -1:
                    Toast.makeText(MainActivity.this, "请求失败，请检查网络", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    String result=msg.obj.toString();
                    if(result.indexOf("address")!=-1){
                        CacheUntil.putString(MainActivity.this,today,result);
                        EventBus.getDefault().post(result);
                    }
                    break;
            }
        }
    };




}
