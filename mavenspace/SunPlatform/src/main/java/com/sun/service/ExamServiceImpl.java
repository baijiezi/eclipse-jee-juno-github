package com.sun.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.common.BaseConditionVO;
import com.sun.dao.ExamMapper;
import com.sun.entity.ExamEntity;
import com.sun.entity.TransActionEntity;

@Service("examService")
public class ExamServiceImpl implements ExamServiceI{
	private ExamMapper examMapper;
	public ExamMapper getExamMapper() {
		return examMapper;
	}
	
	@Autowired
	public void setExamMapper(ExamMapper examMapper) {
		this.examMapper = examMapper;
	}

	public List<ExamEntity> getWhereEntity(ExamEntity examEntity) {
		// TODO Auto-generated method stub
		return examMapper.getWhereEntity(examEntity);
	}

	public int insert(ExamEntity examEntity) {
		return examMapper.insert(examEntity);
	}

	public ExamEntity getExamById(ExamEntity examEntity) {
		// TODO Auto-generated method stub
		return examMapper.getExamById(examEntity);
	}

	
	
	public int getCout(ExamEntity examEntity) {
		// TODO Auto-generated method stub
		return examMapper.getCout(examEntity);
	}

	public List<ExamEntity> searchUser(BaseConditionVO vo) {
		List<ExamEntity> bos = new ArrayList<ExamEntity>();
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		List<ExamEntity> pos = examMapper.findPageBreakByCondition(vo, rb);
		return pos;
	}
}
