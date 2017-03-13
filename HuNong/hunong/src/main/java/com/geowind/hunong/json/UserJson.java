package com.geowind.hunong.json;


import com.geowind.hunong.entity.User;
import com.google.gson.Gson;

/**
 * Created by zhangwen on 2017/3/5.
 */

public class UserJson {
    private static Gson gson;
    public static User parseJsonObject(String jsonString) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);
        return user;
    }
}
