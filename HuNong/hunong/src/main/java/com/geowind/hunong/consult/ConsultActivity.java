package com.geowind.hunong.consult;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;

/**
 * Created by logaxy on 16-11-16.
 */
public class ConsultActivity extends BaseActivity{

    private TextView title;
    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        title=(TextView) findViewById(R.id.title);
        returnButton= (ImageButton) findViewById(R.id.return_btn);
        title.setText("专家咨询");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
