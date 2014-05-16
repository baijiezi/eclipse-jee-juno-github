package com.sun309.gateway.dao;

import java.util.ArrayList;

import com.sun309.gateway.dto.mas.MasRpt;

public interface MasRptDao
{
	public boolean deleteRpt(String ids, String interfaceName);
	public ArrayList<MasRpt> findRptByCondition(String condition, String interfaceName);
}
