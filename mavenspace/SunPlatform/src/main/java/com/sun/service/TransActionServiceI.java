package com.sun.service;

import java.util.List;

import com.sun.common.BaseConditionVO;
import com.sun.common.Page;
import com.sun.entity.TransActionEntity;

public interface TransActionServiceI {
	List<TransActionEntity> getAll();
	List<TransActionEntity> getWhereEntity(TransActionEntity transActionEntity);
	int insert(TransActionEntity transActionEntity);
	TransActionEntity getBookingById(TransActionEntity transActionEntity);
	int getCout(TransActionEntity transActionEntity);
	
	List<TransActionEntity> getUsers(Page page);
	
	List<TransActionEntity> searchUser(BaseConditionVO vo);
}