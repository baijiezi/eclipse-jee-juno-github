package com.sun.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.common.BaseConditionVO;
import com.sun.common.Page;
import com.sun.dao.TransActionMapper;
import com.sun.entity.TransActionEntity;


@Service("transActionService")
public class TransActionServiceImpl implements TransActionServiceI  {
	
	private TransActionMapper transActionMapper;
	public List<TransActionEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TransActionEntity> getWhereEntity(
			TransActionEntity transActionEntity) {

		return transActionMapper.getWhereEntity(transActionEntity);
	}
	public TransActionMapper getTransActionMapper() {
		return transActionMapper;
	}
	@Autowired
	public void setTransActionMapper(TransActionMapper transActionMapper) {
		this.transActionMapper = transActionMapper;
	}

	public int insert(TransActionEntity transActionEntity) {
		// TODO Auto-generated method stub
		return transActionMapper.insert(transActionEntity);
	}

	public TransActionEntity getBookingById(TransActionEntity transActionEntity) {
		// TODO Auto-generated method stub
		return transActionMapper.getBookingById(transActionEntity);
	}

	public int getCout(TransActionEntity transActionEntity) {
		// TODO Auto-generated method stub
		return transActionMapper.getCout(transActionEntity);
	}

	public TransActionEntity getBookingById(Integer currentPage,
			Integer numPerPage, TransActionEntity transActionEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TransActionEntity> getUsers(Page page) {
		// TODO Auto-generated method stub
		return transActionMapper.getUsers(page);
	}

	public List<TransActionEntity> searchUser(BaseConditionVO vo) {
		List<TransActionEntity> bos = new ArrayList<TransActionEntity>();
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		List<TransActionEntity> pos = transActionMapper.findPageBreakByCondition(vo, rb);
		return pos;
	}

}
