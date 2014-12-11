package com.sun.service;

import java.util.List;

import com.sun.entity.RefundEntity;

public interface RefundServiceI {
	public int insert(RefundEntity ce);
	public String refundConn(String json);
	List<RefundEntity> getWhereEntity(RefundEntity refundEntity);
}
