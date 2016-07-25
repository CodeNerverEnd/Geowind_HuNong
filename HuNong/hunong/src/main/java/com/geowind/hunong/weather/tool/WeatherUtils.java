package com.geowind.hunong.weather.tool;

import android.content.Context;

import com.geowind.hunong.weather.region.City;
import com.geowind.hunong.weather.region.District;
import com.geowind.hunong.weather.region.Province;
import com.geowind.hunong.weather.weatherjson.Index;
import com.geowind.hunong.weather.weatherjson.Result;
import com.geowind.hunong.weather.weatherjson.Weather;
import com.geowind.hunong.weather.weatherjson.Weather_data;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by logaxy on 2016/7/18.
 *
 * 类名：WeatherUtils
 * 方法：getJsonStr(String url)、getResult(InputStream in)
 *      fromJson(String jsonStr)、getProvinces(Context context)
 */

public class WeatherUtils {


    /**
     * 方法名：getURL(String location)
     * 功能：获得天气相关的URL
     * 参数：location，要获取天气的位置信息
     * 返回值：String url
     */
    public static String getURL(String location) {
        return "http://api.map.baidu.com/telematics/v3/weather?location="
                + location + "&output=json&ak=" +"q9xQH20VoMP3nPxZBUdCinvK1xcIl8tO";
    }

    /**
     *
     * @param in
     * @return
     */
    private static String getResult(InputStream in) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        try {
            while ((len = in.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, len);
                byteArrayOutputStream.flush();
            }
            return new String(byteArrayOutputStream.toByteArray(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法名：getJsonString(String url)
     * 功能：通过传入的url获取对应的JSON数据
     * 参数：String url
     * 返回值：String jsonString
     */
    public static String getJsonString(String url) {
        HttpGet get = new HttpGet(url);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream in = response.getEntity().getContent();
                return getResult(in);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法名：fromJson(String jsonString)
     * 功能： 解析Json数据
     * @param jsonString
     * @return weather
     */

    public static Weather fromJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String error = jsonObject.getString("error");
            String status = jsonObject.getString("status");
            String date = jsonObject.getString("date");
            if (Integer.parseInt(error) != 0) {
                return null;
            } else {
                Weather weather = new Weather();
                weather.setError(error);
                weather.setStatus(status);
                weather.setDate(date);
                List<Result> resultList = new ArrayList<Result>();
                JSONArray rJsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < rJsonArray.length(); i++) {
                    JSONObject  resultsJsonObject= rJsonArray.getJSONObject(i);
                    Result result = new Result();
                    result.setCurrentCity(resultsJsonObject.getString("currentCity"));
                    result.setPm25(resultsJsonObject.getString("pm25"));
                    List<Index> indexList = new ArrayList<Index>();
                    JSONArray indexJsonArray = resultsJsonObject.getJSONArray("index");
                    for (int j = 0; j < indexJsonArray.length(); j++) {
                        JSONObject iJsonObject = indexJsonArray.getJSONObject(i);
                        Index index = new Index();
                        index.setTitle(iJsonObject.getString("title"));
                        index.setZs(iJsonObject.getString("zs"));
                        index.setTipt(iJsonObject.getString("tipt"));
                        index.setDes(iJsonObject.getString("des"));
                        indexList.add(index);
                    }
                    result.setIndex(indexList);

                    List<Weather_data> weather_data = new ArrayList<Weather_data>();
                    JSONArray wdJsonArray = resultsJsonObject.getJSONArray("weather_data");
                    for (int j = 0; j < wdJsonArray.length(); j++) {
                        JSONObject weatherDataJsonObject = wdJsonArray.getJSONObject(j);
                        Weather_data weatherData = new Weather_data();
                        weatherData.setDate(weatherDataJsonObject.getString("date"));
                        weatherData.setWeather(weatherDataJsonObject.getString("weather"));
                        weatherData.setWind(weatherDataJsonObject.getString("wind"));
                        weatherData.setTemperature(weatherDataJsonObject.getString("temperature"));
                        weather_data.add(weatherData);
                    }
                    result.setWeather_data(weather_data);
                    result.setIndex(indexList);
                    resultList.add(result);
                }
                weather.setResults(resultList);
                return weather;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法名：getProvinces(Context context)
     * 功能：解析省市区XML文件，获取Province类型集合
     * @param context
     * @return List<Province>
     * @throws XmlPullParserException
     * @throws IOException
     */

    public static List<Province> getProvinces(Context context)
            throws XmlPullParserException, IOException {
        List<Province> provinces = null;
        Province province = null;
        List<City> citys = null;
        City city = null;
        List<District> districts = null;
        District district =null;

        InputStream in = context.getResources().openRawResource(
                R.raw.citys_weather);

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(in, "utf-8");
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    provinces = new ArrayList<Province>();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if ("p".equals(tagName)) {
                        province = new Province();
                        citys = new ArrayList<City>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("p_id".equals(attrName))
                                province.setId(attrValue);
                        }
                    }
                    if ("pn".equals(tagName)) {
                        province.setName(parser.nextText());
                    }
                    if ("c".equals(tagName)) {
                        city = new City();
                        districts = new ArrayList<District>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("c_id".equals(attrName))
                                city.setId(attrValue);
                        }
                    }
                    if ("cn".equals(tagName)) {
                        city.setName(parser.nextText());
                    }
                    if ("d".equals(tagName)) {
                        district = new District();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("d_id".equals(attrName))
                                district.setId(attrValue);
                        }
                        district.setName(parser.nextText());
                        districts.add(district);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("c".equals(parser.getName())) {
                        city.setDisList(districts);
                        citys.add(city);
                    }
                    if ("p".equals(parser.getName())) {
                        province.setCitys(citys);
                        provinces.add(province);
                    }
                    break;
            }
            event = parser.next();
        }
        return provinces;
    }
}
