package com.example.administrator.plb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.plb.R;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        handler.sendEmptyMessageDelayed(0,3000);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    };
}
