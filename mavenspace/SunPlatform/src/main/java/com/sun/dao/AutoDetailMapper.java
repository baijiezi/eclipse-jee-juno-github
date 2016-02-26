package com.sun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.AutoDetailEntity;
import com.sun.entity.AutoPayEntity;


public interface AutoDetailMapper {
	List<AutoDetailEntity> getAll();
	List<AutoDetailEntity> findby();
	AutoDetailEntity getAutoByNo(AutoDetailEntity autoDetailEntity);
	int insert(AutoDetailEntity AutoDetailEntity);
	AutoDetailEntity getAutoById(AutoDetailEntity autoDetailEntity);
	
	int getCout(AutoDetailEntity AutoDetailEntity);
	List<AutoDetailEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
	
	int updateByPrimaryKeySelective(AutoDetailEntity autoDetailEntity);
}
