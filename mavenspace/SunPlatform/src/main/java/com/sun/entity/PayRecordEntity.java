package com.sun.entity;


/**
 * ����:��ֵ��¼
 * ����:caolei
 * ����:����:2014-03-11
 * @author Administrator
 *
 */
public class PayRecordEntity {
	public int id;//����ID
	public String userName;//�û���
	public String payMent;//�ʻ�����
	public String memberCard;//���ڿ���
	public String payCardNo;//֧������
	public int fee;//��ֵ���
	public String extOrder;//�ⲿ������
	public String operId;//����Ա
	public String traderId;//�����̻�ID
	public String createTime;//����ʱ��
	public int states;//״̬
	
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
	public String getPayMent() {
		return payMent;
	}
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getExtOrder() {
		return extOrder;
	}
	public void setExtOrder(String extOrder) {
		this.extOrder = extOrder;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
}
