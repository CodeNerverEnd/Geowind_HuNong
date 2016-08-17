package com.geowind.hunong.entity;

import java.util.Date;

/**
 * 任务实体类
 * Created by Jiang on 2016/7/20.
 */
public class Task {
private int no;
    //农机手
    private String mUname;
    //种粮大户
    private String fUname;
    //农田编号
    private int fno;
    //工作量
    private String workLoad;
    //农机编号
    private int mno;
    //作业类型
    private String  type;
    //日期
    private String date;
    //状态
    private int state;
    //农田分区编号
    private int fzno;
    //农田总面积
    private double farea;
    //农田地址
    private String faddr;
    //经度
    private double longitude;
    //纬度
    private double latitude;
    //农田照片
    private String fpic;
    //作物类型
    private String cropType;
    //农机类型
    private String mstyle;
    //备注
    private String note;
   public int getNo() {
        return no;
    }

    public String getUname() {
        return mUname;
    }

    public String getfUname() {
        return fUname;
    }

    public int getFno() {
        return fno;
    }

    public String getWorkLoad() {
        return workLoad;
    }

    public int getMno() {
        return mno;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public int getFzno() {
        return fzno;
    }

    public double getFarea() {
        return farea;
    }

    public String getFaddr() {
        return faddr;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getFpic() {
        return fpic;
    }

    public String getCropType() {
        return cropType;
    }

    public String getMstyle() {
        return mstyle;
    }

    public String getNote() {
        return note;
    }
}
