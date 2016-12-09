package com.geowind.hunong.weather.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.geowind.hunong.R;

/**
 * Created by logaxy on 2016/9/27.
 */

public class MyUtils {

    /**
     * 方法名：pm2_5Rank(String pm2_5)
     * 功能：判断pm2.5等级
     *
     * @param pm2_5
     * @return
     */
    public static int pm2_5Rank(String pm2_5) {
        if (pm2_5 != null) {
            int pm = Integer.parseInt(pm2_5);
            if (pm <= 50)
                return 1;//优
            if (pm <= 100)
                return 2;//良
            if (pm <= 200)
                return 3;//中
            if (pm > 200)
                return 4;//差
        }
        return 0;//异常、无数据
    }


    /**
     * 方法名 ：isNetworkAvailable(Context context)
     * 功能：判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo == null)
            return false;
        if (netinfo.isConnected())
            return true;
        return false;
    }

    /**
     * 功能：将为先高温再低温的温度范围格式数据转化为先低温再高温的格式
     * 例：15~8℃转化为8~15℃
     * （因百度天气接口返回的温度范围格式15~8℃这种，而期望的显示格式
     * 为8~15℃这种）
     *
     * @param string 原始数据
     * @return 转换后的数据
     */
    public static String changeTempFormat(String string) {
        String returnString;
        int indexOfTilde = -1;
        indexOfTilde = string.indexOf("~");

        if (indexOfTilde == -1) {
            returnString = string;
        } else {
            String s, high, low;
            int indexOfTempUnit = -1;
            indexOfTempUnit = string.indexOf("℃");
            s = string.substring(0, indexOfTempUnit);//先去掉单位
            high = s.substring(0, indexOfTilde);
            low = s.substring(indexOfTilde + 1, s.length());
            returnString = low + "~" + high + "℃";//构建新的字符串
        }
        return returnString;
    }

    public static int getResourceBasem2_5Rank(int type) {
        switch (type) {
            case 1:
                return R.mipmap.excellent;
            case 2:
                return R.mipmap.fine;
            case 3:
                return R.mipmap.medium;
            default:
                return R.mipmap.poor;
        }
    }

    public static int getResourceBaseWertherType(int type) {
        switch (type) {
            case 1:
                return R.drawable.weather1;
            case 2:
                return R.drawable.weather2;
            case 3:
                return R.drawable.weather3;
            case 4:
                return R.drawable.weather4;
            case 5:
                return R.drawable.weather5;
            case 6:
                return R.drawable.weather6;
            case 7:
                return R.drawable.weather7;
            case 8:
                return R.drawable.weather8;
            case 9:
                return R.drawable.weather9;
            case 10:
                return R.drawable.weather10;
            case 11:
                return R.drawable.weather11;
            default:
                return R.drawable.weather12;
        }
    }

    public static int getBackgroundResourceBaseWeatherType(int type) {
        switch (type) {
            case 1:
                return R.drawable.weatherbg;
            case 2:
                return R.drawable.weatherbg;
            case 3:
                return R.drawable.weatherbg;
            case 4:
                return R.drawable.weatherbg;
            case 5:
                return R.drawable.weatherbg;
            case 6:
                return R.drawable.weatherbg;
            case 7:
                return R.drawable.weatherbg;
            case 8:
                return R.drawable.weatherbg;
            case 9:
                return R.drawable.weatherbg;
            case 10:
                return R.drawable.weatherbg;
            case 11:
                return R.drawable.weatherbg;
            default:
                return R.drawable.weatherbg;
        }
    }
}
