package com.geowind.hunong.json;

/**
 * Created by zhangwen on 16-7-27. 文库的json数据
 */

import com.geowind.hunong.entity.Library;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LibraryJson {

    private static Gson gson;

    public static List<Library> paseJson(String jsonString){
        Type listType = new TypeToken<List<Library>>(){}.getType();
        if(gson==null)
            //创建gson对象
            gson = new Gson();
         List<Library> libraryList=gson.fromJson(jsonString,listType);
        return libraryList;

    }
    public static Library parseJsonObject(String jsonString) {
        Gson gson = new Gson();
        Library library = gson.fromJson(jsonString, Library.class);
        return library;
    }
}
