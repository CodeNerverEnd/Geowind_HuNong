package com.geowind.hunong.global.activitys;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.json.CenterJson;
import com.geowind.hunong.utils.DialogCreator;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.view.RegisterEditView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by ASUS on 2016/10/25.
 */

public class RegisterActivity extends BaseActivity  {
    private RegisterEditView mEt_username;
    private RegisterEditView mEt_psw;
    private RegisterEditView mEt_rePsw;
    private Button mBt_register;
    private String mUsername;
    private String mPassword;
    private String mCenterName;
    private RegisterEditView mEt_phone;
    private RadioGroup mRg_userType;
    private String mPhone;
    private String mUserType;
    private RegisterEditView mEt_realname;
    private String mRealname;
    private Spinner mSp_center;
    private List mCenters;
    private TextView mTv_title;
    private ImageButton mIb_return;
    private AsyncHttpClient mAsyncHttpClient;
    private RequestParams mParams;
    private String mRePassword;
    private TextView mTv_userType_warror;
    private TextView mTv_center;
    private boolean inputError=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_register);
        initView();
        initData();
        initEvent();
    }


    private void initEvent() {
        mBt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 判断输入是否合法
                 */
                mUsername = mEt_username.getText().toString().trim();
                mPassword = mEt_psw.getText().toString().trim();
                mRePassword = mEt_rePsw.getText().toString().trim();
                mPhone = mEt_phone.getText().toString().trim();
                mRealname = mEt_realname.getText().toString().trim();
                if(TextUtils.isEmpty(mUsername)){
                    mEt_username.setWarrorVisibility(View.VISIBLE);
                    inputError=true;
                } else{
                    mEt_username.setWarrorVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(mPassword)){
                    mEt_psw.setWarrorVisibility(View.VISIBLE);
                    inputError=true;
                }else {
                    mEt_psw.setWarrorVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(mRePassword)){
                    mEt_rePsw.setWarrorVisibility(View.VISIBLE);
                    inputError=true;
                }else {
                    mEt_rePsw.setWarrorVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(mRealname)){
                    mEt_realname.setWarrorVisibility(View.VISIBLE);
                    inputError=true;
                }else {
                    mEt_realname.setWarrorVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(mPhone)){
                    mEt_phone.setWarrorVisibility(View.VISIBLE);
                    inputError=true;
                }else {
                    mEt_phone.setWarrorVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(mUserType))
                {
                    mTv_userType_warror.setVisibility(View.VISIBLE);
                    inputError=true;
                }else {
                    mTv_userType_warror.setVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(mCenterName)){
                    mTv_center.setTextColor(Color.RED);
                    inputError=true;
                }else {
                    mTv_center.setTextColor(getResources().getColor(R.color.chat_text_color));
                }
                if(!isPhone(mPhone)){
                  mEt_phone.setWarrorText("手机号格式有误");
                    mEt_phone.setWarrorVisibility(View.VISIBLE);
                    inputError=true;
                }else {
                    mEt_phone.setWarrorVisibility(View.GONE);
                }
                if(!mPassword.equals(mRePassword)){
                    mEt_rePsw.setWarrorText("两次密码不一致");
                    mEt_rePsw.setWarrorVisibility(View.VISIBLE);
                }else {
                    mEt_rePsw.setWarrorVisibility(View.GONE);
                }
                if(mPassword.length()>16){
                    mEt_psw.setWarrorText("密码不能超过16位");
                    mEt_psw.setWarrorVisibility(View.VISIBLE);
                }else {
                    mEt_psw.setWarrorVisibility(View.GONE);
                    inputError=true;
                }
                if(!inputError)
                requestRegister();
            }
        });
        mIb_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(mCenters!=null)
          mSp_center.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  mCenterName=mCenters.get(position).toString();
              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {

              }
          });
        mRg_userType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rb_farmer:
                        mUserType = "0";
                        break;
                    case R.id.rb_machiner:
                        mUserType="1";
                        break;
                }
            }
        });
    }

    private boolean isPhone(String phone) {
        Pattern pattern=Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))//d{8}$");
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }

    //初始化数据
    private void initData() {
        mCenters=new ArrayList<String>();
        //设置标题栏标题
        mTv_title.setText("注册");
        //初始化请求方法
        mAsyncHttpClient = new AsyncHttpClient();
        mParams = new RequestParams();
        //从服务器获取服务中心名称
        requestCenterFromNet();
        if(mCenters!=null)
        mSp_center.setAdapter(new ArrayAdapter<String>(getApplicationContext()
                ,android.R.layout.simple_spinner_item, mCenters));
    }

    private void requestCenterFromNet() {
        mParams.add("method","getCenter");
        mAsyncHttpClient.post(MyConstants.GETCENTER,mParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                mCenters.addAll(CenterJson.parseJson(result));
                System.out.println("服务中心请求结果："+result);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("服务中心请求失败"+statusCode);
            }
        });
    }


    //请求登录
    private void requestRegister() {

        final Dialog dialog = DialogCreator.createLoadingDialog(RegisterActivity.this, "注册中");
        dialog.show();
        //请求注册添加参数
        mParams.add("method", "register");
        mParams.add("username", mUsername);
        mParams.add("password", mPassword);
        mParams.add("phone",mPhone);
        mParams.add("type",mUserType);
        mParams.add("realname",mRealname);
        mParams.add("centername",mCenterName);

        mAsyncHttpClient.post(MyConstants.REGISTER, mParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                System.out.println("注册成功"+statusCode);
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                 Toast.makeText(getApplicationContext(),"连接失败"+statusCode,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mEt_username = (RegisterEditView) findViewById(R.id.et_register_username);
        mEt_psw = (RegisterEditView) findViewById(R.id.et_register_psw);
        mEt_rePsw = (RegisterEditView) findViewById(R.id.et_register_rePsw);
        mEt_phone = (RegisterEditView) findViewById(R.id.et_register_phone);
        mEt_realname = (RegisterEditView) findViewById(R.id.et_register_realname);
        mRg_userType = (RadioGroup) findViewById(R.id.rg_userType);
        mBt_register = (Button) findViewById(R.id.bt_register);
        mSp_center = (Spinner) findViewById(R.id.sp_center);
        mTv_title = (TextView) findViewById(R.id.title);
        mIb_return = (ImageButton) findViewById(R.id.return_btn);
        mTv_userType_warror = (TextView) findViewById(R.id.tv_userType_warror);
        mTv_center = (TextView) findViewById(R.id.tv_centerName);
    }

}
