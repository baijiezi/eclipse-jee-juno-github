package com.sun.dao;

import java.util.List;

import com.sun.entity.RefundEntity;

/**
 * 作用:退款DAO
 * 日期:2014-03-28
 * @author Administrator
 *
 */
public interface RefundMapper {
	int insert(RefundEntity re);
	List<RefundEntity> getWhereEntity(RefundEntity refundEntity);
}
