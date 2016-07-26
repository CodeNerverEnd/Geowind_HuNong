package com.geowind.hunong.global.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.geowind.hunong.R;
import com.geowind.hunong.map.BaiduMapActivity;
import com.geowind.hunong.weather.WeatherActivity;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by zhangwen on 16-7-25.
 */
public class HomeScrollViewFragment extends Fragment {

    private ObservableScrollView mScrollView;
    private View mView;
    private ImageButton mIb_weather;
    private CardView mCv_map;

    public static HomeScrollViewFragment newInstance() {
        return new HomeScrollViewFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_scrollview, container, false);
        mIb_weather = (ImageButton) mView.findViewById(R.id.ib_weather);
        mCv_map = (CardView) mView.findViewById(R.id.cv_map);
        return mView;
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
        MyClickListener listener=new MyClickListener();
        mIb_weather.setOnClickListener(listener);
        mCv_map.setOnClickListener(listener);
    }
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
