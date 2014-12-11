package com.sun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.AutoPayEntity;


public interface AutoPayMapper {
	List<AutoPayEntity> getAll();
	List<AutoPayEntity> findby();
	List<AutoPayEntity> getWhereEntity(AutoPayEntity autoPayEntity);
	int insert(AutoPayEntity autoPayEntity);
	AutoPayEntity getAutoById(AutoPayEntity autoPayEntity);
	
	int getCout(AutoPayEntity transActionEntity);
	List<AutoPayEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
}
