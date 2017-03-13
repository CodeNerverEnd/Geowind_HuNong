package com.geowind.hunong.consult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geowind.hunong.R;

public class ConsultUploadSussessfullyActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_upload_sussessfully);

        button= (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConsultUploadSussessfullyActivity.this,
                        ConsultRecodesActivity.class);
                startActivity(intent);
            }
        });


    }
}
