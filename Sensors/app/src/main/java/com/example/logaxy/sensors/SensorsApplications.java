package com.example.logaxy.sensors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SensorsApplications extends AppCompatActivity implements View.OnClickListener {

    private Button lightSensorButton;
    private Button stepCounterSensorButton;
    private Button compassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_applications);

        initView();
        initListener();
    }

    private void initListener() {
        lightSensorButton.setOnClickListener(this);
        stepCounterSensorButton.setOnClickListener(this);
        compassButton.setOnClickListener(this);
    }

    private void initView() {
        lightSensorButton = (Button) findViewById(R.id.lightSensorButton);
        stepCounterSensorButton = (Button) findViewById(R.id.stepCounterSensorButton);
        compassButton = (Button) findViewById(R.id.compassButton);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.lightSensorButton:
                intent = new Intent(SensorsApplications.this, Light.class);
                break;
            case R.id.stepCounterSensorButton:
                intent = new Intent(SensorsApplications.this, StepCounter.class);
                break;
            case R.id.compassButton:
                intent = new Intent(SensorsApplications.this, Compass.class);
                break;

        }
        if (null != intent)
            startActivity(intent);

    }
}
