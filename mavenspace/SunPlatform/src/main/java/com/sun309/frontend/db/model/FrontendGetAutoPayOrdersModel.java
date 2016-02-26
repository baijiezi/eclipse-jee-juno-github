package com.sun309.frontend.db.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author BaiJiezi
 *
 */
public class FrontendGetAutoPayOrdersModel implements Serializable {
	private static final long serialVersionUID = 35423423354L;
	private Integer id;
	private String frontOrderNo;
	private Integer hospitalId;
	private String hospitalName;
	private String orderNo;
	private String medicalCardNo;
	private String patientName;
	private Integer patientSex;
	private Integer patientAge;
	private String mobile;
	private String deptName;
	private String doctorName;
	private String cure;
	private String settlementType;
	private Date visitDateTime;
	private String totalFee;
	private String selfFee;
	private String medicalInsurance;
	private String idcard;
	private Integer isSyn;

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
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMedicalCardNo() {
		return medicalCardNo;
	}
	public void setMedicalCardNo(String medicalCardNo) {
		this.medicalCardNo = medicalCardNo;
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
	public Integer getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getCure() {
		return cure;
	}
	public void setCure(String cure) {
		this.cure = cure;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public Date getVisitDateTime() {
		return visitDateTime;
	}
	public void setVisitDateTime(Date visitDateTime) {
		this.visitDateTime = visitDateTime;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSelfFee() {
		return selfFee;
	}
	public void setSelfFee(String selfFee) {
		this.selfFee = selfFee;
	}
	public String getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Integer getIsSyn() {
		return isSyn;
	}
	public void setIsSyn(Integer isSyn) {
		this.isSyn = isSyn;
	}
	
}
