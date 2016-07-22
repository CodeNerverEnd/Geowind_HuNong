package com.geowind.hunong.weather.weatherjson;

/**
 * Created by logaxy on 2016/7/18.
 */
public class Weather_data {
    private String date;
    private String weather;
    private String wind;
    private String temperature;

    public Weather_data() {
    }

    public Weather_data(String date, String weather, String wind,
                        String temperature) {
        this.date = date;
        this.weather = weather;
        this.wind = wind;
        this.temperature = temperature;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}

