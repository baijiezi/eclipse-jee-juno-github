package com.sun309.gateway.dao;

import java.util.ArrayList;

import com.sun309.gateway.dto.Operator;

public interface OperatorDao
{
	public ArrayList<Operator> findOperatorByCondition(String condition);
	public boolean login(String operName);
}
