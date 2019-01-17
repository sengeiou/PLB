package com.example.administrator.plb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.activity.LoginActivity;
import com.example.administrator.plb.activity.my_activity.LingShengSettingActivity;
import com.example.administrator.plb.activity.my_activity.MendianSettingActivity;
import com.example.administrator.plb.activity.my_activity.YingYeStateActivity;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.CacheUntil;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyFragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout mdSetting;
    private ImageView ivMyHead;
    private TextView tvMyName;
    private TextView tvMyYlmd;
    private TextView tvMyState;
    private LinearLayout llMyState;
    private LinearLayout llMyDdsetting;
    private LinearLayout llMyLssetting;
    private LinearLayout llMyYj;
    private TextView tvMyZh;
    private LinearLayout llMyZh;
    private TextView tvMyGx;
    private LinearLayout llMyGx;
    private TextView tvMyLogout;
    private Toolbar toolbar;

    private String myName;
    private int myState;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    private String strOpenTime;
    private String strCloseTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        setHasOptionsMenu(true);
        initView();
        initData();
        return view;
    }

    private void initData() {
        String infoJson = CacheUntil.getString(getActivity(), "infoJson", "");
        Log.e("infoJson", infoJson);
        UserInformBean userInformBean = new Gson().fromJson(infoJson, UserInformBean.class);
        UserInformBean.UserInfoBean userInfo = userInformBean.getUserInfo();


        Log.e("-------name-------", userInfo.getName());
        String myName = userInfo.getName();
        tvMyName.setText(myName);
        tvMyZh.setText(myName);


        myState = userInformBean.getStore().getState();
        Log.e("--------state--------", ""+myState);
        if(myState == 0){
            tvMyState.setText("停止营业");
        }else{
            /**
             * 获取系统时间
             */
            Date time = Calendar.getInstance().getTime();
            String f = format.format(time);
            strOpenTime = userInformBean.getStore().getOpenTime();
            strCloseTime = userInformBean.getStore().getCloseTime();
            try {
                Date openTime = format.parse(strOpenTime);
                Date closeTime = format.parse(strCloseTime);
                Date thisTime = format.parse(f);
                if ((openTime.getTime() - thisTime.getTime())<=0 && (closeTime.getTime() - thisTime.getTime())>=0){
                    tvMyState.setText("正在营业");
                }else {
                    tvMyState.setText("未到营业时间");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void initView() {

        toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mdSetting = (LinearLayout) view.findViewById(R.id.md_setting);
        mdSetting.setOnClickListener(this);

        ivMyHead = (ImageView) view.findViewById(R.id.iv_my_head);
        ivMyHead.setOnClickListener(this);

        tvMyName = (TextView) view.findViewById(R.id.tv_my_name);
        tvMyName.setOnClickListener(this);

        tvMyYlmd = (TextView) view.findViewById(R.id.tv_my_ylmd);
        tvMyYlmd.setOnClickListener(this);

        tvMyState = (TextView) view.findViewById(R.id.tv_my_state);

        llMyState = (LinearLayout) view.findViewById(R.id.ll_my_state);
        llMyState.setOnClickListener(this);

        llMyDdsetting = (LinearLayout) view.findViewById(R.id.ll_my_ddsetting);
        llMyDdsetting.setOnClickListener(this);

        llMyLssetting = (LinearLayout) view.findViewById(R.id.ll_my_lssetting);
        llMyLssetting.setOnClickListener(this);

        llMyYj = (LinearLayout) view.findViewById(R.id.ll_my_yj);
        llMyYj.setOnClickListener(this);

        tvMyZh = (TextView) view.findViewById(R.id.tv_my_zh);
        tvMyZh.setOnClickListener(this);

        llMyZh = (LinearLayout) view.findViewById(R.id.ll_my_zh);
        llMyZh.setOnClickListener(this);

        tvMyGx = (TextView) view.findViewById(R.id.tv_my_gx);
        tvMyGx.setOnClickListener(this);

        llMyGx = (LinearLayout) view.findViewById(R.id.ll_my_gx);
        llMyGx.setOnClickListener(this);

        tvMyLogout = (TextView) view.findViewById(R.id.tv_my_logout);
        tvMyLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.md_setting:
                Intent intent = new Intent(getActivity(), MendianSettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_my_lssetting:
                intent = new Intent(getActivity(), LingShengSettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_my_state:
                intent = new Intent(getActivity(), YingYeStateActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_my_logout:

                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.user,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cancel:
                CacheUntil.putString(getContext(),"infoJson","");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
