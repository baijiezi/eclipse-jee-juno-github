package com.sun.service;

import java.util.List;

import com.sun.common.BaseConditionVO;
import com.sun.entity.FrontendPayRecordEntity;
import com.sun.entity.MannerEntity;

public interface MannerServiceI {
	List<MannerEntity> getWhereEntity(MannerEntity mannerEntity);
	int insert(MannerEntity mannerEntity);
	MannerEntity getExamById(MannerEntity mannerEntity);
	
	List<MannerEntity> searchUser(BaseConditionVO vo);
	int getCout(MannerEntity mannerEntity);
}
