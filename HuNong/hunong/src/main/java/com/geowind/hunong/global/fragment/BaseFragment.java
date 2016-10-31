package com.geowind.hunong.global.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;


import com.geowind.hunong.entity.User;




/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    private Dialog dialog;

    private User myInfo;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mWidth;
    protected int mAvatarSize;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        //订阅接收消息,子类只要重写onEvent就能收到消息
      //  JMessageClient.registerEventReceiver(this);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mAvatarSize = (int) (50 * mDensity);
    }

    @Override
    public void onDestroy() {
        //注销消息接收
     //   JMessageClient.unRegisterEventReceiver(this);
        if (dialog != null) {
            dialog.dismiss();
        }
        super.onDestroy();
    }
}
