package com.geowind.hunong.global.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.view.CircleImageView;

/**
 * Created by zhangwen on 2016/10/31.
 */

public class MsgRecyclerViewAdapter extends RecyclerView.Adapter<MsgRecyclerViewAdapter.MyViewHolder> {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_recyclerview
        ,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        switch (position){
            case 0:
             //   holder.headIcon.setImageDrawable();
                holder.convName.setText("任务提醒");

                break;
            case 1:
                holder.convName.setText("专家回复");
                break;
            case 2:
                holder.convName.setText("系统消息");
                break;

        }

        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    //recyclerView的item点击事件
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView headIcon;
        TextView convName;
        TextView content;
        TextView datetime;
        TextView newMsgNumber;
        public MyViewHolder(View view)
        {
            super(view);
            headIcon= (CircleImageView) view.findViewById(R.id.msg_item_head_icon);
            convName= (TextView) view.findViewById(R.id.conv_item_name);
            content= (TextView) view.findViewById(R.id.msg_item_content);
            datetime= (TextView) view.findViewById(R.id.msg_item_date);
            newMsgNumber= (TextView) view.findViewById(R.id.new_msg_number);

        }

    }
}
