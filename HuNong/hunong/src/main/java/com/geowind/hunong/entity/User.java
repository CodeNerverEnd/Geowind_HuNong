package com.geowind.hunong.entity;

/**
 * Created by Kui on 2016/7/20.
 */
public class User {

    //用户名
    private String username;
    //密码
    private String password;
    //手机号
    private String phone;
    //用户类型
    private String sytle;
    //姓名
    private String name;
    //头像url
    private String picUrl;
    //地址
    private String address;
    //信誉
    private String credit;
    //所属服务中心
    private int scno;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSytle() {
        return sytle;
    }

    public void setSytle(String sytle) {
        this.sytle = sytle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public int getScno() {
        return scno;
    }

    public void setScno(int scno) {
        this.scno = scno;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sytle='" + sytle + '\'' +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", address='" + address + '\'' +
                ", credit='" + credit + '\'' +
                ", scno='" + scno + '\'' +
                '}';
    }
}
