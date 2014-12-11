package com.sun.service;

import java.util.List;

import com.sun.entity.PayRecordEntity;

public interface PayRecordServiceI {
	List<PayRecordEntity> getAll();
	List<PayRecordEntity> getWhereEntity(PayRecordEntity payRecordEntity);
}
