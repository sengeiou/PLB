package com.example.administrator.plb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.plb.R;
import com.example.administrator.plb.activity.operating_activity.ExtractionActivity;
import com.example.administrator.plb.activity.operating_activity.FastRefundActivity;
import com.example.administrator.plb.activity.operating_activity.ShopActivity;
import com.example.administrator.plb.activity.operating_activity.operating_configuration;
import com.example.administrator.plb.activity.operating_activity.operating_data;
import com.example.administrator.plb.activity.operating_activity.operating_evaluation;
import com.example.administrator.plb.activity.operating_activity.operating_financial;
import com.example.administrator.plb.activity.operating_activity.operating_manager;

public class OperatingFragment extends Fragment implements View.OnClickListener {
    private View view;
    private LinearLayout mEvaluation,mFinancial,mData,mManager;
    private LinearLayout mPromote,mConfiguration;
    private LinearLayout mTosignUp,mStare,mOprRefund,mOprInvite;
    private LinearLayout mReplenish,mCourierCompany,mLogisticsDepartment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_operating, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mEvaluation = (LinearLayout) view.findViewById(R.id.evaluation);
        mEvaluation.setOnClickListener(this);
        mFinancial = (LinearLayout) view.findViewById(R.id.financial);
        mFinancial.setOnClickListener(this);
        mData = (LinearLayout) view.findViewById(R.id.data);
        mData.setOnClickListener(this);
        mManager = (LinearLayout) view.findViewById(R.id.manager);
        mManager.setOnClickListener(this);
        mPromote = (LinearLayout) view.findViewById(R.id.promote);
        mPromote.setOnClickListener(this);
        mConfiguration = (LinearLayout) view.findViewById(R.id.configuration);
        mConfiguration.setOnClickListener(this);
        mTosignUp = (LinearLayout) view.findViewById(R.id.Tosign_up);
        mTosignUp.setOnClickListener(this);
        mStare = (LinearLayout) view.findViewById(R.id.stare);
        mStare.setOnClickListener(this);
        mOprRefund = (LinearLayout) view.findViewById(R.id.opr_refund);
        mOprRefund.setOnClickListener(this);
        mOprInvite = (LinearLayout) view.findViewById(R.id.opr_invite);
        mOprInvite.setOnClickListener(this);
        mReplenish = (LinearLayout) view.findViewById(R.id.Replenish);
        mReplenish.setOnClickListener(this);
        mCourierCompany = (LinearLayout) view.findViewById(R.id.Courier_company);
        mCourierCompany.setOnClickListener(this);
        mLogisticsDepartment = (LinearLayout) view.findViewById(R.id.Logistics_department);
        mLogisticsDepartment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.evaluation:
                /*用户评价*/
                startActivity(new Intent(getActivity(), operating_evaluation.class));
                break;
            case R.id.financial:
                /*财务对账*/
                startActivity(new Intent(getActivity(), operating_financial.class));
                break;
            case R.id.data:
                /*经营数据*/
                startActivity(new Intent(getActivity(), operating_data.class));
                break;
            case R.id.manager:
                /*商品管理*/
                startActivity(new Intent(getActivity(), operating_manager.class));
                break;
            case R.id.promote:
                /*门店推广*/
                break;
            case R.id.configuration:
                /*活动配置*/
                startActivity(new Intent(getActivity(),operating_configuration.class));
                break;
            case R.id.Tosign_up:
                /*活动报名*/
                startActivity(new Intent(getActivity(),operating_configuration.class));
                break;
            case R.id.stare:
                /*店铺*/
                startActivity(new Intent(getActivity(),ShopActivity.class));
                break;
            case R.id.opr_refund:
                /*极速退款*/
                startActivity(new Intent(getActivity(),FastRefundActivity.class));
                break;
            case R.id.opr_invite:
                /*货物自取*/
                startActivity(new Intent(getActivity(),ExtractionActivity.class));
                break;
            case R.id.Replenish:
                /*进货商*/
                break;
            case R.id.Courier_company:
                /*快递公司*/
                break;
            case R.id.Logistics_department:
                /*后勤部门*/
                break;
        }
    }


}
