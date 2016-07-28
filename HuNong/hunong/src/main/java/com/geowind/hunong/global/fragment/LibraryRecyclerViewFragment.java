package com.geowind.hunong.global.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geowind.hunong.R;
import com.geowind.hunong.global.adapter.LibraryRecyclerViewAdapter;
import com.geowind.hunong.entity.LibrariesBean;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangwen on 16-7-25.这文库的Fragment
 */
public class LibraryRecyclerViewFragment extends Fragment {
    static final boolean GRID_LAYOUT = false;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LibrariesBean mBean;
    private List<Object> mContentItems = new ArrayList<Object>();

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
        mBean=new LibrariesBean();
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
        {
            for (int i = 0; i < 1; ++i) {
                mContentItems.add(new Object());
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
