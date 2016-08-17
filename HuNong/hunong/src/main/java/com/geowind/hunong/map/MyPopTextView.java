package com.geowind.hunong.map;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geowind.hunong.R;

/**
 * Created by zhangwen on 16-7-29.
 */
public class MyPopTextView extends LinearLayout {

    private View mItem;
    private TextView mTv_tltle;
    private TextView mTv_content;
    private TypedArray mTypedArray;
    private String mTitle;
    private String mContent;

    public MyPopTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mItem = View.inflate(context, R.layout.item_map_pop_tv,null);
        mTypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.popWindowTextView, 0, 0);
        mTv_tltle = (TextView) mItem.findViewById(R.id.tv_popTitle);
        mTv_content = (TextView) mItem.findViewById(R.id.tv_popContent);


       try {
           mTitle = mTypedArray.getString(R.styleable.popWindowTextView_title);
           mContent = mTypedArray.getString(R.styleable.popWindowTextView_content);
           mTypedArray.getColor(R.styleable.popWindowTextView_textColor, Color.BLACK);
           setTitle(mTitle);
           setContent(mContent);

       }finally {
           mTypedArray.recycle();
       }
        addView(mItem);


    }

    public MyPopTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setTitle(String title) {
        mTv_tltle.setText(title);
        invalidate();
        requestLayout();
    }

    public void setContent(String content) {
         mTv_content.setText(content);
        invalidate();
        requestLayout();
    }
}
