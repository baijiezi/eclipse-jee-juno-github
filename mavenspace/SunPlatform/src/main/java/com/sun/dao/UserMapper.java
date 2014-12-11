package com.sun.dao;

import java.util.List;

import com.sun.entity.User;


public interface UserMapper {
	int deleteByPrimaryKey(String id);

	int insert(User record);

	int insertSelective(User record);

	User getUserById(User record);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> getAll();

	List<User> getAll2();

	List<User> getAll3();
}