package com.geowind.hunong.entity;

import java.util.Date;

/**
 * 任务实体类
 * Created by Jiang on 2016/7/20.
 */
public class Task {
    //任务编号
    private int no;
    //农机手
    private String uname;
    //农田编号
    private int fno;
    //工作量
    private int workLoad;
    //农机编号
    private int mno;
    //作业类型
    private int workStyle;
    //日期
    private Date date;

    // 无参构造函数
    public Task(){

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getWorkStyle() {
        return workStyle;
    }

    public void setWorkStyle(int workStyle) {
        this.workStyle = workStyle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "no=" + no +
                ", uname='" + uname + '\'' +
                ", fno=" + fno +
                ", workLoad=" + workLoad +
                ", mno=" + mno +
                ", workStyle=" + workStyle +
                ", date=" + date +
                '}';
    }
}
