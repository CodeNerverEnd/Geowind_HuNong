package com.geowind.hunong.drawer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.shinelw.library.ColorArcProgressBar;

/**
 * Created by logaxy on 16-10-18.
 */
public class CreditActivity extends BaseActivity {

    private ColorArcProgressBar progressBar;
    private int reputationValue;
    private TextView textView;


    private TextView title;
    private ImageButton returnButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        initTitleBar();



        progressBar = (ColorArcProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        reputationValue = 92;
        progressBar.setCurrentValues(reputationValue);

    }

    private void initTitleBar() {

        title = (TextView) findViewById(R.id.title);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("信用信息");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
