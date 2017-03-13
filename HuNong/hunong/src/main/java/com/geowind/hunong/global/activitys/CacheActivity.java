package com.geowind.hunong.global.activitys;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.dao.base.DAO;

import com.geowind.hunong.dao.impl.ExpertReplyDaoImpl;
import com.geowind.hunong.dao.impl.LibSearchDaoImpl;
import com.geowind.hunong.dao.impl.SystemMsgDaoImpl;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.utils.DialogCreator;


/**
 * Created by zhangwen on 2017/3/5.
 */

public class CacheActivity extends  BaseActivity implements View.OnClickListener {

    private RelativeLayout mRl_task;
    private RelativeLayout mRl_systemMsg;
    private RelativeLayout mRl_reply;
    private RelativeLayout mRl_search;
    private ImageButton mIb_return;
    private TextView mTv_title;
    private  DAO taskDao,replyDao,systemMsgDao,searchDao;
    private Context mContext;
    private Dialog mDialog;
    private DAO  dao=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        init();
        initData();
        initEnvent();
    }

    private void initEnvent() {
        mIb_return.setOnClickListener(this);
        mRl_reply.setOnClickListener(this);
        mRl_task.setOnClickListener(this);
        mRl_search.setOnClickListener(this);
        mRl_systemMsg.setOnClickListener(this);
    }

    private void initData() {
        mTv_title.setText("清除缓存");
    }

    private void init() {

        mContext=CacheActivity.this;
        taskDao=new TaskDaoImpl(mContext);
        replyDao=new ExpertReplyDaoImpl(mContext);
        systemMsgDao=new SystemMsgDaoImpl(mContext);
        searchDao=new LibSearchDaoImpl(mContext);

        mRl_task = (RelativeLayout) findViewById(R.id.clear_task);
        mRl_systemMsg = (RelativeLayout) findViewById(R.id.clear_systemMsg);
        mRl_reply = (RelativeLayout) findViewById(R.id.clear_reply);
        mRl_search = (RelativeLayout) findViewById(R.id.clear_search);
        mIb_return = (ImageButton) findViewById(R.id.return_btn);
        mTv_title = (TextView) findViewById(R.id.title);
    }
    View.OnClickListener listenr=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.jmui_cancel_btn:
                    mDialog.cancel();
                    break;
                case R.id.jmui_commit_btn:
                    mDialog.cancel();
                    dao.deleteAll();
                    Toast.makeText(mContext,"记录已删除",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.clear_task:
                dao=taskDao;
                break;
            case R.id.clear_reply:
                dao=replyDao;
                break;
            case R.id.clear_systemMsg:
               dao=searchDao;
                break;
            case R.id.clear_search:
               dao=searchDao;
                break;
            case R.id.return_btn:
                finish();
                break;

        }

        mDialog = DialogCreator.createDeleteMessageDialog(mContext, listenr);
        mDialog.show();
        mDialog.getWindow().setLayout((int) (0.8 * mWidth), WindowManager.LayoutParams.WRAP_CONTENT);


    }
}
