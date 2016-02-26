package com.sun.dao;

import java.util.List;

import com.sun.entity.ConsumEntity;


/**
 * 作用:消费DAO层
 * 日期:2014-03-20
 * 作者:caolei
 * @author Administrator
 *
 */
public interface ConsumeMapper {
	int insert(ConsumEntity ce);
	List<ConsumEntity> getWhereEntity(ConsumEntity consumEntity);
}
