package com.geowind.hunong.pestControl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.List;

/**
 * Created by logaxy on 16-11-15.
 * 用于展示病虫识别的历史纪录
 */
public class Recodes extends BaseActivity {

    private RecyclerView recodesRecyclerView;
    private List<Object> recodeItems;
    private RecodesRecyclerViewAdapter recodesAdapter;

    private SwipeRefreshLayout recodesRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recodes);


        //**************************

        //recodeItems.add();


        // **************************



        initTitleBar();

        recodesRecyclerView = (RecyclerView) findViewById(R.id.recodes_recyclerView);
        recodesRecyclerView.setAdapter(recodesAdapter = new RecodesRecyclerViewAdapter());
        recodesRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recodesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recodesRefresh = (SwipeRefreshLayout) findViewById(R.id.recodes_sr_refresh);
        recodesRefresh.setColorSchemeResources(R.color.colorAccent);

    }

    private void initTitleBar() {

        TextView title;
        ImageButton returnButton;

        title = (TextView) findViewById(R.id.title);
        returnButton = (ImageButton) findViewById(R.id.return_btn);
        title.setText("历史纪录");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    class RecodesRecyclerViewAdapter extends RecyclerView.Adapter<RecodesRecyclerViewAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    Recodes.this).inflate(R.layout.item_pestcontrol_recodes_recyclerview, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView recodesDate;
            private MySquareImageView recodesFirstImage;
            private TextView recodesDescrition;

            public MyViewHolder(View view) {
                super(view);

                recodesDate = (TextView) view.findViewById(R.id.recodes_date);
                recodesFirstImage = (MySquareImageView) view.findViewById(R.id.recodes_firstImage);
                recodesDescrition = (TextView) view.findViewById(R.id.recodes_descrition);
            }

        }
    }

}

