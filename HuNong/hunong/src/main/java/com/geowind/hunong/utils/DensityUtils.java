package com.geowind.hunong.utils;

import android.content.Context;

/**
 * Created by zhangwen on 16-7-17.
 *
 */
//屏幕适配
public class DensityUtils {
    //根据手机的分辨率将dip转化成px
    public static int dipToPx(Context context,float dpValue){
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(scaledDensity*dpValue+0.5f);
    }
    //根据手机的分辨率将px转化成dip
    public static int pxToDip(Context context,float pxValue){
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue/scaledDensity+0.5f);
    }

}
