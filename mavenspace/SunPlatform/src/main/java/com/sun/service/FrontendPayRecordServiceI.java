package com.sun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.FrontendPayRecordEntity;

public interface FrontendPayRecordServiceI {
	List<FrontendPayRecordEntity> getWhereEntity(FrontendPayRecordEntity frontendPayRecordEntity);
	int insert(FrontendPayRecordEntity frontendPayRecordEntity);
	FrontendPayRecordEntity getExamById(FrontendPayRecordEntity frontendPayRecordEntity);
	
	List<FrontendPayRecordEntity> searchUser(BaseConditionVO vo);
	int getCout(FrontendPayRecordEntity transActionEntity);
}
