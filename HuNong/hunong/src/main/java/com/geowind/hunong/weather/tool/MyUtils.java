package com.geowind.hunong.weather.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by logaxy on 2016/9/27.
 */

public class MyUtils {

    /**
     * 方法名：pm2_5Rank(String pm2_5)
     * 功能：判断pm2.5等级
     * @param pm2_5
     * @return
     */
    public static int pm2_5Rank(String pm2_5){
        if(pm2_5!=null){
            int pm=Integer.parseInt(pm2_5);
            if (pm<=50)
                return 1;//优
            if (pm<=100)
                return 2;//良
            if (pm<=200)
                return 3;//中
            if (pm>200)
                return 4;//差
        }
        return 0;//异常、无数据
    }

    /**
     * 方法名 ：isNetworkAvailable(Context context)
     * 功能：判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if( cm == null )
            return false;
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo == null )
            return false;
        if(netinfo.isConnected())
            return true;
        return false;
    }
}
