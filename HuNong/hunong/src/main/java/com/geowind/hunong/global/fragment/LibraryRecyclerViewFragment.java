package com.geowind.hunong.global.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.geowind.hunong.agricultureLibrary.ArticleDetailsActivity;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.global.adapter.LibraryRecyclerViewAdapter;
import com.geowind.hunong.json.LibraryJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangwen on 16-7-25.这文库的Fragment
 */
public class LibraryRecyclerViewFragment extends Fragment {
    static final boolean GRID_LAYOUT = false;
    private RecyclerView mRecyclerView;
    private LibraryRecyclerViewAdapter mAdapter;
    private List<Object> mContentItems = new ArrayList<Object>();
    Library mLibrary;
    private SwipeRefreshLayout mSr_refrsh;
    private TextView mTv_noMoreData;

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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.lib_recyclerView);
        mTv_noMoreData = (TextView) view.findViewById(R.id.tv_noMoreData);
        mSr_refrsh = (SwipeRefreshLayout) view.findViewById(R.id.sr_refresh);
        mSr_refrsh.setColorSchemeResources(R.color.colorAccent);
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
        //RecyclerView的适配器
        mAdapter = new LibraryRecyclerViewAdapter(mContentItems);

        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mLibrary=LibraryJson.parseJsonObject(SpTools.getString(getActivity().getApplicationContext(),MyConstants.LIBRARY_JSON,""));
        mContentItems.add(new Object());
        if(mLibrary!=null)
        {
            for (int i = 1; i <mLibrary.getArticleList().size(); ++i) {
                mContentItems.add(new Object());
            }
            mAdapter.notifyDataSetChanged();
            mAdapter.setOnItemClickLitener(new LibraryRecyclerViewAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent=new Intent(getActivity(), ArticleDetailsActivity.class);
                    intent.putExtra("title",mLibrary.getArticleList().get(position).getTitle());
                    intent.putExtra("articleUrl",mLibrary.getArticleList().get(position).getUrl());
                    startActivity(intent);
                }
            });
        }
        initEvent();
    }

    private void initEvent() {
        mSr_refrsh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSr_refrsh.setRefreshing(false);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
