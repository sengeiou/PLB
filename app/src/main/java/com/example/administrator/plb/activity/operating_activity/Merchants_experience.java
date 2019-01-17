package com.example.administrator.plb.activity.operating_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.plb.R;
import com.example.administrator.plb.View.SinkView;
import com.example.administrator.plb.adapter.MyAdapters;
import com.example.administrator.plb.entity.qustionBeanNo;
import com.example.administrator.plb.entity.qustionBeanYes;
import com.example.administrator.plb.until.CacheUntil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Merchants_experience extends AppCompatActivity implements View.OnClickListener {

    private ImageView mClose5;
    private ImageView mClose1;
    private TextView mExperienceNum1;
    private TextView mExperienceNum2;
    private TextView mExperienceTime;
    private TextView mQuestionYes;
    private TextView mQuestionNo;
    private MyAdapters<qustionBeanYes> myAdapter1 = null;
    private MyAdapters<qustionBeanNo> myAdapter2 = null;
    private List<qustionBeanYes> mData1 = null;
    private List<qustionBeanNo> mData2 = null;
    private ListView mListviewYes;
    private ListView mListviewNo;
    private int num1 = 1, num2 = 3;
    private SinkView mExperienceSinkView;
    private float mPercent;
    //private float mPercentNum=0.3f;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchants_experience);
        initView();
        mExperienceSinkView = (SinkView) findViewById(R.id.experience_sinkView);
        mExperienceSinkView.setOnClickListener(this);
        //mPercentNum= (float) Math.random();
        mPercent =(float) Math.random();
        mExperienceSinkView.setPercent(mPercent);

        mExperienceTime.setText(CacheUntil.getString(this,KEY_Text_Time,""));

    }

    private void initView() {
        mClose5 = (ImageView) findViewById(R.id.close1);
        mClose5.setOnClickListener(this);
        mClose1 = (ImageView) findViewById(R.id.close1);
        mExperienceNum1 = (TextView) findViewById(R.id.experience_num1);
        mExperienceNum2 = (TextView) findViewById(R.id.experience_num2);
        mExperienceTime = (TextView) findViewById(R.id.experienceTime);
        mQuestionYes = (TextView) findViewById(R.id.question_yes);
        mQuestionNo = (TextView) findViewById(R.id.question_no);
        mListviewYes = (ListView) findViewById(R.id.listview_yes);
        mListviewNo = (ListView) findViewById(R.id.listview_no);

        //数据初始化
        mData1 = new ArrayList<qustionBeanYes>();
        mData1.add(new qustionBeanYes(R.mipmap.cuid_yes, "顾客催单率偏高", "影响店铺口碑"));
        mData1.add(new qustionBeanYes(R.mipmap.dingd_yes, "无效订单率偏高", "造成营业额损失"));
        mData1.add(new qustionBeanYes(R.mipmap.action_yes, "未参与平台活动", "影响顾客进店率"));

        mData2 = new ArrayList<qustionBeanNo>();
        mData2.add(new qustionBeanNo(R.mipmap.pingjia_no, "近7天，收到一星差评：" + num1 + "个"));
        mData2.add(new qustionBeanNo(R.mipmap.avgtime_no, "近7天，平均每天接单时长：" + num2 + "秒"));
        mData2.add(new qustionBeanNo(R.mipmap.action_no, "销售额上升"));
        mData2.add(new qustionBeanNo(R.mipmap.action_no, "销售量增加"));
        mData2.add(new qustionBeanNo(R.mipmap.action_no, "访问顾客增加"));

        //Adapter初始化
        myAdapter1 = new MyAdapters<qustionBeanYes>((ArrayList) mData1, R.layout.item_one) {
            @Override
            public void bindView(ViewHolder holder, qustionBeanYes obj) {
                holder.setImageResource(R.id.yes_icon, obj.getaIcon());
                holder.setText(R.id.question_yes, obj.getaQuestion());
                holder.setText(R.id.reason_yes, obj.getaReason());
            }
        };

        myAdapter2 = new MyAdapters<qustionBeanNo>((ArrayList) mData2, R.layout.item_two) {
            @Override
            public void bindView(ViewHolder holder, qustionBeanNo obj) {
                holder.setImageResource(R.id.no_icon, obj.getNoIcon());
                holder.setText(R.id.question_no, obj.getNoQuestion());
            }
        };

        mListviewYes.setAdapter(myAdapter1);
        mListviewNo.setAdapter(myAdapter2);

    }

    /*
     * 体检球
     * */
    private void check() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mPercent = 0;
                while (mPercent <= 1) {
                    mExperienceSinkView.setPercent(mPercent);
                    mPercent += 0.01f;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当进度条到100%时，重置初始百分比
                mPercent = (float) Math.random();
                mExperienceSinkView.setPercent(mPercent);
            }
        });
        mThread.start();
    }

    public static final String KEY_Text_Time="Text_Time";
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.close1:
                Merchants_experience.this.finish();
                break;
            case R.id.experience_sinkView:
                check();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String data=format.format(System.currentTimeMillis());
                mExperienceTime.setText(""+data);
                String mExperienceTimes=mExperienceTime.getText().toString().trim();
                if(!TextUtils.isEmpty(mExperienceTimes)){
                    CacheUntil.putString(this,KEY_Text_Time,mExperienceTime.getText()+"");
                }
                break;
        }
    }


}
