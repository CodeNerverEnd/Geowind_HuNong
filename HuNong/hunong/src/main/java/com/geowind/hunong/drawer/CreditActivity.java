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

    /*
    * titlebar相关
    * */
    private TextView titil;
    private ImageButton returnButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        /*
        * titlebar设置
        * */
        titil=(TextView) findViewById(R.id.title);
        returnButton= (ImageButton) findViewById(R.id.return_btn);
        titil.setText("信用信息");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        progressBar= (ColorArcProgressBar) findViewById(R.id.progressBar);

        reputationValue=100;
        progressBar.setCurrentValues(reputationValue);
    }

}
