package com.geowind.hunong.dao.impl;

import android.content.Context;

import com.geowind.hunong.dao.SystemMsgDao;
import com.geowind.hunong.dao.base.DAOSupport;
import com.geowind.hunong.entity.SystemMsg;

/**
 * Created by zhangwen on 2016/11/24.
 */

public class SystemMsgDaoImpl extends DAOSupport<SystemMsg> implements SystemMsgDao {

    public SystemMsgDaoImpl(Context context) {
        super(context);
    }
}
