package com.sun.entity;

import java.util.Date;

/**
 * ����:����ʵ��
 * ����:2014-03-20
 * ����:caolei
 * @author Administrator
 *
 */
public class ConsumEntity {
	public int id;					//����
	public String userName;			//�û���
	public String optionMents;		//�˻�����
	public String memberCard;		//���ڿ���
	public int fee;					//��ֵ���
	public String operId;			//����Ա
	public String tradeLine;		//�ն���ˮ��
	public String extOrder;			//�ⲿ������
	public String payCardNo;		//֧������
	public String states;			//״̬
	public Date createTime;			//����ʱ�� 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOptionMents() {
		return optionMents;
	}
	public void setOptionMents(String optionMents) {
		this.optionMents = optionMents;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getTradeLine() {
		return tradeLine;
	}
	public void setTradeLine(String tradeLine) {
		this.tradeLine = tradeLine;
	}
	public String getExtOrder() {
		return extOrder;
	}
	public void setExtOrder(String extOrder) {
		this.extOrder = extOrder;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
} 
