package com.geowind.hunong.drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;

/**
 * Created by logaxy on 17-2-25.
 */
public class AboutActivity extends BaseActivity {

    private TextView title;
    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        title = (TextView) findViewById(R.id.title);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("关于");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
