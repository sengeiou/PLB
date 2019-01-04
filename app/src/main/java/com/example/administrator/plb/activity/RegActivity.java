package com.example.administrator.plb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.plb.R;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mRepassword;
    private Button mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mRepassword = (EditText) findViewById(R.id.repassword);
        mReg = (Button) findViewById(R.id.reg);

        mReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg:
              if(submit()){
                  Intent intent=new Intent(RegActivity.this,Audit.class);
                  startActivity(intent);

              }
                break;
        }
    }
    private String usernameString;
    private String passwordString;
    private boolean submit() {
        // validate
        usernameString = mUsername.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return false;
        }

        passwordString = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        String repasswordString = mRepassword.getText().toString().trim();
        if (TextUtils.isEmpty(repasswordString)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!passwordString.equals(repasswordString)) {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent=new Intent();
            intent.putExtra("username",usernameString);
            setResult(1,intent);
            finish();
        }
    };
}
