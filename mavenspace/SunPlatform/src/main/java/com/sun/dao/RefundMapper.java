package com.sun.dao;

import java.util.List;

import com.sun.entity.RefundEntity;

/**
 * ����:�˿�DAO
 * ����:2014-03-28
 * @author Administrator
 *
 */
public interface RefundMapper {
	int insert(RefundEntity re);
	List<RefundEntity> getWhereEntity(RefundEntity refundEntity);
}
