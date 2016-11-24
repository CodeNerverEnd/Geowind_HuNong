package com.geowind.hunong.json;

import com.geowind.hunong.entity.LibSearch;
import com.geowind.hunong.entity.Library;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangwen on 2016/11/22.
 */

public class LibSearchJson {
    private static Gson gson;

    public static List<LibSearch> paseJson(String jsonString){
        Type listType = new TypeToken<List<LibSearch>>(){}.getType();
        if(gson==null)
            //创建gson对象
            gson = new Gson();
        List<LibSearch> libraryList=gson.fromJson(jsonString,listType);
        return libraryList;

    }
    public static LibSearch parseJsonObject(String jsonString) {
        Gson gson = new Gson();
        LibSearch libSearch = gson.fromJson(jsonString, LibSearch.class);
        return libSearch;
    }
}
