package com.geowind.hunong.global.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;


/**
 * Created by zhangwen on 2016/11/13.
 */

public class VerifyPhoneAcitvity extends BaseActivity implements View.OnClickListener {

    private TextView mTv_title;
    private EditText mEt_phone;
    private EditText mEt_verifyCode;
    private TextView mTv_getVierfyCode;
    private TextView mTv_timer;
    private Button mBt_next;
    private int time=60;
    private Thread timer;


    private ImageButton mBt_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_1);
        initView();
        initData();
        initEvent();

    }

    private void initView() {
        mTv_title = (TextView) findViewById(R.id.title);
        mEt_phone = (EditText) findViewById(R.id.et_verifyPhone_phone);
        mEt_verifyCode = (EditText) findViewById(R.id.et_verifyPhone_verifyCode);
        mTv_getVierfyCode = (TextView) findViewById(R.id.tv_verifyPhone_getVerifyCode);
        mTv_timer = (TextView) findViewById(R.id.tv_verifyPhone_timer);
        mBt_next = (Button) findViewById(R.id.bt_verifyPhone_next);
        mBt_return = (ImageButton) findViewById(R.id.return_btn);
    }

    private void initEvent() {
        mBt_next.setOnClickListener(this);
        mTv_getVierfyCode.setOnClickListener(this);

    }

    private void initData() {
        mTv_title.setText("获取验证码");
        timer=new Thread(new TimerRunner());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_btn:
                finish();
                break;
            case R.id.tv_verifyPhone_getVerifyCode:
                mTv_getVierfyCode.setVisibility(View.GONE);
                mTv_timer.setVisibility(View.VISIBLE);
                time=60;
                mTv_timer.setText(time+"s");
                if(!timer.isAlive())
                timer.start();
                break;
            case R.id.bt_verifyPhone_next:
                Intent intent = new Intent(VerifyPhoneAcitvity.this, ResetPswActivity.class);
                startActivity(intent);
                break;
        }
    }
    final Handler handler = new Handler(){          // handle
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                  if(time>0){
                      time--;
                      mTv_timer.setText(time+"s");
                  }else {
                      mTv_getVierfyCode.setVisibility(View.VISIBLE);
                      mTv_timer.setVisibility(View.GONE);
                  }
            }
            super.handleMessage(msg);
        }
    };
    private class TimerRunner implements Runnable{
        @Override
        public void run() {

            while (true) {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();// sleep 1000ms
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }
            }

        }

    }


}
