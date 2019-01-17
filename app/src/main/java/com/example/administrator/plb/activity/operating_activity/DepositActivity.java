package com.example.administrator.plb.activity.operating_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.plb.R;

public class DepositActivity extends Activity implements View.OnClickListener{

    private ImageView close1;
    private TextView tvDepositTuiguangMoney;
    private TextView tvDepositPeisongMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        initView();
    }

    private void initView() {
        close1 = (ImageView) findViewById(R.id.close1);
        close1.setOnClickListener(this);
        tvDepositTuiguangMoney = (TextView) findViewById(R.id.tv_deposit_tuiguang_money);
        tvDepositPeisongMoney = (TextView) findViewById(R.id.tv_deposit_peisong_money);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close1:
                Intent intent = new Intent(DepositActivity.this,operating_financial.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK){
            Intent intent = new Intent(DepositActivity.this,operating_financial.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
