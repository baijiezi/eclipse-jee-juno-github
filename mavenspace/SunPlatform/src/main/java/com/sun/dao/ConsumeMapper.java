package com.sun.dao;

import java.util.List;

import com.sun.entity.ConsumEntity;


/**
 * ����:����DAO��
 * ����:2014-03-20
 * ����:caolei
 * @author Administrator
 *
 */
public interface ConsumeMapper {
	int insert(ConsumEntity ce);
	List<ConsumEntity> getWhereEntity(ConsumEntity consumEntity);
}
