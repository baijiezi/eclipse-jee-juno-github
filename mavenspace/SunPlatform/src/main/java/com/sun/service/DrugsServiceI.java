package com.sun.service;

import java.util.List;

import com.sun.entity.DrugsEntity;

public interface DrugsServiceI {
	List<DrugsEntity> getAll();
	List<DrugsEntity> findby();
	List<DrugsEntity> getWhereEntity(DrugsEntity drugsEntity);
	List<DrugsEntity> getCgEntity(DrugsEntity drugsEntity);
	List<DrugsEntity> getHyEntity(DrugsEntity drugsEntity);
	List<DrugsEntity> getFsEntity(DrugsEntity drugsEntity);
	List<DrugsEntity> getCsEntity(DrugsEntity drugsEntity);
	List<DrugsEntity> getNameEntity(DrugsEntity drugsEntity);
	int insert(DrugsEntity autoPayEntity);
	DrugsEntity getAutoById(DrugsEntity autoPayEntity);
	
	//批量插入
	int batchInsertB2B(List<DrugsEntity> ls);
}
