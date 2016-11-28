package com.geowind.hunong.weather;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.geowind.hunong.utils.LocationUtils;
import com.geowind.hunong.utils.SpTools;
import com.geowind.hunong.utils.WeatherHelper;
import com.geowind.hunong.weather.WeatherSelectCity.activity.SelectCityActivity;
import com.geowind.hunong.weather.tool.MyUtils;
import com.geowind.hunong.weather.tool.WeatherUtils;
import com.geowind.hunong.weather.weatherjson.Result;
import com.geowind.hunong.weather.weatherjson.Weather;
import com.geowind.hunong.weather.weatherjson.Weather_data;

public class WeatherActivity extends BaseActivity {

    private RelativeLayout rootRelativeLayout;
    private LinearLayout linearLayout3;

    /**
     * 固定不变的几张图片，在代码中指定图片资源
     */
    private ImageView imageView_today;//今天
    private ImageView imageView_tomorrow;//明天
    private ImageView imageView_aftertomorrow;//后天
    private ImageView presentWeather;//即时温度
    private ImageView line;//分割线

    /**
     * 包含明天后天天气信息的两个layout，在代码中指定背景图片
     */
    private LinearLayout secondDayLayout;
    private LinearLayout thirdDayLayout;


    private ImageButton btn;
    private TextView cityName;//城市名
    private TextView pm25Value;//pm2.5值
    private ImageView pm2_5Rank;//pm2.5等级
    private TextView Temperature1;//温度范围
    private TextView date;
    private TextView temperatureUnit;//温度单位
    private TextView weather1;//天气情况
    private TextView windstrength1;//风力情况
    private TextView temperatureNow;//实时温度

    //第二天天气数据
    private TextView week2;
    private TextView weather2;
    private TextView windstrength2;
    private TextView temperature2;
    private ImageView image2;
    //第三天天气数据
    private TextView week3;
    private TextView weather3;
    private TextView windstrength3;
    private TextView temperature3;
    private ImageView image3;

    private String selectCity;

    private int weatherType2;
    private int weatherType3;
    private WeatherHelper weatherHelper = new WeatherHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initTitleBar();
        initView();

        //自动定位
        String[] pcd = LocationUtils.getAddr(getApplicationContext());
        selectCity = pcd[1];
        new WeatherAsyncTask().execute(selectCity);

        //按钮监听
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到选择省市区
                Intent intent = new Intent(WeatherActivity.this, SelectCityActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initTitleBar() {

        TextView title;
        ImageButton returnButton;

        title = (TextView) findViewById(R.id.title);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("天气预报");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        rootRelativeLayout = (RelativeLayout) findViewById(R.id.rootRelativeLayout);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);

        imageView_today = (ImageView) findViewById(R.id.imageView_today);
        imageView_tomorrow = (ImageView) findViewById(R.id.imageView_tomorrow);
        imageView_aftertomorrow = (ImageView) findViewById(R.id.imageView_aftertomorrow);
        secondDayLayout = (LinearLayout) findViewById(R.id.secondDayLayout);
        thirdDayLayout = (LinearLayout) findViewById(R.id.thirdDayLayout);

        btn = (ImageButton) findViewById(R.id.btn);
        cityName = (TextView) findViewById(R.id.cityName);
        pm25Value = (TextView) findViewById(R.id.pm25Value);
        pm2_5Rank = (ImageView) findViewById(R.id.pm2_5Rank);
        presentWeather = (ImageView) findViewById(R.id.presentWeather);
        date = (TextView) findViewById(R.id.date);
        temperatureUnit = (TextView) findViewById(R.id.temperatureUnit);
        line = (ImageView) findViewById(R.id.line);
        week2 = (TextView) findViewById(R.id.week2);
        weather2 = (TextView) findViewById(R.id.weather2);
        windstrength2 = (TextView) findViewById(R.id.windstrength2);
        temperature2 = (TextView) findViewById(R.id.temperature2);
        image2 = (ImageView) findViewById(R.id.image2);
        week3 = (TextView) findViewById(R.id.week3);
        weather3 = (TextView) findViewById(R.id.weather3);
        windstrength3 = (TextView) findViewById(R.id.windstrength3);
        temperature3 = (TextView) findViewById(R.id.temperature3);
        image3 = (ImageView) findViewById(R.id.image3);
        weather1 = (TextView) findViewById(R.id.weather1);
        windstrength1 = (TextView) findViewById(R.id.windstrength1);
        temperatureNow = (TextView) findViewById(R.id.temperatureNow);
        Temperature1 = (TextView) findViewById(R.id.temperature1);
    }


    //异步类
    class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String url;
            String jsonString;
            Weather weather;
            url = WeatherUtils.getURL(params[0]);

            if (MyUtils.isNetworkAvailable(getApplicationContext())) {
                jsonString = WeatherUtils.getJsonString(url);
                SpTools.setString(getApplicationContext(), "key", jsonString);
            } else {
                jsonString = SpTools.getString(getApplicationContext(), "key", "");
            }
            weather = WeatherUtils.fromJson(jsonString);
            return weather;
        }

        /**
         * 方法名：onPostExecute(Weather w )
         * 功能：更新UI，与另开线程类似
         *
         * @param w
         */
        @Override
        protected void onPostExecute(Weather w) {
            /**
             * 若一开始使用时没有网,无法拿到天气信息，则只显示当前定位城市，
             * 再若没开启定位权限，显示默认城市北京
             */
            if (w == null) {
                rootRelativeLayout.setBackgroundResource(R.drawable.ic_launcher);

            }

            /**
             * 若已在有网环境下使用过，则显示传入的Weather类对象w里的天气信息，此w对象在
             * 当前没网情况下是上一次有网时保存下来的，在当前有网的情况下是当前获取到的
             */
            else {
                linearLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rootRelativeLayout.setBackgroundColor(Color.parseColor("#ECECEC"));

                Result result = w.getResults().get(0);
                if (result != null) {
                    Weather_data weather_data = result.getWeather_data().get(0);

//                    btn.setImageResource(R.mipmap.select );
                    btn.setBackgroundResource(R.mipmap.select);

                    imageView_today.setImageResource(R.mipmap.today);

                    /**
                     * 显示城市名
                     */
                    cityName.setText(result.getCurrentCity());

                    /**
                     * 显示实时温度
                     */
                    String string = weather_data.getDate();
                    String s = string.substring(14, string.length() - 1);
                    temperatureNow.setText(s.replace("℃", ""));

                    temperatureUnit.setText("℃");//显示“℃”

                    String pm2_5 = "".equals(result.getPm25()) ? "75" : result.getPm25();
                    pm25Value.setText("PM2.5: " + pm2_5);

                    /**
                     * 按xx/xx的格式显示日期
                     */
                    date.setText(w.getDate().substring(5, 10).replace("-", "/"));


                    Temperature1.setText(MyUtils.changeTempFormat(weather_data.getTemperature()));
                    presentWeather.setImageResource(R.mipmap.presentweather);
                    weather1.setText(weather_data.getWeather());
                    windstrength1.setText(weather_data.getWind());
                    line.setImageResource(R.mipmap.line);

                    /**
                     * 显示第二天天气基本数据
                     */
                    weather_data = result.getWeather_data().get(1);

                    temperature2.setText(MyUtils.changeTempFormat(weather_data.getTemperature()));
                    week2.setText(weather_data.getDate());
                    weather2.setText(weather_data.getWeather());
                    windstrength2.setText(weather_data.getWind());
                    secondDayLayout.setBackgroundResource(R.mipmap.rounded);
                    imageView_tomorrow.setImageResource(R.mipmap.tomorrow);

                    weatherType2 = weatherHelper.Sort(weather_data.getWeather());

                    /**
                     * 显示第三天天气基本数据
                     */
                    weather_data = result.getWeather_data().get(2);

                    week3.setText(weather_data.getDate());
                    weather3.setText(weather_data.getWeather());
                    windstrength3.setText(weather_data.getWind());
                    temperature3.setText(MyUtils.changeTempFormat(weather_data.getTemperature()));
                    thirdDayLayout.setBackgroundResource(R.mipmap.rounded);
                    imageView_aftertomorrow.setImageResource(R.mipmap.aftertomorrow);

                    weatherType3 = weatherHelper.Sort(weather_data.getWeather());

                    int PmType = MyUtils.pm2_5Rank(pm2_5);
                    pm2_5Rank.setImageResource(MyUtils.getResourceBasem2_5Rank(PmType));
                    image2.setImageResource(MyUtils.getResourceBaseWertherType(weatherType2));
                    image3.setImageResource(MyUtils.getResourceBaseWertherType(weatherType3));


                }
            }
        }
    }

    /**
     * 功能：获取selectCityActivity中传回的已选择的城市名
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    selectCity = data.getStringExtra("returnString");
                    new WeatherAsyncTask().execute(selectCity);

                }
        }

    }

}
