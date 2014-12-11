package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.UserMapper;
import com.sun.entity.User;



@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	//@Override
	public List<User> getAll() {
		return userMapper.getAll();
	}

	//@Override
	public List<User> getAll2() {
		return userMapper.getAll2();
	}

	//@Override
	public List<User> getAll3() {
		return userMapper.getAll3();
	}

	public int insert(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	public User getUserById(User record) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(record);
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

}
