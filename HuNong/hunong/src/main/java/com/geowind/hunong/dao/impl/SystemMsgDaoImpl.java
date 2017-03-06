package com.geowind.hunong.dao.impl;

import android.content.Context;

import com.geowind.hunong.dao.base.DAO;
import com.geowind.hunong.dao.base.DAOSupport;
import com.geowind.hunong.entity.SystemMsg;

/**
 * Created by zhangwen on 2016/11/24.
 */

public class SystemMsgDaoImpl extends DAOSupport<SystemMsg> implements DAO<SystemMsg> {

    public SystemMsgDaoImpl(Context context) {
        super(context);
    }
}
