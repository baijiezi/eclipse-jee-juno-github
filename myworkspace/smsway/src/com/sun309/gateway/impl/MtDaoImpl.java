package com.sun309.gateway.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.MtDao;
import com.sun309.gateway.dbpool.ConnectionService;
import com.sun309.gateway.dto.Mt;
import com.sun309.gateway.jdbc.Dao;

public class MtDaoImpl extends Dao implements MtDao
{
	public Mt findMt(ConnectionService connection)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id, SM_ID, SRC_ID FROM mt FOR UPDATE");
		List<Map<String, Object>> list = this.select(sql.toString(), connection.getConn());
		if(list == null || list.size() <= 0)
		{
			sql = new StringBuffer();
			sql.append("INSERT INTO mt (SM_ID, SRC_ID) VALUES('2', '2')");
			this.insert(sql.toString(), connection.getConn());
			Mt mt = new Mt();
			mt.setSmId(1l);
			mt.setSrcId(1l);
			return mt;
		}
		ArrayList<Mt> _list = new ArrayList<Mt>();
		for (Map<String, Object> map : list)
		{
			Mt mt = new Mt();
			mt.setId(Long.parseLong(map.get("id").toString()));
			mt.setSmId(Long.parseLong(map.get("SM_ID").toString()));
			mt.setSrcId(Long.parseLong(map.get("SRC_ID").toString()));
			_list.add(mt);
		}
		list = null;
		if (_list != null && _list.size() > 0)
		{
			sql = new StringBuffer();
			sql.append("UPDATE mt SET SM_ID = SM_ID + 1, SRC_ID = SRC_ID + 1  LIMIT 1");
			this.update(sql.toString(), connection.getConn());
			return (Mt) _list.get(0);
		}
		else
		{
			return null;
		}
	}
}