package com.geowind.hunong.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * 实体操作类的通用接口
 * Created by zhangwen on 2016/11/18.
 */

public interface DAO<M> {
    long insert(M m);
    int delete(Serializable id);
    int update(M m);
    List<M> findAll();
    List<M> findByCondition(String[] columns, String selection, String[] selectionArgs, String orderBy, String limit);
}
