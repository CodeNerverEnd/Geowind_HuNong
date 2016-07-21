package com.geowind.hunong.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.geowind.hunong.R;
import com.geowind.hunong.weather.region.City;
import com.geowind.hunong.weather.region.District;
import com.geowind.hunong.weather.region.Province;
import com.geowind.hunong.weather.tool.WeatherUtils;
import com.geowind.hunong.weather.weatherjson.Result;
import com.geowind.hunong.weather.weatherjson.Weather;
import com.geowind.hunong.weather.weatherjson.Weather_data;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class WeatherActivity extends Activity {
    private Spinner spinnerProvince;
    private Spinner spinnerCity;
    private Spinner spinnerDistrict;
    private int currentProvince;
    private ArrayAdapter<Province> provinceAdapter;
    private ArrayAdapter<City> cityAdapter;
    private ArrayAdapter<District> districtAdapter;
    private List<Province> provinces;

    private TextView cityName;//城市名
    private TextView pm25Value;//pm2.5值
    private TextView date;//日期
    private TextView week1;//星期
    private TextView weather1;//天气情况
    private TextView windstrength1;//风力情况
    private TextView temperature1;//温度

    //第二天天气数据
    private TextView week2;
    private TextView weather2;
    private TextView windstrength2;
    private TextView temperature2;
    //第三天天气数据
    private TextView week3;
    private TextView weather3;
    private TextView windstrength3;
    private TextView temperature3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        spinnerProvince = (Spinner) findViewById(R.id.spinner1);
        spinnerCity = (Spinner) findViewById(R.id.spinner2);
        spinnerDistrict = (Spinner) findViewById(R.id.spinner3);

        cityName = (TextView) findViewById(R.id.cityName);
        pm25Value = (TextView) findViewById(R.id.pm25Value);
        date = (TextView) findViewById(R.id.date);

        week2 = (TextView) findViewById(R.id.week2);
        weather2 = (TextView) findViewById(R.id.weather2);
        windstrength2 = (TextView) findViewById(R.id.windstrength2);
        temperature2 = (TextView) findViewById(R.id.temperature2);

        week3 = (TextView) findViewById(R.id.week3);
        weather3 = (TextView) findViewById(R.id.weather3);
        windstrength3 = (TextView) findViewById(R.id.windstrength3);
        temperature3 = (TextView) findViewById(R.id.temperature3);

        week1 = (TextView) findViewById(R.id.week1);
        weather1 = (TextView) findViewById(R.id.weather1);
        windstrength1 = (TextView) findViewById(R.id.windstrength1);
        temperature1 = (TextView) findViewById(R.id.temperature1);

        // 获取到地区信息
        try {
           provinces = WeatherUtils.getProvinces(this);
          // provinces= HttpUtils.getProvinces(getApplicationContext());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }



        provinceAdapter = new ArrayAdapter<Province>(this, R.layout.weather_myspinner,
                android.R.id.text1, provinces);
        provinceAdapter
                .setDropDownViewResource(R.layout.weather_myspinner);
        spinnerProvince.setAdapter(provinceAdapter);

        //设置下拉菜单样式
        provinceAdapter.setDropDownViewResource(R.layout.weather_myspinner);

        cityAdapter = new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item,
                android.R.id.text1, provinces.get(0).getCitys());
        cityAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);


        districtAdapter = new ArrayAdapter<District>(this, R.layout.weather_myspinner,
                android.R.id.text1, provinces.get(0).getCitys().get(0).getDisList());
        districtAdapter
                .setDropDownViewResource(R.layout.weather_myspinner);
        spinnerDistrict.setAdapter(districtAdapter);


//        设置默认值
//        spinnerProvince.setSelection(1,true);
//        spinnerCity.setSelection(1,true);
//        spinnerDistrict.setSelection(1,true);


        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                currentProvince = position;
                cityAdapter = new ArrayAdapter<City>(WeatherActivity.this,
                        R.layout.weather_myspinner, android.R.id.text1,
                        provinces.get(position).getCitys());
                cityAdapter.setDropDownViewResource(R.layout.weather_myspinner);
                spinnerCity.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                districtAdapter = new ArrayAdapter<District>(WeatherActivity.this,
                        R.layout.weather_myspinner,
                        android.R.id.text1, provinces.get(currentProvince)
                        .getCitys().get(position).getDisList());
                districtAdapter.setDropDownViewResource(R.layout.weather_myspinner);
                spinnerDistrict.setAdapter(districtAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                District district= districtAdapter.getItem(position);
                new WeatherAsyncTask().execute(district.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String url = WeatherUtils.getURl(params[0]);
            String jsonStr =  WeatherUtils.getJsonStr(url);
            Weather weather =  WeatherUtils.fromJson(jsonStr);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather r ) {
            Result result = r.getResults().get(0);
            if(result!=null){
                Weather_data weather_data = result.getWeather_data().get(0);
                cityName.setText(result.getCurrentCity());

                String pm2_5 = "".equals(result.getPm25()) ? "75" : result.getPm25();
                pm25Value.setText("pm2.5 : " + pm2_5);
                date.setText(r.getDate());

                String string = weather_data.getDate();
                week1.setText(string.substring(0, 2));
                weather1.setText(weather_data.getWeather());
                windstrength1.setText(weather_data.getWind());
                temperature1.setText(string.substring(14, string.length()-1));

                weather_data = result.getWeather_data().get(1);
                temperature2.setText(weather_data.getTemperature());
                week2.setText(weather_data.getDate());
                weather2.setText(weather_data.getWeather());
                windstrength2.setText(weather_data.getWind());
                temperature2.setText(weather_data.getTemperature());

                weather_data = result.getWeather_data().get(2);

                week3.setText(weather_data.getDate());
                weather3.setText(weather_data.getWeather());
                windstrength3.setText(weather_data.getWind());
                temperature3.setText(weather_data.getTemperature());
            }


        }
    }
}
