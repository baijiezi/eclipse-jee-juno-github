package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.PayOrderMapper;
import com.sun.entity.PayOrderEntity;

@Service("payOrderService")
public class PayOrderServiceImpl implements PayOrderServiceI {
	
	private PayOrderMapper payOrderMapper;
	
	public PayOrderMapper getPayOrderMapper() {
		return payOrderMapper;
	}
	@Autowired
	public void setPayOrderMapper(PayOrderMapper payOrderMapper) {
		this.payOrderMapper = payOrderMapper;
	}
	
	public List<PayOrderEntity> getAll() {
		return payOrderMapper.getAll();
	}
	public List<PayOrderEntity> getWhereEntity(PayOrderEntity payOrderEntity) {
		// TODO Auto-generated method stub
		return payOrderMapper.getWhereEntity(payOrderEntity);
	}

}
