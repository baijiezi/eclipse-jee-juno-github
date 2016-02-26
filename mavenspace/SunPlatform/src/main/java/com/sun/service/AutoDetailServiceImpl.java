package com.sun.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.common.BaseConditionVO;
import com.sun.dao.AutoDetailMapper;
import com.sun.entity.AutoDetailEntity;
import com.sun.entity.AutoPayEntity;

@Service("autoDetailService")
public class AutoDetailServiceImpl implements AutoDetailServiceI {
	
	public AutoDetailMapper autoDetailMapper;
	public List<AutoDetailEntity> findPageBreakByCondition(BaseConditionVO vo,
			RowBounds rb) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AutoDetailEntity> findby() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AutoDetailEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCout(AutoDetailEntity AutoDetailEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(AutoDetailEntity AutoDetailEntity) {
		// TODO Auto-generated method stub
		return autoDetailMapper.insert(AutoDetailEntity);
	}

	public AutoDetailMapper getAutoDetailMapper() {
		return autoDetailMapper;
	}
	
	@Autowired
	public void setAutoDetailMapper(AutoDetailMapper autoDetailMapper) {
		this.autoDetailMapper = autoDetailMapper;
	}

	public AutoDetailEntity getAutoById(AutoDetailEntity AutoDetailEntity) {
		// TODO Auto-generated method stub
		return autoDetailMapper.getAutoById(AutoDetailEntity);
	}

	public AutoDetailEntity getAutoByNo(AutoDetailEntity autoDetailEntity) {
		// TODO Auto-generated method stub
		return autoDetailMapper.getAutoByNo(autoDetailEntity);
	}

	public int updateByPrimaryKeySelective(AutoDetailEntity autoDetailEntity) {
		// TODO Auto-generated method stub
		return autoDetailMapper.updateByPrimaryKeySelective(autoDetailEntity);
	}

}
