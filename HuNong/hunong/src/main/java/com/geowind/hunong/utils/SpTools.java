package com.geowind.hunong.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangwen on 16-7-15.
 */
public class SpTools {
    public static void setBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();//提交保存
    }
    public static  boolean getBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
        return sp.getBoolean(key,value);
    }
    public static void setString(Context context,String key,String value){
        SharedPreferences sp=context.getSharedPreferences(MyConstants.LOCATION,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    public static String getString(Context context,String key,String value){
        SharedPreferences sp=context.getSharedPreferences(MyConstants.LOCATION,Context.MODE_PRIVATE);
        return sp.getString(key,value);
    }
}
