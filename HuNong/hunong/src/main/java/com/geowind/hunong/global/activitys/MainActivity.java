package com.geowind.hunong.global.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.geowind.hunong.R;
import com.geowind.hunong.global.fragment.BbsScrollViewFragment;
import com.geowind.hunong.global.fragment.LibraryRecyclerViewFragment;
import com.geowind.hunong.global.fragment.MsgRecyclerViewFragment;
import com.geowind.hunong.global.fragment.HomeScrollViewFragment;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
/**
 * Created by zhangwen on 16-7-16.
 */
public class MainActivity extends DrawerActivity {
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
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://dongying.dzwww.com/mldy/tsny/200612/W020061227331797817094.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://pic.ltpic.cn/list_thumb_temp/20100809/1281347222406006883tndiwy.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://pic.58pic.com/58pic/15/75/10/29558PICBQK_1024.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://a4.att.hudong.com/72/51/01300000332400126398510292582.jpg");
                }


                return HeaderDesign.fromColorResAndUrl(R.color.colorAccent,null);
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
                        return MsgRecyclerViewFragment.newInstance();
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
}
