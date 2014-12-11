package com.sun.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.common.BaseConditionVO;
import com.sun.dao.FrontendPayRecordMapper;
import com.sun.entity.ExamEntity;
import com.sun.entity.FrontendPayRecordEntity;

@Service("frontendPayRecordService")
public class FrontendPayRecordServiceImpl implements FrontendPayRecordServiceI {
	public FrontendPayRecordMapper frontendPayRecordMapper;
	
	public FrontendPayRecordEntity getExamById(
			FrontendPayRecordEntity frontendPayRecordEntity) {
		// TODO Auto-generated method stub
		return frontendPayRecordMapper.getExamById(frontendPayRecordEntity);
	}

	public List<FrontendPayRecordEntity> getWhereEntity(
			FrontendPayRecordEntity frontendPayRecordEntity) {
		// TODO Auto-generated method stub
		return frontendPayRecordMapper.getWhereEntity(frontendPayRecordEntity);
	}

	public int insert(FrontendPayRecordEntity frontendPayRecordEntity) {
		// TODO Auto-generated method stub
		return frontendPayRecordMapper.insert(frontendPayRecordEntity);
	}

	public FrontendPayRecordMapper getFrontendPayRecordMapper() {
		return frontendPayRecordMapper;
	}
	
	@Autowired
	public void setFrontendPayRecordMapper(
			FrontendPayRecordMapper frontendPayRecordMapper) {
		this.frontendPayRecordMapper = frontendPayRecordMapper;
	}

	public int getCout(FrontendPayRecordEntity transActionEntity) {
		// TODO Auto-generated method stub
		return frontendPayRecordMapper.getCout(transActionEntity);
	}

	public List<FrontendPayRecordEntity> searchUser(BaseConditionVO vo) {
		List<FrontendPayRecordEntity> bos = new ArrayList<FrontendPayRecordEntity>();
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		List<FrontendPayRecordEntity> pos = frontendPayRecordMapper.findPageBreakByCondition(vo, rb);
		return pos;
	}

}
