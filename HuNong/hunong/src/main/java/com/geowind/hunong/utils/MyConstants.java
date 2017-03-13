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
    String LibraryURL= "http://115.159.125.122:8080/MutualAgriculture/LibraryServlet";//文库
//    String LibraryURL= "http://192.168.1.123:8080/MutualAgriculture/LibraryServlet";//文库
    //String TASKURL="http://115.159.125.122:8080/HuNong/TaskServlet";
   // String TASKURL="http://115.159.125.122:8080/MutualAgriculture/TaskServlet";//任务
    String TASKURL="http://192.168.0.107:8080/MutualAgriculture/TaskServlet";//任务
    String LIBRARY_JSON="libraryJson";//文库的json数据
    String HOMEURL="";//主页数据的URL
    String CACHED_APP_KEY="cachedAppkey";//缓存的appkey
    //病虫害反馈及专家咨询处URL，服务器地址
    String PEST_OR_CONSULT_UPLOAD_URL="http://192.168.1.113:8080/MutualAgriculture/pestOrConsultInfoUploadServlet";
    String REGISTER="http://115.159.125.122:8080/MutualAgriculture/UserServlet";//注册的
    String GETCENTER="http://115.159.125.122:8080/MutualAgriculture/UserServlet";//获取服务中心
    String LIBRARYSEARCH="http://115.159.125.122:8080/MutualAgriculture/LibraryServlet";//文库搜索
//    String LIBRARYSEARCH="http://192.168.1.118:8080/MutualAgriculture/LibraryServlet";//文库搜索
    String DATABASE="honong";//数据库名
    String TABLE_TASK="task";//任务表名
    String TABLE_SYSTEM_MSG="system_msg";//系统消息表
    String TABLE_EXPERT_REPLY="expert_reply";//专家回复
    String IS_LIGHT="isLight";//是否闪烁呼吸灯
    String IS_VIBRATE="isVibrate";//是否震动
    String USER_TYPE="userType";//用户类型
}
