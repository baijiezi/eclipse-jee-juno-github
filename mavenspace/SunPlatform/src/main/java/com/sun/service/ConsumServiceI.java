package com.sun.service;

import java.util.List;

import com.sun.entity.ConsumEntity;

public interface ConsumServiceI {
	int insert(ConsumEntity ce);
	String Communication(String Json);
	List<ConsumEntity> getWhereEntity(ConsumEntity consumEntity);
}