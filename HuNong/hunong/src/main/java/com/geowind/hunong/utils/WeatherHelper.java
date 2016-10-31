package com.geowind.hunong.utils;

import android.os.AsyncTask;

import com.geowind.hunong.weather.tool.WeatherUtils;
import com.geowind.hunong.weather.weatherjson.Result;
import com.geowind.hunong.weather.weatherjson.Weather;
import com.geowind.hunong.weather.weatherjson.Weather_data;

/**
 * Created by logaxy on 2016/7/21.
 */
public class WeatherHelper {

    static final  int RAINANDCLOUD=1;               //  雨+云
    static final int RAINANDTHUNDER=2;             //  雨+雷
    static final int RAINANDSNOW=3;                //  雨+雪
    static final int RAINANDOVERCAST=4;            //  雨+阴
    static final int CLOUDOROVERCAST=5;            //  云/阴
    static final int SMOG=6;                       //  雾/霾
    static final int RAIN=7;                       //  雨
    static final int SNOW=8;                       //  雪
    static final int WIND=9;                       //  风
    static final int SANDSTORM=10;                 //  沙
    static final int SUNNY=11;                     //  晴
    static final int DEFAULT=12;                   //  默认


    public String weatherDetails="";

    public void setWeatherDetails(String weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    /**
     * 方法名：getWehtherBaseAddress(String[] pcd)
     * 功能：用于接受一个存有省市区的字符串数组，查询天气情况
     * 参数：字符串数组，pcd[0]、pcd[1]、pcd[2]分别代表省、市、区
     * 返回值：int型常量，代表天气类型
     */
    public  String getWehtherBaseAddress(final String[] pcd){
        new InterimWeatherAsyncTask().execute(pcd[2]);
        return weatherDetails;
    }

    //异步类，获取天气

    class InterimWeatherAsyncTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String url = WeatherUtils.getURL(params[0]);
            String jsonStr =  WeatherUtils.getJsonString(url);
            Weather weather =  WeatherUtils.fromJson(jsonStr);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather w ) {
            Result result = w.getResults().get(0);
            if(result!=null){
                Weather_data weather_data = result.getWeather_data().get(0);
                setWeatherDetails(weather_data.getWeather());
            }
        }
    }

    /**
     * 方法名：Sort(String weatherDetails)
     * 功能：用于接受一个描述天气的字符串，然后检测该字符串是否
     *      拥有特殊天气关键字的一个或多个，进而查询天气类型
     * 参数：描述天气的字符串weatherDetails
     * 返回值：int型常量，代表天气类型
     */
    public int Sort(String weatherDetails){

        if((weatherDetails.indexOf("雨")!=-1)&&(weatherDetails.indexOf("云")!=-1))
            return RAINANDCLOUD;
        else if((weatherDetails.indexOf("雨")!=-1)&&(weatherDetails.indexOf("雷")!=-1))
            return RAINANDTHUNDER;
        else if((weatherDetails.indexOf("雨")!=-1)&&(weatherDetails.indexOf("雪")!=-1))
            return RAINANDSNOW;
        else if((weatherDetails.indexOf("雨")!=-1)&&(weatherDetails.indexOf("阴")!=-1))
            return RAINANDOVERCAST;
        else if((weatherDetails.indexOf("云")!=-1)||(weatherDetails.indexOf("阴")!=-1))
            return CLOUDOROVERCAST;
        else if((weatherDetails.indexOf("雾霾")!=-1)||(weatherDetails.indexOf("阴")!=-1))
            return SMOG;
        else if((weatherDetails.indexOf("雨")!=-1))
            return RAIN;
        else if((weatherDetails.indexOf("雪")!=-1))
            return SNOW;
        else if((weatherDetails.indexOf("风")!=-1))
            return WIND;
        else if((weatherDetails.indexOf("沙")!=-1))
            return SANDSTORM;
        else if((weatherDetails.indexOf("晴")!=-1))
            return SUNNY;
        else
            return DEFAULT;
    }

    /**
     * 方法名：getWeatherTypeBaseAddress(String[] pcd)
     * 功能： 根据省市区查询天气类型
     * 参数： 字符串数组，pcd[0]、pcd[1]、pcd[2]分别代表省、市、区
     * 返回值： int型常量，代表天气类型
     */
    public int getWeatherTypeBaseAddress(String[] pcd){
        String weatherDetails1=getWehtherBaseAddress(pcd);
        return Sort(weatherDetails1);
    }
}
