package com.sun309.gateway.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.OperatorDao;
import com.sun309.gateway.dto.Operator;
import com.sun309.gateway.jdbc.Dao;

public class OperatorDaoImpl extends Dao implements OperatorDao
{

	public ArrayList<Operator> findOperatorByCondition(String condition)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT oper_id, oper_name, add_time, app_desc, access FROM operator");
		sql.append(this.getSelectCondition(condition));
		List<Map<String, Object>> _list = this.select(sql.toString());
		
		ArrayList<Operator> list = new ArrayList<Operator>();
		for(Map<String, Object> map : _list)
		{
			Operator o = new Operator();
			o.setAccess(this.ObjectToString(map, "access"));
			o.setAddTime(this.ObjectToLong(map, "add_time"));
			o.setAppDesc(this.ObjectToString(map, "app_desc"));
			o.setOperId(this.ObjectToInteger(map, "oper_id"));
			o.setOperName(this.ObjectToString(map, "oper_name"));
			list.add(o);
		}
		return list;
	}
	
	public boolean login(String operName)
	{
		ArrayList<Operator> list = this.findOperatorByCondition(new StringBuffer("oper_name='").append(operName).append("'").toString());
		if(list != null && list.size() > 0)
		{
			return true;
		}
		return false;
	}

}
