package com.geowind.hunong.utils;

/**
 * Created by zhangwen on 16-7-15.
 */
public interface MyConstants {
    String CONFIGFILE="configfile";//sharePreferences的文件名
    String ISSETUP="issetup";//是否设置过向导
    String ENCODING="utf-8";//编码方式
    String USERNAME="username";//用户名id

    // String LOGINURL="http://115.159.125.122:8080/HuNong/UserServlet";
    String LOGINURL="http://115.159.125.122:8080/MutualAgriculture/UserServlet";
   // String LOGINURL="http://192.168.1.122:8080/MutualAgriculture/UserServlet";//用户登录的URL

    String LOCATION="location";//当前的位置信息
    String ISLOGIN="islogin";//当前登录状态
    //String LibraryURL= "http://115.159.125.122:8080/HuNong/LibraryServlet";
    String LibraryURL= "http://115.159.125.122:8080/MutualAgriculture/LibraryServlet";

    //String TASKURL="http://115.159.125.122:8080/HuNong/TaskServlet";
    String TASKURL="http://115.159.125.122:8080/MutualAgriculture/TaskServlet";

    String LIBRARY_JSON="libraryJson";//文库的json数据
    String HOMEURL="";//主页数据的URL
    String CACHED_APP_KEY="cachedAppkey";//缓存的appkey

    //病虫害反馈处，服务器地址
    String PESTCONTROL_UPLOAD_URL="http://192.168.1.119:8080/MutualAgriculture/insectInfoUploadServlet";
    String REGISTER="http://192.168.1.107:8080/MutualAgriculture/UserServlet";//注册的
    String GETCENTER="http://192.168.1.107:8080/MutualAgriculture/UserServlet";//获取服务中心
}
