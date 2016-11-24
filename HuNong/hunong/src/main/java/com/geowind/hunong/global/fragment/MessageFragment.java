package com.geowind.hunong.global.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.MsgDetailsActivity;
import com.geowind.hunong.global.adapter.MsgRecyclerViewAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.zip.Inflater;

/**
 * Created by ASUS on 2016/10/22.
 */

public class MessageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MsgRecyclerViewAdapter mAdapter=new MsgRecyclerViewAdapter();

    public static MessageFragment newInstance(){
        return new MessageFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_messageList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //设置分割线
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        initEvent();
    }

    private void initEvent() {
        mAdapter.setOnItemClickLitener(new MsgRecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),MsgDetailsActivity.class);
                intent.putExtra("msgType",mAdapter.getMsgType());
                startActivity(intent);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_msg,container,false);
    }

}
