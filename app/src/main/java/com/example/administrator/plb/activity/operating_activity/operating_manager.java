package com.example.administrator.plb.activity.operating_activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.GoodsListAdapter;
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.sqllite.GoodsSqlHelper;

import java.util.ArrayList;
import java.util.List;

/*
* 商品管理
* */
public class operating_manager extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_return;//返回键
    private LinearLayout ly_classification;//底部商品管理
    private LinearLayout ly_TheSorting;//底部排序批量操作
    private LinearLayout ly_NewGoods;//底部新建商品
    private Toolbar toolbar;
    private ExpandableListView listView;


    private GoodsSqlHelper sqlHelper;
    private SQLiteDatabase database;
    private GoodsListBean listBean;
    private GoodsListAdapter adapter;

    private GoodsListBean.ClassBean classBean;
    private GoodsListBean.GoodsBean goodsBean;
    private List<GoodsListBean.ClassBean>classBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_manager);
        initView();
        initDate();
        setSupportActionBar(toolbar);
        sqlHelper=new GoodsSqlHelper(this);
        database=sqlHelper.getWritableDatabase();
    }

    private void initView() {
        listView=findViewById(R.id.list);
        iv_return=(ImageView) findViewById(R.id.iv_return);//返回键
        ly_classification=(LinearLayout) findViewById(R.id.ly_classification);//底部商品管理
        ly_TheSorting=(LinearLayout) findViewById(R.id.ly_TheSorting);//底部排序批量操作
        ly_NewGoods=(LinearLayout) findViewById(R.id.ly_NewGoods);//底部新建商品
        toolbar=findViewById(R.id.toolbar);
    }
    private void initDate() {
        iv_return.setOnClickListener(this);//返回键
        ly_classification.setOnClickListener(this);//底部商品管理
        ly_TheSorting.setOnClickListener(this);//底部排序批量操作
        ly_NewGoods.setOnClickListener(this);//底部新建商品



        listBean=new GoodsListBean();
        classBeans=new ArrayList<>();
        List<GoodsListBean.GoodsBean>goodsBeans=new ArrayList<>();
        goodsBeans.add(new GoodsListBean.GoodsBean("安慕希","副食品","",100,"件",100,100,"1234-5-6"));
        classBeans.add(new GoodsListBean.ClassBean("123","213",goodsBeans));
        listBean.setList(classBeans);
        adapter=new GoodsListAdapter(this,listBean);
        listView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回键
            case R.id.iv_return:
                finish();
                break;
            //底部商品管理
            case R.id.ly_classification:
                startActivityForResult(new Intent(operating_manager.this,NewClassActivity.class),1);
                break;
            //底部排序批量操作
            case R.id.ly_TheSorting:

                break;
            //底部新建商品
            case R.id.ly_NewGoods:
                ArrayList<String>list=new ArrayList<>();
                for(int i=0;i<listBean.getList().size();i++){
                    list.add(listBean.getList().get(i).getClassName());
                }
                String[]strings=new String[list.size()];
                strings=list.toArray(strings);
                Intent intent=new Intent(operating_manager.this,NewGood.class);
                intent.putExtra("className",strings);
                startActivityForResult(intent,2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                String className=data.getStringExtra("className");
                String note=data.getStringExtra("note");
                insertClassName(className, note);

                break;
            case 2:
                Bundle bundle= data.getBundleExtra("goods");
                bundle.getSerializable("goods");
                break;
        }
    }

    private void insertClassName(String className, String note) {
        ContentValues contentValues=new ContentValues();
        contentValues.put("className",className);
        contentValues.put("note",note);
        database.insert(GoodsSqlHelper.ClassTable,null,contentValues);
        classBean=new GoodsListBean.ClassBean(className,note,new ArrayList<GoodsListBean.GoodsBean>());
        classBeans.add(classBean);
        adapter.notifyDataSetChanged();
        //listBean
    }
}
