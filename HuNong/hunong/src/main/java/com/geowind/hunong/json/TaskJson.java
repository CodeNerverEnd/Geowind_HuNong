package com.geowind.hunong.json;

import com.geowind.hunong.entity.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangwen on 16-7-29.
 */

public class TaskJson {
    private static Gson gson;
    public static List<Task> paseJson(String jsonString){
        Type listType = new TypeToken<List<Task>>(){}.getType();
        if(gson==null)
            //创建gson对象
            gson = new Gson();
        List<Task> list=gson.fromJson(jsonString,listType);
        return list;
    }
}
