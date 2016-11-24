package com.geowind.hunong.json;

import com.geowind.hunong.annotation.TableName;
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
    public static Task parseJsonObject(String jsonString) {
        Gson gson = new Gson();
        Task task = gson.fromJson(jsonString, Task.class);
        return task;
    }
}
