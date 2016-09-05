package com.geowind.hunong.global.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.application.JChatDemoApplication;
import com.geowind.hunong.global.fragment.BbsScrollViewFragment;
import com.geowind.hunong.global.fragment.LibraryRecyclerViewFragment;
import com.geowind.hunong.global.fragment.HomeScrollViewFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.jchat.android.activity.ConversationListFragment;
import com.jchat.android.activity.CropImageActivity;
import com.jchat.android.activity.FixProfileActivity;
import com.jchat.android.activity.LoginActivity;
import com.jchat.android.activity.ReloginActivity;
import com.jchat.android.chatting.utils.FileHelper;
import com.jchat.android.chatting.utils.HandleResponseCode;
import com.jchat.android.chatting.utils.SharePreferenceManager;

import java.io.File;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by zhangwen on 16-7-16.
 */
public class MainActivity extends DrawerActivity {
    private final static String TAG = "MainActivity";
    private MaterialViewPager mViewPager;
    private Toolbar mToolbar;
    private ArrayList<String> mTitleNames;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDate();
        initEvent();
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
                          return ConversationListFragment.newInstance();
                    case 2://论坛
                         return BbsScrollViewFragment.newInstance();
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
    }

    @Override
    public void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        //第一次登录需要设置昵称
        boolean flag = SharePreferenceManager.getCachedFixProfileFlag();
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (myInfo == null) {
            Intent intent = new Intent();
            if (null != SharePreferenceManager.getCachedUsername()) {
                intent.putExtra("userName", SharePreferenceManager.getCachedUsername());
                intent.putExtra("avatarFilePath", SharePreferenceManager.getCachedAvatarPath());
                intent.setClass(this, ReloginActivity.class);
            } else {
                intent.setClass(this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        } else {
            JChatDemoApplication.setPicturePath(myInfo.getAppKey());
            if (TextUtils.isEmpty(myInfo.getNickname()) && flag) {
                Intent intent = new Intent();
                intent.setClass(this, FixProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }
//        mConversationListFragment.sortConvList();
        super.onResume();
    }


}
