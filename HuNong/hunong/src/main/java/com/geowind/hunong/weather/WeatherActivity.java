package com.geowind.hunong.weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.utils.LocationUtils;
import com.geowind.hunong.utils.SpTools;
import com.geowind.hunong.weather.tool.WeatherUtils;
import com.geowind.hunong.weather.weatherjson.Result;
import com.geowind.hunong.weather.weatherjson.Weather;
import com.geowind.hunong.weather.weatherjson.Weather_data;

public class WeatherActivity extends Activity {

    private Button btn;

    private TextView cityName;//城市名
    private TextView pm25Value;//pm2.5值
    private TextView Temperature1 ;//温度范围
    private TextView week1;//星期
    private TextView weather1;//天气情况
    private TextView windstrength1;//风力情况
    private TextView temperatureNow;//实时温度

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

    private String selectCity;

    /**
     * 功能：获取selectCityActivity中传回的已选择的城市名
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    selectCity=data.getStringExtra("returnString") ;
                    new WeatherAsyncTask().execute(selectCity);

                }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        btn=(Button)findViewById(R.id.btn);

        cityName        = (TextView) findViewById(R.id.cityName);
        pm25Value       = (TextView) findViewById(R.id.pm25Value);
        week2           = (TextView) findViewById(R.id.week2);
        weather2        = (TextView) findViewById(R.id.weather2);
        windstrength2   = (TextView) findViewById(R.id.windstrength2);
        temperature2    = (TextView) findViewById(R.id.temperature2);
        week3           = (TextView) findViewById(R.id.week3);
        weather3        = (TextView) findViewById(R.id.weather3);
        windstrength3   = (TextView) findViewById(R.id.windstrength3);
        temperature3    = (TextView) findViewById(R.id.temperature3);
        weather1        = (TextView) findViewById(R.id.weather1);
        windstrength1   = (TextView) findViewById(R.id.windstrength1);
        temperatureNow  = (TextView) findViewById(R.id.temperatureNow);
        Temperature1    = (TextView) findViewById(R.id.temperature1);

        //自动定位
        String[] pcd=LocationUtils.getAddr(getApplicationContext());
        selectCity=pcd[1];
        new WeatherAsyncTask().execute(selectCity);

        //按钮监听
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到选择省市区
                Intent intent=new Intent(WeatherActivity.this,WeatherSelectCityActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }


    //异步类
    class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String url;
            String jsonString;
            Weather weather;
            url = WeatherUtils.getURL(params[0]);

            if(isNetworkAvailable(getApplicationContext())){
                jsonString =  WeatherUtils.getJsonString(url);
                SpTools.setString(getApplicationContext(),"key",jsonString);
            }

            else {
                jsonString=SpTools.getString(getApplicationContext(),"key","");
            }
            weather =  WeatherUtils.fromJson(jsonString);
            return weather;
        }

        /**
         * 方法名：onPostExecute(Weather w )
         * 功能：与另开线程类似，用来更新UI
         * @param w
         */
        @Override
        protected void onPostExecute(Weather w ) {
            /**
             * 若一开始使用时没有网,无法拿到天气信息，则只显示当前定位城市，
             * 再若没开启定位权限，显示默认城市北京
             */
            if(w==null){
                String[] pcd=LocationUtils.getAddr(getApplicationContext());
                if(pcd[1]!=null)
                    cityName.setText(pcd[1]);
                else
                    cityName.setText("北京");
            }

            /**
             * 若已在有网环境下使用过，则显示传入的Weather类对象w里的天气信息，此w对象在
             * 当前没网情况下是上一次有网时保存下来的，在当前有网的情况下是当前获取到的
             */
            else{
                Result result = w.getResults().get(0);
                if(result!=null){
                    Weather_data weather_data = result.getWeather_data().get(0);
                    cityName.setText(result.getCurrentCity());

                    String pm2_5 = "".equals(result.getPm25()) ? "75" : result.getPm25();
                    pm25Value.setText("pm2.5 : " + pm2_5);
                    Temperature1.setText(weather_data.getTemperature());

                    String string = weather_data.getDate();
                    weather1.setText(weather_data.getWeather());
                    windstrength1.setText(weather_data.getWind());
                    temperatureNow.setText(string.substring(14, string.length()-1));

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


        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if( cm == null )
                return false;
            NetworkInfo netinfo = cm.getActiveNetworkInfo();
            if (netinfo == null )
                return false;
            if(netinfo.isConnected())
                return true;
            return false;
        }
    }
}
