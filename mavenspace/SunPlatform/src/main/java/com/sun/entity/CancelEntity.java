package com.sun.entity;

/**
 * ����:�˷���ϸʵ��
 * ����:caolei
 * ����:����:2014-08-11
 * @author Administrator 
 *
 */
public class CancelEntity {
	public String id;	//����ID
	public String userName;//�û���
	public String payCardNo;//֧������
	public String exOrder;//�ⲿ������
	public String operId;//����Ա
	public String states;//״̬
	public String cancelDate;//�˿�ʱ��
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getExOrder() {
		return exOrder;
	}
	public void setExOrder(String exOrder) {
		this.exOrder = exOrder;
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
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	
}
