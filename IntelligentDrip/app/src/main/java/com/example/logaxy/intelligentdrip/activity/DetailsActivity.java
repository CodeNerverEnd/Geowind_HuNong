package com.example.logaxy.intelligentdrip.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.logaxy.intelligentdrip.R;
import com.example.logaxy.intelligentdrip.entity.InfusionData;

public class DetailsActivity extends BaseActivity {

    InfusionData infusionData;

    private TextView building_num;
    private TextView room_num;
    private TextView bed_num;
    private TextView tatol_soup;
    private TextView residue_soup;
    private TextView speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        initView();

        Intent intent = getIntent();
        infusionData = (InfusionData) intent.getSerializableExtra("infusionData");

        building_num.setText(infusionData.getBuilding_num() + "");
        room_num.setText(infusionData.getRoom_num() + "");
        bed_num.setText(infusionData.getBed_num() + "");
        tatol_soup.setText(infusionData.getTotal_soup() + "ml");
        residue_soup.setText(infusionData.getResidue_soup() + "ml");
        speed.setText(infusionData.getSpeed() + "");


    }

    private void initView() {
        building_num = (TextView) findViewById(R.id.dtv_buildingNum);
        room_num = (TextView) findViewById(R.id.dtv_roomNum);
        bed_num = (TextView) findViewById(R.id.dtv_bedNum);
        tatol_soup = (TextView) findViewById(R.id.dtv_totalSoup);
        residue_soup = (TextView) findViewById(R.id.dtv_residueSoup);
        speed = (TextView) findViewById(R.id.dtv_speed);
    }
}
