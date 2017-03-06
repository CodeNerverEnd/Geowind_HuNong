package com.geowind.hunong.dao.impl;

import android.content.Context;

import com.geowind.hunong.dao.base.DAO;
import com.geowind.hunong.dao.base.DAOSupport;
import com.geowind.hunong.entity.Task;


/**
 * 实现类
 * Created by zhangwen on 2016/11/18.
 */

public class TaskDaoImpl extends DAOSupport<Task> implements DAO<Task> {
    public TaskDaoImpl(Context context) {
        super(context);
    }
}
