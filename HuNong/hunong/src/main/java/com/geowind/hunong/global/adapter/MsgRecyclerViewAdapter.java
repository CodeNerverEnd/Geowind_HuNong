package com.geowind.hunong.global.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geowind.hunong.R;

import java.util.List;

/**
 * Created by zhangwen on 16-7-25.  RecyclerView的适配器
 */

public class MsgRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public MsgRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        return  TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_msg, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}