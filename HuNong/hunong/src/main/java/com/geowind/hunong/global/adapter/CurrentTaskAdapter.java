package com.geowind.hunong.global.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.entity.Task;

import java.util.List;

/**
 * Created by zhangwen on 2017/3/24.
 */

public class CurrentTaskAdapter extends BaseAdapter {
    List<Task> mTasks;
    Context mContext;
    public CurrentTaskAdapter(List<Task> tasks,Context context) {
        this.mTasks=tasks;
        mContext=context;
    }

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
    public View getView(int i, View view, ViewGroup parent) {
        view = View.inflate(mContext, R.layout.item_lv_task_ing, null);
        TextView tv_taskName = (TextView) view.findViewById(R.id.tv_task_ing_name);
        TextView tv_taskTime = (TextView) view.findViewById(R.id.tv_task_ing_time);
        TextView tv_taskAddress = (TextView) view.findViewById(R.id.tv_task_ing_address);
        TextView tv_machineType = (TextView) view.findViewById(R.id.tv_MachineType);
        ImageView iv_taskType= (ImageView) view.findViewById(R.id.iv_taskType);
        switch (mTasks.get(i).getMstyle()){
            case "耕种机械":
                iv_taskType.setImageResource(R.drawable.task_type_gzjx);
                break;
            case "动力传送机械":
                iv_taskType.setImageResource(R.drawable.task_type_dlcs);
                break;
            case "灌溉机械":
                iv_taskType.setImageResource(R.drawable.task_type_ggjx);
                break;
            case "收获机械":
                iv_taskType.setImageResource(R.drawable.task_type_shjx);
                break;
        }
        tv_taskName.setText("任务" + (i + 1));
        tv_taskTime.setText(mTasks.get(i).getDate());
        tv_machineType.setText(mTasks.get(i).getMstyle());
        tv_taskAddress.setText(mTasks.get(i).getAddress());
        return view;
    }
}
