package com.sun.entity;

import java.util.Date;

/**
 * 作用:帐户充值实体
 * 日期:2014-03-28
 * 作者:caolei
 * @author Administrator
 *
 */
public class ChargeEntity {
	public int id;	//主键id
	public String userId;	//用户ID
	public String extOrder;	//外部订单号
	public String optionPayMents;	//指定消费帐户类型与金额
	public String payCardNo;	//支付银行卡号
	public String password;		//密码
	public String kzCardNo;		//充值康众卡号
	public int fee;				//充值金额
	public String terminalId;	//终端ID
	public Date createTime;		//创建时间
	public Date translTime;		//处理时间
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