package com.sun.entity;

/**
 * 作用:补缴订单实体
 * 作者:caolei
 * 日期:2014-03-11
 * @author Administrator
 *
 */
public class PayOrderEntity {
	public int id;//主键ID
	public String hospitalId;//医院ID
	public String hospitalName;//医院名称
	public String userName;//用户名
	public String patientName;//患者姓名
	public String medicalCardNo;//诊疗卡号
	public String mobile;//手机号码
	public String idCard;//身份证
	public String orderNo;//订单号
	public String fee;//挂号费
	public String payCardNo;//支付卡号
	public String mentType;//结算方式
	public String payMentId;//支付号
	public String payMent;//账户类型
	public String operId;//操作员
	public String status;//状态
	public String createTime;//创建时间
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getMentType() {
		return mentType;
	}
	public void setMentType(String mentType) {
		this.mentType = mentType;
	}
	public String getPayMentId() {
		return payMentId;
	}
	public void setPayMentId(String payMentId) {
		this.payMentId = payMentId;
	}
	public String getPayMent() {
		return payMent;
	}
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
