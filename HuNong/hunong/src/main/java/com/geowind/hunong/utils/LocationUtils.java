package com.geowind.hunong.utils;

import android.content.Context;
import android.text.TextUtils;
/**
 * Created by zhangwen on 16-7-20.
 */
public class LocationUtils {
    public static String[] getAddr(Context context){
        String[] ssq=new String[3];
       if(!TextUtils.isEmpty(SpTools.getString(context,MyConstants.LOCATION,""))){
           String address=SpTools.getString(context,MyConstants.LOCATION,"").replace("中国","");
           String province="无";
           String city="无";
           String district="无";
           String as[]=null,as1[]=null;
           if(address.contains("省")){
               as=address.split("省");
               province=as[0];
               as1=as[1].split("市");
               city=as1[0];
           }else{
               if(address.contains("重庆")){
                   province="重庆";
                   as1=address.split("市");
                   city="重庆";

               }else if(address.contains("北京")){
                   province="北京";
                   as1=address.split("市");
                   city="北京";
               }
               else if(address.contains("上海")){
                   province="上海";
                   as1=address.split("市");
                   city="上海";

               }else if(address.contains("天津")){
                   province="天津";
                   as1=address.split("市");
                   city="天津";

               }else if(address.contains("内蒙古")){
                   province="内蒙古";
                   String[] a=as[1].split("自治区");
                   as1=a[1].split("市");
                   city=as1[0];

               }else if(address.contains("西藏")){
                   province="西藏";
                   String[] a=address.split("自治区");
                   as1=a[1].split("市");
                   city=as1[0];

               }else if(address.contains("新疆")){
                   province="新疆";
                   String[] a=address.split("自治区");
                   as1=a[1].split("市");
                   city=as1[0];

               }else if(address.contains("宁夏")){
                   province="宁夏";
                   String[] a=address.split("自治区");
                   as1=a[1].split("市");
                   city=as1[0];

               }else if(address.contains("广西")){
                   province="广西";
                   String[] a=address.split("自治区");
                   as1=a[1].split("市");
                   city=as1[0];

               }

           }
           if(as1[1].contains("区")){
               String[]  as2=as1[1].split("区");
               district=as2[0];
           }else if(as1[1].contains("乡")) {
               String[]   as2=as1[1].split("乡");
               district=as2[0];
           }else if(as1[1].contains("县")) {
               String[]   as2=as1[1].split("县");
               district=as2[0];
           }else if(as1[1].contains("市")) {
               String[]   as2=as1[1].split("市");
               district=as2[0];
           }
           ssq[0]=province;
           ssq[1]=city;
           ssq[2]=district;

       }
        return ssq;
    }
}
