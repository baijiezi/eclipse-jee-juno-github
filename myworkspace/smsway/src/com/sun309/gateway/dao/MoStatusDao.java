package com.sun309.gateway.dao;

import com.sun309.gateway.dto.mas.MasMo;

public interface MoStatusDao
{
	public void updateOverTimeMoStatus(MasMo masMo, String interfaceName);
	public void delete(int id);
}
