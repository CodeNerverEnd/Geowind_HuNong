package com.geowind.hunong.global.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwen on 2016/11/6.
 */

public class AddBbsActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTv_title;
    private ImageButton mIb_back;
    private EditText mEt_bbsContent;
    private Button mBt_release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bbs);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
    }

    private void initView() {
        mTv_title = (TextView) findViewById(R.id.jmui_title_tv);
        mIb_back = (ImageButton) findViewById(R.id.return_btn);
        mEt_bbsContent = (EditText) findViewById(R.id.et_bbs_edit);
        mBt_release = (Button) findViewById(R.id.bt_bbs_release);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_btn:
                finish();
                break;
            case R.id.bt_bbs_release:
                //发布帖子
                break;

        }
    }
}
