package com.geowind.hunong.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhangwen on 16-7-21.
 */
public class InpustreamUtils {
    public static String getTextFromInputStream(InputStream is){
        StringBuilder text=new StringBuilder();
        BufferedReader bfr=new BufferedReader(new InputStreamReader(is));
        String line= null;
        try {
            while (null != (line = bfr.readLine())){
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  text.toString();
    }

}
