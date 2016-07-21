package com.geowind.hunong.global.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.geowind.hunong.R;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置向导界面，采用viewPager
 * Created by zhangwen on 16-7-15.
 */
public class GuideActivity extends Activity {

    private ViewPager mVp_guidePager;
    private View mView_movePoint;
    private Button mBt_startfeel;
    private int[] mPics;
    private List<ImageView> mGuids;
    private MyAdapter mAdapter;
    private List<ImageView> mPoints;
    private LinearLayout mLl_points;
    private int mMoveDistance;
    private float mLefteMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent(){
        //页面选择事件
      mVp_guidePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             ///计算移动的点的间距
              mLefteMargin = mMoveDistance *( positionOffset+position);
              //设置红点的左边距
              RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) mView_movePoint.getLayoutParams();
              params.leftMargin=Math.round(mLefteMargin);
              mView_movePoint.setLayoutParams(params);
          }
         //viewPager滑动到最后一个页面的时候显示button
          @Override
          public void onPageSelected(int position) {
              if(position==mGuids.size()-1){
                  mBt_startfeel.setVisibility(View.VISIBLE);
              }else {
                  mBt_startfeel.setVisibility(View.GONE);
              }

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });
        //按钮点击事件
        //保存设置状态，进入主界面
        mBt_startfeel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpTools.setBoolean(getApplicationContext(), MyConstants.ISSETUP,true);
                //进入主界面
                 Intent intent=new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();//关闭自己
            }
        });
        mView_movePoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mView_movePoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mMoveDistance = mLl_points.getChildAt(1).getLeft() - mLl_points.getChildAt(0).getLeft();
            }
        });
    }

    private void initData() {

        mPics = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3,R.drawable.guide_4};
        //定义viewPager的容器
        mGuids = new ArrayList<ImageView>();
        mPoints=new ArrayList<ImageView>();
        for(int i=0;i<mPics.length;i++){
            ImageView imageView=new ImageView(getApplicationContext());
            imageView.setBackgroundResource(mPics[i]);
            mGuids.add(imageView);
        }
        mAdapter = new MyAdapter();
        mVp_guidePager.setAdapter(mAdapter);
    }
    //ViewPager的适配器
    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return  mGuids.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View child=mGuids.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.actitvity_guide);
        mVp_guidePager = (ViewPager) findViewById(R.id.vp_guide_pager);
        mLl_points = (LinearLayout) findViewById(R.id.ll_piont);
        mView_movePoint = findViewById(R.id.view_movepoint);
        mBt_startfeel = (Button) findViewById(R.id.bt_startfeel);
    }
}
