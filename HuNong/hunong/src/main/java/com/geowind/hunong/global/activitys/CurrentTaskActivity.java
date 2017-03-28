package com.geowind.hunong.global.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.global.adapter.CurrentTaskAdapter;
import com.geowind.hunong.json.TaskJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangwen on 16-8-26.
 */
public class CurrentTaskActivity extends BaseActivity {

    private ImageButton mIv_back;
    private ListView mLv_task;
    private List<Task> mTasks = new ArrayList<Task>();
    private CurrentTaskAdapter adapter;
    private TextView mTv_nomoreTask;
    private TextView mTv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_ing);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentTaskActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mLv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CurrentTaskActivity.this, TaskDetailsActivity.class);
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
        });
    }

    private void initData() {
        mTv_title.setText("正在进行");

        getDataFromDB();
        if (mTasks == null || mTasks.size() == 0) {
            mTv_nomoreTask.setVisibility(View.VISIBLE);
        }
        adapter=new CurrentTaskAdapter(mTasks,CurrentTaskActivity.this);
        mLv_task.setAdapter(adapter);
    }

    private void getDataFromDB() {
        TaskDaoImpl taskDao = new TaskDaoImpl(getApplicationContext());
        mTasks.addAll(taskDao.findAll());
    }

    private void initView() {
        mIv_back = (ImageButton) findViewById(R.id.return_btn);
        mLv_task = (ListView) findViewById(R.id.lv_task_ing);
        mTv_nomoreTask = (TextView) findViewById(R.id.tv_nomoreTask);
        mTv_title = (TextView) findViewById(R.id.title);
    }


}
