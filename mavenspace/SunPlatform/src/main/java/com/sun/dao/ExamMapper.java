package com.sun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.ExamEntity;

public interface ExamMapper {
	List<ExamEntity> getWhereEntity(ExamEntity examEntity);
	int insert(ExamEntity examEntity);
	ExamEntity getExamById(ExamEntity examEntity);
	
	int getCout(ExamEntity examEntity);
	List<ExamEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
}
