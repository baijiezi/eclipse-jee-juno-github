package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.dao.DrugsMapper;
import com.sun.entity.DrugsEntity;

@Service("drugsService")
public class DrugsServiceImpl implements DrugsServiceI {
	
	private DrugsMapper drugsMapper;
	
	public DrugsMapper getDrugsMapper() {
		return drugsMapper;
	}
	@Autowired
	public void setDrugsMapper(DrugsMapper drugsMapper) {
		this.drugsMapper = drugsMapper;
	}

	public List<DrugsEntity> findby() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DrugsEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public DrugsEntity getAutoById(DrugsEntity autoPayEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DrugsEntity> getWhereEntity(DrugsEntity drugsEntity) {
		// TODO Auto-generated method stub
		return drugsMapper.getWhereEntity(drugsEntity);
	}

	public int insert(DrugsEntity autoPayEntity) {
		return drugsMapper.insert(autoPayEntity);
	}
	public List<DrugsEntity> getCgEntity(DrugsEntity drugsEntity) {
		// TODO Auto-generated method stub
		return drugsMapper.getCgEntity(drugsEntity);
	}
	public List<DrugsEntity> getCsEntity(DrugsEntity drugsEntity) {
		// TODO Auto-generated method stub
		return drugsMapper.getCsEntity(drugsEntity);
	}
	public List<DrugsEntity> getFsEntity(DrugsEntity drugsEntity) {
		// TODO Auto-generated method stub
		return drugsMapper.getFsEntity(drugsEntity);
	}
	public List<DrugsEntity> getHyEntity(DrugsEntity drugsEntity) {
		// TODO Auto-generated method stub
		return drugsMapper.getHyEntity(drugsEntity);
	}
	public List<DrugsEntity> getNameEntity(DrugsEntity drugsEntity) {
		String str=drugsEntity.getName().trim();
		boolean isWord=str.matches("[a-zA-Z]+");//¥¶¿Ì ‰»Î◊÷∑˚¥Æ
		if(isWord){
			drugsEntity.setCode(drugsEntity.getName().trim());
		}else{
			drugsEntity.setMedicineName(str);
		}
		return drugsMapper.getNameEntity(drugsEntity);
	}
	
	@Transactional 
	public int batchInsertB2B(List<DrugsEntity> ls) {
		return drugsMapper.batchInsertB2B(ls);
		
		
	}
}
