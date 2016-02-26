package com.sun.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.common.BaseConditionVO;
import com.sun.dao.MannerMapper;
import com.sun.entity.MannerEntity;
import com.sun.entity.TransActionEntity;
@Service("mannerService")
public class MannerServiceImpl implements MannerServiceI {
	
	private MannerMapper mannerMapper;
	public MannerEntity getExamById(MannerEntity examEntity) {
		return mannerMapper.getExamById(examEntity);
	}

	public List<MannerEntity> getWhereEntity(MannerEntity examEntity) {
		return mannerMapper.getWhereEntity(examEntity);
	}

	public int insert(MannerEntity examEntity) {
		return mannerMapper.insert(examEntity);
	}

	public MannerMapper getMannerMapper() {
		return mannerMapper;
	}
	@Autowired
	public void setMannerMapper(MannerMapper mannerMapper) {
		this.mannerMapper = mannerMapper;
	}

	
	public int getCout(MannerEntity mannerEntity) {
		return mannerMapper.getCout(mannerEntity);
	}

	public List<MannerEntity> searchUser(BaseConditionVO vo) {
		List<MannerEntity> bos = new ArrayList<MannerEntity>();
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		List<MannerEntity> pos = mannerMapper.findPageBreakByCondition(vo, rb);
		return pos;
	}

}
