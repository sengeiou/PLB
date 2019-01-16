package com.example.administrator.plb.activity.operating_activity.configuration_fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.plb.R;

/**
 * Created by xlj on 2019.1.16.
 */
public class New_Activities extends Fragment implements View.OnClickListener{
    private Button btn_man,btn_jian,btn_zhe,btn_ling,btn_xin;
    private Context mContext;
    private AlertDialog.Builder builder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        return inflater.inflate(R.layout.activity_new_activity,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn_man = view.findViewById(R.id.btn_man);
        btn_man.setOnClickListener(this);
        btn_jian = view.findViewById(R.id.btn_jian);
        btn_jian.setOnClickListener(this);
        btn_zhe = view.findViewById(R.id.btn_zhe);
        btn_zhe.setOnClickListener(this);
        btn_ling = view.findViewById(R.id.btn_ling);
        btn_ling.setOnClickListener(this);
        btn_xin = view.findViewById(R.id.btn_xin);
        btn_xin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_man:
                builder = new AlertDialog.Builder(mContext);
                builder.setTitle("满减活动")
                        .setMessage("确定新建满减活动吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_man.setVisibility(View.INVISIBLE);
                                Toast.makeText(mContext,"新建满减活动成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            break;
            case R.id.btn_jian:
                builder = new AlertDialog.Builder(mContext);
                builder.setTitle("减配送费")
                        .setMessage("确定新建减配送费活动吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_jian.setVisibility(View.INVISIBLE);
                                Toast.makeText(mContext,"新建减配送费活动成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                    builder.create();
                    builder.show();
                break;
            case R.id.btn_zhe:
                builder = new AlertDialog.Builder(mContext);
                builder.setTitle("减配送费")
                        .setMessage("确定新建折扣商品活动吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_zhe.setVisibility(View.INVISIBLE);
                                Toast.makeText(mContext,"新建折扣商品活动成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
                break;
            case R.id.btn_ling:
                builder = new AlertDialog.Builder(mContext);
                builder.setTitle("减配送费")
                        .setMessage("确定新建商家优惠券活动吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_ling.setVisibility(View.INVISIBLE);
                                Toast.makeText(mContext,"新建商家优惠券活动成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
                break;
            case R.id.btn_xin:
                builder = new AlertDialog.Builder(mContext);
                builder.setTitle("减配送费")
                        .setMessage("确定新建新客立减活动吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_xin.setVisibility(View.INVISIBLE);
                                Toast.makeText(mContext,"新建新客立减活动成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
                break;
        }
    }
}
