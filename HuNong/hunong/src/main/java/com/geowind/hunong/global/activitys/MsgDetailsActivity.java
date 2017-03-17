package com.geowind.hunong.global.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.global.adapter.MsgDetailAdapter;

import java.util.List;


/**
 * Created by zhangwen on 2016/11/6.
 */

public class MsgDetailsActivity extends BaseActivity {

    private ListView mLv_msgList;
    private MsgDetailAdapter mAdapter;
    private TextView mTv_title;
    private ImageButton mIb_return;
    private String msgType;
    private List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_details);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mIb_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLv_msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (msgType){
                    case "任务提醒":
                        if(position>0){
                            toTaskDetailsActivity(mTasks,position);
                        }

                        break;
                    case "专家回复":
                    break;
                    case "系统消息":
                    break;


                }
            }
        });
    }

    private void toTaskDetailsActivity(List<Task> mTasks,int i) {
        Intent intent = new Intent(MsgDetailsActivity.this, TaskDetailsActivity.class);
        intent.putExtra("cropType", mTasks.get(i).getCroptype());
        intent.putExtra("date", mTasks.get(i).getDate());
        intent.putExtra("address", mTasks.get(i).getAddress());
        intent.putExtra("area", mTasks.get(i).getBarea() + "");
        intent.putExtra("fpic", mTasks.get(i).getPic());
        intent.putExtra("farmZon", mTasks.get(i).getBarea() + "块");
        intent.putExtra("machineId", mTasks.get(i).getMid());
        intent.putExtra("machineType", mTasks.get(i).getMstyle());
        intent.putExtra("note", mTasks.get(i).getNote());
        intent.putExtra("bname",mTasks.get(i).getBname());
        intent.putExtra("workload",mTasks.get(i).getWorkLoad());
        intent.putExtra("picUrl",mTasks.get(i).getPic());
        String state = mTasks.get(i).getState();
        if (state.equals("0")) {
            intent.putExtra("taskState", "正在进行");
        } else
            intent.putExtra("taskState", "未完成");
        intent.putExtra("workLoad", mTasks.get(i).getWorkLoad());
        startActivity(intent);
    }

    private void initData() {
        mAdapter = new MsgDetailAdapter(getApplicationContext(),getIntent());
        mLv_msgList.setAdapter(mAdapter);
        mTv_title.setText(msgType);
        mTasks = mAdapter.getTasks();
    }


    private void initView() {
        msgType=getIntent().getStringExtra("msgType");
        mLv_msgList = (ListView) findViewById(R.id.lv_msgDetails);
        mTv_title = (TextView) findViewById(R.id.title);
        mIb_return = (ImageButton) findViewById(R.id.return_btn);
    }

}
