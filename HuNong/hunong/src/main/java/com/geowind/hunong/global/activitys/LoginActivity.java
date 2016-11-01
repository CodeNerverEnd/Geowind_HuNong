package com.geowind.hunong.global.activitys;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.utils.DialogCreator;
import com.geowind.hunong.utils.EncryptUtils;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends Activity {
    private EditText mEt_userId;
    private EditText mEt_password;
    private Button mTv_register;
    private Button mTv_forgetPsw;
    private ImageButton mIb_login;
    private String mUserId;
    private String mPassword;

    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Window window=this.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }


    private void initView() {
        mEt_userId = (EditText) findViewById(R.id.et_userName);
        mEt_password = (EditText) findViewById(R.id.et_password);
        mTv_register = (Button) findViewById(R.id.bt_register);
        mTv_forgetPsw = (Button) findViewById(R.id.tv_forgetPsw);
        mIb_login = (ImageButton) findViewById(R.id.login_btn);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                //隐藏软键盘
                InputMethodManager manager = ((InputMethodManager) this
                        .getSystemService(Activity.INPUT_METHOD_SERVICE));
                if (this.getWindow().getAttributes().softInputMode
                        != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                    if (this.getCurrentFocus() != null) {
                        manager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }

                mUserId = mEt_userId.getText().toString().trim();
                mPassword = mEt_password.getText().toString().trim();
                if (mUserId.equals("")) {
                    Toast.makeText(getApplicationContext(),"用户名不能为空", Toast.LENGTH_SHORT).show();
                    break;
                } else if (mPassword.equals("")) {
                    Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                final Dialog dialog = DialogCreator.createLoadingDialog(LoginActivity.this,
                        getApplicationContext().getString(R.string.login_hint));
                dialog.show();
                requstLogin();
                dialog.dismiss();
                break;

            case R.id.bt_register:
               startRegisterActivity();
                break;
            case R.id.tv_forgetPsw:
                startForgetPasswordActivity();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public Context getContext() {
        return this;
    }

    public void startMainActivity() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setClass(getContext(), com.geowind.hunong.global.activitys.MainActivity.class);
        startActivity(intent);
    }

    public void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void startForgetPasswordActivity(){

      //  Intent intent=new Intent(this,ResetPasswordActivity.class);
      //  startActivity(intent);
    }

    public void requstLogin() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","login");
        params.add("username",mUserId);
        params.add("password", EncryptUtils.md5AndSha(mPassword));

        client.post(MyConstants.LOGINURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                SpTools.setBoolean(getApplicationContext(), MyConstants.ISLOGIN,true);
                SpTools.setString(getApplicationContext(),MyConstants.USERNAME,mUserId);
                startMainActivity();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"连接失败"+statusCode,Toast.LENGTH_LONG).show();
            }
        });
    }

}
