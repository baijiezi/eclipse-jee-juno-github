package com.sun309.gateway.dao;

import com.sun309.gateway.dto.mas.MasMt;

public interface MasMtDao
{
	public MasMt insert(String mobile , String content, long smId, long srcId, String interfaceName, Integer needCallBack); 
}
