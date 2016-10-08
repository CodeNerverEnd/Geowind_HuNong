package com.geowind.hunong.pestControl;

import android.graphics.Bitmap;

/**
 * Created by logaxy on 2016/9/25.
 */

public class MyPhotoUtil {

    /**
     * 作用：把bitmap转换成正方形
     * @param bitmap
     * @param sideLength
     * @return newBitmap
     */
    public static Bitmap changeBitmapToSquare(Bitmap bitmap, int sideLength) {
        if(null == bitmap || sideLength <= 0)
        {
            return  null;
        }

        Bitmap newBitmap = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if(widthOrg > sideLength && heightOrg > sideLength)
        {
            int longerEdge = (int)(sideLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : sideLength;
            int scaledHeight = widthOrg > heightOrg ? sideLength : longerEdge;
            Bitmap scaledBitmap;

            try{
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            }
            catch(Exception e){
                return null;
            }

            //截取正中部分。
            int xTopLeft = (scaledWidth - sideLength) / 2;
            int yTopLeft = (scaledHeight - sideLength) / 2;

            try{
                newBitmap = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, sideLength, sideLength);
                scaledBitmap.recycle();
            }
            catch(Exception e){
                return null;
            }
        }
        return newBitmap;
    }
}
