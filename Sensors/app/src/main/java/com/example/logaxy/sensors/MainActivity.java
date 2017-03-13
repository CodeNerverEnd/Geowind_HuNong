package com.example.logaxy.sensors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getAllSensorsButton;
    private Button sensorsApplicationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initListener() {
        getAllSensorsButton.setOnClickListener(this);
        sensorsApplicationsButton.setOnClickListener(this);
    }

    private void initView() {
        getAllSensorsButton = (Button) findViewById(R.id.getAllSensorsButton);
        sensorsApplicationsButton = (Button) findViewById(R.id.sensorsApplicationsButton);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.getAllSensorsButton:
                intent = new Intent(MainActivity.this, ShowAllSensors.class);
                break;
            case R.id.sensorsApplicationsButton:
                intent = new Intent(MainActivity.this, SensorsApplications.class);
                break;
        }
        startActivity(intent);
    }
}
