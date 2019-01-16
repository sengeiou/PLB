package com.example.administrator.plb.activity.my_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.SelectDay;

import java.util.ArrayList;
import java.util.List;

public class SelectDayListviewActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
    private ImageView myBack;
    private TextView tvSelectDay;
    private CheckBox cbSelectDay1;
    private CheckBox cbSelectDay2;
    private CheckBox cbSelectDay3;
    private CheckBox cbSelectDay4;
    private CheckBox cbSelectDay5;
    private CheckBox cbSelectDay6;
    private CheckBox cbSelectDay7;
    private CheckBox cbSelectDayAll;

    private List<CheckBox> listCheckBox = new ArrayList<>();
    private String[] checked = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day_listview);

        initView();

    }


    private void initView() {
        myBack = findViewById(R.id.my_back);
        myBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(SelectDayListviewActivity.this,UpdateTimeActivity.class);
                startActivity(intent);
            }
        });
        tvSelectDay = findViewById(R.id.tv_select_day);
        tvSelectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNum();
                Intent intent = new Intent(SelectDayListviewActivity.this,UpdateTimeActivity.class);
                intent.putExtra("checked", checked);
                startActivity(intent);
                finish();
            }
        });
        cbSelectDay1 = (CheckBox) findViewById(R.id.cb_select_day_1);
        cbSelectDay2 = (CheckBox) findViewById(R.id.cb_select_day_2);
        cbSelectDay3 = (CheckBox) findViewById(R.id.cb_select_day_3);
        cbSelectDay4 = (CheckBox) findViewById(R.id.cb_select_day_4);
        cbSelectDay5 = (CheckBox) findViewById(R.id.cb_select_day_5);
        cbSelectDay6 = (CheckBox) findViewById(R.id.cb_select_day_6);
        cbSelectDay7 = (CheckBox) findViewById(R.id.cb_select_day_7);
        /**
         * 将所有CheckBox添加到list集合
         */
        listCheckBox.add(cbSelectDay1);
        listCheckBox.add(cbSelectDay2);
        listCheckBox.add(cbSelectDay3);
        listCheckBox.add(cbSelectDay4);
        listCheckBox.add(cbSelectDay5);
        listCheckBox.add(cbSelectDay6);
        listCheckBox.add(cbSelectDay7);
        /**
         * 设置所有CheckBox监听
         */
        for (int i=0;i<listCheckBox.size();i++){
            listCheckBox.get(i).setOnCheckedChangeListener(this);
        }
        /**
         * 全选的CheckBox监听
         */
        cbSelectDayAll = (CheckBox) findViewById(R.id.cb_select_day_all);
        cbSelectDayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSelectDayAll.isChecked()){
                    for (int i=0;i<listCheckBox.size();i++){
                        listCheckBox.get(i).setChecked(true);
                    }
                }else {
                    for (int i=0;i<listCheckBox.size();i++){
                        listCheckBox.get(i).setChecked(false);
                    }
                }
            }
        });
    }

    private void checkNum() {
        Log.e("cbSelectDay1", cbSelectDay1.isChecked()+"");
        if (cbSelectDay1.isChecked()){
            checked[0] = "周一";
        }else {
            checked[0] = "";
        }
        if (cbSelectDay2.isChecked()){
            checked[1] = "周二";
        }else {
            checked[1] = "";
        }
        if (cbSelectDay3.isChecked()){
            checked[2] = "周三";
        }else {
            checked[2] = "";
        }
        if (cbSelectDay4.isChecked()){
            checked[3] = "周四";
        }else {
            checked[3] = "";
        }
        if (cbSelectDay5.isChecked()){
            checked[4] = "周五";
        }else {
            checked[4] = "";
        }
        if (cbSelectDay6.isChecked()){
            checked[5] = "周六";
        }else {
            checked[5] = "";
        }
        if (cbSelectDay7.isChecked()){
            checked[6] = "周日";
        }else {
            checked[6] = "";
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked){
            cbSelectDayAll.setChecked(false);
        }
    }
}
