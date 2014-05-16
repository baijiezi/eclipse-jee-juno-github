package com.sun309.jdbc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface JdbcDaoService
{
	public String getSelectCondition(String condition);
	public String ObjectToString(Map<String, Object> map, String key);
	public Integer ObjectToInteger(Map<String, Object> map, String key);
	public Boolean ObjectToBoolean(Map<String, Object> map, String key);
	public Long ObjectToLong(Map<String, Object> map, String key);
	public Double ObjectToDouble(Map<String, Object> map, String key);
	public void commit(Connection conn);
	public boolean begin(Connection conn);
	public List<Map<String, Object>> select(String sql, Connection conn);
	public long insert(String sql);
	public int update(String sql);
	public int delete(String sql);
	
	public boolean batchExecute(ArrayList<String> sql);
	public List<Map<String, Object>> select(String sql);
	public Short ObjectToShort(Map<String, Object> map, String string);
	public boolean update(String sql, Connection conn);
	public long insert(String sql, Connection conn);
	public void rollback(Connection conn);
}
