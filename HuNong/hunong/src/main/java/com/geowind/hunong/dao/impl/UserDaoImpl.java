package com.geowind.hunong.dao.impl;

import android.content.Context;

import com.geowind.hunong.dao.base.DAO;
import com.geowind.hunong.dao.base.DAOSupport;
import com.geowind.hunong.entity.User;

/**
 * Created by zhangwen on 2017/3/5.
 */

public class UserDaoImpl extends DAOSupport<User> implements DAO<User> {
    public UserDaoImpl(Context context) {
        super(context);
    }

}
