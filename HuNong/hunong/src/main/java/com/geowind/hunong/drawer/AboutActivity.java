package com.geowind.hunong.drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.drawer.feedback.FeedbackActivity;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.geowind.hunong.utils.MyConstants;

/**
 * Created by logaxy on 17-2-25.
 */
public class AboutActivity extends BaseActivity {
    
    private TextView versionTextView;

    private RelativeLayout checkUpdateLayout;
    private RelativeLayout rateLayout;
    private RelativeLayout feedbackLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initTitleBar();
        initView();
        setVersionNumber();

        checkUpdateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateApplication();
            }
        });

        rateLayout.setOnClickListener(new View.OnClickListener() {

            //调用已安装应用市场进行评分的Uri
            Uri uri=Uri.parse("market://details?id="+getPackageName());

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        //反馈
        feedbackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AboutActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setVersionNumber() {
        versionTextView.setText(MyConstants.VERSION);
    }

    private void initTitleBar() {
        TextView title;
        ImageButton returnButton;

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

    //更新应用
    private void UpdateApplication() {
        /*后续需实现：网络检查、版本检查以及更新*/
        Toast.makeText(AboutActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        checkUpdateLayout = (RelativeLayout) findViewById(R.id.checkUpdateLayout);
        rateLayout= (RelativeLayout) findViewById(R.id.rateLayout);
        feedbackLayout= (RelativeLayout) findViewById(R.id.feedbackLayout);
        versionTextView= (TextView) findViewById(R.id.versionTextView);
    }


}
