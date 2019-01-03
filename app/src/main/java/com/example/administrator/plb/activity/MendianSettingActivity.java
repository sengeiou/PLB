package com.example.administrator.plb.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.MendianAdapter;
import com.example.administrator.plb.entity.MenDianSetting;

import java.util.ArrayList;
import java.util.List;

public class MendianSettingActivity extends Activity {
    private ListView mListView;
    private List<MenDianSetting> mdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mendian_setting);
        initMenDian();
        initView();
        initData();
    }

    private void initMenDian() {
        for (int i=0;i<1;i++){
            MenDianSetting md1 = new MenDianSetting("营业状态","待开始营业",0);
            mdList.add(md1);
            MenDianSetting md2 = new MenDianSetting("餐厅公告","",0);
            mdList.add(md2);
            MenDianSetting md3 = new MenDianSetting("店铺头像","",R.mipmap.ic_launcher);
            mdList.add(md3);
            MenDianSetting md4 = new MenDianSetting("餐厅电话","183-6335-5515",0);
            mdList.add(md4);
            MenDianSetting md5 = new MenDianSetting("餐厅地址","中新路",0);
            mdList.add(md5);
            MenDianSetting md6 = new MenDianSetting("营业时间","10:00 - 23:00",0);
            mdList.add(md6);
            MenDianSetting md7 = new MenDianSetting("营业物资","",0);
            mdList.add(md7);
            MenDianSetting md8 = new MenDianSetting("配送信息","",0);
            mdList.add(md8);

        }
    }

    private void initData() {
        MendianAdapter adapter = new MendianAdapter(this,mdList);
        mListView.setAdapter(adapter);
    }

    private void initView() {
        mListView = findViewById(R.id.mListView);
    }
}
