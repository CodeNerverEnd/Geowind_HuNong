package com.geowind.hunong.consult;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.view.View.OnClickListener;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;

import java.util.Random;


/**
 * Created by logaxy on 16-11-16.
 */
public class ConsultActivity extends BaseActivity implements OnClickListener {


    public String[] keywords = {
            "稻飞虱", "茶树种植", "小麦冻害", "水稻钻心病", "水稻叶瘟",
            "施肥", "玉米秃尖", "水稻黑条矮缩病", "防洪防旱", "农药使用",
            "玉米管理", "白粉虱", "棉红蜘蛛", "作物保鲜", "稻纵卷叶螟",
            "水稻倒伏",
    };

    private KeywordsFlow keywordsFlow;
    private TextView keywordsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        initTitleBar();

        initKeywordsFlow();



    }

    private void initTitleBar() {
        TextView title;
        ImageButton returnButton;
        Button confirm;//确认按钮

        title = (TextView) findViewById(R.id.jmui_title_tv);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("专家咨询");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        confirm = (Button) findViewById(R.id.jmui_commit_btn);
        confirm.setText("上传");
    }

    private void initKeywordsFlow(){
        keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);
        keywordsFlow.setDuration(800l);
        keywordsFlow.setOnItemClickListener(this);
        feedKeywordsFlow(keywordsFlow, keywords);
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
        keywordsTextView = (TextView) findViewById(R.id.keywortextview);
    }

    private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < KeywordsFlow.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            keywordsFlow.feedKeyword(tmp);
        }
    }


    @Override
    public void onClick(View v) {
        String keyword = ((TextView) v).getText().toString();
        if(keywordsTextView.getText().toString().indexOf(keyword)==-1)
            keywordsTextView.append(keyword + "; ");
    }
}


