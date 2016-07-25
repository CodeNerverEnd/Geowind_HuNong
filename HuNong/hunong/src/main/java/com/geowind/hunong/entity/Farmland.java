package com.geowind.hunong.entity;

/**
 * Created by Kui on 2016/7/20.
 */
public class Farmland {
    //编号
    private int no;
    //经纬度
    private String coordinate;
    //地址
    private String address;
    //面积
    private double area;
    //实景图
    private String picUrl;
    //拥有者编号
    private int ono;
    //流传信息
    private String transMsg;
    //产量
    private double prodMsg;
    //区号
    private int areaCode;
    //作物状态
    private int CropState;
    //pH
    private double ph;
    //氮磷钾
    private String npk;

    public Farmland() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getOno() {
        return ono;
    }

    public void setOno(int ono) {
        this.ono = ono;
    }

    public String getTransMsg() {
        return transMsg;
    }

    public void setTransMsg(String transMsg) {
        this.transMsg = transMsg;
    }

    public double getProdMsg() {
        return prodMsg;
    }

    public void setProdMsg(double prodMsg) {
        this.prodMsg = prodMsg;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getCropState() {
        return CropState;
    }

    public void setCropState(int cropState) {
        CropState = cropState;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public String getNpk() {
        return npk;
    }

    public void setNpk(String npk) {
        this.npk = npk;
    }

    @Override
    public String toString() {
        return "Farmland{" +
                "no=" + no +
                ", coordinate='" + coordinate + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", picUrl='" + picUrl + '\'' +
                ", ono=" + ono +
                ", transMsg='" + transMsg + '\'' +
                ", prodMsg=" + prodMsg +
                ", areaCode=" + areaCode +
                ", CropState=" + CropState +
                ", ph=" + ph +
                ", npk='" + npk + '\'' +
                '}';
    }
}
