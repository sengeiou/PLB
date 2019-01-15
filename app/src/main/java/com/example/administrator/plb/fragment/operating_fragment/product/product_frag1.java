package com.example.administrator.plb.fragment.operating_fragment.product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.plb.R;
import com.example.administrator.plb.adapter.ProductSortSdapter;
import com.example.administrator.plb.entity.productbean;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品销售额
 */
public class product_frag1 extends Fragment implements View.OnClickListener {
    private View view;
    private Context mContext;
    private ProductSortSdapter adapter;
    private List<productbean> mData = null;
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
    private int i = 1;
    private ListView mSortListview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_product_frag1, container, false);
        initView(view);
        setData();
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
        mSortListview = (ListView) view.findViewById(R.id.sort_listview);
    }

    public void setData() {
        mContext = getActivity();
        mData = new LinkedList<productbean>();
        for(int i=0;i<3;i++){
            mData.add(new productbean(R.mipmap.sort1, "章鱼小丸子", 1200, 55, "好评率100%", 88, 1));
            mData.add(new productbean(R.mipmap.sort2, "糖醋排骨", 1400, 45, "好评率80%", 66, 5));
            mData.add(new productbean(R.mipmap.sort3, "正新鸡排", 1600, 65, "好评率70%", 86, 2));
            mData.add(new productbean(R.mipmap.sort4, "酒糟凤爪", 1500, 35, "好评率90%", 67, 3));
            mData.add(new productbean(R.mipmap.sort5, "鸡蛋仔冰淇淋", 1300, 45, "好评率80%", 77, 0));
        }
        Collections.sort(mData, new setSort());
        adapter = new ProductSortSdapter((LinkedList<productbean>) mData, mContext);
        adapter.notifyDataSetChanged();
        mSortListview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.product_max:
                i=1;
                Collections.sort(mData, new setSort());
                adapter.notifyDataSetChanged();
                mSortListview.setAdapter(adapter);
                Toast.makeText(getActivity(),"销售额排序"+i,Toast.LENGTH_SHORT).show();
                break;
            case R.id.product_nummax:
                i=2;
                Collections.sort(mData, new setSort());
                adapter.notifyDataSetChanged();
                mSortListview.setAdapter(adapter);
                Toast.makeText(getActivity(),"销量排序"+i,Toast.LENGTH_SHORT).show();
                break;
            case R.id.product_good:
                i=3;
                Collections.sort(mData, new setSort());
                adapter.notifyDataSetChanged();
                mSortListview.setAdapter(adapter);
                Toast.makeText(getActivity(),"赞排序"+i,Toast.LENGTH_SHORT).show();
                break;
            case R.id.product_bad:
                i=4;
                Collections.sort(mData, new setSort());
                adapter.notifyDataSetChanged();
                mSortListview.setAdapter(adapter);
                Toast.makeText(getActivity(),"踩排序"+i,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class setSort implements Comparator<productbean> {
        @Override
        public int compare(productbean o1, productbean o2) {
            if (i == 1) {
                return -(o1.getShop_money() - o2.getShop_money());
            }
            if (i == 2) {
                return -(o1.getShop_num() - o2.getShop_num());
            }
            if (i == 3) {
                return -(o1.getShop_good() - o2.getShop_good());
            }
            if (i == 4) {
                return -(o1.getShop_bad() - o2.getShop_bad());
            } else {
                return 0;
            }

        }
    }

}
