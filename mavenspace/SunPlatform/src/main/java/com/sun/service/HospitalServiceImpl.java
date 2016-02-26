package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.HospitalMapper;
import com.sun.entity.HospitalEntity;

@Service("hospitalService")
public class HospitalServiceImpl implements HospitalServiceI {
	
	private HospitalMapper hospitalMapper;
	
	public HospitalMapper getHospitalMapper() {
		return hospitalMapper;
	}
	@Autowired
	public void setHospitalMapper(HospitalMapper hospitalMapper) {
		this.hospitalMapper = hospitalMapper;
	}

	public List<HospitalEntity> getAll() {
		// TODO Auto-generated method stub
		return hospitalMapper.getAll();
	}

}
