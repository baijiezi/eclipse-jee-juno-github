package com.sun.entity;

/**
 * ����:����������ϸ
 * ����:caolei
 * ����:����:2014-03-11
 * @author Administrator 
 *
 */
public class AutoPayEntity {
	public int id;
	public String hospitalName;	//ҽԺ����
	public String medicalCardNo;//���ƿ�
	public String idCard;		//���֤
	public String orderNo;		//������
	public String payMoney;		//֧�����
	public String payCardNo;	//֧������
	public String payType;		//֧������
	public String payTradeLine;	//֧����ˮ
	public String payDate;		//֧��ʱ��
	public String mobile;		//�ֻ�����
	public String userName;		//�û�����
	public String startDate;		//��ʼ����
	public String endDate;		//��������
	private String payTranLine;	//֧����ˮ
	private String systemPayTranLine;//ϵͳ�ο���
	public String frontOrderNo;
	public String remark;//��ע
	public String states;//����״̬
	public String operId;		//����Ա

	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getMedicalCardNo() {
		return medicalCardNo;
	}
	public void setMedicalCardNo(String medicalCardNo) {
		this.medicalCardNo = medicalCardNo;
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
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
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
	public String getPayTradeLine() {
		return payTradeLine;
	}
	public void setPayTradeLine(String payTradeLine) {
		this.payTradeLine = payTradeLine;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFrontOrderNo() {
		return frontOrderNo;
	}
	public void setFrontOrderNo(String frontOrderNo) {
		this.frontOrderNo = frontOrderNo;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
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

}
