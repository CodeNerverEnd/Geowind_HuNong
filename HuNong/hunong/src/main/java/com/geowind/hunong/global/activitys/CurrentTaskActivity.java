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
    private MyAdapter adapter = new MyAdapter();
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
                intent.putExtra("cropType", mTasks.get(i).getCropType());
                intent.putExtra("date", mTasks.get(i).getDate());
                intent.putExtra("address", mTasks.get(i).getFaddr());
                intent.putExtra("area", mTasks.get(i).getFarea() + "");
                intent.putExtra("fpic", mTasks.get(i).getFpic());
                intent.putExtra("farmZon", mTasks.get(i).getFzno() + "区");
                intent.putExtra("machineId", mTasks.get(i).getMno());
                intent.putExtra("machineType", mTasks.get(i).getMstyle());
                intent.putExtra("note", mTasks.get(i).getNote());
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
        mLv_task.setAdapter(adapter);
        getDataFromDB();
        if (mTasks == null || mTasks.size() == 0) {
            mTv_nomoreTask.setVisibility(View.VISIBLE);
        }
    }

    private void getDataFromDB() {
        TaskDaoImpl taskDao = new TaskDaoImpl(getApplicationContext());
        mTasks.addAll(taskDao.findAll());
        for (Task task : mTasks) {
            System.out.print(task.getWorkLoad() + " WorkLoad");
            System.out.print(task.getMno() + " Mno");
            System.out.print(task.getDate() + " Date");
            System.out.print(task.getWorkLoad() + " WorkLoad");
            System.out.print(task.getState() + " State");
            System.out.print(task.getFzno() + " Fzno");
            System.out.print(task.getFarea() + "  Farea");
            System.out.print(task.getfUname() + " fUname");
            System.out.println(task.getFpic() + " Fpic");
        }
    }

    private void initView() {
        mIv_back = (ImageButton) findViewById(R.id.return_btn);
        mLv_task = (ListView) findViewById(R.id.lv_task_ing);
        mTv_nomoreTask = (TextView) findViewById(R.id.tv_nomoreTask);
        mTv_title = (TextView) findViewById(R.id.title);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(getApplicationContext(), R.layout.item_lv_task_ing, null);
            TextView tv_taskName = (TextView) view.findViewById(R.id.tv_task_ing_name);
            TextView tv_taskTime = (TextView) view.findViewById(R.id.tv_task_ing_time);
            TextView tv_taskState = (TextView) view.findViewById(R.id.tv_task_ing_state);
            TextView tv_taskAddress = (TextView) view.findViewById(R.id.tv_task_ing_address);
            TextView tv_machineType = (TextView) view.findViewById(R.id.tv_MachineType);
            tv_taskName.setText("任务" + (i + 1));
            tv_taskTime.setText(mTasks.get(i).getDate());
            tv_machineType.setText(mTasks.get(i).getMstyle());
            String state = mTasks.get(i).getState();
            if (state != null && state.equals("0")) {
                tv_taskState.setText("正在进行");
            } else {
                tv_taskState.setTextColor(Color.RED);
                tv_taskState.setText("未完成");
            }
            tv_taskAddress.setText(mTasks.get(i).getFaddr());
            return view;
        }
    }
}
