package com.geowind.hunong.entity;

import com.geowind.hunong.annotation.Colnum;
import com.geowind.hunong.annotation.ID;
import com.geowind.hunong.annotation.TableName;
import com.geowind.hunong.dao.DBHelper;

import java.util.Date;

/**
 * 任务实体类
 */
@TableName(DBHelper.TASK_TABLE_NAME)
public class Task {
    @ID(autoincrement=true)
    @Colnum(DBHelper.ID)
    private int tid;
    //农机手
    @Colnum(DBHelper.UNMAE)
    private String muser;
    //块编号
    @Colnum(DBHelper.FNO)
    private int bid;
    //块名
    @Colnum(DBHelper.BNAME)
    private  String bname;
    //工作量
    @Colnum(DBHelper.WORKLOAD)
    private String workLoad;
    //农机编号
    @Colnum(DBHelper.MNO)
    private String mid;
    //作业类型
    @Colnum(DBHelper.TYPE)
    private String  type;
    //日期
    @Colnum(DBHelper.DATE)
    private String date;
    //状态
    @Colnum(DBHelper.STATE)
    private String state;
    //农田分区编号
    @Colnum(DBHelper.FZNO)
    private String zonename;
    //农田总面积
    @Colnum(DBHelper.FAREA)
    private double barea;
    //农田地址
    @Colnum(DBHelper.FADDR)
    private String address;
    //经度
    @Colnum(DBHelper.LONGITUDE)
    private double longitude;
    //纬度
    @Colnum(DBHelper.LATITUDE)
    private double latitude;
    //农田照片
    @Colnum(DBHelper.FPIC)
    private String pic;
    //作物类型
    @Colnum(DBHelper.CROPTYPE)
    private String croptype;
    //农机类型
    @Colnum(DBHelper.MSTYLE)
    private String mstyle;
    //备注
    @Colnum(DBHelper.NOTE)
    private String note;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(String workLoad) {
        this.workLoad = workLoad;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    public double getBarea() {
        return barea;
    }

    public void setBarea(double barea) {
        this.barea = barea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCroptype() {
        return croptype;
    }

    public void setCroptype(String croptype) {
        this.croptype = croptype;
    }

    public String getMstyle() {
        return mstyle;
    }

    public void setMstyle(String mstyle) {
        this.mstyle = mstyle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
