package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.until.HttpUtil;
import com.example.administrator.plb.until.PhotoUntil;

import java.io.File;

import static com.example.administrator.plb.until.PhotoUntil.CAMERA;
import static com.example.administrator.plb.until.PhotoUntil.CROP;
import static com.example.administrator.plb.until.PhotoUntil.GALLERY;

/**
 * Created by tlf on 2019/1/2.
 */

public class NewGood extends AppCompatActivity implements View.OnClickListener {


    private PhotoUntil photoUntil;
    private Intent intent;
    private String[] classNames;
    private String[] unit;
    private String[] shelves = new String[]{"否", "是"};
    private ImageView iv_return;
    private TextView save;
    private EditText et_goodsName;
    private EditText et_info;
    private Spinner sp_className;
    private ImageView goodsImage;
    private EditText et_wholesalePrice;
    private EditText et_marketPrice;
    private EditText et_retailPrice;
    private EditText et_brand;
    private EditText et_stocks;
    private EditText et_minCount;
    private Spinner sp_unit;
    private TextView time;
    private Spinner imported;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgood);
        initView();
        intent = getIntent();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.save:
                submit();
                break;
            case R.id.goodsImage:
                photoUntil = new PhotoUntil(this);
                break;
            case R.id.time:
                break;
        }
    }





    private File file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            file = photoUntil.crop();
        }
        if (requestCode == GALLERY && resultCode == RESULT_OK) {
            file = photoUntil.crop(new File(photoUntil.handleImage(data)));
        }
        if (requestCode == CROP && resultCode == RESULT_OK) {
            Glide.with(this).load(file).error(R.mipmap.logo).into(goodsImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                photoUntil.camera();
            } else {
                Toast.makeText(this, "部分权限未授予，相机无法使用", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                photoUntil.gallery();
            } else {
                Toast.makeText(this, "权限未授予，相册无法打开", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void initView() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        save = (TextView) findViewById(R.id.save);
        et_goodsName = (EditText) findViewById(R.id.et_goodsName);
        et_info = (EditText) findViewById(R.id.et_info);
        sp_className = (Spinner) findViewById(R.id.sp_className);
        goodsImage = (ImageView) findViewById(R.id.goodsImage);
        et_wholesalePrice = (EditText) findViewById(R.id.et_wholesalePrice);
        et_marketPrice = (EditText) findViewById(R.id.et_marketPrice);
        et_retailPrice = (EditText) findViewById(R.id.et_retailPrice);
        et_brand = (EditText) findViewById(R.id.et_brand);
        et_stocks = (EditText) findViewById(R.id.et_stocks);
        et_minCount = (EditText) findViewById(R.id.et_minCount);
        sp_unit = (Spinner) findViewById(R.id.sp_unit);
        time = (TextView) findViewById(R.id.time);
        imported = (Spinner) findViewById(R.id.imported);
    }

    private void submit() {
        String goodsName = et_goodsName.getText().toString().trim();
        if (TextUtils.isEmpty(goodsName)) {
            Toast.makeText(this, "请输入商品名", Toast.LENGTH_SHORT).show();
            return;
        }

        String info = et_info.getText().toString().trim();
        if (TextUtils.isEmpty(info)) {
            Toast.makeText(this, "请输入商品详细名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String wholesalePrice = et_wholesalePrice.getText().toString().trim();
        if (TextUtils.isEmpty(wholesalePrice)) {
            Toast.makeText(this, "请填写批发价", Toast.LENGTH_SHORT).show();
            return;
        }

        String marketPrice = et_marketPrice.getText().toString().trim();
        if (TextUtils.isEmpty(marketPrice)) {
            Toast.makeText(this, "请填写市场参考价", Toast.LENGTH_SHORT).show();
            return;
        }

        String retailPrice = et_retailPrice.getText().toString().trim();
        if (TextUtils.isEmpty(retailPrice)) {
            Toast.makeText(this, "请填写零售价", Toast.LENGTH_SHORT).show();
            return;
        }

        String brand = et_brand.getText().toString().trim();
        if (TextUtils.isEmpty(brand)) {
            Toast.makeText(this, "请填写品牌", Toast.LENGTH_SHORT).show();
            return;
        }

        String stocks = et_stocks.getText().toString().trim();
        if (TextUtils.isEmpty(stocks)) {
            Toast.makeText(this, "请填写库存", Toast.LENGTH_SHORT).show();
            return;
        }

        String minCount = et_minCount.getText().toString().trim();
        if (TextUtils.isEmpty(minCount)) {
            Toast.makeText(this, "请填写最少起购量", Toast.LENGTH_SHORT).show();
            return;
        }

        new HttpUtil("http://39.98.68.40:8080/RetailManager/addCommodity.do",handler,0);

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
