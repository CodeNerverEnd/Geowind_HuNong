package com.geowind.zhangwen.sqlitetest.dao.impl;

import android.content.Context;

import com.geowind.zhangwen.sqlitetest.Task;
import com.geowind.zhangwen.sqlitetest.dao.TaskDao;
import com.geowind.zhangwen.sqlitetest.dao.base.DAOSupport;


/**
 * Created by zhangwen on 2016/11/18.
 */

public class TaskDaoImpl extends DAOSupport<Task> implements TaskDao {
    public TaskDaoImpl(Context context) {
        super(context);
    }
}
