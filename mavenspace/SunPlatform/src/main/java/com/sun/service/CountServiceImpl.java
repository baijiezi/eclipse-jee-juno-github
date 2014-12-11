package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.CountMapper;
import com.sun.entity.CountEntity;

@Service("countService")
public class CountServiceImpl implements CountServiceI {
	
	private CountMapper countMapper;

	public CountMapper getCountMapper() {
		return countMapper;
	}
	@Autowired
	public void setCountMapper(CountMapper countMapper) {
		this.countMapper = countMapper;
	}

	public List<CountEntity> getBooking(CountEntity ce) {
		// TODO Auto-generated method stub
		return countMapper.getBooking(ce);
	}
	public List<CountEntity> getAutoPay(CountEntity ce) {
		// TODO Auto-generated method stub
		return countMapper.getAutoPay(ce);
	}
	public List<CountEntity> getBookingTerminal(CountEntity ce) {
		// TODO Auto-generated method stub
		return countMapper.getBookingTerminal(ce);
	}
	public List<CountEntity> getAutoPayTerminal(CountEntity ce) {
		// TODO Auto-generated method stub
		return countMapper.getAutoPayTerminal(ce);
	}
}
