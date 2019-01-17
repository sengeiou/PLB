package com.example.administrator.plb.activity;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.until.CountDownTimerUtils;
import com.example.administrator.plb.until.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * 注册页
 */
public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText mPassword;
    private EditText mRepassword;
    private EditText telphone;

    private Button mReg;
    private EditText code;
    private Button getcode;

    private LinearLayout progressBar;
    CountDownTimerUtils countDownTimerUtils;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                if (msg.obj!=null){
                    progressBar.setVisibility(View.GONE);
                    countDownTimerUtils=new CountDownTimerUtils(getcode,60000,1000);
                    countDownTimerUtils.start();
                    Log.e("sss",msg.obj.toString()+"");
                }else {
                    Log.e("obj", "obj为空");
                }

            }
            if (msg.what == 2){
                if (msg.obj!=null){
                    String str = msg.obj.toString();
                    try {
                        int state = new JSONObject(str).getInt("state");
                        if (state == 200){
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(RegActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("JSON解析失败", e.getMessage());
                    }
                }else {
                    Log.e("obj", "obj为空");
                }
            }
        }
    };


    private String strPwd;
    private String strPhone;
    private String strCode;
    private int roleId = 2;//用户角色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        username=findViewById(R.id.username);

        mPassword = (EditText) findViewById(R.id.password);
        mRepassword = (EditText) findViewById(R.id.repassword);
        mReg = (Button) findViewById(R.id.reg);
        telphone=findViewById(R.id.telphone);
        mReg.setOnClickListener(this);
        code=findViewById(R.id.code);
        getcode=findViewById(R.id.getCode);
        getcode.setOnClickListener(this);
        progressBar=findViewById(R.id.prb);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg:
              if(submit()){
                  strPwd = mPassword.getText().toString();
                  strPhone = telphone.getText().toString();
                  strCode = this.code.getText().toString();
                  progressBar.setVisibility(View.VISIBLE);
                  sendInfo(strPhone, strPwd, roleId, strCode);
              }
            break;
            case R.id.getCode:
                if(TextUtils.isEmpty(telphone.getText()) ){
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }else {
                    getcode.setBackgroundResource(R.drawable.login_button_selector2);
                    progressBar.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessageDelayed(1,2000);
                    Connetion();
                }
                break;
        }
    }

    private void sendInfo(String phone,String pwd,int roleId,String code){
        String url = "http://39.98.68.40:8080/RetailManager/register?phone="+phone+"&password="+pwd+"&roleId="+roleId+"&code="+code;
        HttpUtil httpUtil = new HttpUtil(url,handler,2);
        httpUtil.openConn();
    }

    private void Connetion() {
        HttpUtil httpUtil=new HttpUtil("http://39.98.68.40:8080/RetailManager/getCode?phone="+telphone.getText(),handler,1);
        httpUtil.openConn();
    }

    private String passwordString;
    private boolean submit() {
        // validate

        if(TextUtils.isEmpty( username.getText())){
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(username.getText().length()>6){
            Toast.makeText(this, "不能超过6个字符", Toast.LENGTH_SHORT).show();
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
        if(telphone.length()!=11){
            Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if (TextUtils.isEmpty(code.getText())){
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
