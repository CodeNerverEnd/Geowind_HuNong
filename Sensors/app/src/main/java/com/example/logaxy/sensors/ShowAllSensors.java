package com.example.logaxy.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShowAllSensors extends AppCompatActivity {

    private TextView allSensorsTextView;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_sensors);

        initView();
        showSensors();
    }

    private void showSensors() {
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s : sensorList
                ) {
            allSensorsTextView.append(s.getName() + "\n");
        }
    }

    private void initView() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        allSensorsTextView = (TextView) findViewById(R.id.textView);

        //设置滚动条
        allSensorsTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
