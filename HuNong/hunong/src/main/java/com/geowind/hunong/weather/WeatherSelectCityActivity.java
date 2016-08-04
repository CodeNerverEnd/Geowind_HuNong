package com.geowind.hunong.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.geowind.hunong.R;
import com.geowind.hunong.weather.region.City;
import com.geowind.hunong.weather.region.District;
import com.geowind.hunong.weather.region.Province;
import com.geowind.hunong.weather.tool.WeatherUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class  WeatherSelectCityActivity extends Activity {

    //省、市、区下拉菜单
    private Spinner spinnerProvince;
    private Spinner spinnerCity;
    private Spinner spinnerDistrict;
    private int currentProvince;
    private ArrayAdapter<Province> provinceAdapter;
    private ArrayAdapter<City> cityAdapter;
    private ArrayAdapter<District> districtAdapter;
    private List<Province> provinces;


    private String returnString;//选择的城市名，返回给WeatherActivity
    private Button ack;//确认按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_select_city);


        spinnerProvince = (Spinner)  findViewById(R.id.spinner1);
        spinnerCity     = (Spinner)  findViewById(R.id.spinner2);
        spinnerDistrict = (Spinner)  findViewById(R.id.spinner3);

        ack= (Button) findViewById(R.id.ack);

        try {
            provinces = WeatherUtils.getProvinces(this);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        provinceAdapter = new ArrayAdapter<Province>(this, R.layout.weather_myspinner,
                android.R.id.text1, provinces);
        provinceAdapter.setDropDownViewResource(R.layout.weather_myspinner);
        spinnerProvince.setAdapter(provinceAdapter);
//        spinnerProvince.setPrompt("省份");

        provinceAdapter.setDropDownViewResource(R.layout.weather_myspinner);

        cityAdapter = new ArrayAdapter<City>(this, R.layout.weather_myspinner,
                android.R.id.text1, provinces.get(0).getCitys());
        cityAdapter.setDropDownViewResource(R.layout.weather_myspinner);
        spinnerCity.setAdapter(cityAdapter);

        districtAdapter = new ArrayAdapter<District>(this, R.layout.weather_myspinner,
                android.R.id.text1, provinces.get(0).getCitys().get(0).getDisList());
        districtAdapter.setDropDownViewResource(R.layout.weather_myspinner);
        spinnerDistrict.setAdapter(districtAdapter);

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                currentProvince = position;
                cityAdapter = new ArrayAdapter<City>(WeatherSelectCityActivity.this,
                        R.layout.weather_myspinner, android.R.id.text1,
                        provinces.get(position).getCitys());
                cityAdapter.setDropDownViewResource(R.layout.weather_myspinner);
                spinnerCity.setAdapter(cityAdapter);
//                spinnerCity.setPrompt("城市");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                districtAdapter = new ArrayAdapter<District>(WeatherSelectCityActivity.this,
                        R.layout.weather_myspinner,
                        android.R.id.text1, provinces.get(currentProvince)
                        .getCitys().get(position).getDisList());
                districtAdapter.setDropDownViewResource(R.layout.weather_myspinner);
                spinnerDistrict.setAdapter(districtAdapter);
//                spinnerDistrict.setPrompt("区县");

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

                returnString=district.getName();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //监听确认按钮，按下按钮则返回WeatherActivity,并把选择的城市名以字符串形式传回
        ack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("returnString",returnString);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
