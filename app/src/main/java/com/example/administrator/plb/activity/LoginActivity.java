package com.example.administrator.plb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.until.CacheUntil;

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
    private Button mLogin;
    private TextView mReg;
    String[]permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
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
        mReg = (TextView) findViewById(R.id.reg);
        mReg.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        //获取存储的登陆信息
        String username=CacheUntil.getString(this,"username","");
        String password=CacheUntil.getString(this,"password","");
        mUsername.setText(username);
        mPassword.setText(password);
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            //如果两者不为空则为自动调用接口登陆

            //显示加载View
        }else{
            //如果不成功则提示用户手动登陆
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                //显示加载View

                //提交信息
                submit();
                break;
            case R.id.reg:
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==1){
            mUsername.setText(data.getStringExtra("username"));
        }
    }

    private boolean submit() {
        // validate
        String usernameString = mUsername.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return false;
        }

        String passwordString = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        //调用接口，验证登陆


        return true;

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://登陆失败
                    break;
                case 1://登陆成功
                    break;
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
