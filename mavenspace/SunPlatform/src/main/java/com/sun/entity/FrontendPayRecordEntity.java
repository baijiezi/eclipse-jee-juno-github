package com.sun.entity;


public class FrontendPayRecordEntity {
	private Integer id;
	private String frontOrderNo;	//去重时间戳
	private String hospitalId;		//医院ID
	private String hospitalName;	//医院名称
	private String userName;		//用户名
	private String patientName;		//患者姓名
	private String medicalCardNo;	//诊疗卡
	private String mobile;			//手机号码
	private String orderNo;			//订单号
	private String realPayFee;		 //支付金额
	private String payCardNo;		//支付卡号
	private String payType;			//支付类型
	private String settlementType;	//结算方式
	private String paymentId;	//支付号
	private String payTranLine;//支付流水
	private String systemPayTranLine;//系统支付流水
	private String optionPayments;//支付渠道
	private String operId;			//终端ID
	private String terminalTranLine;//终端流水
	private String settleId;//结算ID
	private String payTime;//支付时间
	private String states;//状态
	private String remark;//备注
	private String startDate;//开始日期
	private String endDate;//结束日期
	private String businessType;//业务类型
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRealPayFee() {
		return realPayFee;
	}
	public void setRealPayFee(String realPayFee) {
		this.realPayFee = realPayFee;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
	public String getTerminalTranLine() {
		return terminalTranLine;
	}
	public void setTerminalTranLine(String terminalTranLine) {
		this.terminalTranLine = terminalTranLine;
	}
	public String getSettleId() {
		return settleId;
	}
	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
