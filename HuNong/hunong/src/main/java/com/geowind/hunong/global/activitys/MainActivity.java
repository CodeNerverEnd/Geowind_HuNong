package com.geowind.hunong.global.activitys;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;


import com.geowind.hunong.R;
import com.geowind.hunong.global.fragment.BbsFragment;
import com.geowind.hunong.global.fragment.LibraryRecyclerViewFragment;
import com.geowind.hunong.global.fragment.HomeScrollViewFragment;
import com.geowind.hunong.global.fragment.MessageFragment;
import com.geowind.hunong.utils.JpushUtil;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by zhangwen on 16-7-16.
 */
public class MainActivity extends DrawerActivity {
    private final static String TAG = "MainActivity";
    private MaterialViewPager mViewPager;
    private Toolbar mToolbar;
    private ArrayList<String> mTitleNames;
    public static boolean isForeground = false;
    private ImageView mAddImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDate();
        initEvent();
    }
    private void showFloatButton() {
        mAddImage = new ImageView(getApplicationContext());
        mAddImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
    }
    private void initEvent() {
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.colorAccent),getResources().getDrawable(R.drawable.selector_header_bg));
            }

        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    }


    private void initDate() {
       //极光推送的初始化
        registerMessageReceiver();  // used for receive msg
        String alias= SpTools.getString(getApplicationContext(), MyConstants.USERNAME,"");
        //为极光推送设置别名
        JPushInterface.setAlias(getApplicationContext(),alias , new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                //设置别名后返回的结果

            }
        });
        //主页的viewpager标签
        mTitleNames=new ArrayList<String>();
        mTitleNames.add("主页");
        mTitleNames.add("消息");
        mTitleNames.add("论坛");
        mTitleNames.add("文库");
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

                switch (position % 4) {
                    case 0://主页
                        return HomeScrollViewFragment.newInstance();
                    case 1://消息
                          return MessageFragment.newInstance();
                    case 2://论坛
                         return BbsFragment.newInstance();
                    case 3://文库
                        return LibraryRecyclerViewFragment.newInstance();
                    default:
                        return HomeScrollViewFragment.newInstance();
                }
            }


            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleNames.get(position);
            }
        });


        //测试极光推送
        String udid =  JpushUtil.getImei(getApplicationContext(), "");

        String appKey = JpushUtil.getAppKey(getApplicationContext());
        if (null == appKey) appKey = "AppKey异常";

    }

    //接收来自服务器自定义的消息
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!JpushUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

//对接收到的消息进行处理
    private void setCostomMsg(String msg){
       System.out.println("接收到的极光消息："+msg);
    }


    private void initView() {

        setContentView(R.layout.fragment_main);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mToolbar = mViewPager.getToolbar();
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
        //显示浮动按钮
        showFloatButton();
    }

    @Override
    public void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMessageReceiver);
    }
}
