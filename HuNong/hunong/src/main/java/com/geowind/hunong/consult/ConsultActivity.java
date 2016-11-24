package com.geowind.hunong.consult;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;

/**
 * Created by logaxy on 16-11-16.
 */
public class ConsultActivity extends BaseActivity{

    /*
     * titlebar相关
     * */
    private TextView title;
    private ImageButton returnButton;
    private Button confirm;//确认按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        /*
        * titlebar设置
        * */
        title = (TextView) findViewById(R.id.jmui_title_tv);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("专家咨询");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        confirm= (Button) findViewById(R.id.jmui_commit_btn);
        confirm.setText("上传");

    }
}
