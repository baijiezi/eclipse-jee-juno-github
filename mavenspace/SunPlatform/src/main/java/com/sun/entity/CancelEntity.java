package com.sun.entity;

/**
 * 作用:退费明细实体
 * 作者:caolei
 * 日期:日期:2014-08-11
 * @author Administrator 
 *
 */
public class CancelEntity {
	public String id;	//主键ID
	public String userName;//用户名
	public String payCardNo;//支付卡号
	public String exOrder;//外部订单号
	public String operId;//操作员
	public String states;//状态
	public String cancelDate;//退款时间
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
