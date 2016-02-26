package com.sun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.AutoDetailEntity;
import com.sun.entity.AutoPayEntity;


public interface AutoDetailServiceI {
	List<AutoDetailEntity> getAll();
	List<AutoDetailEntity> findby();
	AutoDetailEntity getAutoByNo(AutoDetailEntity autoDetailEntity);
	int insert(AutoDetailEntity autoDetailEntity);
	AutoDetailEntity getAutoById(AutoDetailEntity autoDetailEntity);
	
	int getCout(AutoDetailEntity autoDetailEntity);
	List<AutoDetailEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
	int updateByPrimaryKeySelective(AutoDetailEntity autoDetailEntity);
}