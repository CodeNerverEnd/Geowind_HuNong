package com.geowind.hunong.global.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by zhangwen on 16-7-18.
 */
public class EnquryBaseTagPage extends BaseTagPage {
    public EnquryBaseTagPage(Context context) {
        super(context);
    }

    @Override
    public void initDate() {
        TextView tv_content=new TextView(context);
        tv_content.setText("资讯的内容");
        tv_content.setGravity(Gravity.CENTER);
        tv_content.setTextSize(25);
        mFl_content.addView(tv_content);
        super.initDate();
    }
}
