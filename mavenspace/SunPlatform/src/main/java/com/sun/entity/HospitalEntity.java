package com.sun.entity;

public class HospitalEntity {
	public int id;	//ID主键
	public int hospital_id;//医院ID
	public String hospital_name;//医院名字
	public String mail;//邮箱
	public String states;//状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public String getMail() {
		return mail;//邮箱
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getStates() {
		return states;//状态
	}
	public void setStates(String states) {
		this.states = states;
	}
}
