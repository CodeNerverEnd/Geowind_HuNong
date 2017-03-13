package com.example.logaxy.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Light extends AppCompatActivity {

    private TextView showLightStrengthTextView;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        initVIew();
        showLightStrength();

    }

    private void showLightStrength() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];
                showLightStrengthTextView.setText(value + " Lux");

                /*
                屏幕亮度根据关照自动变化，以200Lux为基准按比例调整屏幕亮度,
                大于200亮度调整为最大，小于50为0.25倍
                 */
                float screenLightLevel=value/200.f;
                if(screenLightLevel>1.0f)
                    screenLightLevel=1.0f;
                if(screenLightLevel<0.25f)
                    screenLightLevel=0.25f;
                setScreenBrightness(screenLightLevel);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {


            }
        }, lightSensor, SensorManager.SENSOR_DELAY_UI);

    }

    private void initVIew() {
        showLightStrengthTextView = (TextView) findViewById(R.id.showLightStrengthTextView);
    }

    //调整屏幕亮度，传入参数代表亮度比例
    private void setScreenBrightness(float f){
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.screenBrightness = f;
        localWindow.setAttributes(localLayoutParams);
    }
}
