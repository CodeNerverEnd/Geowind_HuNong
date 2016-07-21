package com.geowind.hunong.global.basepage;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.geowind.hunong.R;

/**
 * Created by zhangwen on 16-7-18.
 */
//界面上面标题栏布局基类
public class BaseTagPage {
    Context context;
   protected View mRoot;//界面的根布局
//   protected ImageButton mIb_menu;
//   protected TextView mTv_title;
   protected FrameLayout mFl_content;

    public BaseTagPage(Context context){
        this.context=context;
        initView();
        initDate();
        initEvent();
    }

    public void initEvent() {

    }

    public void initDate() {
    }

    public void initView() {
        mRoot = View.inflate(context, R.layout.fragment_base_content,null);
//        mIb_menu = (ImageButton) mRoot.findViewById(R.id.bt_menu);
//        mTv_title = (TextView) mRoot.findViewById(R.id.tv_base_title);
        mFl_content = (FrameLayout) mRoot.findViewById(R.id.fl_base_content);

    }
    public View getRoot(){
        return mRoot;
    }
}
