package com.sun.entity;

import java.util.List;

public class User {
	private List<UserRole> userRoles;

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	private String id;	//主键ID
	
	private String orgName;//菜单栏

	private String name;//用户名

	private String password;//密码
	
	private String user_level;//用户级别
	
	private String real_name;//真实姓名
	
	private String department;//所属部门
	
	private String mobile;//手机号码

	private String states;//状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

//	public Date getCreatedatetime() {
//		return createdatetime;
//	}
//
//	public void setCreatedatetime(Date createdatetime) {
//		this.createdatetime = createdatetime;
//	}
//
//	public Date getModifydatetime() {
//		return modifydatetime;
//	}
//
//	public void setModifydatetime(Date modifydatetime) {
//		this.modifydatetime = modifydatetime;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}