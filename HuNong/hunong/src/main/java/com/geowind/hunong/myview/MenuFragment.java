package com.geowind.hunong.myview;

import android.view.View;

import com.geowind.hunong.R;
import com.lidroid.xutils.ViewUtils;

/**
 * Created by zhangwen on 16-7-18.
 */
public class MenuFragment extends BaseFragment {
    @Override
    public View initView() {
        View root=View.inflate(mMainActivity, R.layout.fregment_menu_view,null);
        ViewUtils.inject(this,root);
        return root;
    }
}
