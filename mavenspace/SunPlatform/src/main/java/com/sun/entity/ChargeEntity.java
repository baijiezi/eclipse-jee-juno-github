package com.sun.entity;

import java.util.Date;

/**
 * ����:�ʻ���ֵʵ��
 * ����:2014-03-28
 * ����:caolei
 * @author Administrator
 *
 */
public class ChargeEntity {
	public int id;	//����id
	public String userId;	//�û�ID
	public String extOrder;	//�ⲿ������
	public String optionPayMents;	//ָ�������ʻ���������
	public String payCardNo;	//֧�����п���
	public String password;		//����
	public String kzCardNo;		//��ֵ���ڿ���
	public int fee;				//��ֵ���
	public String terminalId;	//�ն�ID
	public Date createTime;		//����ʱ��
	public Date translTime;		//����ʱ��
	public Date getTranslTime() {
		return translTime;
	}
	public void setTranslTime(Date translTime) {
		this.translTime = translTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExtOrder() {
		return extOrder;
	}
	public void setExtOrder(String extOrder) {
		this.extOrder = extOrder;
	}
	public String getOptionPayMents() {
		return optionPayMents;
	}
	public void setOptionPayMents(String optionPayMents) {
		this.optionPayMents = optionPayMents;
	}
	public String getPayCardNo() {
		return payCardNo;
	}
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKzCardNo() {
		return kzCardNo;
	}
	public void setKzCardNo(String kzCardNo) {
		this.kzCardNo = kzCardNo;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}