package com.geowind.hunong.drawer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;

/**
 * Created by ASUS on 2016/10/25.
 */

public class MeInfoActivity extends BaseActivity {

    private TextView mTv_title;
    private ImageButton mById;
    private TextView mTv_nikeName;
    private TextView mTv_gender;
    private TextView mTv_address;
    private TextView mTv_center;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_me_info);
        initView();
        initData();
    }

    private void initData() {
        mTv_title.setText("个人信息");
    }

    private void initView() {
        mTv_title = (TextView) findViewById(R.id.title);
        mById = (ImageButton) findViewById(R.id.return_btn);
        mTv_nikeName = (TextView) findViewById(R.id.nick_name_tv);
        mTv_gender = (TextView) findViewById(R.id.gender_tv);
        mTv_address = (TextView) findViewById(R.id.region_tv);
        mTv_center = (TextView) findViewById(R.id.center_tv);

    }
}
