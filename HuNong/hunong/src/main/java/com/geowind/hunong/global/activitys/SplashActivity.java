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
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.geowind.hunong.R;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.json.LibraryJson;
import com.geowind.hunong.loginregist.LoginActvity;
import com.geowind.hunong.utils.LocationUtils;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 *
 * Created by zhangwen on 16-7-15.
 */
public class SplashActivity  extends Activity{
    private ImageView mIv_icon;
    private AnimationSet mAs;
    private RelativeLayout mRl_splash;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private BDLocation mCurrentLocation;//当前位置
    private boolean isFristLocate=true;
    public static List<ImageView> mSlidingImgs;
    Library mLibrary;

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
                getDataFromNet();
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
                       Intent intent=new Intent(getApplicationContext(), LoginActvity.class);
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

    public void getDataFromNet() {
        //请求文库数据
        requstLibrary();
    }
    public static List<ImageView> getImgFromNet(){
        return mSlidingImgs;
    }

    private void requstLibrary() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","getTitles");
        params.add("category",String.valueOf(1));
        params.add("begin",String.valueOf(0));
        client.post(MyConstants.LibraryURL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody);
                SpTools.setString(getApplicationContext(),MyConstants.LIBRARY_JSON,jsonString);
                System.out.println(jsonString);
//                mLibrary=LibraryJson.parseJsonObject(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,""));
                mLibrary= LibraryJson.parseJsonObject(jsonString);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"哎呀，没网了",Toast.LENGTH_SHORT).show();
            }
        });
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
        }
    }

}
