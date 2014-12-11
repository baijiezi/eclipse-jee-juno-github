package com.sun.dao;

import java.util.List;

import com.sun.entity.PayRecordEntity;

public interface PayRecordMapper {
	List<PayRecordEntity> getAll();
	List<PayRecordEntity> findby();
	List<PayRecordEntity> getWhereEntity(PayRecordEntity payRecordEntity);
}
