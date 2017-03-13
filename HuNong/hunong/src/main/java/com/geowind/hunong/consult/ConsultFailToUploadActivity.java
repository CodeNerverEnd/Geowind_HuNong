package com.geowind.hunong.consult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geowind.hunong.R;

public class ConsultFailToUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_fail_to_upload);

        setContentView(R.layout.network_unavailable_layout);
    }
}
