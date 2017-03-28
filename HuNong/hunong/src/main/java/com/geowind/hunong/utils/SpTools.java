package com.geowind.hunong.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**SharedPreferences 工具类
 * Created by zhangwen on 16-7-15.
 */
public class SpTools {

    //该类中的方法都是静态的

    private static SharedPreferences sp;
    public static void  init(Context context,String name){
        sp=context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    //保存bool类型的数据
    public static void setBoolean(Context context, String key, boolean value){
        sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();//提交保存
    }
    //获取bool类型的数据
    public static  boolean getBoolean(Context context,String key,boolean value){
       sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
        return sp.getBoolean(key,value);
    }
    //保存字符串
    public static void setString(Context context,String key,String value){
         sp=context.getSharedPreferences(MyConstants.CONFIGFILE,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    //获取字符串
    public static String getString(Context context,String key,String value){
         sp=context.getSharedPreferences(MyConstants.CONFIGFILE,Context.MODE_PRIVATE);
        return sp.getString(key,value);
    }
}
