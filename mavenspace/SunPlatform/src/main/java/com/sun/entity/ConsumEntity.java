package com.sun.entity;

import java.util.Date;

/**
 * 作用:消费实体
 * 日期:2014-03-20
 * 作者:caolei
 * @author Administrator
 *
 */
public class ConsumEntity {
	public int id;					//主键
	public String userName;			//用户名
	public String optionMents;		//账户类型
	public String memberCard;		//康众卡号
	public int fee;					//充值金额
	public String operId;			//操作员
	public String tradeLine;		//终端流水号
	public String extOrder;			//外部订单号
	public String payCardNo;		//支付卡号
	public String states;			//状态
	public Date createTime;			//创建时间 
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
