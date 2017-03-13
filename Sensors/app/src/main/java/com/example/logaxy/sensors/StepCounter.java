package com.example.logaxy.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StepCounter extends AppCompatActivity {

    private TextView stepTextView;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        initView();
        showStep();
    }

    private void showStep() {
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor stepCounterSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                int value= (int) event.values[0];
                stepTextView.setText(value+"");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },stepCounterSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    private void initView() {
        stepTextView= (TextView) findViewById(R.id.stepTextView);
    }
}
