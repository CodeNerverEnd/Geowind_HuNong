package com.geowind.hunong.entity;

/**
 * Insectcontrol entity. @author MyEclipse Persistence Tools
 */
public class Insectcontrol implements java.io.Serializable {

	// Fields
	private Integer cid;
	private User user;
	private String descr;
	private String uploadImage;
	private String uploadtime;
	private String insectImage;
	private String info;
	private String harm;
	private String counterplan;
	private String solvetime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Insectcontrol() {
	}

	/** full constructor */
	public Insectcontrol(User user, String descr, String uploadImage,
			String uploadtime, String insectImage, String info, String harm,
			String counterplan, String solvetime, Integer status) {
		this.user = user;
		this.descr = descr;
		this.uploadImage = uploadImage;
		this.uploadtime = uploadtime;
		this.insectImage = insectImage;
		this.info = info;
		this.harm = harm;
		this.counterplan = counterplan;
		this.solvetime = solvetime;
		this.status = status;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUploadImage() {
		return this.uploadImage;
	}

	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getInsectImage() {
		return this.insectImage;
	}

	public void setInsectImage(String insectImage) {
		this.insectImage = insectImage;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getHarm() {
		return this.harm;
	}

	public void setHarm(String harm) {
		this.harm = harm;
	}

	public String getCounterplan() {
		return this.counterplan;
	}

	public void setCounterplan(String counterplan) {
		this.counterplan = counterplan;
	}

	public String getSolvetime() {
		return this.solvetime;
	}

	public void setSolvetime(String solvetime) {
		this.solvetime = solvetime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}