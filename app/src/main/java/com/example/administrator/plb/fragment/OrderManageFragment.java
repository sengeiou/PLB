package com.example.administrator.plb.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.FragmentAdapter;
import com.example.administrator.plb.entity.OrderBean;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.fragment.orderManage_fragment.CancelFragment;
import com.example.administrator.plb.fragment.orderManage_fragment.CompletedFragment;
import com.example.administrator.plb.fragment.orderManage_fragment.OnGoingFragment;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.HttpUtil;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OrderManageFragment extends Fragment implements View.OnClickListener{

    private TextView mTitle,time;
    private Toolbar mToolbar;
    private TabLayout mTab;
    private ViewPager mViewPager;
    private LinearLayout prb_login;
    private UserInformBean userInformBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manager,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mToolbar=view.findViewById(R.id.toolbar);
        mTab=view.findViewById(R.id.tab);
        mViewPager=view.findViewById(R.id.viewPager);
        time=view.findViewById(R.id.time);
        prb_login=view.findViewById(R.id.prb_login);
        initView();
    }

    private void initView() {

        //从缓存中拿出用户信息
        userInformBean = new Gson().fromJson(CacheUntil.getString(getActivity(), "infoJson", ""), UserInformBean.class);

        //设置当前时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        time.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));
        time.setOnClickListener(this);

        //添加ViewPager和Fragment
        List<Fragment> fragments=new ArrayList<>();
        List<String> titles=new ArrayList<>();
        fragments.add(new OnGoingFragment());//进行中
        fragments.add(new CompletedFragment());//已完成
        fragments.add(new CancelFragment());//已取消

        titles.add("进行中");
        titles.add("已完成");
        titles.add("已取消");
        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments,titles));
        mTab.setupWithViewPager(mViewPager);

        //在页面初始化时请求更新数据
        RequestOrderList(simpleDateFormat.format(Calendar.getInstance().getTime()));
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        switch (v.getId()) {
            case R.id.time:
                //点击时间TextVIew时，弹出日期选择对话框，选择后重新访问数据
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        String startTime = year + "-" + (month + 1) + "-" + dayOfMonth;
                        RequestOrderList(startTime);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
        }
    }

    private void RequestOrderList(String startTime) {
        //获取数据的缓存信息，如果为空，则向服务器丁秋数据，否则，将数据使用EventBus发送信息到子Fragment进行数据更新

        String string = CacheUntil.getString(getActivity(), startTime, "");

        if(TextUtils.isEmpty(string)){
            String[] strings=startTime.split("-");
            Calendar instance = Calendar.getInstance();
            instance.set(
                    Integer.parseInt(strings[0]),
                    Integer.parseInt(strings[1]),
                    Integer.parseInt(strings[2]));
            instance.add(Calendar.DATE,1);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String endTime=sdf.format(instance.getTime());
            new HttpUtil("http://39.98.68.40:8080/RetailManager/getOrderByTime?" +
                    "storeId="+userInformBean.getStore().getStoreId()+
                    "&startTime=" + startTime +
                    "&endTime=" + endTime + "",
                    handler,0 ).openConn();
            prb_login.setVisibility(View.VISIBLE);
        }else{
            EventBus.getDefault().post(string);
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            prb_login.setVisibility(View.GONE);
            switch (msg.what){
                case 0:
                    String result=msg.obj.toString();
                    //接收到数据后，以时间为key，将结果存入本地，如果存在，将会覆盖
                    if(result.indexOf("result")!=-1){
                        CacheUntil.putString(getActivity(),time.getText().toString(),result);
                        EventBus.getDefault().post(result);
                    }
                    break;
            }
        }
    };

}
