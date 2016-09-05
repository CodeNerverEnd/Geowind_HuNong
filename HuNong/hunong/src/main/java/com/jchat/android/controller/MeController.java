package com.jchat.android.controller;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import com.geowind.hunong.R;
import com.jchat.android.activity.MeFragment;
import com.jchat.android.chatting.utils.DialogCreator;
import com.jchat.android.tools.NativeImageLoader;
import com.jchat.android.view.MeView;


public class MeController implements OnClickListener {

    private MeView mMeView;
    private MeFragment mContext;
    private Dialog mDialog;
    private int mWidth;

    public MeController(MeView meView, MeFragment context, int width) {
        this.mMeView = meView;
        this.mContext = context;
        this.mWidth = width;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_avatar_iv:
                Log.i("MeController", "avatar onClick");
                mContext.startBrowserAvatar();
                break;
            case R.id.take_photo_iv:
                OnClickListener listener = new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.jmui_take_photo_btn:
                                mDialog.cancel();
                                mContext.takePhoto();
                                break;
                            case R.id.jmui_pick_picture_btn:
                                mDialog.cancel();
                                mContext.selectImageFromLocal();
                                break;
                        }
                    }
                };
                mDialog = DialogCreator.createSetAvatarDialog(mContext.getActivity(), listener);
                mDialog.show();
                mDialog.getWindow().setLayout((int) (0.8 * mWidth), WindowManager.LayoutParams.WRAP_CONTENT);
                break;
            case R.id.user_info_rl:
                mContext.startMeInfoActivity();
                break;
            case R.id.setting_rl:
                mContext.StartSettingActivity();
                break;
//			//退出登录 清除Notification，清除缓存
            case R.id.logout_rl:
                listener = new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.jmui_cancel_btn:
                                mDialog.cancel();
                                break;
                            case R.id.jmui_commit_btn:
                                mContext.Logout();
                                mContext.cancelNotification();
                                NativeImageLoader.getInstance().releaseCache();
                                mContext.getActivity().finish();
                                mDialog.cancel();
                                break;
                        }
                    }
                };
                mDialog = DialogCreator.createLogoutDialog(mContext.getActivity(), listener);
                mDialog.getWindow().setLayout((int) (0.8 * mWidth), WindowManager.LayoutParams.WRAP_CONTENT);
                mDialog.show();
                break;
        }
    }

}
