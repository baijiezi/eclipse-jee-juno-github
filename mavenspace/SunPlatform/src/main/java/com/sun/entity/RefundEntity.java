package com.sun.entity;


/**
 * ����:�˷�ʵ��
 * ����:2014-03-28
 * ����:caolei
 * @author Administrator
 *
 */
public class RefundEntity {
	public int id;	//����ID
	public String userName;	//�û���
	public String payMentId;	//֧����
	public String operId;	//�ն�ID
	public String extOrderSn;	//�ⲿ������
	public String states;		//״̬
	public String cancelDate;		//����ʱ��

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayMentId() {
		return payMentId;
	}
	public void setPayMentId(String payMentId) {
		this.payMentId = payMentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getExtOrderSn() {
		return extOrderSn;
	}
	public void setExtOrderSn(String extOrderSn) {
		this.extOrderSn = extOrderSn;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

}
