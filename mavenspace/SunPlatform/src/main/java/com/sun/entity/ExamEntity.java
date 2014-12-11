package com.sun.entity;

/**
 * 作者:caolei
 * 日期：2014-09-08
 * 作用:终端发诊疗卡记录明细实体
 * @author Administrator
 *
 */
public class ExamEntity {
	public int id;//主键ID
	public String hospitalId;//医院ID
	public String hospitalName;//医院名称
	public String patientName;//患者姓名
	public String patientSex;//患者性别
	public String idCard;//患者身份证编号
	public String birthDay;//患者生日
	public String address;//地址
	public String mobile;//电话
	public String medicalCardNo;//卡号
	public String password;//密码
	public String operId;//终端ID
	public String createTime;//发卡日期
	private String frontOrderNo;
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMedicalCardNo() {
		return medicalCardNo;
	}
	public void setMedicalCardNo(String medicalCardNo) {
		this.medicalCardNo = medicalCardNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
