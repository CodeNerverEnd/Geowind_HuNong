package com.geowind.hunong.global.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.geowind.hunong.R;
import com.geowind.hunong.json.HomeData;
import com.geowind.hunong.map.BaiduMapActivity;
import com.geowind.hunong.utils.DensityUtils;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.weather.WeatherActivity;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangwen on 16-7-25.
 */
public class HomeScrollViewFragment extends Fragment {

    private ObservableScrollView mScrollView;
    private View mView;
    private ImageButton mIb_weather;
    private CardView mCv_map;
    private ViewPager mSlidingShow;
    private MyClickListener mListener;
    private SlidingShowAdapter mSlidingShowAdapter;
    private List<Image> mSlidingImages;
    private Gson mGson;
    private List<HomeData.SlidingShow> mSlidingShowDatas;
    private BitmapUtils mBitmapUtils;
    private int pointSelectIndex;
//测试用的图片URL
    String imgPaths[]={"http://pic.baike.soso.com/p/20130802/20130802214330-825586008.jpg",
        "http://img03.tooopen.com/uploadfile/downs/images/20110811/sy_20110811170230819021.jpg","http://img.zcool.cn/community/03396a3554c74f700000158fc16b02b.jpg",
        "http://rios.co/wp-content/uploads/2012/11/rioswing_diy_field_02.jpg",
        "http://abc.2008php.com/2012_Website_appreciate/2012-06-24/20120624160018.jpg"

};
    private LinearLayout mLl_ponits;
    private int mPotinIsSelect;
    private SlidingShow mMySliding;

    public static HomeScrollViewFragment newInstance() {
        return new HomeScrollViewFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_scrollview, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mIb_weather = (ImageButton) mView.findViewById(R.id.ib_weather);
        mCv_map = (CardView) mView.findViewById(R.id.cv_map);
        mSlidingShow = (ViewPager) mView.findViewById(R.id.home_slidingShow);
        mSlidingShowDatas = new ArrayList<HomeData.SlidingShow>();
        mBitmapUtils = new BitmapUtils(getActivity().getApplicationContext());
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.ARGB_4444);//设置一个字节的像素
        mLl_ponits = (LinearLayout) mView.findViewById(R.id.ll_home_piont);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener = new MyClickListener();
        mMySliding = new SlidingShow();
//        getDataFromNet();
//        processData();
        setData();

        initEvent();
    }
//    轮播图功能类
    private class SlidingShow extends Handler implements Runnable{
     public void stopSlidingShow(){
         removeCallbacksAndMessages(null);
     }
     public void startSlidingShow(){
         postDelayed(this,5000);
     }
    @Override
    public void run() {
        //控制轮播图的显示
        mSlidingShow.setCurrentItem((mSlidingShow.getCurrentItem()+1)%mSlidingShow.getAdapter().getCount());
        postDelayed(this,5000);
    }
}
    //处理数据
    private void processData() {

    }
    //设置数据

    private void setData() {
        setSlidingShowData();
        initPoints();
        setPotinIsSelect(pointSelectIndex);
        mMySliding.startSlidingShow();//开启轮播图
    }
//设置轮播图上的点
    private void initPoints() {
        mLl_ponits.removeAllViews();
        for(int i=0;i<5;i++){
            View v_point=new View(getActivity().getApplicationContext());
            v_point.setBackgroundResource(R.drawable.selector_point);
            v_point.setEnabled(false);//默认都是灰色点
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils.dipToPx(getActivity(), 5), DensityUtils.dipToPx(getActivity(), 5));
            layoutParams.leftMargin=DensityUtils.dipToPx(getActivity(),10);
            v_point.setLayoutParams(layoutParams);
            mLl_ponits.addView(v_point);

        }
    }

    /**
     * 轮播图的适配操作
     */
    private void setSlidingShowData() {
        //准备轮播图的数据
        mSlidingImages = new ArrayList<Image>();
        //从网络获取数据
        mSlidingShowAdapter = new SlidingShowAdapter();//创建适配器对象
        mSlidingShow.setAdapter(mSlidingShowAdapter);//设置适配器
    }

    public String getDataFromNet() {
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.add("method","home");
        final String mDataFromNet=null;
        asyncHttpClient.post(MyConstants.HOMEURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                mDataFromNet=new String(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return mDataFromNet;
    }
//设置轮播图上的点是否选中
    public void setPotinIsSelect(int potinIsSelect) {
        for (int i=0;i<5;i++){
            mLl_ponits.getChildAt(i).setEnabled(i==pointSelectIndex);
        }
    }

    /**
     * 轮播图的适配类
     */
private  class SlidingShowAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv_SlidingImage=new ImageView(getActivity().getApplicationContext());
//            设置默认图片
            iv_SlidingImage.setImageResource(R.drawable.defualt);
            //给图片添加数据
//            mSlidingShowDatas.get(position);
            //Xutils的BitMap异步加载图片并显示
            mBitmapUtils.display(iv_SlidingImage,imgPaths[position]);
            container.addView(iv_SlidingImage);
//            给图片添加点击事件，在图片轮播的时候，用户按住图片可停止播放松开继续播放
            iv_SlidingImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN://按下
                            mMySliding.stopSlidingShow();
                            break;
                        case MotionEvent.ACTION_UP://松开
                            mMySliding.startSlidingShow();
                            break;
                        case MotionEvent.ACTION_CANCEL://取消
                            mMySliding.startSlidingShow();
                            break;
                        default:
                            break;

                    }
                    return true;
                }
            });
            return iv_SlidingImage;
        }
        //设置轮播图点的选择变化


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
    /**
     * 点击事件
     */
    private void initEvent() {
        //天气
        mIb_weather.setOnClickListener(mListener);
        //地图
        mCv_map.setOnClickListener(mListener);
        //轮播图
        mSlidingShow.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当滑动到此页面时,设置点被选中
                pointSelectIndex=position;
                setPotinIsSelect(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     *
     * @param c 跳向另个activity的方法
     */
    private void toActivity(Class c){
        Intent intent=new Intent(getActivity(),c);
        startActivity(intent);
    }
    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int id=view.getId();
            switch (id){
                case R.id.ib_weather:
                    toActivity(WeatherActivity.class);
                    break;
                case R.id.ib_zixun:
                    toActivity(BaiduMapActivity.class);
                    break;
                case R.id.ib_debug:
                    break;
                case R.id.cv_map:
                    toActivity(BaiduMapActivity.class);
                    break;
                case R.id.cv_ing:
                    break;
            }
        }
    }
}
