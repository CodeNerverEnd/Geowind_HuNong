package com.geowind.hunong.application;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by zhangwen on 2016/10/22.
 */

public class HunongApplication extends Application {
    public static String PICTURE_DIR = "sdcard/hunong/pictures/";
    public static final int REQUEST_CODE_TAKE_PHOTO = 4;
    public static final int REQUEST_CODE_SELECT_PICTURE = 6;
    public static final int RESULT_CODE_SELECT_PICTURE = 8;
    public static final int REQUEST_CODE_SELECT_ALBUM = 10;
    public static final int RESULT_CODE_SELECT_ALBUM = 11;
    public static final int REQUEST_CODE_BROWSER_PICTURE = 12;
    public static final int RESULT_CODE_BROWSER_PICTURE = 13;
    public static final int REQUEST_CODE_CROP_PICTURE = 18;
    public static  int NEW_TASK_COUNT=0;
    public static  int NEW_EXPERT_REPLY_COUNT=0;
    public static  int NEW_SYSTEM_MSG_COUNT=0;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
/**
 * 发送key给服务 校验key是否正确
 */
        SDKInitializer.initialize(getApplicationContext());

    }




}
