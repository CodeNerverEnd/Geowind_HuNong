package com.geowind.hunong.global.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geowind.hunong.R;
import com.geowind.hunong.agricultureLibrary.LibraryUtils;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.entity.LibrariesBean;
import com.geowind.hunong.json.LibraryJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by zhangwen on 16-7-25.  RecyclerView的适配器
 */

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.MyViewHolder> {

    List<Object> contents;
    private Context mContext;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    String[] sortType;
    private List<Library> mLibraries;

    public LibraryRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {

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
        mLibraries=new ArrayList<>();


        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
               mContext).inflate(R.layout.gird_item_lib, parent,
                false));
        return holder;
    }
//请求网络数据
    private void requstLibrary() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","getTitles");
        params.add("category",String.valueOf(1));
        params.add("begin",String.valueOf(0));
        client.post(MyConstants.LibraryURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String jsonString=new String(responseBody);
//                mLibraries.addAll(LibraryJson.paseJson(jsonString));
//                Library library=LibraryJson.parseJsonObject(jsonString);
                System.out.println(jsonString);
                SpTools.setString(mContext,MyConstants.LIBRARY_JSON,jsonString);

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(mContext,"连接失败",Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //如果本地
        if(!TextUtils.isEmpty(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,"")))
            mLibraries.addAll(LibraryJson.paseJson(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,"")));
        else   requstLibrary();

        if(!TextUtils.isEmpty(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,""))){
            mLibraries.addAll(LibraryJson.paseJson(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,"")));
            System.out.println(mLibraries.size());
//            System.out.println(mLibraries.get(position).getHeadContent());
            holder.tv_title.setText(mLibraries.get(position).getTitle());
            holder.tv_title.setText(mLibraries.get(position).getUrl());
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_sortType;
        private TextView tv_title;
        private TextView tv_headContent;
        public MyViewHolder(View view)
        {
            super(view);
            tv_sortType = (TextView) view.findViewById(R.id.tv_lib_sortType);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_headContent = (TextView) view.findViewById(R.id.tv_headContent);
        }

    }
}
