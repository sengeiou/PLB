package com.example.administrator.plb.fragment.operating.operating_data_fragment.product;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.plb.R;

public class product_frag1 extends Fragment implements View.OnClickListener {
    private View view;
    private Spinner mProductSpinner;
    /**
     * 销售额最高
     */
    private TextView mProductMax;
    /**
     * 销售量最高
     */
    private TextView mProductNummax;
    /**
     * 赞
     */
    private TextView mProductGood;
    /**
     * 踩
     */
    private TextView mProductBad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_product_frag1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mProductSpinner = (Spinner) view.findViewById(R.id.product_spinner);
        mProductMax = (TextView) view.findViewById(R.id.product_max);
        mProductMax.setOnClickListener(this);
        mProductNummax = (TextView) view.findViewById(R.id.product_nummax);
        mProductNummax.setOnClickListener(this);
        mProductGood = (TextView) view.findViewById(R.id.product_good);
        mProductGood.setOnClickListener(this);
        mProductBad = (TextView) view.findViewById(R.id.product_bad);
        mProductBad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.product_max:
                break;
            case R.id.product_nummax:
                break;
            case R.id.product_good:
                break;
            case R.id.product_bad:
                break;
        }
    }
}
