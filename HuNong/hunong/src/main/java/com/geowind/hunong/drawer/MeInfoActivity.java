package com.geowind.hunong.drawer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.dao.impl.UserDaoImpl;
import com.geowind.hunong.entity.ExpertReply;
import com.geowind.hunong.entity.User;
import com.geowind.hunong.global.activitys.BaseActivity;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/10/25.
 */

public class MeInfoActivity extends BaseActivity {

    private TextView mTv_title;
    private ImageButton mById;
    private TextView mTv_nikeName;
    private TextView mTv_gender;
    private TextView mTv_address;
    private TextView mTv_center;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_info);
        initView();
        initData();
        iniEvent();
    }

    private void iniEvent() {
        mById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        mTv_title.setText("个人信息");
        List<User> users =new ArrayList<User>();
        try{
            UserDaoImpl userDao=new UserDaoImpl(MeInfoActivity.this);
            users.addAll( userDao.findAll());
        }catch (Exception e){
                System.out.println("查询失败");
        }
        if(users.size()!=0){
            User user=users.get(0);
            if(user!=null){
                System.out.println("数据库中的用户信息"+user.toString());
                mTv_nikeName.setText(user.getUsername());
                mTv_gender.setText(user.getSex());
                mTv_center.setText(user.getCenterName());
                mTv_address.setText(user.getAddress());

            }

        }


    }

    private void initView() {
        mTv_title = (TextView) findViewById(R.id.title);
        mById = (ImageButton) findViewById(R.id.return_btn);
        mTv_nikeName = (TextView) findViewById(R.id.nick_name_tv);
        mTv_gender = (TextView) findViewById(R.id.gender_tv);
        mTv_address = (TextView) findViewById(R.id.region_tv);
        mTv_center = (TextView) findViewById(R.id.center_tv);

    }
}
