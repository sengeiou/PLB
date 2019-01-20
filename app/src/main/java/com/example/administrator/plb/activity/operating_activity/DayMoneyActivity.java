package com.example.administrator.plb.activity.operating_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.OrderFragmentAdapter;
import com.example.administrator.plb.entity.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class DayMoneyActivity extends Activity implements View.OnClickListener {

    private ImageView close1;
    private ListView mlist;
    private List<OrderBean> list;
    private OrderFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_money);
        initView();
    }

    private void initView() {
        close1 = (ImageView) findViewById(R.id.close1);
        close1.setOnClickListener(this);
        mlist = (ListView) findViewById(R.id.list);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close1:
                Intent intent = new Intent(DayMoneyActivity.this, operating_financial.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(DayMoneyActivity.this, operating_financial.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
