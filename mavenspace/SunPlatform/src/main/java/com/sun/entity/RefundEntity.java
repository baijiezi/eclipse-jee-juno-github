package com.sun.entity;


/**
 * 作用:退费实体
 * 日期:2014-03-28
 * 作者:caolei
 * @author Administrator
 *
 */
public class RefundEntity {
	public int id;	//主键ID
	public String userName;	//用户名
	public String payMentId;	//支付号
	public String operId;	//终端ID
	public String extOrderSn;	//外部订单号
	public String states;		//状态
	public String cancelDate;		//接收时间

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
