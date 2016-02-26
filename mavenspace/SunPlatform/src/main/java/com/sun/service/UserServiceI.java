package com.sun.service;

import java.util.List;

import com.sun.entity.User;


public interface UserServiceI {

	public User getUserById(User record);

	List<User> getAll();

	List<User> getAll2();

	List<User> getAll3();

	int insert(User user);
	
	int updateByPrimaryKeySelective(User record);
}
