package com.sun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.FrontendPayRecordEntity;

public interface FrontendPayRecordMapper {
	List<FrontendPayRecordEntity> getWhereEntity(FrontendPayRecordEntity frontendPayRecordEntity);
	int insert(FrontendPayRecordEntity frontendPayRecordEntity);
	FrontendPayRecordEntity getExamById(FrontendPayRecordEntity frontendPayRecordEntity);
	List<FrontendPayRecordEntity> findPageBreakByCondition(BaseConditionVO vo, RowBounds rb);
	int getCout(FrontendPayRecordEntity frontendPayRecordEntity);
	int updateByPrimaryKeySelective(FrontendPayRecordEntity frontendPayRecordEntity);
}
