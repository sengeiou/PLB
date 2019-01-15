package com.example.administrator.plb.activity.operating_activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.plb.BuildConfig;
import com.example.administrator.plb.R;
import com.example.administrator.plb.activity.RegIDCardActivity;
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.until.FileUtils;
import com.example.administrator.plb.until.PhotoUntil;

import java.io.File;
import java.io.FileNotFoundException;

import static com.example.administrator.plb.until.PhotoUntil.CAMERA;
import static com.example.administrator.plb.until.PhotoUntil.CROP;
import static com.example.administrator.plb.until.PhotoUntil.GALLERY;

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
    private String[]unit;
    private ImageView goodsImage;
    private PhotoUntil photoUntil;
    private GoodsListBean.GoodsBean goodsBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgood);
        intent=getIntent();
        classNames=intent.getStringArrayExtra("className");
        if (intent.getBundleExtra("goods")!=null){
            goodsBean= (GoodsListBean.GoodsBean) intent.getBundleExtra("goods").getSerializable("goods");
        }
        initView();

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
                photoUntil=new PhotoUntil(this);
                break;
        }
    }
    private void initView() {
        goodsImage=findViewById(R.id.goodsImage);
        iv_return = (ImageView) findViewById(R.id.iv_return);
        save = (TextView) findViewById(R.id.save);
        et_goodsName = (EditText) findViewById(R.id.et_goodsName);
        sp_className = (Spinner) findViewById(R.id.sp_className);
        et_price = (EditText) findViewById(R.id.et_price);
        et_Inventory = (EditText) findViewById(R.id.et_Inventory);
        et_minCount = (EditText) findViewById(R.id.et_minCount);
        sp_unit = (Spinner) findViewById(R.id.sp_unit);


        unit= getResources().getStringArray(R.array.unit);
        sp_unit.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unit));
        sp_className.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classNames));

        if(goodsBean!=null){
            getData();
        }

        save.setOnClickListener(this);
        iv_return.setOnClickListener(this);
        goodsImage.setOnClickListener(this);
    }

    private void getData() {
        Glide.with(this).load(goodsBean.getGoodsImage()).error(R.mipmap.logo).into(goodsImage);
        et_goodsName.setText(goodsBean.getGoodsName());
        et_Inventory.setText(goodsBean.getInventory()+"");
        et_minCount.setText(goodsBean.getMinCount()+"");
        et_price.setText(goodsBean.getGoodsPrice()+"");

        for(int i=0;i<classNames.length;i++){
            if(classNames[i].equals(goodsBean.getGoodsClass())){
                sp_className.setSelection(i);
                break;
            }
        }
        for(int i=0;i<unit.length;i++){
            if(unit[i].equals(goodsBean.getGoodsUnit())){
                sp_unit.setSelection(i);
                break;
            }
        }
    }

    private void submit() {
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

        GoodsListBean.GoodsBean goodsBean=new GoodsListBean.GoodsBean(
                goodsName,
                classNames[sp_className.getSelectedItemPosition()],
                file==null?"":file.getAbsolutePath(),
                Double.parseDouble(price),
                unit[sp_unit.getSelectedItemPosition()],
                Integer.parseInt(Inventory),
                Integer.parseInt(minCount),"");

                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",goodsBean);
                intent.putExtra("goods",bundle);
                setResult(2,intent);
                finish();
    }
    private File file;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAMERA && resultCode==RESULT_OK){
            file = photoUntil.crop();
        }
        if(requestCode==GALLERY && resultCode==RESULT_OK){

            file= photoUntil.crop(new File(photoUntil.handleImage(data)));
        }
        if(requestCode==CROP && resultCode==RESULT_OK){
               Glide.with(this).load(Uri.fromFile(file)).error(R.mipmap.logo).into(goodsImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                photoUntil.camera();
            }else{
                Toast.makeText(this, "部分权限未授予，相机无法使用", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==2){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                photoUntil.gallery();
            }else{
                Toast.makeText(this, "权限未授予，相册无法打开", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
