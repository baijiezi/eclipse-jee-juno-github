package com.sun.dao;

import java.util.List;

import com.sun.entity.DrugsEntity;



/**
 * ����:2014-10-22
 * ����:ҩƷ��ѯDAO
 * ����:caolei
 * @author Administrator
 *
 */
public interface DrugsMapper {
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
	//��������
	int batchInsertB2B(List<DrugsEntity> ls);
}
