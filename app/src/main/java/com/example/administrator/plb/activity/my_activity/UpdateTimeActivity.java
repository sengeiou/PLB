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
import com.example.administrator.plb.until.HttpUtil;

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
            if (msg.what == msgWhat){

            }
        }
    };
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_time);
        initView();
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
        String[] checkeds = intent.getStringArrayExtra("checked");
        if (checkeds != null){
            StringBuffer str = new StringBuffer();
            for (int i=0;i<checkeds.length;i++){
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
        }
    }



    private void showDialog(){

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

                //sendHttp();
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
                endDialog.setTitle("选择开始时间");
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
                finish();
                break;
        }
    }
}
