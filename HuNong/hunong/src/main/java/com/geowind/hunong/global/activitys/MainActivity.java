package com.geowind.hunong.global.activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.geowind.hunong.R;
import com.geowind.hunong.myview.ContentFragment;
import com.geowind.hunong.myview.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

/**
 * Created by zhangwen on 16-7-16.
 */
public class MainActivity extends SlidingActivity {
    private static final String MENUE_TAG = "menue_tag";
    private static final String MAIN_TAG = "main_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        FragmentManager fm=getFragmentManager();
        //获取事务
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //完成替换
        fragmentTransaction.replace(R.id.fl_sliding_left, new MenuFragment(), MENUE_TAG);
       fragmentTransaction.replace(R.id.fl_main,
                new ContentFragment(), MAIN_TAG);
        //提交事务
        fragmentTransaction.commit();

    }

    private void initView() {
        setContentView(R.layout.fragement_main);
        setBehindContentView(R.layout.fragment_sliding_left);
        SlidingMenu menu=getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);//设置滑动模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置全屏滑动
        menu.setBehindOffsetRes(R.dimen.sliding_menue_offset);//设置主界面剩余的位置

    }
}
