package com.example.administrator.plb.activity.operating_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.NewGoodAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlf on 2019/1/2.
 */

public class NewGood extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_ListOfGoods;//分类菜单
    private TextView tv_Specifications;//多规格(多种价格设置)

    private NewGoodAdapter newGoodAdapter;
    private List<String> mList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgood);
        initView();
        initDate();



        mList=new ArrayList<>();
        mList.add("dsadsa");
        mList.add("dsadsa");
        mList.add("dsadsa");
        mList.add("dsadsa");
        mList.add("dsadsa");
        newGoodAdapter=new NewGoodAdapter(this,mList);
        lv_ListOfGoods.setAdapter(newGoodAdapter);


    }


    private void initView() {
        lv_ListOfGoods=(ListView) findViewById(R.id.lv_ListOfGoods);//分类菜单
        tv_Specifications=(TextView) findViewById(R.id.tv_Specifications);//多规格(多种价格设置)
    }
    private void initDate() {
        tv_Specifications.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
