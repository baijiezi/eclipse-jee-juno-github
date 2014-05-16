package com.sun309.gateway.dao;

import java.util.List;
import java.util.Map;

import com.sun309.gateway.dto.SpMessageMapping;

public interface SpMessageMappingDao
{
	public boolean insert(SpMessageMapping mapping);
	public List<Map<String,Object>> getTop10List(String access);
}
