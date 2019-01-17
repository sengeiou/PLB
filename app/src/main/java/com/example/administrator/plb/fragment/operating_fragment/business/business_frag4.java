package com.example.administrator.plb.fragment.operating_fragment.business;

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
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.plb.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
 * create by csy 1/2
 * 营业统计
 * 自定义
 * */
public class business_frag4 extends Fragment implements View.OnClickListener {
    private View view;
    public Context context;
    /**
     * 自定义
     */
    private TextView mBusinessTime4;
    /**
     * ￥1,872.5
     */
    private TextView mAmountOfBusiness4;
    /**
     * 53
     */
    private TextView mEffectiveOrderQuantity4;
    /**
     * 客单价￥35.33
     */
    private TextView mGuestUnitPrice4;
    /**
     * ￥73
     */
    private TextView mMerchantsSubsidies4;
    /**
     * 0
     */
    private TextView mInvalidOrderQuantity4;
    /**
     * 损失营业额约￥0
     */
    private TextView mLostTurnover4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_business_frag4, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBusinessTime4 = (TextView) view.findViewById(R.id.business_time4);
        mBusinessTime4.setOnClickListener(this);
        mAmountOfBusiness4 = (TextView) view.findViewById(R.id.amount_of_business4);
        mEffectiveOrderQuantity4 = (TextView) view.findViewById(R.id.Effective_order_quantity4);
        mGuestUnitPrice4 = (TextView) view.findViewById(R.id.guest_unit_price4);
        mMerchantsSubsidies4 = (TextView) view.findViewById(R.id.Merchants_subsidies4);
        mInvalidOrderQuantity4 = (TextView) view.findViewById(R.id.Invalid_order_quantity4);
        mLostTurnover4 = (TextView) view.findViewById(R.id.Lost_turnover4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.business_time4:
                setDate();
                break;
        }
    }

    public void setDate(){
        //点击"日期"按钮布局 设置日期
        mBusinessTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过自定义控件AlertDialog实现
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = (LinearLayout) getLayoutInflater().inflate(R.layout.business_date_dialog, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //初始化当前日期
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH), null);
                //设置date布局
                builder.setView(view);
                builder.setTitle("自定义日期");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        mBusinessTime4.setText(sb);
                        //赋值后面闹钟使用
                        /*mYear = datePicker.getYear();
                        mMonth = datePicker.getMonth();
                        mDay = datePicker.getDayOfMonth();*/
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }

}
