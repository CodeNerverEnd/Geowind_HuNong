package com.geowind.hunong.drawer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.jchat.android.activity.LoginActivity;
import com.jchat.android.chatting.CircleImageView;
import com.jchat.android.chatting.utils.BitmapLoader;

import java.lang.ref.WeakReference;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by zhangwen on 16-9-1.
 */
public class DrawView extends LinearLayout {

    private TextView mTv_userName;
    private TextView mTv_exit;
    private String mPath;
    private UserInfo mUserInfo;
    private CircleImageView mTakePhotoBtn;
    private TextView mUserNameTv;
    private TextView mNickNameTv;
    private RelativeLayout mSettingRl;
    private RelativeLayout mLogoutRl;
    private Context mContext;
    private int mWidth;
    private int mHeight;
//    private final MyHandler myHandler = new MyHandler(this);
    private TextView mTv_historTask;
    private TextView mTv_credit;
    private TextView mTv_presonalInfol;
    private TextView mTv_help;
    private TextView mTv_about;
    private ImageView mIv_atavar;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

    public void initView() {
        mUserInfo = JMessageClient.getMyInfo();
        mTv_userName = (TextView)findViewById(R.id.tv_userName);
        mTv_exit = (TextView)findViewById(R.id.tv_exit);
        mTv_historTask = (TextView)findViewById(R.id.tv_historyTask);
        mTv_credit = (TextView)findViewById(R.id.tv_credit);
        mTv_presonalInfol = (TextView)findViewById(R.id.tv_personal_info);
        mTv_help = (TextView)findViewById(R.id.tv_help);
        mTv_about = (TextView) findViewById(R.id.tv_about);
        mTv_userName.setText(SpTools.getString(mContext, MyConstants.USERNAME,""));
        mIv_atavar = (ImageView) findViewById(R.id.iv_avatar);
    }
    public void setListeners(OnClickListener onClickListener) {
        mIv_atavar.setOnClickListener(onClickListener);
         mTv_about.setOnClickListener(onClickListener);
        mTv_historTask.setOnClickListener(onClickListener);
        mTv_credit.setOnClickListener(onClickListener);
        mTv_presonalInfol.setOnClickListener(onClickListener);
        mTv_help.setOnClickListener(onClickListener);
        mTv_about.setOnClickListener(onClickListener);
    }

    public void showPhoto(final Bitmap bitmap) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (bitmap != null) {
//                    Bitmap bmp = BitmapLoader.doBlur(bitmap, false);
//                    Message msg = myHandler.obtainMessage();
//                    msg.obj = bmp;
//                    msg.sendToTarget();
//                }
//            }
//        });
//        thread.start();
        mTakePhotoBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mTakePhotoBtn.setImageBitmap(bitmap);
    }

    public void showPhoto(final String path) {
        Log.i("MeView", "updated path:  " + path);
        final Bitmap bitmap = BitmapLoader.getBitmapFromFile(path, mWidth, mHeight);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (bitmap != null) {
//                    Bitmap bmp = BitmapLoader.doBlur(bitmap, false);
//                    Message msg = myHandler.obtainMessage();
//                    msg.obj = bmp;
//                    msg.sendToTarget();
//                }
//            }
//        });
//        thread.start();
        mTakePhotoBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mTakePhotoBtn.setImageBitmap(bitmap);
    }

//    public void showNickName(String nickname) {
//        mNickNameTv.setText(nickname);
//    }

//   private static class MyHandler extends Handler {
//        private final WeakReference<DrawView> mMeView;
//
//        public MyHandler(DrawView meView) {
//            mMeView = new WeakReference<DrawView>(meView);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            DrawView meView = mMeView.get();
//            if (null != meView&&msg!=null) {
//                Bitmap bitmap = (Bitmap) msg.obj;
//                meView.mAvatarIv.setImageBitmap(bitmap);
//                meView.mAvatarIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            }
//        }
//    }
}
