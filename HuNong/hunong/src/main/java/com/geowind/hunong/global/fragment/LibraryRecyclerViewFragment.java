package com.geowind.hunong.global.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.entity.SystemMsg;
import com.geowind.hunong.global.activitys.ArticleDetailsActivity;
import com.geowind.hunong.global.activitys.LibrarySearchActiviy;
import com.geowind.hunong.global.adapter.LibraryRecyclerViewAdapter;
import com.geowind.hunong.json.LibraryJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.geowind.hunong.global.adapter.LibraryRecyclerViewAdapter.CATEGORY;
import static com.geowind.hunong.global.adapter.LibraryRecyclerViewAdapter.NOWPAGE;


/**
 * Created by zhangwen on 16-7-25.这文库的Fragment
 */
public class LibraryRecyclerViewFragment extends Fragment implements View.OnClickListener{
    static final boolean GRID_LAYOUT = false;
    private RecyclerView mRecyclerView;
    private LibraryRecyclerViewAdapter mAdapter;
    private SwipeRefreshLayout mSr_refrsh;
    private TextView mTv_noMoreData;
    private FloatingActionButton mFab_search;
    private final int REFRESHING=0;
    private final int LOADING_MORE=1;
    public static LibraryRecyclerViewFragment newInstance() {
        return new LibraryRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library_reclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter=new LibraryRecyclerViewAdapter();
        initView(view);
        initData();
        initEvent();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.lib_recyclerView);
        mTv_noMoreData = (TextView) view.findViewById(R.id.tv_noMoreData);
        mSr_refrsh = (SwipeRefreshLayout) view.findViewById(R.id.sr_refresh);
        //floatActionButton
        mFab_search = (FloatingActionButton) view.findViewById(R.id.fab_libSearch);
    }


    private void initData() {

        mSr_refrsh.setColorSchemeResources(R.color.colorAccent);
        mFab_search.attachToRecyclerView(mRecyclerView);
        mFab_search.show();

        RecyclerView.LayoutManager layoutManager;
        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //设置分割线
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.requstLibrary(NOWPAGE,CATEGORY,REFRESHING);
        mAdapter.notifyDataSetChanged();

    }



    private void initEvent() {

        mAdapter.setOnItemClickLitener(new LibraryRecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), ArticleDetailsActivity.class);
                intent.putExtra("articleUrl",mAdapter.getLibraries().get(position-1).getUrl());
                startActivity(intent);
            }
        });
        mSr_refrsh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSr_refrsh.setRefreshing(false);
                                mAdapter. requstLibrary(0,CATEGORY,REFRESHING);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });

        mFab_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_libSearch:
                Intent intent=new Intent(getActivity(), LibrarySearchActiviy.class);
                startActivity(intent);
                break;
        }

    }
}
