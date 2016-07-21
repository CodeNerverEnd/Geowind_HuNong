package com.geowind.hunong.myview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geowind.hunong.global.activitys.MainActivity;

/**
 * Created by zhangwen on 16-7-18.
 */
public abstract class BaseFragment extends Fragment {
    protected MainActivity mMainActivity;//上下文
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity= (MainActivity) getActivity();//获取Fragment所在的Activity
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=initView();
        return root;
    }
    //必须覆盖次方法完成界面的显示
    public abstract View initView();
    //初始化事件和数据
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }
//子类覆盖此方法完成数据的初始化
    public void initData() {

    }
//子类完成此方法完成事件的添加
    public void initEvent() {

    }


}
