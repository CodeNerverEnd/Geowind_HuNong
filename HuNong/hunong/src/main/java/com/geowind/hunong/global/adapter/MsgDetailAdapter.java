package com.geowind.hunong.global.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.application.HunongApplication;
import com.geowind.hunong.dao.impl.ExpertReplyDaoImpl;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.ExpertReply;
import com.geowind.hunong.entity.SystemMsg;
import com.geowind.hunong.entity.Task;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwen on 2016/11/12.
 */

public class MsgDetailAdapter  extends BaseAdapter{
    private  Context mContext;
    private Intent mIntent;
    private TextView mTv_title;
    private TextView mTv_expertReply;
    private TextView mTv_titlebar;
    private String mMsgType;
    private TextView mTv_taskTile;
    private TextView mTv_address;
    private List<Task> mTasks;
    private ImageView mIv_msg_taskImg;
    private TextView mTv_mType;
    private TextView mTv_msg_date;
    private  List<SystemMsg> mSystemMsgs;
    private  List<ExpertReply> mExpertReplies;
    private TextView mTv_feedbackDesc;

    public  MsgDetailAdapter(Context context,Intent intent){

        mContext=context;
        mIntent=intent;
        mTasks=new ArrayList<Task>();
        mSystemMsgs = new ArrayList<SystemMsg>();
        mExpertReplies = new ArrayList<ExpertReply>();
        mExpertReplies.addAll(new ExpertReplyDaoImpl(mContext).findAll());
        mTasks.addAll(new TaskDaoImpl(mContext).findAll());
        mMsgType = mIntent.getStringExtra("msgType");
    }
    @Override
    public int getCount() {
        switch (mMsgType){
            case "任务提醒":
            return mTasks.size();

            case "专家回复":
                return  mExpertReplies.size();
            case "系统消息":
                return mSystemMsgs.size();

        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Intent intent =mIntent;
        if (null != intent) {


            switch (mMsgType){
                case "任务提醒":
                    HunongApplication.NEW_TASK_COUNT=0;
                    convertView=View.inflate(mContext, R.layout.item_task_msglist,null);
                    mTv_title = (TextView) convertView.findViewById(R.id.tv_msg_taskTile);
                    mIv_msg_taskImg = (ImageView) convertView.findViewById(R.id.iv_msg_taskImg);
                    mTv_address = (TextView) convertView.findViewById(R.id.tv_msg_taskAdress);
                    mTv_mType = (TextView) convertView.findViewById(R.id.tv_taskDetails_minchineType);
                    mTv_msg_date = (TextView) convertView.findViewById(R.id.tv_msg_date);
                    if(mTasks.size()>0){
                        mTv_address.setText(mTasks.get(position).getAddress());
                        mTv_title.setText("任务"+position);
                        BitmapUtils bitmapUtils=new BitmapUtils(mContext);
                        bitmapUtils.display(mIv_msg_taskImg,mTasks.get(position).getPic());
                        mTv_msg_date.setText(mTasks.get(position).getDate());
                    }

                    break;
                case "专家回复":
                    HunongApplication.NEW_EXPERT_REPLY_COUNT=0;
                    convertView=View.inflate(mContext, R.layout.item_expert_reply,null);
                    mTv_feedbackDesc = (TextView) convertView.findViewById(R.id.tv_feedbackDesc);
                    mTv_expertReply = (TextView) convertView.findViewById(R.id.tv_expertReply);
                    if(mExpertReplies.size()>0){
                        mTv_feedbackDesc.setText(mExpertReplies.get(position).getAcontent());
                        mTv_expertReply.setText(mExpertReplies.get(position).getCcontent());
                    }
                    break;
                case "系统消息":
                    HunongApplication.NEW_SYSTEM_MSG_COUNT=0;
                    convertView=View.inflate(mContext, R.layout.item_system_msglist,null);
                    mTv_title = (TextView) convertView.findViewById(R.id.tv_sysMsgTitle);
                    TextView tv_msg_sysMsgContent= (TextView) convertView.findViewById(R.id.tv_msg_sysMsgContent);
                    if(mSystemMsgs.size()>0){
                       // mTv_
                    }
                    break;
                default:
                    convertView=View.inflate(mContext, R.layout.item_msg_list,null);

            }
        }else {
            convertView=View.inflate(mContext, R.layout.item_msg_list,null);
        }

        return convertView;
    }

}
