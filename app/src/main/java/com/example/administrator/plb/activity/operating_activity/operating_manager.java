package com.example.administrator.plb.activity.operating_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.plb.R;

/*
* 商品管理
* */
public class operating_manager extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_return;//返回键
    private ImageView iv_search;//搜索
    private ListView lv_goods;//左边商品分类
    private ListView lv_CommodityContent;//右边商品内容
    private LinearLayout ly_classification;//底部商品管理
    private LinearLayout ly_TheSorting;//底部排序批量操作
    private LinearLayout ly_NewGoods;//底部新建商品
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_manager);
        initView();
        initDate();
        setSupportActionBar(toolbar);
    }

    private void initView() {
        iv_return=(ImageView) findViewById(R.id.iv_return);//返回键
        iv_search=(ImageView) findViewById(R.id.iv_search);//搜索
        lv_goods=(ListView) findViewById(R.id.lv_goods);//左边商品分类
        lv_CommodityContent=(ListView) findViewById(R.id.lv_CommodityContent);//右边商品内容
        ly_classification=(LinearLayout) findViewById(R.id.ly_classification);//底部商品管理
        ly_TheSorting=(LinearLayout) findViewById(R.id.ly_TheSorting);//底部排序批量操作
        ly_NewGoods=(LinearLayout) findViewById(R.id.ly_NewGoods);//底部新建商品
        toolbar=findViewById(R.id.toolbar);
    }
    private void initDate() {
        iv_return.setOnClickListener(this);//返回键
        iv_search.setOnClickListener(this);//搜索
        ly_classification.setOnClickListener(this);//底部商品管理
        ly_TheSorting.setOnClickListener(this);//底部排序批量操作
        ly_NewGoods.setOnClickListener(this);//底部新建商品
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.operating_manager,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回键
            case R.id.iv_return:
                finish();
                break;
            //搜索
            case R.id.iv_search:

                break;
            //底部商品管理
            case R.id.ly_classification:

                break;
            //底部排序批量操作
            case R.id.ly_TheSorting:

                break;
            //底部新建商品
            case R.id.ly_NewGoods:
                startActivity(new Intent(operating_manager.this,NewGood.class));
                break;
        }
    }
}
