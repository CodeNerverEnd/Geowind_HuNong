package com.geowind.hunong.global.activitys;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.application.HunongApplication;
import com.geowind.hunong.global.adapter.MsgDetailAdapter;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhangwen on 2016/11/6.
 */

public class MsgDetailsActivity extends BaseActivity {

    private ListView mLv_msgList;
    private MsgDetailAdapter mAdapter;
    private TextView mTv_title;
    private ImageButton mIb_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_details);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mIb_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        mAdapter = new MsgDetailAdapter(getApplicationContext(),getIntent());
        mLv_msgList.setAdapter(mAdapter);
        mTv_title.setText(getIntent().getStringExtra("msgType"));
    }


    private void initView() {
        mLv_msgList = (ListView) findViewById(R.id.lv_msgDetails);
        mTv_title = (TextView) findViewById(R.id.title);
        mIb_return = (ImageButton) findViewById(R.id.return_btn);
    }

}
