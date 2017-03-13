package com.geowind.hunong.pestControl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geowind.hunong.R;

public class PestUploadSussessfullyActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_upload_sussessfully);

        button= (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PestUploadSussessfullyActivity.this,
                        PestControlRecodes.class);
                startActivity(intent);
            }
        });
    }
}
