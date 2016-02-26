package com.sun.entity;


/**
 * 作用:订单明细实体
 * 作者:caolei
 * 日期:2014-03-11
 * @author Administrator
 *
 */
public class TransActionEntity {
	public int id;										//数据库id
	public String hospitalId;							//医院ID
	public String hospitalName;							//医院名称
	public String userName;								//用户名
	public String scheduleId;							//排班ID
	public String idCard;								//身份证
	public String birthday;								//患者生日
	public String medicalCardNo;							//诊疗卡
	public String mobile;								//手机号
	public String patientName;							//患者姓名
	public String patientSex;							//患者性别
	public String address;								//患者地址
	public String deptName;								//科室名称
	public String doctorName;							//医生名称
	public String orderTime;							//下单时间
	public String visitTime;							//就诊时间
	public String serialNo;								//号球
	public String fee;									//挂号费
	public String payCardNo;							//支付卡号
	public String settlementType;						//结算方式
	public String paymentId;							//支付号
	public String optionPayments;						//帐户类型
	public String orderNo;								//订单号
	public String operId;								//操作员
	public String states;								//状态
	public String remark;								//备注
	public String isToday;								//是否为当天预约
	public String jzFee;								//就诊费
	public String startDate;								//开始日期
	public String endDate;								//结束日期
	public String jzstarTime;							//就诊开始日期
	public String jzendTime;							//就诊结束日期

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
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
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
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	public String getIsToday() {
		return isToday;
	}
	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}
	public String getJzFee() {
		return jzFee;
	}
	public void setJzFee(String jzFee) {
		this.jzFee = jzFee;
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
}
