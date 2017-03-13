package com.example.logaxy.intelligentdrip.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.logaxy.intelligentdrip.R;
import com.example.logaxy.intelligentdrip.adapter.MyAdapter;
import com.example.logaxy.intelligentdrip.entity.InfusionData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<InfusionData> infusionDatas = new ArrayList<>();

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyAdapter myAdapter = new MyAdapter(MainActivity.this, R.layout.infusion_data_item, infusionDatas);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);

        initMyEntities();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                InfusionData infusionData = infusionDatas.get(position);
                //
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();

                //
                bundle.putSerializable("infusionData", infusionData);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initMyEntities() {
        //联网获取
        InfusionData entity1 = new InfusionData(3,1,1,1,250f,118.25f,3.5f);
        infusionDatas.add(entity1);
        InfusionData entity2 = new InfusionData(3,1,2,1,250f,65.22f,5.3f);
        infusionDatas.add(entity2);
        InfusionData entity3 = new InfusionData(3,1,3,1,300f,78.24f,3.55f);
        infusionDatas.add(entity3);
        InfusionData entity4 = new InfusionData(3,1,4,1,400f,285f,4.2f);
        infusionDatas.add(entity4);
        InfusionData entity5 = new InfusionData(3,1,5,1,200f,252f,5.0f);
        infusionDatas.add(entity5);
        InfusionData entity6 = new InfusionData(3,1,6,1,300f,124f,3.1f);
        infusionDatas.add(entity6);
        InfusionData entity7 = new InfusionData(3,1,7,1,150f,135f,3.5f);
        infusionDatas.add(entity7);
        InfusionData entity8 = new InfusionData(3,1,8,1,200f,127f,4.22f);
        infusionDatas.add(entity8);
    }



}
