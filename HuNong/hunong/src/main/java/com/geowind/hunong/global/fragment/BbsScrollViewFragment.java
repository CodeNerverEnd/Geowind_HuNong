package com.geowind.hunong.global.fragment;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geowind.hunong.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.oguzdev.circularfloatingactionmenu.lib.FloatingActionButton;

/**
 * Created by zhangwen on 16-7-25.
 */
//论坛的fragment
public class BbsScrollViewFragment extends Fragment {

    private ObservableScrollView mScrollView;
    private FloatingActionButton mRightLowerFloatButton;
    private ImageView mAddImage;

    public static BbsScrollViewFragment newInstance() {
        return new BbsScrollViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bbs_scrollview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        showFloatButton();
        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }

    @Override
    public void onDestroy() {
        mRightLowerFloatButton.setVisibility(View.GONE);
        super.onDestroy();
    }

    private void showFloatButton() {
        mAddImage = new ImageView(getActivity());
        mAddImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
        mRightLowerFloatButton = new FloatingActionButton.Builder(getActivity())
                .setContentView(mAddImage)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .build();
    }
}
