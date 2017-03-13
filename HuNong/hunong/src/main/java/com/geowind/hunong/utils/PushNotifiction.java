package com.geowind.hunong.utils;

import android.app.Notification;
import android.content.Context;

import com.geowind.hunong.R;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhangwen on 2017/2/27.
 */

public class PushNotifiction {
    public static  void setPushNotificationStyle(Context context,BasicPushNotificationBuilder builder){
        if(builder==null){
            builder = new BasicPushNotificationBuilder(context);
            builder.statusBarDrawable = R.drawable.jpush_notification_icon;
            builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                    | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
            SpTools.setBoolean(context,MyConstants.IS_LIGHT,true);
            SpTools.setBoolean(context,MyConstants.IS_VIBRATE,true);
        }


        boolean showLight=SpTools.getBoolean(context,MyConstants.IS_LIGHT,false);
        boolean vibrate=SpTools.getBoolean(context,MyConstants.IS_VIBRATE,false);
        if(showLight==true&&vibrate==true){
            builder.notificationDefaults = Notification.DEFAULT_SOUND
                    | Notification.DEFAULT_VIBRATE
                    | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        }else if(showLight==true&&vibrate==false){
            builder.notificationDefaults = Notification.DEFAULT_LIGHTS;

        }else if(showLight==false&&vibrate==true){
            builder.notificationDefaults = Notification.DEFAULT_SOUND
                    | Notification.DEFAULT_VIBRATE;


        }

        JPushInterface.setPushNotificationBuilder(1, builder);

    }

}
