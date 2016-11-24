package com.geowind.hunong.global.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.internal.Objects;
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

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.MyViewHolder>{

    private  int NOWPAGE = 0;
    private  int CATEGORY = 0;
    private Context mContext;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    Library mLibrary;
    int itemCount=0;
    private MyViewHolder mHolder;


    public LibraryRecyclerViewAdapter() {

        mLibrary=new Library();
        requstLibrary(NOWPAGE,CATEGORY);
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
        return itemCount+1;
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
               holder.mButton1.setOnClickListener(holder);
               holder.mButton2.setOnClickListener(holder);
               holder.mButton3.setOnClickListener(holder);
               holder.mButton4.setOnClickListener(holder);
               holder.mButton5.setOnClickListener(holder);
               holder.mButton6.setOnClickListener(holder);
               holder.mButton7.setOnClickListener(holder);
               holder.mButton8.setOnClickListener(holder);


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
//               //如果本地
//               if(!TextUtils.isEmpty(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,"")))
//                   mLibrary=LibraryJson.parseJsonObject(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,""));

               if(position>0){
                   if(mLibrary!=null)
                   {
//                       holder.tv_headContent.setText(mLibrary.getArticleList().get(position).getHeadContent());
  //                     holder.tv_title.setText(mLibrary.getArticleList().get(position).getTitle());
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

    /**
     * 从服务器获取数据
     *
     */

    public void requstLibrary(int begin,int category) {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","getArticles");
        params.add("category",String.valueOf(category));
        params.add("nowPage",String.valueOf(begin));
        client.post(MyConstants.LibraryURL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody);
            //    SpTools.setString(getActivity(),MyConstants.LIBRARY_JSON,jsonString);
//                mLibrary= LibraryJson.parseJsonObject(jsonString);
//                itemCount=mLibrary.getArticleList().size();
                System.out.println("文库====="+jsonString);
                notifyDataSetChanged();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
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


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tv_sortType;
        private TextView tv_title;
        private TextView tv_headContent;
        private final Button mButton1;
        private final Button mButton2;
        private final Button mButton3;
        private final Button mButton4;
        private final Button mButton5;
        private final Button mButton6;
        private final Button mButton7;
        private final Button mButton8;

        public MyViewHolder(View view)
        {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_headContent = (TextView) view.findViewById(R.id.tv_headContent);
            mButton1 = (Button) view.findViewById(R.id.category_1);
            mButton2 = (Button) view.findViewById(R.id.category_2);
            mButton3 = (Button) view.findViewById(R.id.category_3);
            mButton4 = (Button) view.findViewById(R.id.category_4);
            mButton5 = (Button) view.findViewById(R.id.category_5);
            mButton6 = (Button) view.findViewById(R.id.category_6);
            mButton7 = (Button) view.findViewById(R.id.category_7);
            mButton8 = (Button) view.findViewById(R.id.category_8);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.category_1:
                    NOWPAGE=0;
                    CATEGORY=1;
                    requstLibrary(0,1);
                    break;
                case R.id.category_2:
                    NOWPAGE=0;
                    CATEGORY=2;
                    requstLibrary(0,2);
                    break;
                case R.id.category_3:
                    NOWPAGE=0;
                    CATEGORY=3;
                    requstLibrary(0,3);
                    break;
                case R.id.category_4:
                    NOWPAGE=0;
                    CATEGORY=4;
                    requstLibrary(0,4);
                    break;
                case R.id.category_5:
                    NOWPAGE=0;
                    CATEGORY=5;
                    requstLibrary(0,5);
                    break;
                case R.id.category_6:
                    NOWPAGE=0;
                    CATEGORY=6;
                    requstLibrary(0,6);
                    break;
                case R.id.category_7:
                    NOWPAGE=0;
                    CATEGORY=7;
                    requstLibrary(0,7);
                    break;
                case R.id.category_8:
                    NOWPAGE=0;
                    CATEGORY=8;
                    requstLibrary(0,8);
                    break;

            }
        }
    }
}
