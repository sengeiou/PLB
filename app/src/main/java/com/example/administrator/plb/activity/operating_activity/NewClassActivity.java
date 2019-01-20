package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.HttpUtil;

public class NewClassActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvReturn;
    private TextView mSave;
    private EditText mEtClassName;
    private EditText mEtNote;
    private int action;
    private int storeId;
    private int classificationId;
    private String classname;
    private String note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);
        Intent intent= getIntent();
        action=intent.getIntExtra("action",0);
        storeId=intent.getIntExtra("storeId",0);
        classificationId=intent.getIntExtra("classificationId",0);
        initView();
    }
    private void initView() {
        mIvReturn = (ImageView) findViewById(R.id.iv_return);
        mSave = (TextView) findViewById(R.id.save);
        mEtClassName = (EditText) findViewById(R.id.et_className);
        mEtNote = (EditText) findViewById(R.id.et_note);

        Intent intent=getIntent();
        mEtClassName.setText(intent.getStringExtra("className"));
        mEtNote.setText(intent.getStringExtra("note"));

        mIvReturn.setOnClickListener(this);
        mSave.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String className = mEtClassName.getText().toString().trim();
        if (TextUtils.isEmpty(className)) {
            Toast.makeText(this, "请输入分类名", Toast.LENGTH_SHORT).show();
            return;
        }
        String note = mEtNote.getText().toString().trim();
        if (TextUtils.isEmpty(note)) {
            Toast.makeText(this, "请输入分类说明", Toast.LENGTH_SHORT).show();
            return;
        }

        if(action==0){
            new HttpUtil("http://39.98.68.40:8080/RetailManager/" +
                    "addCommodityType?classificationName="+className+
                    "&typeDescribe="+note+
                    "&storeId="+storeId
                    ,handler,0).openConn();
        }else if(action==1){
            new HttpUtil("http://39.98.68.40:8080/RetailManager/" +
                    "updateCommodityType.do?classificationName="+className+
                    "&typeDescribe="+note+
                    "&storeId="+storeId+
                    "&classificationId="+classificationId
                    ,handler,0).openConn();
        }



    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String string = msg.obj.toString();
                    if(string.indexOf("OK")!=-1){
                        setResult(RESULT_OK);
                        finish();
                    }
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.save:
                submit();
                break;
        }
    }

}
