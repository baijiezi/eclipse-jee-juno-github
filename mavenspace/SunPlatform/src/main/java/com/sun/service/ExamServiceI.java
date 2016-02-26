package com.sun.service;

import java.util.List;

import com.sun.common.BaseConditionVO;
import com.sun.entity.ExamEntity;
import com.sun.entity.TransActionEntity;

public interface ExamServiceI {
	List<ExamEntity> getWhereEntity(ExamEntity examEntity);
	int insert(ExamEntity examEntity);
	ExamEntity getExamById(ExamEntity examEntity);
	
	int getCout(ExamEntity examEntity);
	List<ExamEntity> searchUser(BaseConditionVO vo);
}
