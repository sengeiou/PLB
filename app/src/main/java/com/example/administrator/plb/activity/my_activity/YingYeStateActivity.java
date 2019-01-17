package com.example.administrator.plb.activity.my_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.HttpUtil;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YingYeStateActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回
     */
    private ImageView ivYyBack;
    /**
     * 修改营业时间
     */
    private TextView tvYyAlterTime;

    /**
     * 营业状态图片
     */
    private ImageView ivYyState;
    /**
     * 营业状态显示文字
     */
    private TextView tvYyState;

    /**
     * 营业时间
     */
    private TextView tvYyTime;
    /**
     * 营业时间说明
     */
    private TextView tvYyTimeSm;
    /**
     * 修改营业状态
     */
    private Button btnYyState;
    /**
     * 修改营业状态说明
     */
    private TextView tvYyBtnSm;

    /**
     * 记录营业状态 1正在营业，0停止营业,2 未到营业时间
     */
    private int state = 0;
    private LinearLayout llYyTime;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                dialog.dismiss();
                Log.e("sssss", msg.obj.toString());
                updateState();
            }
            if (msg.what == 2){
                String string = msg.obj.toString();
                CacheUntil.putString(getApplicationContext(), "infoJson", string);
                state = new Gson().fromJson(string, UserInformBean.class).getStore().getState();
                Intent intent = new Intent(YingYeStateActivity.this,YingYeStateActivity.class);
                startActivity(intent);
                finish();
            }
            if (msg.what == 3){
                if (msg.obj != null){
                    String string = msg.obj.toString();
                    UserInformBean.StoreBean store = new Gson().fromJson(string, UserInformBean.class).getStore();
                    if (state == 1){
                        /**
                         * 获取系统时间
                         */
                        Date time = Calendar.getInstance().getTime();
                        String f = YingYeStateActivity.this.format.format(time);

                        strOpenTime = store.getOpenTime();
                        strCloseTime = store.getCloseTime();

                        int tuesday = store.getTuesday();
                        int wednesday = store.getWednesday();
                        int thursday = store.getThursday();
                        int friday = store.getFriday();
                        int sunday = store.getSunday();
                        int saturday = store.getSaturday();
                        try {
                            Date openTime = YingYeStateActivity.this.format.parse(strOpenTime);
                            Date closeTime = YingYeStateActivity.this.format.parse(strCloseTime);
                            Date thisTime = format.parse(f);
                            if ((openTime.getTime() - thisTime.getTime())<=0 && (closeTime.getTime() - thisTime.getTime())>=0){
                                startStateSetting();
                            }else {
                                state = 2;
                                notTimeStateSetting();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };
    private AlertDialog dialog;
    private UserInformBean.StoreBean store;
    private UserInformBean userInformBean;
    private String userName;
    private String pwd;
    private String strOpenTime;
    private String strCloseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying_ye_state);
        initView();
        initShowState();
        initData();

    }

    private void initShowState() {
        String infoJson = CacheUntil.getString(this, "infoJson", "");
        userName = CacheUntil.getString(this, "userName", "");
        pwd = CacheUntil.getString(this, "pwd", "");
        if (!TextUtils.isEmpty(infoJson)){
            userInformBean = new Gson().fromJson(infoJson, UserInformBean.class);
            store = userInformBean.getStore();
            state = store.getState();
            Log.e("state", state+"");
            if (this.state ==2){
                notTimeStateSetting();
            }else if (this.state ==1){
                startStateSetting();
            }else if (this.state ==0){
                stopStateSetting();
            }
        }
    }

    private void initData() {
        String url = "http://39.98.68.40:8080/RetailManager/login.do?username="+userName+"&password="+pwd+"&roleId="+2;
        HttpUtil httpUtil = new HttpUtil(url,handler,3);
        httpUtil.openConn();
    }

    private void initView() {
        ivYyBack = (ImageView) findViewById(R.id.iv_yy_back);
        tvYyAlterTime = (TextView) findViewById(R.id.tv_yy_alter_time);
        ivYyState = (ImageView) findViewById(R.id.iv_yy_state);
        tvYyState = (TextView) findViewById(R.id.tv_yy_state);
        llYyTime = (LinearLayout) findViewById(R.id.ll_yy_time);
        tvYyTime = (TextView) findViewById(R.id.tv_yy_time);
        tvYyTimeSm = (TextView) findViewById(R.id.tv_yy_time_sm);
        btnYyState = (Button) findViewById(R.id.btn_yy_state);
        tvYyBtnSm = (TextView) findViewById(R.id.tv_yy_btn_sm);

        ivYyBack.setOnClickListener(this);
        btnYyState.setOnClickListener(this);
        tvYyAlterTime.setOnClickListener(this);
    }

    /**
     * 未到营业时间效果设置
     */
    private void notTimeStateSetting(){
        ivYyState.setImageResource(R.drawable.state1);
        tvYyState.setText("待开始营业");
        llYyTime.setVisibility(View.VISIBLE);
        tvYyTime.setText(strOpenTime+"-"+strCloseTime);
        tvYyTimeSm.setText("当前店铺处于设置的营业时间外，且设置了不接受预订单");
        btnYyState.setText("停止营业");
        btnYyState.setTextColor(Color.RED);
        btnYyState.setBackgroundResource(R.drawable.btn_bg_red);
        tvYyBtnSm.setText("当您希望长时间不再接收订单时，请点击上方按钮停止营业，开启后需要手动恢复");
    }

    /**
     * 停止营业效果显示
     */
    private void stopStateSetting(){
        ivYyState.setImageResource(R.drawable.state2);
        tvYyState.setText("已停止营业");
        llYyTime.setVisibility(View.GONE);
        tvYyTimeSm.setText("当前店铺停止提供服务，不接受任何订单，手动恢复营业后可正常接收新订单");
        btnYyState.setText("恢复营业");
        btnYyState.setTextColor(Color.GREEN);
        btnYyState.setBackgroundResource(R.drawable.btn_bg_gree);
        tvYyBtnSm.setText("当您希望接收新订单时，请点击上方按钮恢复营业");
    }

    /**
     * 正在营业效果显示
     */
    private void startStateSetting(){
        ivYyState.setImageResource(R.drawable.state3);
        tvYyState.setText("正在营业");
        llYyTime.setVisibility(View.GONE);
        tvYyTimeSm.setText("当前店铺处于设置的营业时间内，可以接收新订单");
        btnYyState.setText("停止营业");
        btnYyState.setTextColor(Color.RED);
        btnYyState.setBackgroundResource(R.drawable.btn_bg_red);
        tvYyBtnSm.setText("当您希望长时间不再接收订单时，请点击上方按钮停止营业，开启后需要手动恢复");
    }
    private void showDialog(){
        dialog = new AlertDialog.Builder(this)
                .setView(LayoutInflater.from(this).inflate(R.layout.dialog_update_yy_state, null)).create();
        dialog.show();
        sendInfo();

    }
    private void sendInfo(){
        int sendState = 1;
        if (state==1){
            sendState = 0;
        }else if (state==0){
            initData();
        }
        String url="http://39.98.68.40:8080/RetailManager/updateShopHours?state="+sendState+"&storeId="+store.getStoreId();
        HttpUtil httpUtil = new HttpUtil(url,handler,1);
        httpUtil.openConn();
    }
    private void updateState(){
        String url = "http://39.98.68.40:8080/RetailManager/login.do?username="+userName+"&password="+pwd+"&roleId="+2;
        HttpUtil httpUtil = new HttpUtil(url,handler,2);
        httpUtil.openConn();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_yy_back:
                finish();
                break;
            case R.id.btn_yy_state:
                showDialog();
                break;
            case R.id.tv_yy_alter_time:
                Intent intent = new Intent(YingYeStateActivity.this,UpdateTimeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
