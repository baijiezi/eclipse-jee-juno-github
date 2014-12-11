package com.sun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sun.common.BaseConditionVO;
import com.sun.entity.AutoPayEntity;
import com.sun.entity.TransActionEntity;

public interface AutoPayServiceI {
	List<AutoPayEntity> getAll();
	List<AutoPayEntity> getWhereEntity(AutoPayEntity autoPayEntity);
	int insert(AutoPayEntity autoPayEntity);
	AutoPayEntity getAutoById(AutoPayEntity autoPayEntity);
	
	int getCout(AutoPayEntity transActionEntity);
	List<AutoPayEntity> searchAutoPay(BaseConditionVO vo);
}
