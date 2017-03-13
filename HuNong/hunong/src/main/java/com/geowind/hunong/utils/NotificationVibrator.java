package com.geowind.hunong.utils;

import android.content.Context;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by zhangwen on 2017/2/27.
 */

public class NotificationVibrator {
    public static  void startVibrator(Context context){
        Vibrator  vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        //震动长度
        long[] pattern={100,100};
        vibrator.vibrate(pattern,1);
    }
}
