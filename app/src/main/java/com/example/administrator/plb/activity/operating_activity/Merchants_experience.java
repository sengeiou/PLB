package com.example.administrator.plb.activity.operating_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.plb.R;

public class Merchants_experience extends AppCompatActivity implements View.OnClickListener {

    private ImageView mClose5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchants_experience);
        initView();
    }

    private void initView() {
        mClose5 = (ImageView) findViewById(R.id.close1);
        mClose5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.close1:
                Merchants_experience.this.finish();
                break;
        }
    }
}
