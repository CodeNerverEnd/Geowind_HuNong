package com.geowind.hunong.pestControl;

import com.geowind.hunong.entity.Insectcontrol;
import com.google.gson.Gson;

/**
 * Created by logaxy on 2016/11/3.
 */
public class ResultJson {
    private static Gson gson;
    public static Insectcontrol paseJson(String jsonString){
        if(gson==null)
            gson=new Gson();
        Insectcontrol insectcontrol=gson.fromJson(jsonString,Insectcontrol.class);
        return insectcontrol;
    }
}
