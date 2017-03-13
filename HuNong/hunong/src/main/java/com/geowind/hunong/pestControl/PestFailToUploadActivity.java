package com.geowind.hunong.pestControl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geowind.hunong.R;

public class PestFailToUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_fail_to_upload);

        setContentView(R.layout.network_unavailable_layout);

    }
}
