package com.geowind.hunong.consult;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.view.View.OnClickListener;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.geowind.hunong.utils.multiFilesUploadUtil;
import com.geowind.hunong.utils.MyConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by logaxy on 16-11-16.
 */
public class ConsultActivity extends BaseActivity implements OnClickListener {

    private EditText keywordsTextView;
    private EditText describeEditText;
    private KeywordsFlow keywordsFlow;
    private Button confirm;//确认按钮

    private static String uploadUrl = MyConstants.PEST_OR_CONSULT_UPLOAD_URL;
    private static String op = "consultInfo";
    private String userName = "geowind";

    public String[] keywords = {
            "稻飞虱", "茶树种植", "小麦冻害", "水稻钻心病", "水稻叶瘟",
            "施肥", "玉米秃尖", "水稻黑条矮缩病", "防洪防旱", "农药使用",
            "玉米管理", "白粉虱", "棉红蜘蛛", "作物保鲜", "稻纵卷叶螟",
            "水稻倒伏","种植技术",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        confirm = (Button) findViewById(R.id.jmui_commit_btn);
        initTitleBar();
        initKeywordsFlow();

        describeEditText = (EditText) findViewById(R.id.editText);

        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConsultActivity.this, "上传中...", Toast.LENGTH_SHORT).show();

                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //上传成功
                        if (msg.what == 1) {
                            Intent intent = new Intent(ConsultActivity.this, ConsultUploadSussessfullyActivity.class);
                            startActivity(intent);
                        }
                        //上传不成功
                        else {
                            Intent intent = new Intent(ConsultActivity.this, ConsultFailToUploadActivity.class);
                            startActivity(intent);
                        }
                    }
                };

                new Thread() {
                    public void run() {
                        System.out.println("qewrtyuiop;lkjghfdsadfghj");
                        String consultsDescribe = describeEditText.getText().toString();
                        String contentKeyWords = keywordsTextView.getText().toString();
                        String result = "0";//服务器返回结果

                        final Map<String, String> map = new HashMap<String, String>();
                        map.put("op", op);
                        map.put("uesrname", userName);
                        map.put("describe", consultsDescribe);
                        map.put("keywords", contentKeyWords);

                        try {
                            result = multiFilesUploadUtil.uploadSubmit(uploadUrl, map, null);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Message msg = handler.obtainMessage(Integer.parseInt(result));
                        msg.sendToTarget();
                    }
                }.start();

            }
        });
    }


    private void initTitleBar() {
        TextView title;
        ImageButton returnButton;
        title = (TextView) findViewById(R.id.jmui_title_tv);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("专家咨询");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirm.setText("上传");
    }

    private void initKeywordsFlow() {
        keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);
        keywordsFlow.setDuration(800l);
        keywordsFlow.setOnItemClickListener(this);
        feedKeywordsFlow(keywordsFlow, keywords);
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
        keywordsTextView = (EditText) findViewById(R.id.keywortextview);
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
        if (keywordsTextView.getText().toString().indexOf(keyword) == -1)
            keywordsTextView.append(keyword + "; ");
    }
}


