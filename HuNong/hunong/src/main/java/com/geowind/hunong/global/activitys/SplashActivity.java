package com.geowind.hunong.global.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.geowind.hunong.R;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;

/**
 *
 * Created by zhangwen on 16-7-15.
 */
public class SplashActivity  extends Activity{
    private ImageView mIv_icon;
    private AnimationSet mAs;
    private RelativeLayout mRl_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        startAnimation();
        initEvent();
    }
//设置动画的监听事件
    private void initEvent() {
        mAs.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               if(SpTools.getBoolean(getApplicationContext(), MyConstants.ISSETUP,false)){
                   //进入主界面
                  Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   finish();
               }else {
                   //进入向导界面
                   Intent intent=new Intent(getApplicationContext(),GuideActivity.class);
                   startActivity(intent);
                   finish();
               }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mIv_icon = (ImageView) findViewById(R.id.iv_icon_hunong);
        mRl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
    }

    private void startAnimation() {
        mAs = new AnimationSet(false);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
        mAs.setDuration(2000);
        mAs.setFillAfter(true);
        mAs.addAnimation(alphaAnimation);
        mRl_splash.startAnimation(mAs);
    }

}
