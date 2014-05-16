package com.sun309.gateway.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.BlackListDao;
import com.sun309.gateway.dto.BlackList;
import com.sun309.gateway.jdbc.Dao;

public class BlackListDaoImpl extends Dao implements BlackListDao
{

	public boolean checkMobileIsBlackList(String mobile)
	{
		ArrayList<BlackList> list = this.findBlackListByCondition(new StringBuffer("mobile='").append(mobile).append("'").toString());
		if(list!=null && list.size() > 0) return true;
		return false;
	}

	public ArrayList<BlackList> findBlackListByCondition(String condition)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT mobile FROM black_list");
		sql.append(this.getSelectCondition(condition));
		List<Map<String, Object>> list = this.select(sql.toString());
		ArrayList<BlackList> _list = new ArrayList<BlackList>();
		for(Map<String, Object> map : list)
		{
			BlackList b = new BlackList();
			b.setMobile(this.ObjectToString(map, "mobile")); 
			_list.add(b);
		}
		return _list;
	}

}
