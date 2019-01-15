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
import com.example.administrator.plb.entity.GoodsListBean;
import com.example.administrator.plb.sqllite.GoodsSqlHelper;
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


    private GoodsSqlHelper sqlHelper;
    private SQLiteDatabase database;
    private GoodsListBean listBean;
    private GoodsListAdapter adapter;

    private GoodsListBean.ClassBean classBean;
    private GoodsListBean.GoodsBean goodsBean;
    private List<GoodsListBean.ClassBean>classBeans;
    private TextView save;
    private PullToRefreshLayout refresh;

    private int type;
    private int group;
    private int child;

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
        refresh=findViewById(R.id.refresh);
        toolbar=findViewById(R.id.toolbar);
        save=findViewById(R.id.save);
    }
    private void initDate() {
        iv_return.setOnClickListener(this);//返回键
        ly_classification.setOnClickListener(this);//底部商品管理
        ly_TheSorting.setOnClickListener(this);//底部排序批量操作
        ly_NewGoods.setOnClickListener(this);//底部新建商品
        save.setOnClickListener(this);
        refresh.setRefreshListener(refreshListener);
        listView.setOnChildClickListener(onChildClickListener);
        registerForContextMenu(listView);

        refresh.setCanLoadMore(false);
        listBean=new GoodsListBean();
        classBeans=new ArrayList<>();
        List<GoodsListBean.GoodsBean>goodsBeans=new ArrayList<>();
        goodsBeans.add(new GoodsListBean.GoodsBean("安慕希","副食品","",100,"件",100,100,"1234-5-6","上架"));
        classBeans.add(new GoodsListBean.ClassBean("123","213",goodsBeans));
        listBean.setList(classBeans);
        adapter=new GoodsListAdapter(this,listBean);
        listView.setAdapter(adapter);

    }


    private BaseRefreshListener refreshListener=new BaseRefreshListener() {
        @Override
        public void refresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh.finishRefresh();
                }
            },2000);
        }

        @Override
        public void loadMore() {

        }
    };

    private ExpandableListView.OnChildClickListener onChildClickListener=new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            GoodsListBean.GoodsBean goodsBean= listBean.getList().get(groupPosition).getList().get(childPosition);
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
                    GoodsListBean.ClassBean classBean= listBean.getList().get(group);
                    Intent intent=new Intent(operating_manager.this,NewClassActivity.class);
                    intent.putExtra("className",classBean.getClassName());
                    intent.putExtra("note",classBean.getNote());
                    startActivityForResult(intent,2);
                }else if(type==ExpandableListView.PACKED_POSITION_TYPE_CHILD){

                    ArrayList<String>list=new ArrayList<>();
                    for(int i=0;i<listBean.getList().size();i++){
                        list.add(listBean.getList().get(i).getClassName());
                    }
                    String[]strings=new String[list.size()];
                    strings=list.toArray(strings);
                    GoodsListBean.GoodsBean goodsBean= listBean.getList().get(group).getList().get(child);
                    Intent intent=new Intent(operating_manager.this,NewGood.class);
                    Bundle bundle=new Bundle();
                    intent.putExtra("className",strings);
                    bundle.putSerializable("goods",goodsBean);
                    intent.putExtra("goods",bundle);
                    startActivityForResult(intent,2);
                }
                break;
            case R.id.del:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

                if(type==ExpandableListView.PACKED_POSITION_TYPE_GROUP){
                    alertDialog.setMessage("是否删除分类,删除的分类商品也将删除")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    classBeans.remove(group);
                                    listView.setAdapter(adapter);

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
                                listBean.getList().get(group).getList().remove(child);
                                listView.collapseGroup(group);
                                listView.expandGroup(group);
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
                startActivityForResult(intent,1);
                break;
            case R.id.save:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1://新建
                if(resultCode==1){
                    String className=data.getStringExtra("className");
                    String note=data.getStringExtra("note");
                    boolean isRepeat=false;
                    for(int i=0;i<listBean.getList().size();i++){
                        if(className.equals(listBean.getList().get(i).getClassName())){
                            isRepeat=true;
                        }
                    }
                    if(!isRepeat){
                        insertClassName(className, note);
                    }else{
                        Toast.makeText(this, "分类名已存在", Toast.LENGTH_SHORT).show();
                    }

                }
                if (resultCode==2){
                    Bundle bundle= data.getBundleExtra("goods");
                    GoodsListBean.GoodsBean goodsBean= (GoodsListBean.GoodsBean) bundle.getSerializable("data");
                    for(int i=0;i<listBean.getList().size();i++){
                        if(goodsBean.getGoodsClass().equals(listBean.getList().get(i).getClassName())){
                            listBean.getList().get(i).getList().add(goodsBean);
                            listView.collapseGroup(i);
                            listView.expandGroup(i);
                            break;
                        }
                    }
                }
                break;
            case 2://修改
                if(resultCode==1){
                    listBean.getList().get(group).setClassName(data.getStringExtra("className"));
                    listBean.getList().get(group).setNote(data.getStringExtra("note"));
                    adapter.notifyDataSetChanged();
                }
                if(resultCode==2){
                    GoodsListBean.GoodsBean goods= (GoodsListBean.GoodsBean) data.getBundleExtra("goods").getSerializable("data");
                    GoodsListBean.GoodsBean goodsBean=listBean.getList().get(group).getList().get(child);
                    goodsBean.setGoodsClass(goods.getGoodsClass());
                    goodsBean.setGoodsImage(goods.getGoodsImage());
                    goodsBean.setGoodsName(goods.getGoodsName());
                    goodsBean.setGoodsPrice(goods.getGoodsPrice());
                    goodsBean.setGoodsUnit(goods.getGoodsUnit());
                    goodsBean.setInventory(goods.getInventory());
                    goodsBean.setMinCount(goods.getMinCount());
                    goodsBean.setSellingTime(goods.getSellingTime());
                    listView.collapseGroup(group);
                    listView.expandGroup(group);
                }
                break;
        }
    }

    private void insertClassName(String className, String note) {
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("className",className);
//        contentValues.put("note",note);
//        database.insert(GoodsSqlHelper.ClassTable,null,contentValues);
        classBean=new GoodsListBean.ClassBean(className,note,new ArrayList<GoodsListBean.GoodsBean>());
        classBeans.add(classBean);
        adapter.notifyDataSetChanged();
        //listBean
    }
}
