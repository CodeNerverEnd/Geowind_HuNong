package com.geowind.hunong.drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.dao.impl.UserDaoImpl;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.entity.User;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.geowind.hunong.global.activitys.LoginActivity;
import com.geowind.hunong.json.TaskJson;
import com.geowind.hunong.json.UserJson;
import com.geowind.hunong.map.MyPopTextView;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangwen on 16-9-1.
 */
public class HistoryTaskActivity extends BaseActivity{

    private TextView mTv_title;
    private ImageButton mIb_return;
    private ListView mListView;
    private List<Task> mTasks;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task);
        initView();
        initData();
        innitEvent();
    }

    private void innitEvent() {
        mIb_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void initData() {
        mTv_title.setText("历史任务");
        mTasks=new ArrayList<Task>();
        //向服务器请求历史任务
        requstHistoryTask();

    }
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(HistoryTaskActivity.this,R.layout.item_history_task,null);
            MyPopTextView tv_time= (MyPopTextView) convertView.findViewById(R.id.tv_hisTime);
            MyPopTextView tv_addr= (MyPopTextView) convertView.findViewById(R.id.tv_hisAddr);
            MyPopTextView tv_bname= (MyPopTextView) convertView.findViewById(R.id.tv_hisBName);
            MyPopTextView tv_hisCroType= (MyPopTextView) convertView.findViewById(R.id.tv_hisCroType);
            MyPopTextView tv_hisMType= (MyPopTextView) convertView.findViewById(R.id.tv_hisMType);
            MyPopTextView tv_hisState= (MyPopTextView) convertView.findViewById(R.id.tv_hisState);
            tv_time.setContent(mTasks.get(position).getDate());
            tv_addr.setContent(mTasks.get(position).getAddress());
            tv_bname.setContent(mTasks.get(position).getBname());
            tv_hisCroType.setContent(mTasks.get(position).getCroptype());
            tv_hisMType.setContent(mTasks.get(position).getMstyle());
            tv_hisState.setContent(mTasks.get(position).getState());

            return convertView;
        }
    };

    private void initView() {
        mTv_title = (TextView) findViewById(R.id.title);
        mIb_return = (ImageButton) findViewById(R.id.return_btn);
        mListView = (ListView) findViewById(R.id.lv_historyTask);

    }

    public void requstHistoryTask() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("op","historyTask");
        params.add("username",SpTools.getString(HistoryTaskActivity.this,MyConstants.USERNAME,""));

        client.post(MyConstants.HISTORY_TASK_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                SpTools.setString(HistoryTaskActivity.this,MyConstants.HISTORY_TASK,);
//                String s = SpTools.getString(HistoryTaskActivity.this, MyConstants.HISTORY_TASK, "");
                List<Task> tasks = TaskJson.paseJson(new String(responseBody));
                if(tasks!=null){
                    mTasks.addAll(tasks);
                }
                mAdapter = new MyAdapter();
                mListView.setAdapter(mAdapter);
                mTv_title.setText(R.string.title_history_task);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"连接失败"+statusCode,Toast.LENGTH_LONG).show();
            }
        });
    }
}
