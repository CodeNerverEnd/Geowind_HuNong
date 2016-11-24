package com.geowind.hunong.global.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.map.MyPopTextView;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by zhangwen on 16-8-26.
 */
public class TaskDetailsActivity extends BaseActivity {

    private ImageButton mIb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_ing_details);
        MyPopTextView tv_croType= (MyPopTextView) findViewById(R.id.tv_taskDetails_cropType);
        MyPopTextView tv_date= (MyPopTextView) findViewById(R.id.tv_taskDetails_date);
        MyPopTextView tv_address= (MyPopTextView) findViewById(R.id.tv_taskDetails_address);
        MyPopTextView tv_area= (MyPopTextView) findViewById(R.id.tv_taskDetails_area);
        MyPopTextView tv_farmZon= (MyPopTextView) findViewById(R.id.tv_taskDetails_farmZon);
        MyPopTextView tv_machineId= (MyPopTextView) findViewById(R.id.tv_taskDetails_minchineId);
        MyPopTextView tv_machineType= (MyPopTextView) findViewById(R.id.tv_taskDetails_minchineType);
        MyPopTextView tv_taskState= (MyPopTextView) findViewById(R.id.tv_taskDetails_taskState);
        MyPopTextView tv_workLoad= (MyPopTextView) findViewById(R.id.tv_taskDetails_workLoad);
        TextView tv_note= (TextView) findViewById(R.id.tv_taskDetails_note);
        ImageView iv_farm= (ImageView) findViewById(R.id.iv_farm);
        final Intent intent=this.getIntent();
        tv_croType.setContent(intent.getStringExtra("cropType"));
        tv_date.setContent(intent.getStringExtra("date"));
        tv_area.setContent(intent.getStringExtra("area"));
        tv_address.setContent(intent.getStringExtra("address"));
        tv_area.setContent(intent.getStringExtra("area"));
        tv_farmZon.setContent(intent.getStringExtra("farmZon"));
        tv_machineId.setContent(intent.getStringExtra("machineId"));
        tv_machineType.setContent(intent.getStringExtra("machineType"));
        tv_taskState.setContent(intent.getStringExtra("taskState"));
        tv_workLoad.setContent(intent.getStringExtra("workLoad"));
        tv_note.setText(intent.getStringExtra("note"));
        BitmapUtils bitmapUtils=new BitmapUtils(getApplicationContext());
        bitmapUtils.display(iv_farm,intent.getStringExtra("picUrl"));
        mIb_back = (ImageButton) findViewById(R.id.ib_taskDetails_back);
        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(TaskDetailsActivity.this,CurrentTaskActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
