package com.geowind.hunong.pestControl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by logaxy on 2016/9/22.
 * 自定义的正方形ImageView，其高度保持与宽度一致
 */
public class MySquareImageView extends ImageView {
    public MySquareImageView(Context context) {
        super(context);
    }
    public MySquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MySquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //高度等于宽度
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
