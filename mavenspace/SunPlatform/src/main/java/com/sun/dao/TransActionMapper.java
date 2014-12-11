package com.sun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.common.Page;
import com.sun.entity.TransActionEntity;

public interface TransActionMapper {
	List<TransActionEntity> getAll();
	List<TransActionEntity> findby();
	List<TransActionEntity> getWhereEntity(TransActionEntity transActionEntity);
	int insert(TransActionEntity transActionEntity);
	TransActionEntity getBookingById(TransActionEntity transActionEntity);
	int getCout(TransActionEntity transActionEntity);
	
	public List<TransActionEntity> getUsers(Page page);
	
	List<TransActionEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
}