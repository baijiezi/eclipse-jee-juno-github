package com.sun309.frontend.db.model;

import java.io.Serializable;
import java.util.Date;

public class FrontendBookingOrderModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String frontOrderNo;
	private Integer hospitalId;
	private String hospitalName;
	private String userName;
	private String scheduleId;
	private String idCard;
	private String birthday;
	private String medicalCardNo;
	private String mobile;
	private String patientName;
	private Integer patientSex;
	private String address;
	private String deptName;
	private String doctorName;
	private Date orderDateTime;
	private String visitDateTime;
	private Integer serialNo;
	private Double fee;
	private Double jzFee;
	private String payCardNo;
	private Integer settlementType;
	private String paymentId;
	private String optionPayments;
	private String orderNo;
	private String operId;
	private Integer states;
	private Integer isSyn;
	private String remark;
	private Integer isToday;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMedicalCardNo() {
		return medicalCardNo;
	}
	public void setMedicalCardNo(String medicalCardNo) {
		this.medicalCardNo = medicalCardNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(Integer patientSex) {
		this.patientSex = patientSex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Date getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getVisitDateTime() {
		return visitDateTime;
	}
	public void setVisitDateTime(String visitDateTime) {
		this.visitDateTime = visitDateTime;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public Integer getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getOptionPayments() {
		return optionPayments;
	}
	public void setOptionPayments(String optionPayments) {
		this.optionPayments = optionPayments;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsSyn() {
		return isSyn;
	}
	public void setIsSyn(Integer isSyn) {
		this.isSyn = isSyn;
	}
	public Double getJzFee() {
		return jzFee;
	}
	public void setJzFee(Double jzFee) {
		this.jzFee = jzFee;
	}
	public Integer getIsToday() {
		return isToday;
	}
	public void setIsToday(Integer isToday) {
		this.isToday = isToday;
	}
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
	}
	

}
