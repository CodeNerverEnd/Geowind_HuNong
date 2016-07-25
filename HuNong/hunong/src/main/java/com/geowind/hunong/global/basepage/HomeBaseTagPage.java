package com.geowind.hunong.global.basepage;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;


import com.geowind.hunong.R;

/**
 * Created by zhangwen on 16-7-18.
 */
public class HomeBaseTagPage extends BaseTagPage {

    private View mView;
    private ImageButton mIb_weather;

    public HomeBaseTagPage(Context context) {
        super(context);}
    @Override
    public void initData() {
        mView = View.inflate(context, R.layout.home,null);
        mIb_weather = (ImageButton) mView.findViewById(R.id.ib_weather);
        mFl_content.addView(mView);
        super.initData();
    }
    @Override
    public void initEvent() {
        super.initEvent();
    }
}
