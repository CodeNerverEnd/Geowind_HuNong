package com.geowind.hunong.entity;

import android.content.Context;

import com.geowind.hunong.entity.Library;

import java.util.List;

/**
 * Created by logaxy on 2016/7/26.
 */
public class LibrariesBean {
    public List<Library> getList(Context context) {
        return list;
    }

    public void setList(Context context,List<Library> list) {
        this.list = list;
    }

 private    List<Library> list;
}
