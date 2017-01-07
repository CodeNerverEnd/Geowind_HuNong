package com.geowind.hunong.json;

import com.geowind.hunong.entity.ExpertReply;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangwen on 2016/11/28.
 */

public class ExpertReplyJson {

    private static Gson gson;

    public static List<ExpertReply> paseJson(String jsonString){
        Type listType = new TypeToken<List<ExpertReply>>(){}.getType();
        if(gson==null)
            //创建gson对象
            gson = new Gson();
        List<ExpertReply> list=gson.fromJson(jsonString,listType);
        return list;

    }
    public static ExpertReply parseJsonObject(String jsonString) {
        Gson gson = new Gson();
        ExpertReply object = gson.fromJson(jsonString, ExpertReply.class);
        return object;
    }
}
