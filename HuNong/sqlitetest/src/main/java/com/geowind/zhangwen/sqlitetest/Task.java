package com.geowind.zhangwen.sqlitetest;


import com.geowind.zhangwen.sqlitetest.annotation.Colnum;
import com.geowind.zhangwen.sqlitetest.annotation.ID;
import com.geowind.zhangwen.sqlitetest.annotation.TableName;
import com.geowind.zhangwen.sqlitetest.dao.DBHelper;

/**
 * 任务实体类
 * Created by Jiang on 2016/7/20.
 */
@TableName(DBHelper.TASK_TABLE_NAME)
public class Task {
    @ID(autoincrement=true)
    @Colnum(DBHelper.ID)
    private int no;
    //农机手
    @Colnum(DBHelper.UNMAE)
    private String mUname;
    //种粮大户
    @Colnum(DBHelper.FNAME)
    private String fUname;
//    //农田编号
//    @Colnum(DBHelper.FNO)
//    private int fno;
//    //工作量
//    @Colnum(DBHelper.WORKLOAD)
//    private String workLoad;
//    //农机编号
//    @Colnum(DBHelper.MNO)
//    private String mno;
//    //作业类型
//    @Colnum(DBHelper.TYPE)
//    private String  type;
//    //日期
//    @Colnum(DBHelper.DATE)
//    private String date;
//    //状态
//    @Colnum(DBHelper.STATE)
//    private String state;
//    //农田分区编号
//    @Colnum(DBHelper.FZNO)
//    private String fzno;
//    //农田总面积
//    @Colnum(DBHelper.FAREA)
//    private double farea;
//    //农田地址
//    @Colnum(DBHelper.FADDR)
//    private String faddr;
//    //经度
//    @Colnum(DBHelper.LONGITUDE)
//    private double longitude;
//    //纬度
//    @Colnum(DBHelper.LATITUDE)
//    private double latitude;
//    //农田照片
//    @Colnum(DBHelper.FPIC)
//    private String fpic;
//    //作物类型
//    @Colnum(DBHelper.CROPTYPE)
//    private String cropType;
//    //农机类型
//    @Colnum(DBHelper.MSTYLE)
//    private String mstyle;
//    //备注
//    @Colnum(DBHelper.NOTE)
//    private String note;

    public void setNo(int no) {
        this.no = no;
    }

    public void setUname(String uname) {
        mUname = uname;
    }

    public void setfUname(String fUname) {
        this.fUname = fUname;
    }

//    public void setFno(int fno) {
//        this.fno = fno;
//    }
//
//    public void setWorkLoad(String workLoad) {
//        this.workLoad = workLoad;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setMno(String mno) {
//        this.mno = mno;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public void setFzno(String fzno) {
//        this.fzno = fzno;
//    }
//
//    public void setFaddr(String faddr) {
//        this.faddr = faddr;
//    }
//
//    public void setFarea(double farea) {
//        this.farea = farea;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public void setFpic(String fpic) {
//        this.fpic = fpic;
//    }
//
//    public void setCropType(String cropType) {
//        this.cropType = cropType;
//    }
//
//    public void setMstyle(String mstyle) {
//        this.mstyle = mstyle;
//    }

   public int getNo() {
        return no;
    }

    public String getUname() {
        return mUname;
    }

    public String getfUname() {
        return fUname;
    }

//    public int getFno() {
//        return fno;
//    }

//    public String getWorkLoad() {
//        return workLoad;
//    }
//
//    public String getMno() {
//        return mno;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public String getFzno() {
//        return fzno;
//    }
//
//    public double getFarea() {
//        return farea;
//    }
//
//    public String getFaddr() {
//        return faddr;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public String getFpic() {
//        return fpic;
//    }
//
//    public String getCropType() {
//        return cropType;
//    }
//
//    public String getMstyle() {
//        return mstyle;
//    }
//
//    public String getNote() {
//        return note;
//    }
}
