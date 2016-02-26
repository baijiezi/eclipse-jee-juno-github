package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.PayRecordMapper;
import com.sun.entity.PayRecordEntity;

@Service("payRecordService")
public class PayRecordServiceImpl implements PayRecordServiceI {
	
	private PayRecordMapper payRecordMapper;
	public PayRecordMapper getPayRecordMapper() {
		return payRecordMapper;
	}
	@Autowired
	public void setPayRecordMapper(PayRecordMapper payRecordMapper) {
		this.payRecordMapper = payRecordMapper;
	}
	public List<PayRecordEntity> getAll() {
		return payRecordMapper.getAll();
	}
	public List<PayRecordEntity> getWhereEntity(PayRecordEntity payRecordEntity) {
		return payRecordMapper.getWhereEntity(payRecordEntity);
	}

}
