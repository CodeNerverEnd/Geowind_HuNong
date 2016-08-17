package com.geowind.hunong.global.activitys;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.loginregist.LoginActvity;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;

import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by zhangwen on 16-7-24.
 */
public class DrawerActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView mTv_userName;
    private TextView mTv_exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {

        initView();
        initData();
        initEvent();
        super.onStart();

    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.dl_main);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);
        mTv_userName = (TextView) mDrawer.findViewById(R.id.tv_userName);
        mTv_exit = (TextView) mDrawer.findViewById(R.id.tv_exit);
    }
    private void initData() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
        mTv_userName.setText(SpTools.getString(getApplicationContext(),MyConstants.USERNAME,""));
    }



    private void initEvent() {
        mTv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpTools.setBoolean(getApplicationContext(),MyConstants.ISLOGIN,false);
                SpTools.setString(getApplicationContext(),MyConstants.USERNAME,"");
                Intent intent=new Intent(getApplicationContext(), LoginActvity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }
}
