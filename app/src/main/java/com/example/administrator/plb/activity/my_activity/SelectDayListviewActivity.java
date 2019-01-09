package com.example.administrator.plb.activity.my_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.SelectDayAdapter;
import com.example.administrator.plb.entity.SelectDay;

import java.util.ArrayList;
import java.util.List;

public class SelectDayListviewActivity extends Activity {
    private ListView mLlistView;
    private ImageView myBack;
    private List<SelectDay> sdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day_listview);

        initView();
        initData();
    }

    private void initData() {
        SelectDay sd1 = new SelectDay("星期一",false);
        sdList.add(sd1);

        SelectDay sd2 = new SelectDay("星期二",false);
        sdList.add(sd2);

        SelectDay sd3 = new SelectDay("星期三",false);
        sdList.add(sd3);

        SelectDay sd4 = new SelectDay("星期四",false);
        sdList.add(sd4);

        SelectDay sd5 = new SelectDay("星期五",false);
        sdList.add(sd5);

        SelectDay sd6 = new SelectDay("星期六",false);
        sdList.add(sd6);

        SelectDay sd7 = new SelectDay("星期天",false);
        sdList.add(sd7);

        SelectDayAdapter adapter = new SelectDayAdapter(this,sdList);
        mLlistView.setAdapter(adapter);


    }

    private void initView() {
        mLlistView = findViewById(R.id.my_ListView);
        myBack = findViewById(R.id.my_back);
        myBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
