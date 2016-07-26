package com.geowind.hunong.utils;

import android.content.Context;

import java.util.List;

/**
 * Created by logaxy on 2016/7/26.
 */
public class ListBean {
    Context context;

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    List<Object> list;
    public ListBean(Context context){
        this.context=context;
    }

}
