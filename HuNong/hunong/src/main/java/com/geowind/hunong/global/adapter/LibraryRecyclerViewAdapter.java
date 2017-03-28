package com.geowind.hunong.global.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.M;
import com.facebook.common.activitylistener.ListenableActivity;
import com.facebook.common.internal.Objects;
import com.geowind.hunong.R;
import com.geowind.hunong.entity.LibSearch;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.entity.SystemMsg;
import com.geowind.hunong.json.LibraryJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangwen on 16-7-25.  RecyclerView的适配器
 */

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.MyViewHolder>{

    public static   int NOWPAGE = 0;
    public   static int CATEGORY = 0;
    private Context mContext;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    static final int TYPE_NOMOREDATA=2;
    private final int REFRESHING=0;
    private final int LOADING_MORE=1;
    private List<Library> mLibraries;
    private  boolean isNomoreData=false;
    private MyViewHolder mHolder;



    public LibraryRecyclerViewAdapter() {

        mLibraries=new ArrayList<Library>();
    }
    public List<Library> getLibraries(){
        return mLibraries;
    }


    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }



    @Override
    public int getItemCount() {
        return mLibraries.size()+1;
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
           case TYPE_NOMOREDATA:
               holder = new MyViewHolder(LayoutInflater.from(
                       mContext).inflate(R.layout.item_library_nomore_data, parent,
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
//               //如果本地
//               if(!TextUtils.isEmpty(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,"")))
//                   mLibrary=LibraryJson.parseJsonObject(SpTools.getString(mContext,MyConstants.LIBRARY_JSON,""));
               if(position>0){
                   holder.tv_headContent.setText(mLibraries.get(position-1).getSummary());
                   holder.tv_title.setText(mLibraries.get(position-1).getTitle());
                   BitmapUtils bitmapUtils=new BitmapUtils(mContext);
                   bitmapUtils.display(holder.mIv_library,mLibraries.get(position-1).getImgURL());

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
                   if(position==mLibraries.size()&&isNomoreData==false){
                       requstLibrary(NOWPAGE+1,CATEGORY,LOADING_MORE);

                   }else if(isNomoreData==true){
                      // Toast.makeText(mContext,"没有更多数据",Toast.LENGTH_SHORT).show();
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
    /**
     * 从服务器获取数据
     *
     */

    public void requstLibrary(int begin, int category, final int requstType) {
        isNomoreData=false;
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","getArticles");
        params.add("category",String.valueOf(category));
        params.add("nowPage",String.valueOf(begin));
        client.post(MyConstants.LibraryURL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody);

                if(jsonString==null){

                }else {
                    if(LibraryJson.paseJson(jsonString).size()==0)
                        isNomoreData=true;
                    if(requstType==REFRESHING){
                        mLibraries.clear();
                    }
                    mLibraries.addAll(LibraryJson.paseJson(jsonString));
                    notifyDataSetChanged();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tv_sortType;
        private TextView tv_title;
        private TextView tv_headContent;
        private final ImageButton mButton1;
        private final ImageButton mButton2;
        private final ImageButton mButton3;
        private final ImageButton mButton4;
        private final ImageButton mButton5;
        private final ImageButton mButton6;
        private final ImageButton mButton7;
        private final ImageButton mButton8;
        private final ImageView mIv_library;

        public MyViewHolder(View view)
        {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_headContent = (TextView) view.findViewById(R.id.tv_headContent);
            mIv_library = (ImageView) view.findViewById(R.id.iv_library);
            mButton1 = (ImageButton) view.findViewById(R.id.category_1);
            mButton2 = (ImageButton) view.findViewById(R.id.category_2);
            mButton3 = (ImageButton) view.findViewById(R.id.category_3);
            mButton4 = (ImageButton) view.findViewById(R.id.category_4);
            mButton5 = (ImageButton) view.findViewById(R.id.category_5);
            mButton6 = (ImageButton) view.findViewById(R.id.category_6);
            mButton7 = (ImageButton) view.findViewById(R.id.category_7);
            mButton8 = (ImageButton) view.findViewById(R.id.category_8);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.category_1:
                    NOWPAGE=0;
                    CATEGORY=1;
                    requstLibrary(0,1,REFRESHING);
                    break;
                case R.id.category_2:
                    NOWPAGE=0;
                    CATEGORY=2;
                    requstLibrary(0,2,REFRESHING);
                    break;
                case R.id.category_3:
                    NOWPAGE=0;
                    CATEGORY=3;
                    requstLibrary(0,3,REFRESHING);
                    break;
                case R.id.category_4:
                    NOWPAGE=0;
                    CATEGORY=4;
                    requstLibrary(0,4,REFRESHING);
                    break;
                case R.id.category_5:
                    NOWPAGE=0;
                    CATEGORY=5;
                    requstLibrary(0,5,REFRESHING);
                    break;
                case R.id.category_6:
                    NOWPAGE=0;
                    CATEGORY=6;
                    requstLibrary(0,6,REFRESHING);
                    break;
                case R.id.category_7:
                    NOWPAGE=0;
                    CATEGORY=7;
                    requstLibrary(0,7,REFRESHING);
                    break;
                case R.id.category_8:
                    NOWPAGE=0;
                    CATEGORY=8;
                    requstLibrary(0,8,REFRESHING);
                    break;

            }
            notifyDataSetChanged();
        }
    }
}
