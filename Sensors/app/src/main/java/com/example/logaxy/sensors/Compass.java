package com.example.logaxy.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Compass extends AppCompatActivity {

    private SensorManager sensorManager;
    private ImageView compassImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compassImage = (ImageView) findViewById(R.id.compassImage);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //获取加速度传感器
        Sensor acceleromenterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //获取地磁传感器
        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(listener, acceleromenterSensor, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_UI);

    }

    SensorEventListener listener = new SensorEventListener() {

        float[] acceleromenterValues = new float[3];
        float[] magneticValues = new float[3];

        private float lastRotateDegree;

        @Override
        public void onSensorChanged(SensorEvent event) {

            //获取加速度传感器和地磁传感器的数据，并复制到两个数组中去
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                acceleromenterValues = event.values.clone();
            else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                magneticValues = event.values.clone();

            //旋转矩阵R[],用来保存磁场数据和加速度数据
            float[] R = new float[9];
            float[] values = new float[3];

            //根据acceleromenterSensor和magneticSensor的数据填充旋转矩阵R[]
            SensorManager.getRotationMatrix(R, null, acceleromenterValues, magneticValues);
            //根据旋转矩阵R[]填充values[]
            SensorManager.getOrientation(R, values);

            //获取方向角度，实现指南针为旋转背景图片，此处将角度取反
            float rotateDegree = -(float) Math.toDegrees(values[0]);

            //角度变化大于2时，旋转图片
            if (Math.abs(rotateDegree - lastRotateDegree) > 2) {
                RotateAnimation animation = new RotateAnimation(lastRotateDegree, rotateDegree,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);
                compassImage.startAnimation(animation);
                lastRotateDegree = rotateDegree;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
