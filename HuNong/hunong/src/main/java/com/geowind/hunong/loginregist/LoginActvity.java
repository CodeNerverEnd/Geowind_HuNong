package com.geowind.hunong.loginregist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geowind.hunong.R;

import java.util.ArrayList;

/**
 * Created by zhangwen on 16-7-19.
 */
public class LoginActvity extends Activity {

    private static final String TAG = "LoginActivity";
    private EditText mEt_userName;
    private EditText mEt_psw;
    private Button mBt_login;
    private TextView mTv_forgetPsw;
    private String mUserName;
    private String mPassword;
    private String mMPassword;
    private ArrayList<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initData();
        initEvent();
    }

    private void init() {
        setContentView(R.layout.activity_login);
    }

    private void initEvent() {
        mBt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {
        mUserName = mEt_userName.getText().toString();
        mMPassword = mEt_psw.getText().toString();
    }

    private void initView() {
        mEt_userName = (EditText) findViewById(R.id.et_userName);
        mEt_psw = (EditText) findViewById(R.id.et_userName);
        mBt_login = (Button) findViewById(R.id.bt_login);
        mTv_forgetPsw = (TextView) findViewById(R.id.tv_forgetPsw);
    }
}

