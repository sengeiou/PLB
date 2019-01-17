package com.example.administrator.plb.activity.my_activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class UpdateTimeActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView select_day;
    private int msgWhat = 1;
    private Button btnUpdateTime;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private ImageView back;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    private Date startTime,endTime;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2){
                if (msg.obj!=null){
                    infoJson = msg.obj.toString();
                    initView();
                    CacheUntil.putString(getApplicationContext(), "infoJson", infoJson);
                }
            }
            if (msg.what == msgWhat){
                if (msg.obj!=null){
                    Log.e("rel", msg.obj.toString());
                    try {
                        String string = new JSONObject(msg.obj.toString()).getString("result");
                        if (string.equals("OK")){
                            initData();
                            Intent intent = new Intent(UpdateTimeActivity.this,YingYeStateActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(UpdateTimeActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
    private List<String> data = new ArrayList<>();
    private String[] checkeds;
    private UserInformBean.StoreBean store;
    private int[] day = new int[7];;
    private String infoJson;
    private String userName;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_time);
        initData();

    }

    private void initData() {
        String infoJson = CacheUntil.getString(this, "infoJson", "");
        userName = CacheUntil.getString(this, "userName", "");
        pwd = CacheUntil.getString(this, "pwd", "");
        String url = "http://39.98.68.40:8080/RetailManager/login.do?username="+ userName +"&password="+ pwd +"&roleId="+2;
        HttpUtil httpUtil = new HttpUtil(url,handler,2);
        httpUtil.openConn();
    }

    private void initView() {
        select_day = findViewById(R.id.select_day);
        select_day.setOnClickListener(this);
        btnUpdateTime = findViewById(R.id.btn_update_time);
        btnUpdateTime.setOnClickListener(this);
        tvStartTime = findViewById(R.id.tv_update_time_start);
        tvStartTime.setOnClickListener(this);
        tvEndTime = findViewById(R.id.tv_update_time_end);
        tvEndTime.setOnClickListener(this);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        Intent intent = getIntent();
        checkeds = intent.getStringArrayExtra("checked");
        store = new Gson().fromJson(infoJson, UserInformBean.class).getStore();
        if (checkeds != null){
            StringBuffer str = new StringBuffer();
            for (int i = 0; i< checkeds.length; i++){
                if (!TextUtils.isEmpty(checkeds[i])){
                    str.append(checkeds[i]+" ");
                    switch (checkeds[i]){
                        case "周一":
                            data.add("monday");
                            break;
                        case "周二":
                            data.add("tuesday");
                            break;
                        case "周三":
                            data.add("wednesday");
                            break;
                        case "周四":
                            data.add("thursday");
                            break;
                        case "周五":
                            data.add("friday");
                            break;
                        case "周六":
                            data.add("saturday");
                            break;
                        case "周日":
                            data.add("sunday");
                            break;
                    }
                }else {
                    Log.e("null", "null");
                }
            }
            if (data.size() == 7){
                select_day.setText("每日");
            }else if (data.size()==0){
                select_day.setText("每日");
            }else {
                select_day.setText(str);
            }
        }else {
            checkeds = new String[7];
            if (infoJson !=null){
                int monday = store.getMonday();
                Log.e("monday", monday+"");
                int tuesday = store.getTuesday();
                int wednesday = store.getWednesday();
                int thursday = store.getThursday();
                int friday = store.getFriday();
                int sunday = store.getSunday();
                int saturday = store.getSaturday();

                if (monday==1){
                    checkeds[0] = "周一";
                }else {
                    checkeds[0] = "";
                }
                if (tuesday==1){
                    checkeds[1] = "周二";
                }else {
                    checkeds[1] = "";
                }
                if (wednesday==1){
                    checkeds[2] = "周三";
                }else {
                    checkeds[2] = "";
                }
                if (thursday==1){
                    checkeds[3] = "周四";
                }else {
                    checkeds[3] = "";
                }
                if (friday==1){
                    checkeds[4] = "周五";
                }else {
                    checkeds[4] = "";
                }
                if (sunday==1){
                    checkeds[5] = "周六";
                }else {
                    checkeds[5] = "";
                }
                if (saturday==1){
                    checkeds[6] = "周日";
                }else {
                    checkeds[6] = "";
                }
                StringBuffer str = new StringBuffer();
                for (int i = 0; i< checkeds.length; i++){
                    if (!TextUtils.isEmpty(checkeds[i])){
                        str.append(checkeds[i]+" ");
                        switch (checkeds[i]){
                            case "周一":
                                data.add("monday");
                                break;
                            case "周二":
                                data.add("tuesday");
                                break;
                            case "周三":
                                data.add("wednesday");
                                break;
                            case "周四":
                                data.add("thursday");
                                break;
                            case "周五":
                                data.add("friday");
                                break;
                            case "周六":
                                data.add("saturday");
                                break;
                            case "周日":
                                data.add("sunday");
                                break;
                        }
                    }else {
                        Log.e("null", "null");
                    }
                }
                if (data.size() == 7){
                    select_day.setText("每日");
                }else if (data.size()==0){
                    select_day.setText("每日");
                }else {
                    select_day.setText(str);
                }
                try {
                    startTime = format.parse(store.getOpenTime());
                    endTime = format.parse(store.getCloseTime());
                    tvStartTime.setText(format.format(startTime));
                    tvEndTime.setText(format.format(endTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                Log.e("json", "josn为空");
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_day:
                Intent intent = new Intent(UpdateTimeActivity.this,SelectDayListviewActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_update_time:
                sendHttp();
                break;
            case R.id.tv_update_time_start:
                DatePickDialog startDialog = new DatePickDialog(this);
                //设置上下年分限制
                startDialog.setYearLimt(5);
                //设置标题
                startDialog.setTitle("选择开始时间");
                //设置类型
                startDialog.setType(DateType.TYPE_HM);
                //设置消息体的显示格式，日期格式
                startDialog.setMessageFormat("HH:mm");
                //设置选择回调
                startDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                startDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        startTime = date;
                        tvStartTime.setText(format.format(startTime));
                    }
                });
                startDialog.show();
                break;
            case R.id.tv_update_time_end:
                DatePickDialog endDialog = new DatePickDialog(this);
                //设置上下年分限制
                endDialog.setYearLimt(5);
                //设置标题
                endDialog.setTitle("选择结束时间");
                //设置类型
                endDialog.setType(DateType.TYPE_HM);
                //设置消息体的显示格式，日期格式
                endDialog.setMessageFormat("HH:mm");
                //设置选择回调
                endDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                endDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        endTime = date;
                        if (endTime.getTime() - startTime.getTime()<=0){
                            Toast.makeText(UpdateTimeActivity.this, "结束时间不能小于开始时间", Toast.LENGTH_SHORT).show();
                            tvEndTime.setText("00:00");
                        }else {
                            tvEndTime.setText(format.format(endTime));
                        }

                    }
                });
                endDialog.show();
                break;
            case R.id.back:
                intent = new Intent(UpdateTimeActivity.this,YingYeStateActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void sendHttp() {
        for (int i=0;i<day.length;i++){
            if (TextUtils.isEmpty(checkeds[i])){
                day[i] = 0;
            }else {
                day[i] = 1;
            }
        }
        String url = "http://39.98.68.40:8080/RetailManager/updateShopHours?" +
                "monday="+ day[0]+
                "&tuesday="+ day[1]+
                "&wednesday="+ day[2]+
                "&thursday="+ day[3]+
                "&friday="+ day[4]+
                "&sunday="+ day[5]+
                "&saturday="+ day[6]+
                "&openTime="+ tvStartTime.getText()+
                "&closeTime="+tvEndTime.getText()+
                "&storeId="+store.getStoreId();
        Log.e("url", url);
       new HttpUtil(url,handler,msgWhat).openConn();
    }
}
