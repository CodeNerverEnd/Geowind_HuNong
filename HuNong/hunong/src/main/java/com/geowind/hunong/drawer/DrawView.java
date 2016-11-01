package com.geowind.hunong.drawer;

import android.content.Context;
import android.graphics.Bitmap;

import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.utils.BitmapLoader;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.geowind.hunong.view.CircleImageView;

/**
 * Created by zhangwen on 16-9-1.
 */
public class DrawView extends LinearLayout {

    private TextView mTv_userName;
    private TextView mTv_exit;
    private String mPath;
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
        mTakePhotoBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mTakePhotoBtn.setImageBitmap(bitmap);
    }

    public void showPhoto(final String path) {
        Log.i("MeView", "updated path:  " + path);
        final Bitmap bitmap = BitmapLoader.getBitmapFromFile(path, mWidth, mHeight);
        mTakePhotoBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mTakePhotoBtn.setImageBitmap(bitmap);
    }
}
