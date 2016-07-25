package com.geowind.hunong.myview;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.geowind.hunong.R;
import com.geowind.hunong.global.fragment.FragAdapter;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
/**
 * Created by zhangwen on 16-7-18.
 */
//主界面的fragment
public class ContentFragment extends BaseFragment {

    private ArrayList<Fragment> mFragments;
    @ViewInject(R.id.materialViewPager)
    private MaterialViewPager mViewPager;
    private android.support.v7.widget.Toolbar mToolbar;
    @Override
    public View initView() {
        View root=View.inflate(mMainActivity, R.layout.activity_main,null);
        ViewUtils.inject(this,root);
//        mToolbar = mViewPager.getToolbar();
        return root;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MsgFragment());
        mFragments.add(new LibraryFragment());
        mFragments.add(new ZixunFragment());
        ViewPager viewPager = mViewPager.getViewPager();
        viewPager.setAdapter(new FragAdapter(getFragmentManager(),mFragments));
//设置viewPager的adapter
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        super.initData();
    }

    @Override
    public void initEvent() {
        //设置ViewPager的监听事件
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
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
        super.initEvent();
    }
    }

