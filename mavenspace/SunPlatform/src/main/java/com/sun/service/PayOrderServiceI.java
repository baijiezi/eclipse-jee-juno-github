package com.sun.service;

import java.util.List;

import com.sun.entity.PayOrderEntity;

public interface PayOrderServiceI {
	List<PayOrderEntity> getAll();
	List<PayOrderEntity> getWhereEntity(PayOrderEntity payOrderEntity);
}
