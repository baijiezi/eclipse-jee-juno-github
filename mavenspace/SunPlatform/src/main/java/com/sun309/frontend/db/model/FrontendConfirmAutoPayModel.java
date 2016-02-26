package com.sun309.frontend.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author BaiJiezi
 *
 */
public class FrontendConfirmAutoPayModel implements Serializable {
	private static final long serialVersionUID = 354354L;
	private Integer id;
	private String frontOrderNo;
	private Integer hospitalId;
	private String hospitalName;
	private String userName;
	private String patientName;
	private String medicalCardNo;
	private String mobile;
	private String orderNo;
	private Double realPayFee;
	private String payCardNo;
	private Integer settlementType;
	private String paymentId;
	private String payTranLine;
	private String systemPayTranLine;
	private String optionPayments;
	private String operId;
	private String settleId;
	private Date payTime;
	private Integer states;
	private Integer isSyn;
	private String remark;
	
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
	
	public String getPayTranLine() {
		return payTranLine;
	}
	public void setPayTranLine(String payTranLine) {
		this.payTranLine = payTranLine;
	}
	public String getSystemPayTranLine() {
		return systemPayTranLine;
	}
	public void setSystemPayTranLine(String systemPayTranLine) {
		this.systemPayTranLine = systemPayTranLine;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Double getRealPayFee() {
		return realPayFee;
	}
	public void setRealPayFee(Double realPayFee) {
		this.realPayFee = realPayFee;
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
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getSettleId() {
		return settleId;
	}
	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
	}
	public Integer getIsSyn() {
		return isSyn;
	}
	public void setIsSyn(Integer isSyn) {
		this.isSyn = isSyn;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
