package com.geowind.hunong.json;

import com.geowind.hunong.entity.Library;
import com.geowind.hunong.entity.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhangwen on 16-7-29.
 */

//[{"id":"0","latitude":"26.893444","lontitude":"112.613454","address":"西渡村B区","taskType":"收割","plantsType":"水稻","machineName":"收割机0143546","taskTime":"2016年8月8日","taskState":"已完成","imgUrl":"http://img.taopic.com/uploads/allimg/110125/292-11012511135849.jpg"},
//        {"id":"2","latitude":"26.895676","lontitude":"112.615442","address":"三塘镇A区","taskType":"播种","plantsType":"玉米","machineName":"无人机04854","taskTime":"2016年8月9日","taskState":"正在进行","imgUrl":"http://www.microfotos.com/pic/1/152/15273/1527300preview4.jpg"},
//        {"id":"3","latitude":"26.893445","lontitude":"112.614566","address":"二塘村B区","taskType":"插秧","plantsType":"水稻","machineName":"插秧机0545546","taskTime":"2016年8月10日","taskState":"未进行","imgUrl":"http://www.microfotos.com/pic/1/122/12253/1225352preview4.jpg"},
//        {"id":"4","latitude":"26.893489","lontitude":"112.614598","address":"白沙洲E区","taskType":"耕作","plantsType":"水稻","machineName":"拖拉机0143546","taskTime":"2016年8月11日","taskState":"未进行","imgUrl":"http://pic2.ooopic.com/12/23/83/95b7OOOPIC67_1024.jpg"}]

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
