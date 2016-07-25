package com.geowind.hunong.myview;
import android.view.View;

import com.geowind.hunong.R;
import com.lidroid.xutils.ViewUtils;

/**
 * Created by zhangwen on 16-7-24.
 */
//主页Fragment
public class HomeFragment extends BaseFragment {
    @Override
    public View initView() {
        View root=View.inflate(mMainActivity, R.layout.home,null);
        ViewUtils.inject(this,root);
        return root;
    }

    @Override
    public void initData() {

    }
}
