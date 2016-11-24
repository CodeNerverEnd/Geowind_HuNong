package com.geowind.hunong.global.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.geowind.hunong.R;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;


import java.util.List;


/**
 *
 * Created by zhangwen on 16-7-15.
 */
public class SplashActivity  extends Activity{
    private AnimationSet mAs;
    private RelativeLayout mRl_splash;
    public static List<ImageView> mSlidingImgs;
    Library mLibrary;
    public BDLocationListener myListener ;
    private BDLocation mCurrentLocation;//当前位置
    public LocationClient mLocationClient = null;
    private boolean isFristLocate=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        startAnimation();
        initEvent();

    }

    private void initData() {
        //初始化定位数据
        myListener=new MyLocationListener();
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );    //注册监听函数

        initLocation();

    }

    //设置动画的监听事件
    private void initEvent() {
        mAs.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mLocationClient.start();
                //联网操作
//                getDataFromNet();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               if(SpTools.getBoolean(getApplicationContext(), MyConstants.ISSETUP,false)){
                   if(SpTools.getBoolean(getApplicationContext(),MyConstants.ISLOGIN,false)){
                       //进入主界面
                       Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(intent);
                       finish();
                   }else{
                       //进入登录界面
                       Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                       startActivity(intent);
                       finish();
                   }

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
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mRl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
    }
    private void startAnimation() {
        mAs = new AnimationSet(false);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
        mAs.setDuration(1000);
        mAs.setFillAfter(true);
        mAs.addAnimation(alphaAnimation);
        mRl_splash.startAnimation(mAs);
    }


    public static List<ImageView> getImgFromNet(){
        return mSlidingImgs;
    }


    @Override
    protected void onResume() {

        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
    /**
     * 注册监听事件
     */
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mCurrentLocation=location;
            //保存当前位置
            SpTools.setString(getApplicationContext(), MyConstants.LOCATION,mCurrentLocation.getAddrStr());
            if(isFristLocate){
                isFristLocate=false;
            }else {
                mLocationClient.stop();
            }
            System.out.println("当前定位："+mCurrentLocation.getAddrStr());
        }
    }
    /**
     * 初始化定位参数
     */
    private   void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        mLocationClient.setLocOption(option);
    }
}
