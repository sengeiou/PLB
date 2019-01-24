package com.example.administrator.plb.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 登陆页
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private int roleId = 2;
    private Button mLogin;
    private TextView mReg;
    private LinearLayout prbLogin;
    String[]permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    private String passwordString;
    private String usernameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(this,permissions,321);//请求文件读写，拍照权限
        initView();
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        prbLogin = findViewById(R.id.prb_login);
        prbLogin.setVisibility(View.GONE);
        mReg = (TextView) findViewById(R.id.reg);
        mReg.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        //获取存储的登陆信息
        String info=CacheUntil.getString(this,"infoJson","");
        if (!TextUtils.isEmpty(info)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                //显示加载View
                prbLogin.setVisibility(View.VISIBLE);
                //提交信息
                boolean submit = submit();
                if (submit){
                    prbLogin.setVisibility(View.VISIBLE);
                    String username = mUsername.getText().toString();
                    String pwd = mPassword.getText().toString();
                    sendInfo(username,pwd);
                }

                break;
            case R.id.reg:
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    private void sendInfo(String username,String pwd){
        String url = "http://39.98.68.40:8080/RetailManager/login.do?username="+username+"&password="+pwd+"&roleId="+roleId;
        HttpUtil httpUtil = new HttpUtil(url,handler,1);
        httpUtil.openConn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==1){
            mUsername.setText(data.getStringExtra("username"));
        }
    }

    private boolean submit() {
        // validate
        usernameString = mUsername.getText().toString().trim();
        passwordString = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                prbLogin.setVisibility(View.GONE);
                String result = msg.obj.toString();
                if (msg.obj.toString().indexOf("userInfo")!=-1){
                    //Log.e("objq", msg.obj.toString());
                    String json = msg.obj.toString();
                    CacheUntil.putString(getApplicationContext(), "infoJson", json);
                    CacheUntil.putString(getApplicationContext(), "userName", usernameString);
                    CacheUntil.putString(getApplicationContext(), "pwd", passwordString);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
