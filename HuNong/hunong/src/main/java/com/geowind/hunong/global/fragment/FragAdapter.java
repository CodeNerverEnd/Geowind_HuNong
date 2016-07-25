package com.geowind.hunong.global.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwen on 16-7-24.
 */
public class FragAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    public FragAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        mFragments=fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 4) {
            case 0:
                return "主页";
            case 1:
                return "消息";
            case 2:
                return "文库";
            case 3:
                return "地图";
        }
        return "";
    }
}
