package com.example.administrator.plb.activity.my_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.MendianAdapter;
import com.example.administrator.plb.entity.MenDianSetting;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.CacheUntil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MendianSettingActivity extends Activity implements View.OnClickListener{
    private ListView mListView;
    private List<MenDianSetting> mdList = new ArrayList<>();
    private ImageView back;

    private int myState;
    private String myPhone;
    private String myAddress;
    private String myTine;
    private String strState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mendian_setting);
        initView();
        initGson();
        initMenDian();
        initData();
    }

    private void initGson() {
        String infoJson = CacheUntil.getString(getApplication(),"infoJson","");
        UserInformBean informBean = new Gson().fromJson(infoJson,UserInformBean.class);
        UserInformBean.UserInfoBean infoBean = informBean.getUserInfo();

        myState = informBean.getStore().getState();
        myPhone = infoBean.getPhone();
        myAddress = infoBean.getAddress();

    }

    private void initMenDian() {
            if(myState == 0){
                strState = "停止营业";
            }else{
                strState = "正在营业";
            }
            MenDianSetting md1 = new MenDianSetting("营业状态", strState, 0);
            mdList.add(md1);
            MenDianSetting md2 = new MenDianSetting("餐厅公告", "", 0);
            mdList.add(md2);
            MenDianSetting md3 = new MenDianSetting("店铺头像", "", R.mipmap.ic_launcher);
            mdList.add(md3);
            MenDianSetting md4 = new MenDianSetting("餐厅电话", myPhone, 0);
            mdList.add(md4);
            MenDianSetting md5 = new MenDianSetting("餐厅地址", myAddress, 0);
            mdList.add(md5);
            MenDianSetting md6 = new MenDianSetting("营业时间", "10:00 - 23:00", 0);
            mdList.add(md6);
            MenDianSetting md7 = new MenDianSetting("营业物资", "", 0);
            mdList.add(md7);
            MenDianSetting md8 = new MenDianSetting("配送信息", "", 0);
            mdList.add(md8);
    }

    private void initData() {
        MendianAdapter adapter = new MendianAdapter(this, mdList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MendianSettingActivity.this, YingYeStateActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        mListView = findViewById(R.id.mListView);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
