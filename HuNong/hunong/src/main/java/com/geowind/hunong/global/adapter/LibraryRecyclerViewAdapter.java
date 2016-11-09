package com.geowind.hunong.global.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.json.LibraryJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangwen on 16-7-25.  RecyclerView的适配器
 */

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.MyViewHolder> {

    List<Object> contents;
    private Context mContext;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    String[] sortType;
    Library mLibrary;
    private MyViewHolder mHolder;

    public LibraryRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext=parent.getContext();
        MyViewHolder holder;
       switch (viewType){
           case  TYPE_HEADER:
                   holder=  new MyViewHolder(LayoutInflater.from(
                       mContext).inflate(R.layout.list_item_card_big, parent,
                       false));
               break;
             default:
                   holder = new MyViewHolder(LayoutInflater.from(
                           mContext).inflate(R.layout.gird_item_lib, parent,
                           false));
                   break;
       }
        return holder;
    }

    @Override
           public void onBindViewHolder( final  MyViewHolder holder, final int position) {
               //如果本地
               if(!TextUtils.isEmpty(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,"")))
                   mLibrary=LibraryJson.parseJsonObject(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,""));
               if(position>0){
                   if(mLibrary!=null)
                   {
                       holder.tv_headContent.setText(mLibrary.getArticleList().get(position).getHeadContent());
                       holder.tv_title.setText(mLibrary.getArticleList().get(position).getTitle());
                   }

           // 如果设置了回调，则设置点击事件
           if (mOnItemClickLitener != null)
           {
               holder.itemView.setOnClickListener(new View.OnClickListener()
               {
                   @Override
                   public void onClick(View v)
                   {
                       int pos = holder.getLayoutPosition();
                       mOnItemClickLitener.onItemClick(holder.itemView, pos);
                   }
               });
           }
       }

    }

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
        private TextView tv_sortType;
        private TextView tv_title;
        private TextView tv_headContent;
        public MyViewHolder(View view)
        {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_headContent = (TextView) view.findViewById(R.id.tv_headContent);
        }

    }
}
