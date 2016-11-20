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
    /*
    1、雨+云
    2、雨+雷
    3、雨+雪
    4、雨+阴
    5、雪/冰
    6、云/阴
    7、雾/霾
    8、雨
    9、风
    10、沙
    11、晴
    12、默认
    */
    static final int RAINandCLOUD=1;
    static final int RAINandTHUNDER=2;
    static final int RAINandSNOW=3;
    static final int RAINandOVERCAST=4;
    static final int SNOWorICE=5;
    static final int CLOUDorOVERCAST=6;
    static final int SMOG=7;
    static final int RAIN=8;
    static final int WIND=9;
    static final int SandSTorM=10;
    static final int SUNNY=11;
    static final int DEFAULT=12;


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
            return RAINandCLOUD;
        else if((weatherDetails.indexOf("雨")!=-1)&&(weatherDetails.indexOf("雷")!=-1))
            return RAINandTHUNDER;
        else if((weatherDetails.indexOf("雨")!=-1)&&(weatherDetails.indexOf("雪")!=-1))
            return RAINandSNOW;
        else if((weatherDetails.indexOf("雨")!=-1)||(weatherDetails.indexOf("阴")!=-1))
            return RAINandOVERCAST;
        else if((weatherDetails.indexOf("雪")!=-1)||(weatherDetails.indexOf("冰")!=-1))
            return SNOWorICE;
        else if((weatherDetails.indexOf("云")!=-1)||(weatherDetails.indexOf("阴")!=-1))
            return CLOUDorOVERCAST;
        else if((weatherDetails.indexOf("雾")!=-1)||(weatherDetails.indexOf("霾")!=-1))
            return SMOG;
        else if((weatherDetails.indexOf("雨")!=-1))
            return RAIN;
        else if((weatherDetails.indexOf("风")!=-1))
            return WIND;
        else if((weatherDetails.indexOf("沙")!=-1))
            return SandSTorM;
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
