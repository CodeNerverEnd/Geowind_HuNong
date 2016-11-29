package com.geowind.hunong.entity;

import com.geowind.hunong.annotation.Colnum;
import com.geowind.hunong.annotation.ID;
import com.geowind.hunong.annotation.TableName;
import com.geowind.hunong.dao.DBHelper;

/**
 * 专家回复实体类
 * Created by zhangwen on 2016/11/24.
 */
@TableName(DBHelper.EXPERT_REPLY)
public class ExpertReply{
    @ID(autoincrement = true)
    @Colnum(DBHelper.EXPERT_ID)
    private Integer cid;//id
    private String userName;//用户名
    @Colnum(DBHelper.CCONTENT)
    private String ccontent;//提问内容
    @Colnum(DBHelper.CTIME)
    private  String ctime;//提问时间
    @Colnum(DBHelper.ACONTENT)
    private  String acontent;//回复内容
    @Colnum(DBHelper.ATIME)
    private String atime;//回复时间
    @Colnum(DBHelper.REPLY_STATUS)
    private  String status;//状态
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
