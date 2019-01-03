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
import com.example.administrator.plb.fragment.operating.operating_data;
import com.example.administrator.plb.fragment.operating.operating_evaluation;
import com.example.administrator.plb.fragment.operating.operating_financial;
import com.example.administrator.plb.fragment.operating.operating_manager;

public class OperatingFragment extends Fragment implements View.OnClickListener {
    private View view;
    private LinearLayout mEvaluation;
    private LinearLayout mFinancial;
    private LinearLayout mData;
    private LinearLayout mManager;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.evaluation:
                startActivity(new Intent(getActivity(),operating_evaluation.class));//用户评价
                break;
            case R.id.financial:
                startActivity(new Intent(getActivity(),operating_financial.class));//财务对账
                break;
            case R.id.data:
                startActivity(new Intent(getActivity(),operating_data.class));//经营数据
                break;
            case R.id.manager:
                startActivity(new Intent(getActivity(),operating_manager.class));//商品管理
                break;
        }
    }
}
