package com.sun.common;

import java.util.Date;

public class BaseConditionVO {
	public final static int PAGE_SHOW_COUNT = 50;
	private int pageNum = 1;
	private int pageSize = 0;
	private int totalCount = 0;
	private String orderField;
	private String orderDirection;
	private String keywords;
	private String status;
	private String type;
	private String startDate;
	private String endDate;
	
	private String medicalCardNo;//诊疗卡号
	private String patientName;//患者姓名
	private String hospitalName;//医院名称
	private String payCardNo;//支付卡号
	private String mobile;//手机号码
	private String orderNo;//订单号
	private String userName;
	public String jzstarTime;							//就诊开始日期
	public String jzendTime;							//就诊结束日期
	public String operId;
	public String states;
	
	private Date starDate;

	
	public String getType() {
		return "".equals(type) ? null : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return "".equals(status)? null : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize > 0 ? pageSize : PAGE_SHOW_COUNT;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderDirection() {
		return "desc".equals(orderDirection) ? "desc" : "asc";
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getKeywords() {
		return "".equals(keywords)? null : keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public int getStartIndex() {
		int pageNum = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
		return pageNum * this.getPageSize();
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
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
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJzstarTime() {
		return jzstarTime;
	}
	public void setJzstarTime(String jzstarTime) {
		this.jzstarTime = jzstarTime;
	}
	public String getJzendTime() {
		return jzendTime;
	}
	public void setJzendTime(String jzendTime) {
		this.jzendTime = jzendTime;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
}
