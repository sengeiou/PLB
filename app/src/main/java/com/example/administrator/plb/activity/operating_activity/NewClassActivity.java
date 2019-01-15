package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.until.CacheUntil;

public class NewClassActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvReturn;
    private TextView mSave;
    private EditText mEtClassName;
    private EditText mEtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);

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

        Intent intent=new Intent();
        intent.putExtra("className",className);
        intent.putExtra("note",note);
        setResult(1,intent);
        finish();


    }

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
