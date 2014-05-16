package com.sun309.gateway.dao;

import java.util.ArrayList;

import com.sun309.gateway.dto.BlackList;

public interface BlackListDao
{
	public ArrayList<BlackList> findBlackListByCondition(String condition);
	public boolean checkMobileIsBlackList(String mobile);
}
