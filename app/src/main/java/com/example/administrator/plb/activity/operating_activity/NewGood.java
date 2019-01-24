package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.Base64Util;
import com.example.administrator.plb.until.HttpUtil;
import com.example.administrator.plb.until.PhotoUntil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.administrator.plb.until.PhotoUntil.CAMERA;
import static com.example.administrator.plb.until.PhotoUntil.CROP;
import static com.example.administrator.plb.until.PhotoUntil.GALLERY;

/**
 * Created by tlf on 2019/1/2.
 */

public class NewGood extends AppCompatActivity implements View.OnClickListener {


    private PhotoUntil photoUntil;
    private Intent intent;
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

    private int storeId;
    private int firsttypeId;
    private int[]classId;
    private String[]classNames;
    private EditText et_shelfLife;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgood);
        initView();
        initData();
    }

    private void initData() {
        intent = getIntent();
        storeId=intent.getIntExtra("storeId",-1);
        firsttypeId=intent.getIntExtra("firsttypeId",-1);
        classId=intent.getIntArrayExtra("classId");
        classNames=intent.getStringArrayExtra("classNames");
        unit=getResources().getStringArray(R.array.unit);

        sp_className.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classNames));
        sp_unit.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unit));
        imported.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,shelves));
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
    private Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            file = photoUntil.crop();
        }
        if (requestCode == GALLERY && resultCode == RESULT_OK) {
            file = photoUntil.crop(new File(photoUntil.handleImage(data)));
        }
        if (requestCode == CROP && resultCode == RESULT_OK) {
            InputStream in= null;
            try {
                in = new FileInputStream(file);
                bitmap=BitmapFactory.decodeStream(in);
                Glide.with(this).load(file).asBitmap().error(R.mipmap.logo).into(goodsImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

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
        et_wholesalePrice = (EditText) findViewById(R.id.et_wholesalePrice);
        et_marketPrice = (EditText) findViewById(R.id.et_marketPrice);
        et_retailPrice = (EditText) findViewById(R.id.et_retailPrice);
        et_brand = (EditText) findViewById(R.id.et_brand);
        et_stocks = (EditText) findViewById(R.id.et_stocks);
        et_minCount = (EditText) findViewById(R.id.et_minCount);
        sp_unit = (Spinner) findViewById(R.id.sp_unit);
        time = (TextView) findViewById(R.id.time);
        imported = (Spinner) findViewById(R.id.imported);
        et_shelfLife =  findViewById(R.id.et_shelfLife);
        goodsImage = (ImageView) findViewById(R.id.goodsImage);

        save.setOnClickListener(this);
        iv_return.setOnClickListener(this);
        goodsImage.setOnClickListener(this);
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
        String shelfLife = et_shelfLife.getText().toString().trim();
        if (TextUtils.isEmpty(shelfLife)) {
            Toast.makeText(this, "请填写保质期", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        StringBuffer stringBuffer = getStringBuffer();

//        String json="{\n" +
//                "\"wholesalePrice\":"+Double.parseDouble(wholesalePrice)+",\n" +
//                "\"retailPrice\":"+Double.parseDouble(retailPrice)+",\n" +
//                "\"marketPrice\":"+Double.parseDouble(marketPrice)+",\n" +
//                "\"Image\":\""+stringBuffer.toString()+"\",\n" +
//                "\"detailedurl\":\""+stringBuffer.toString()+"\",\n" +
//                "\"unit\":\""+unit[sp_unit.getSelectedItemPosition()]+"\",\n" +
//                "\"minNum\":"+Integer.parseInt(minCount)+",\n" +
//                "\"shelfLife\":"+Integer.parseInt(shelfLife)+",\n" +
//                "\"brand\":\""+brand+"\",\n" +
//                "\"imported\":"+imported.getSelectedItemPosition()+",\n" +
//                "\"info\":\""+info+"\",\n" +
//                "\"firsttypeId\":"+firsttypeId+",\n" +
//                "\"storeId\":"+storeId+",\n" +
//                "\"stocks\":"+Integer.parseInt(stocks)+",\n" +
//                "\"goodsName\":\""+goodsName+"\",\n" +
//                "}";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("wholesalePrice",Double.parseDouble(wholesalePrice));
            jsonObject.put("retailPrice",Double.parseDouble(retailPrice));
            jsonObject.put("marketPrice",Double.parseDouble(marketPrice));
            jsonObject.put("image",stringBuffer.toString());
            jsonObject.put("detailedurl",stringBuffer.toString());
            jsonObject.put("unit",unit[sp_unit.getSelectedItemPosition()]);

            jsonObject.put("minNum",Integer.parseInt(minCount));
            jsonObject.put("shelfLife",Integer.parseInt(shelfLife));
            jsonObject.put("brand",brand);
            jsonObject.put("imported",imported.getSelectedItemPosition());

            jsonObject.put("info",info);
            jsonObject.put("firsttypeId",firsttypeId);
            jsonObject.put("stocks",Integer.parseInt(stocks));
            jsonObject.put("goodsName",goodsName);
            jsonObject.put("storeId",storeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outputStream=new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/index.json"));
            outputStream.write(jsonObject.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("json",jsonObject.toString());
        new HttpUtil("http://172.20.10.4:8080/RetailManager/addCommodity.do",handler,0).openPostConn(jsonObject.toString());

    }

    @NonNull
    private StringBuffer getStringBuffer() {
        byte[]bytes;
        StringBuffer string=new StringBuffer();
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            bytes=new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            string.append(Base64.encodeToString(bytes,Base64.NO_WRAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String result=msg.obj.toString();
                    if(result.indexOf("OK")!=-1){
                        Toast.makeText(NewGood.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(NewGood.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case -1:
                    break;
            }
        }
    };
}
