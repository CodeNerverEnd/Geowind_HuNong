package com.geowind.hunong.global.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;

import com.geowind.hunong.R;
import com.geowind.hunong.global.fragment.RecyclerViewFragment;
import com.geowind.hunong.global.fragment.WebViewFragment;
import com.geowind.hunong.myview.HomeFragment;
import com.geowind.hunong.myview.LibraryFragment;
import com.geowind.hunong.myview.ZixunFragment;
import com.geowind.hunong.myview.MsgFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
/**
 * Created by zhangwen on 16-7-16.
 */
public class MainActivity extends DrawerActivity {
    private ArrayList<Fragment> mFragments;
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
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }


                return null;
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }

    private void initDate() {
        mFragments = new ArrayList<Fragment>();
        mTitleNames = new ArrayList<String>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MsgFragment());
        mFragments.add(new LibraryFragment());
        mFragments.add(new ZixunFragment());
        mTitleNames.add("主页");
        mTitleNames.add("消息");
        mTitleNames.add("文库");
        mTitleNames.add("资讯");

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

//                return mFragments.get(position);
//                return RecyclerViewFragment.newInstance();
                switch (position % 4) {
                    case 0:
                        return RecyclerViewFragment.newInstance();
                    case 1:
                        return RecyclerViewFragment.newInstance();
                    case 2:
                        return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleNames.get(position);
            }
        });


    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mToolbar = mViewPager.getToolbar();
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

    }
}
