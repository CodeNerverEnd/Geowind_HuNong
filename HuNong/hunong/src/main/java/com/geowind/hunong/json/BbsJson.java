package com.geowind.hunong.json;

import com.geowind.hunong.entity.BBs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ${zhangwen} on 2016/10/27.
 */

public class BbsJson {
 private static Gson gson;
    public static List<BBs> parseJson(String jsonString){
        if(gson==null){
            gson=new Gson();
        }
        Type listType=new TypeToken<List<BBs>>(){}.getType();
        return gson.fromJson(jsonString,listType);
    }
}
