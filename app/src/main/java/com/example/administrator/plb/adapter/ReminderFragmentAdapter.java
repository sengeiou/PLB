package com.example.administrator.plb.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.entity.OrderBean;

import java.util.List;

public class ReminderFragmentAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private OrderBean orderBean;

    public ReminderFragmentAdapter(Context context, OrderBean orderBean) {
        this.context = context;
        this.orderBean = orderBean;
    }

    @Override
    public int getCount() {
        return orderBean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_reminder_adapter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mId.setText("" + orderBean.getResult().get(position).getId());
        viewHolder.mName.setText(orderBean.getResult().get(position).getConsignee());
        viewHolder.mPhone.setText(orderBean.getResult().get(position).getPhone());
        viewHolder.mTime.setText("" + orderBean.getResult().get(position).getOrderTime());
        viewHolder.mTotal.setText("￥"+orderBean.getResult().get(position).getMoney() + "");
        viewHolder.mOrderNumber.setText("订单编号:" + orderBean.getResult().get(position).getOrderid());
        viewHolder.mAddress.setText(orderBean.getResult().get(position).getAddress());
        List<OrderBean.ResultBean.OrderitemVOBean>shoppingBeans= orderBean.getResult().get(position).getOrderitemVO();
        for (int i = 0; i < shoppingBeans.size(); i++) {
            OrderBean.ResultBean.OrderitemVOBean bean = shoppingBeans.get(i);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            TableRow tableRow = new TableRow(context);
            TextView textView1 = new TextView(context);
            TextView textView2 = new TextView(context);
            TextView textView3 = new TextView(context);
            textView1.setText(bean.getGoodsName());
            textView2.setText("x" + bean.getNumber()+"/"+bean.getSubtotal());
            textView3.setText("￥" + bean.getSubtotal());
            textView1.setLayoutParams(params);
            textView2.setLayoutParams(params);
            textView3.setLayoutParams(params);

            textView3.setGravity(Gravity.RIGHT);
            int padding = context.getResources().getDimensionPixelSize(R.dimen.order_adapter_padding);
            tableRow.setPadding(padding, padding, padding, padding);

            tableRow.addView(textView1);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            viewHolder.mTable.addView(tableRow);
        }

        viewHolder.mPhone.setOnClickListener(this);
        viewHolder.mAccept.setOnClickListener(this);
        return viewHolder.rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.phone:
                TextView textView=(TextView)v;
                String phone=textView.getText().toString();
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                context.startActivity(intent);
                break;
            case R.id.cancel_order:
                showCancelOrderDiaLog();
                break;
            case R.id.accept:
                break;
        }
    }

    private void showCancelOrderDiaLog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context).setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setMessage("确定取消订单？");
        dialog.create().show();
    }


    public static class ViewHolder {
        public View rootView;
        public TextView mId;
        public TextView mWay;
        public TextView mTime;
        public TextView mPhone;
        public TextView mName;
        public TableLayout mTable;
        public TextView mTransport;
        public TextView mTotal;
        public TextView mOrderNumber;
        public Button mCancelOrder;
        public Button mAccept;
        public TextView mAddress;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mId = (TextView) rootView.findViewById(R.id.id);
            this.mWay = (TextView) rootView.findViewById(R.id.way);
            this.mTime = (TextView) rootView.findViewById(R.id.time);
            this.mPhone = (TextView) rootView.findViewById(R.id.phone);
            this.mName = (TextView) rootView.findViewById(R.id.name);
            this.mTable = (TableLayout) rootView.findViewById(R.id.table);
            this.mTransport = (TextView) rootView.findViewById(R.id.transport);
            this.mTotal = (TextView) rootView.findViewById(R.id.total);
            this.mOrderNumber = (TextView) rootView.findViewById(R.id.order_number);
            this.mCancelOrder = (Button) rootView.findViewById(R.id.cancel_order);
            this.mAccept = (Button) rootView.findViewById(R.id.accept);
            this.mAddress = (TextView) rootView.findViewById(R.id.address);

        }


    }

}
