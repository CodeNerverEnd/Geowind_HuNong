package com.geowind.hunong.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定实体字段与数据库表中列的对应关系
 * Created by zhangwen on 2016/11/18.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Colnum {
    /**
     *
     * @return
     */
    String value();
}
