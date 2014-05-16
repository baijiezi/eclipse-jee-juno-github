package com.sun309.gateway.dao;

import com.sun309.gateway.dbpool.ConnectionService;

public interface MessagesIdDao 
{
	public int findMessageId(ConnectionService connection);
}
