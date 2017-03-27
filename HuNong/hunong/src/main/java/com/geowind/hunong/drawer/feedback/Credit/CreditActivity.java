package com.geowind.hunong.drawer.feedback.Credit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.geowind.hunong.R;



/**
 * Created by logaxy on 16-10-18.
 */
public class CreditActivity extends Activity {

    private ImageButton backButtom;

    private ColorArcProgressBar progressBar;
    private int reputationValue;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);



        backButtom= (ImageButton) findViewById(R.id.backButtom);

        progressBar = (ColorArcProgressBar) findViewById(R.id.progressBar);

        reputationValue = 92;
        progressBar.setCurrentValues(reputationValue);
        progressBar.setLongDegreeColor("#FFFFFF");
        progressBar.setShortDegreeColor("#FFFFFF");
        progressBar.setHintColor("#FFFFFF");
        progressBar.setBgArcColor("#FFFFFF");
        progressBar.setValueTextColor("#FFFFFF");

        backButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
