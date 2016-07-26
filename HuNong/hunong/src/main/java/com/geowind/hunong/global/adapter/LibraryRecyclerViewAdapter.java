package com.geowind.hunong.global.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geowind.hunong.R;

import java.util.List;
import java.util.Objects;

/**
 * Created by zhangwen on 16-7-25.  RecyclerView的适配器
 */

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.MyViewHolder> {

    List<Object> contents;
    private Context mContext;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    String[] sortType;
    public LibraryRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
//        switch (position) {
//            case 0:
//                return TYPE_HEADER;
//            default:
//                return TYPE_CELL;
//        }
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        mContext=parent.getContext();
        sortType=mContext.getResources().getStringArray(R.array.lib_sortTpe);
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
               mContext).inflate(R.layout.gird_item_lib, parent,
                false));
        return holder;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(sortType[position]);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_lib_sortType);
        }

    }
}
