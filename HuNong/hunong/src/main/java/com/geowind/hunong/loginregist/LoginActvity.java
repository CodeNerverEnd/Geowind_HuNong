package com.geowind.hunong.loginregist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.SplashActivity;
import com.geowind.hunong.utils.EncryptUtils;
import com.geowind.hunong.utils.InpustreamUtils;
import com.geowind.hunong.utils.MyConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangwen on 16-7-19.
 */
//用户登录
public class LoginActvity extends Activity {

    private static final String TAG = "LoginActivity";
    private EditText mEt_userName;
    private EditText mEt_psw;
    private ImageButton mBt_login;
    private TextView mTv_forgetPsw;
    private String mUserName;
    private String mPassword;
    private TextView tv_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("404");
        init();
        initView();
        initData();
        initEvent();
    }

    private void init() {
        setContentView(R.layout.activity_login);
    }
//登录按钮的点击事件
    private void initEvent() {
        mBt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(mUserName+mPassword);
                if(mEt_userName.getText()==null||mPassword==null){
                    Toast.makeText(getApplicationContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    mUserName = mEt_userName.getText().toString();
                    mPassword = mEt_psw.getText().toString();
                    requstLogin();//向服务器发送登录请求
                }

            }
        });
    }
    //请求登录
    private void requstLogin() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","login");
        params.add("username",mUserName);
        params.add("password", EncryptUtils.md5AndSha(mPassword));
        client.post(MyConstants.URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(),new String(responseBody),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"连接失败",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initData() {

    }

    private void initView() {
        mEt_userName = (EditText) findViewById(R.id.et_userName);
        mEt_psw = (EditText) findViewById(R.id.et_password);
        mBt_login = (ImageButton) findViewById(R.id.bt_login);
        mTv_forgetPsw = (TextView) findViewById(R.id.tv_forgetPsw);
        tv_regist = (TextView) findViewById(R.id.tv_login_regist);
    }
}

