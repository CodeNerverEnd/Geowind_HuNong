package com.geowind.hunong.entity;

import com.geowind.hunong.annotation.Colnum;
import com.geowind.hunong.annotation.ID;
import com.geowind.hunong.annotation.TableName;
import com.geowind.hunong.dao.DBHelper;

/**
 * 系统消息实体类
 * Created by zhangwen on 2016/11/24.
 */
@TableName(DBHelper.SYSTEM_MSG)
public class SystemMsg {
    @ID(autoincrement = true)
    @Colnum(DBHelper.SYSTEM_MSG_ID)
    private Integer id;
    @Colnum(DBHelper.SYSTEM_TITLE)
    private String title;
    @Colnum(DBHelper.SYSTEM_CONTENT)
    private  String content;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
