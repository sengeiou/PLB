package com.example.administrator.plb.activity.operating_activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.GoodsListAdapter;
import com.example.administrator.plb.entity.ClassInfoBean;
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.entity.UserInformBean;
import com.example.administrator.plb.until.CacheUntil;
import com.example.administrator.plb.until.HttpUtil;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.jwenfeng.library.pulltorefresh.view.HeadRefreshView;


import java.lang.reflect.InvocationTargetException;
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


    private GoodsListAdapter adapter;

    private TextView save;
    private PullToRefreshLayout refresh;

    private int type;
    private int group;
    private int child;

    private UserInformBean bean;
    private List<ClassInfoBean.ClassBean>list;

    private int RequestCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_manager);

        initView();
        initDate();
        setSupportActionBar(toolbar);
    }

    private void initView() {
        listView=findViewById(R.id.list);
        iv_return=(ImageView) findViewById(R.id.iv_return);//返回键
        ly_classification=(LinearLayout) findViewById(R.id.ly_classification);//底部商品管理
        ly_TheSorting=(LinearLayout) findViewById(R.id.ly_TheSorting);//底部排序批量操作
        ly_NewGoods=(LinearLayout) findViewById(R.id.ly_NewGoods);//底部新建商品
        refresh=findViewById(R.id.refresh);
        toolbar=findViewById(R.id.toolbar);
    }
    private void initDate() {
        iv_return.setOnClickListener(this);//返回键
        ly_classification.setOnClickListener(this);//底部商品管理
        ly_TheSorting.setOnClickListener(this);//底部排序批量操作
        ly_NewGoods.setOnClickListener(this);//底部新建商品
        refresh.setRefreshListener(refreshListener);
        listView.setOnChildClickListener(onChildClickListener);
        registerForContextMenu(listView);
        refresh.setCanLoadMore(false);
        bean=new Gson().fromJson(CacheUntil.getString(this,"infoJson",""),UserInformBean.class);

        if(bean!=null){
            refresh();
        }


    }

    private void refresh() {
        initData(bean);
        adapter=new GoodsListAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    refresh.finishRefresh();
                    String result=msg.obj.toString();
                    if(result.indexOf("userInfo")!=-1){
                        CacheUntil.putString(operating_manager.this,"infoJson",result);
                        bean=new Gson().fromJson(result,UserInformBean.class);
                        refresh();
                    }
                    break;
                case 1:
                    if(msg.obj.toString().indexOf("OK")!=-1){
                        Toast.makeText(operating_manager.this, "删除成功", Toast.LENGTH_SHORT).show();
                        Updata();
                    }
                    break;

            }
        }
    };



    private BaseRefreshListener refreshListener=new BaseRefreshListener() {
        @Override
        public void refresh() {
            new HttpUtil("http://39.98.68.40:8080/RetailManager/login.do?roleId=2&phone=test001&password=123",handler,0).openConn();
        }

        @Override
        public void loadMore() {

        }
    };

    private ExpandableListView.OnChildClickListener onChildClickListener=new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            return true;
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ExpandableListView.ExpandableListContextMenuInfo info= (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        type= ExpandableListView.getPackedPositionType(info.packedPosition);
        group= ExpandableListView.getPackedPositionGroup(info.packedPosition);
        child= ExpandableListView.getPackedPositionChild(info.packedPosition);
        getMenuInflater().inflate(R.menu.operating_manager,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                if(type==ExpandableListView.PACKED_POSITION_TYPE_GROUP){
                    Intent intent=new Intent(operating_manager.this,NewClassActivity.class);
                    intent.putExtra("action",1);
                    intent.putExtra("storeId",list.get(group).getStoreId());
                    intent.putExtra("classificationId",list.get(group).getClassificationId());
                    intent.putExtra("className",list.get(group).getClassificationName());
                    intent.putExtra("note",list.get(group).getTypeDescribe().toString());
                    startActivityForResult(intent,1);
                }else if(type==ExpandableListView.PACKED_POSITION_TYPE_CHILD){
                    Intent intent=new Intent(operating_manager.this,NewGood.class);
                    intent.putExtra("action",1);
                    intent.putExtra("storeId",list.get(group).getStoreId());
                    startActivityForResult(intent,1);
                }
                break;
            case R.id.del:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

                if(type==ExpandableListView.PACKED_POSITION_TYPE_GROUP){
                    alertDialog.setMessage("是否删除分类,删除的分类商品也将删除")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new HttpUtil("http://39.98.68.40:8080/RetailManager/deleteCommodityType?classificationId="+list.get(group).getClassificationId(),handler,1).openConn();
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();
                }else if(type==ExpandableListView.PACKED_POSITION_TYPE_CHILD){
                alertDialog.setMessage("是否删除商品？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new HttpUtil("http://39.98.68.40:8080/RetailManager/deletesCommodity.do?commodityId="+list.get(group).getCommodityListBeans().get(child).getId(),handler,1);

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
                break;
        }
        return super.onContextItemSelected(item);
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
                Intent intent1 = new Intent(operating_manager.this, NewClassActivity.class);
                intent1.putExtra("action",0);
                intent1.putExtra("storeId",bean.getStore().getStoreId());
                startActivityForResult(intent1,1);
                break;
            //底部排序批量操作
            case R.id.ly_TheSorting:

                break;
            //底部新建商品
            case R.id.ly_NewGoods:
                Intent intent=new Intent(operating_manager.this,NewGood.class);
                int[]classId=new int[list.size()];
                String[]classNames=new String[list.size()];
                for(int i=0;i<list.size();i++){
                    classId[i]=list.get(i).getClassificationId();
                    classNames[i]=list.get(i).getClassificationName();
                }
                intent.putExtra("storeId",bean.getStore().getStoreId());
                intent.putExtra("firsttypeId",bean.getStore().getStoreId());
                intent.putExtra("classId",classId);
                intent.putExtra("classNames",classNames);
                startActivityForResult(intent,1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            //调用服务器更新数据
            Updata();
        }
    }
    private void Updata(){
        new HttpUtil("http://39.98.68.40:8080/RetailManager/login.do?roleId=2&phone=test001&password=123",handler,0).openConn();
    }
    private void initData(UserInformBean userBean){
        list=new ArrayList<>();
        for(int i=0;i<userBean.getCommodityTypeList().size();i++){
            UserInformBean.CommodityTypeListBean typeListBean= userBean.getCommodityTypeList().get(i);
            List<UserInformBean.CommodityListBean>listBean=new ArrayList<>();
            for(int k=0;k<userBean.getCommodityList().size();k++){
                if(userBean.getCommodityList().get(k).getFirsttypeId()==typeListBean.getClassificationId()){
                    listBean.add(userBean.getCommodityList().get(k));
                }
            }
            ClassInfoBean.ClassBean classBean=new ClassInfoBean.ClassBean(
                    typeListBean.getClassificationId(),
                    typeListBean.getClassificationName(),
                    typeListBean.getParentId(),
                    typeListBean.getType(),
                    typeListBean.getTypeDescribe(),
                    typeListBean.getStoreId(),
                    listBean
            );
            list.add(classBean);
        }

    }
}
