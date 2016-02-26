package com.sun.dao;

import java.util.List;

import com.sun.entity.PayOrderEntity;

public interface PayOrderMapper {
	List<PayOrderEntity> getAll();
	List<PayOrderEntity> findby();
	List<PayOrderEntity> getWhereEntity(PayOrderEntity payOrderEntity);
}
