package com.geowind.hunong.entity;

import com.geowind.hunong.annotation.Colnum;
import com.geowind.hunong.annotation.ID;
import com.geowind.hunong.annotation.TableName;
import com.geowind.hunong.dao.DBHelper;

import java.util.Date;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@TableName(DBHelper.USER_TABLE)
public class User {

	// Fields
	@ID(autoincrement=false)
	@Colnum(DBHelper.USERNAME)
	private String username;
	private Center center;
	private String password;
	private String realname;
	@Colnum(DBHelper.SEX)
	private String sex;
	@Colnum(DBHelper.BIRTHDAY)
	private Date birthday;
	@Colnum(DBHelper.PHONE)
	private String phone;
	@Colnum(DBHelper.USER_TYPE)
	private Integer type;
	@Colnum(DBHelper.PICTURE)
	private String picture;
	@Colnum(DBHelper.ADDRESS)
	private String address;
	@Colnum(DBHelper.CREDIT)
	private String credit;
	@Colnum(DBHelper.VALID)
	private Integer valid;
	@Colnum(DBHelper.CENTERNAME)
	private String centerName;
	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}




	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Center getCenter() {
		return this.center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCredit() {
		return this.credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", center=" + center +
				", password='" + password + '\'' +
				", realname='" + realname + '\'' +
				", sex='" + sex + '\'' +
				", birthday=" + birthday +
				", phone='" + phone + '\'' +
				", type=" + type +
				", picture='" + picture + '\'' +
				", address='" + address + '\'' +
				", credit='" + credit + '\'' +
				", valid=" + valid +
				", centerName='" + centerName + '\'' +
				'}';
	}
}