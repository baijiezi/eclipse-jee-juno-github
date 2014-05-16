package com.sun309.gateway.impl;

import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.MessagesIdDao;
import com.sun309.gateway.dbpool.ConnectionService;
import com.sun309.gateway.dto.MessagesId;
import com.sun309.gateway.jdbc.Dao;

public class MessagesIdDaoImpl extends Dao implements MessagesIdDao
{
	public int findMessageId(ConnectionService connection)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT message_id FROM messages_id FOR UPDATE");
		List<Map<String, Object>> list = this.select(sql.toString(), connection.getConn());
		if(list == null || list.size() <= 0) 
		{
			sql = new StringBuffer();
			sql.append("INSERT INTO messages_id(message_id) VALUES (2) ");
			this.insert(sql.toString(), connection.getConn());
			return 1;
		}
		MessagesId messagesId = new MessagesId(); 
		for (Map<String, Object> map : list)
		{
			messagesId.setMessageId(Integer.parseInt(map.get("message_id").toString()));
		}
		list = null;
		sql = new StringBuffer();
		sql.append("UPDATE messages_id SET message_id=message_id+1");
		this.update(sql.toString(), connection.getConn());
		return messagesId.getMessageId().intValue();
	}
}
