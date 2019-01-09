package com.example.administrator.plb.activity.my_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.plb.R;

public class UpdateTimeActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView select_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_time);
        initView();
    }

    private void initView() {
        select_day = findViewById(R.id.select_day);
        select_day.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_day:
                Intent intent = new Intent(UpdateTimeActivity.this,SelectDayListviewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
