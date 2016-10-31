package com.geowind.hunong.global.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geowind.hunong.R;
import com.geowind.hunong.global.adapter.BbsRecyclerViewAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.melnykov.fab.FloatingActionButton;


/**
 * Created by ${zhangwen} on 2016/10/27.
 */

public class BbsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRl_refresh;
    private FloatingActionButton mFab;
    private BbsRecyclerViewAdapter mAdapter=new BbsRecyclerViewAdapter();
    public static BbsFragment newInstance() {
        return new BbsFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bbs,container,false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.bbs_recyclerView);
        mRl_refresh = (SwipeRefreshLayout) view.findViewById(R.id.bbs_sr_refresh);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.attachToRecyclerView(mRecyclerView);
        mFab.show();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //设置分割线
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }
}
