package com.geowind.hunong.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geowind.hunong.R;

/**
 * Created by zhangwen on 2016/10/31.
 */

public class RegisterEditView extends LinearLayout {


    private View mItem;
    private EditText mContent;
    private TypedArray mTypedArray;
    private String mContent1;
    private TextView mWarror;
    private String mHint;
    private Boolean mPassword;

    public RegisterEditView(Context context) {
        super(context);
    }

    public RegisterEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mItem = View.inflate(context, R.layout.register_editview,null);
        mContent = (EditText) mItem.findViewById(R.id.tv_registerView_content);
        mWarror = (TextView) mItem.findViewById(R.id.tv_warror);
        mTypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.registerEditView,0,0);

        try {
            mContent1 = mTypedArray.getString(R.styleable.registerEditView_register_content);
            mHint = mTypedArray.getString(R.styleable.registerEditView_contentHint);
            mPassword = mTypedArray.getBoolean(R.styleable.registerEditView_register_password,false);
        }finally {
            mTypedArray.recycle();
        }
        //如果是密码
        if(mPassword)
        mContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mContent.setHint(mHint);
        mWarror.setText("*");//设置默认的提醒是*
        mWarror.setVisibility(GONE);//设置提醒默认隐藏
        mWarror.setTextColor(Color.RED);//设置提醒颜色为红色
        addView(mItem);

    }
    public  void setContentText(String  text){
        mContent.setText(text);
        invalidate();
        requestLayout();
    }
    //设置提醒文字
    public void setWarrorText(String text){
        mWarror.setText(text);
    }
    public void setWarrorVisibility(int visibility){
        mWarror.setVisibility(visibility);
    }

    public CharSequence getText(){
        return  mContent.getText();
    }

    public RegisterEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
