package com.geowind.hunong.pestControl;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;

/**
 * Created by logaxy on 16-10-24.
 */
public class ResultActivity extends BaseActivity {

    /*
     * titlebar相关
     * */
    private TextView titil;
    private ImageButton returnButton;

    String description;
    String harm;
    String countermeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}
