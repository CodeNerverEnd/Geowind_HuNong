package com.geowind.hunong.loginregist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.agricultureLibrary.LibraryUtils;
import com.geowind.hunong.global.activitys.MainActivity;
import com.geowind.hunong.utils.EncryptUtils;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpConnection;

import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

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
                mUserName = mEt_userName.getText().toString();
                mPassword = mEt_psw.getText().toString();
                if(TextUtils.isEmpty(mUserName)||TextUtils.isEmpty(mPassword)){
                    Toast.makeText(getApplicationContext(),"输入不能为空",Toast.LENGTH_LONG).show();
                }
                requstLogin();//向服务器发送登录请求

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

        client.post(MyConstants.LOGINURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                SpTools.setBoolean(getApplicationContext(), MyConstants.ISLOGIN,true);
                //进入主界面
                SpTools.setString(getApplicationContext(),MyConstants.UserId,mUserName);

                if(result.equals("登录成功")){
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
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

