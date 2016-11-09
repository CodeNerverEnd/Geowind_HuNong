package com.geowind.hunong.global.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.geowind.hunong.R;

/**
 * Created by zhangwen on 2016/11/6.
 */

public class MsgDetailsActivity extends BaseActivity {

    private ListView mLv_msgList;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_details);
        initView();
        initData();
    }

    private void initData() {
        mAdapter = new MyAdapter();
        mLv_msgList.setAdapter(mAdapter);
    }
    private  class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
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
            return null;
        }
    }

    private void initView() {
        mLv_msgList = (ListView) findViewById(R.id.lv_msgDetails);
    }

}
