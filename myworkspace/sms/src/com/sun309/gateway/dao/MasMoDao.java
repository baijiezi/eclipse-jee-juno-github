package com.sun309.gateway.dao;

import java.util.ArrayList;

import com.sun309.gateway.dto.mas.MasMo;

public interface MasMoDao
{
	public ArrayList<MasMo> findMoByCondition(String condition, String interfaceName);
	public boolean delete(String smId, String interfaceName);
	public boolean deleteUnicom(String AUTOSN, String interfaceName);
}
