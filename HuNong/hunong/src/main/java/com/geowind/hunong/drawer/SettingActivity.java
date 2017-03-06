package com.geowind.hunong.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.geowind.hunong.global.activitys.CacheActivity;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.PushNotifiction;
import com.geowind.hunong.utils.SpTools;

import cn.jpush.android.api.BasicPushNotificationBuilder;

/**
 * Created by zhangwen on 2016/11/5.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private CheckBox mCb_light;
    private CheckBox mCb_vibrate;
    private TextView mTitle;
    private BasicPushNotificationBuilder mBuilder;
    private ImageButton mIb_return;
    private RelativeLayout mRl_clearCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        initEvent();
    }

    private void initEvent() {
        mCb_light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SpTools.setBoolean(SettingActivity.this,MyConstants.IS_LIGHT,false);
                }else {
                    SpTools.setBoolean(SettingActivity.this,MyConstants.IS_LIGHT,true);
                }
                PushNotifiction.setPushNotificationStyle(SettingActivity.this,mBuilder);
            }
        });
        mCb_vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SpTools.setBoolean(SettingActivity.this,MyConstants.IS_VIBRATE,false);
                }else {
                    SpTools.setBoolean(SettingActivity.this,MyConstants.IS_VIBRATE,true);
                }
                PushNotifiction.setPushNotificationStyle(SettingActivity.this,mBuilder);
            }
        });
        mIb_return.setOnClickListener(this);
        mRl_clearCache.setOnClickListener(this);

    }

    private void init() {
        mBuilder = new BasicPushNotificationBuilder(SettingActivity.this);
        mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText("系统设置");
        mIb_return = (ImageButton) findViewById(R.id.return_btn);
        mCb_light = (CheckBox) findViewById(R.id.push_light);
        mCb_vibrate = (CheckBox) findViewById(R.id.push_vibrate);
        mCb_light.setChecked(SpTools.getBoolean(SettingActivity.this,MyConstants.IS_LIGHT,false));
        mCb_vibrate.setChecked(SpTools.getBoolean(SettingActivity.this,MyConstants.IS_VIBRATE,false));
        mRl_clearCache = (RelativeLayout) findViewById(R.id.clear_cache_rl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_btn:
                finish();
                break;
            case  R.id.clear_cache_rl:
                Intent intent=new Intent(getApplicationContext(), CacheActivity.class);
                startActivity(intent);
                break;
        }
    }
}
