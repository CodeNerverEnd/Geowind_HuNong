package com.geowind.hunong.dao.impl;

import android.content.Context;

import com.geowind.hunong.dao.base.DAO;
import com.geowind.hunong.dao.base.DAOSupport;
import com.geowind.hunong.entity.ExpertReply;

/**
 * Created by zhangwen on 2016/11/24.
 */

public class ExpertReplyDaoImpl extends DAOSupport<ExpertReply> implements DAO<ExpertReply> {
    public ExpertReplyDaoImpl(Context context) {
        super(context);
    }
}
