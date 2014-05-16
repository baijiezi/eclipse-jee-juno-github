package com.sun309.gateway.dao;

import com.sun309.gateway.dbpool.ConnectionService;
import com.sun309.gateway.dto.Mt;

public interface MtDao
{
	public Mt findMt(ConnectionService connection);
}
