package com.sun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.FrontendPayRecordEntity;
import com.sun.entity.MannerEntity;


public interface MannerMapper {
	List<MannerEntity> getWhereEntity(MannerEntity examEntity);
	int insert(MannerEntity examEntity);
	MannerEntity getExamById(MannerEntity examEntity);
	
	List<MannerEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
	int getCout(MannerEntity mannerEntity);
}
