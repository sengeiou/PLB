package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;

/**
 * Created by tlf on 2019/1/2.
 */

public class NewGood extends AppCompatActivity implements View.OnClickListener {


    private ImageView iv_return;
    private TextView save;
    private EditText et_goodsName;
    private Spinner sp_className;
    private EditText et_price;
    private EditText et_Inventory;
    private EditText et_minCount;
    private Spinner sp_unit;
    private Intent intent;
    private String[]classNames;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgood);
        intent=getIntent();
        classNames=intent.getStringArrayExtra("className");
        initView();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                break;
            case R.id.save:

                finish();
                break;
        }
    }

    private void initView() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        save = (TextView) findViewById(R.id.save);
        et_goodsName = (EditText) findViewById(R.id.et_goodsName);
        sp_className = (Spinner) findViewById(R.id.sp_className);
        et_price = (EditText) findViewById(R.id.et_price);
        et_Inventory = (EditText) findViewById(R.id.et_Inventory);
        et_minCount = (EditText) findViewById(R.id.et_minCount);
        sp_unit = (Spinner) findViewById(R.id.sp_unit);

        String[]unit= getResources().getStringArray(R.array.unit);
        sp_unit.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unit));
        sp_className.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classNames));
    }

    private void submit() {
        // validate
        String goodsName = et_goodsName.getText().toString().trim();
        if (TextUtils.isEmpty(goodsName)) {
            Toast.makeText(this, "限10字以内", Toast.LENGTH_SHORT).show();
            return;
        }

        String price = et_price.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, "请填写价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String Inventory = et_Inventory.getText().toString().trim();
        if (TextUtils.isEmpty(Inventory)) {
            Toast.makeText(this, "请填写库存", Toast.LENGTH_SHORT).show();
            return;
        }

        String minCount = et_minCount.getText().toString().trim();
        if (TextUtils.isEmpty(minCount)) {
            Toast.makeText(this, "请填写购买最小数量", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
