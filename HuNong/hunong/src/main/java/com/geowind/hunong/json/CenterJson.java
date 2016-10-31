package com.geowind.hunong.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangwen on 2016/10/27.
 */

public class CenterJson {
    private static Gson gson;
    public static List<String> parseJson(String jsonString){
        Type listType=new TypeToken<List<String>>(){}.getType();
        if(gson==null)
            gson=new Gson();
        return gson.fromJson(jsonString,listType);
    }
}
