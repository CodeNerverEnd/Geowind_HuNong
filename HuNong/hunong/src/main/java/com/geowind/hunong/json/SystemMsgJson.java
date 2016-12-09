package com.geowind.hunong.json;

import com.geowind.hunong.entity.SystemMsg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangwen on 2016/11/28.
 */

public class SystemMsgJson {
    private static Gson gson;

    public static List<SystemMsg> paseJson(String jsonString){
        Type listType = new TypeToken<List<SystemMsg>>(){}.getType();
        if(gson==null)
            //创建gson对象
            gson = new Gson();
        List<SystemMsg> list=gson.fromJson(jsonString,listType);
        return list;

    }
    public static SystemMsg parseJsonObject(String jsonString) {
        Gson gson = new Gson();
        SystemMsg object = gson.fromJson(jsonString, SystemMsg.class);
        return object;
    }
}
