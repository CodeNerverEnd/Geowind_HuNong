package com.geowind.hunong.global.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geowind.hunong.R;

/**
 * Created by zhangwen on 2016/10/27.
 */

public class BbsRecyclerViewAdapter extends RecyclerView.Adapter<BbsRecyclerViewAdapter.MyViewHolder> {
    private OnItemClickLister mItemClickLister;
    public interface  OnItemClickLister{
        void onItemClick(
                View view,int position);
    }
    public void setOnClickListener(OnItemClickLister onClickListener){
        this.mItemClickLister=onClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_bbs_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       if(mItemClickLister!=null){
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mItemClickLister.onItemClick(v,position);
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView mTv_title;
        private final TextView mTv_author;
        private final TextView mTv_date;
        private final TextView mTv__agreeCount;
        private final TextView mTv_commentCount;

        public MyViewHolder(View view)
        {
            super(view);
            mTv_title = (TextView) view.findViewById(R.id.bbs_title);
            mTv_author = (TextView) view.findViewById(R.id.author);
            mTv_date = (TextView) view.findViewById(R.id.bbs_date);
            mTv__agreeCount = (TextView) view.findViewById(R.id.bbs_agree);
            mTv_commentCount = (TextView) view.findViewById(R.id.commentCount);
        }


    }
}
