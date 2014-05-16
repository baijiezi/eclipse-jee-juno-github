package com.sun309.gateway.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.sun309.gateway.masdbpool.ConnectionService;


public class MasDao
{
	private Logger log = Logger.getLogger(MasDao.class);

	/**
	 * 构造where条件
	 * 
	 * @param condition
	 * @return
	 */
	public String getSelectCondition(String condition)
	{
		StringBuffer con = new StringBuffer();
		if (!StringUtils.isEmpty(condition.trim()))
		{
			con.append(" WHERE ");
			con.append(condition);
		}
		return con.toString();
	}
	
	/**
	 * 获取MAP的键
	 * @param map
	 * @param key
	 * @return
	 */
	public String ObjectToString(Map<String, Object> map, String key)
	{
		try{return map.get(key)!=null?map.get(key).toString():null;}catch(Exception e) { return null; }
	}
	
	public Integer ObjectToInteger(Map<String, Object> map, String key)
	{
		try{return Integer.parseInt(map.get(key).toString());}catch(Exception e) { return null; }
	}
	
	public Boolean ObjectToBoolean(Map<String, Object> map, String key)
	{
		try{return Boolean.parseBoolean(map.get(key).toString());}catch(Exception e) { return null; }
	}
	
	public Long ObjectToLong(Map<String, Object> map, String key)
	{
		try{return Long.parseLong(map.get(key).toString());}catch(Exception e) { return null; }
	}
	
	public Double ObjectToDouble(Map<String, Object> map, String key)
	{
		try{return Double.parseDouble(map.get(key).toString());}catch(Exception e) { return null; }
	}
	
	public Short ObjectToShort(Map<String, Object> map, String key)
	{
		try{return Short.parseShort(map.get(key).toString());}catch(Exception e) { return null; }
	}
	
	/**
	 * 开始事务
	 * @param conn
	 * @return
	 */
	public boolean begin(Connection conn)
	{
		try
		{
			if (conn != null)
			{
				conn.setAutoCommit(false);
				//log.debug("成功开启事务BEGIN!");
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	/**
	 * 提交事务
	 * 
	 * @param conn
	 * @return
	 */
	public void commit(Connection conn)
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.commit();
				//log.debug("成功提交事务COMMIT!");
			}
		}
		catch (SQLException e)
		{
			rollback(conn);
		}
	}
	
	/**
	 * 回滚
	 * 
	 * @param conn
	 */
	public void rollback(Connection conn)
	{
		try
		{
			if (conn != null && !conn.isClosed())
				conn.rollback();
			//log.debug("成功回滚事务ROLLBACK!");
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}
	/**
	 * 在外部开启连接，把连接传入，查询，自身不做事务
	 * 
	 * @param sql
	 * @param conn
	 * 
	 * @return
	 */
	public List<Map<String, Object>> select(String sql, Connection conn)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try
		{
			stmt = conn.prepareStatement(sql);
			//log.debug("Executing Select sql : " + sql);
			rs = stmt.executeQuery();

			// 封装resultSet数据 ,利用ResultSetMetaData对象可获得表的结构
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next())
			{
				// 循环表
				// 每行记录放到一个map里！
				Map<String, Object> map = new HashMap<String, Object>();// 每行记录放到一个
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
					map.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
				}
				list.add(map);
				map=null;
				rsmd=null;
			}
		}
		catch (Exception e)
		{
			rollback(conn);
			log.debug("select exception " + e);
			return null;
		}
		finally
		{
			try
			{
				stmt.close();
				rs.close();
			}
			catch(Exception ex)
			{
				stmt = null;
				rs = null;
			}
		}
		return list;
	}
	/**
	 * 在外部开启连接，把连接传入，插入，自身不做事务
	 * 
	 * @param sql
	 * @param conn
	 * 
	 * @return
	 */
	public long insert(String sql, Connection conn)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long _key = 0;
		try
		{
			stmt = conn.prepareStatement(sql);
			//log.debug("Executing sql : " + sql);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			while (rs.next())
			{
				_key = rs.getLong(1);
			}
			if(_key == 0) _key = 1;
		}
		catch (Exception e)
		{
			rollback(conn);
			log.debug(e);
		}
		finally
		{
			try
			{
				stmt.close();
				rs.close();
			}
			catch(Exception ex)
			{
				stmt = null;
				rs = null;
			}
		}
		return (int) _key;
	}

	/**
	 * 在外部开启连接，把连接传入，更新，自身不做事务
	 * 
	 * @param sql
	 * @return
	 */
	public boolean update(String sql, Connection conn)
	{
		PreparedStatement stmt = null;
		boolean result = false;
		try
		{
			stmt = conn.prepareStatement(sql);
			//log.debug("Executing sql : " + sql);
			stmt.executeUpdate();
			result = true;		
		}
		catch (Exception e)
		{
			result = false;
			rollback(conn);
			log.debug(e);
		}
		finally
		{
			try
			{
				stmt.close();
			}
			catch(Exception ex)
			{
				stmt = null;
			}
		}
		return result;
	}

	/**
	 * 在外部开启连接，把连接传入，删除，自身不做事务 
	 * 
	 * @param sql
	 * @return
	 */
	public boolean delete(String sql, Connection conn)
	{
		return update(sql, conn);
	}
	
	
	/**
	 * 执行添加sql(自身完成事务)
	 * 
	 * @param sql
	 * @return
	 */
	public long insert(String sql)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long _key = 0;
		ConnectionService service = new ConnectionService();
		try
		{
			service.createConnection();
			conn = service.getConn();
			stmt = conn.prepareStatement(sql);
			//log.debug("Executing sql : " + sql);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			while (rs.next())
			{
				_key = rs.getLong(1);
			}
			// 提交事务
			commit(conn);
			if(_key == 0) _key = 1;
		}
		catch (Exception e)
		{
			rollback(conn);
			log.debug(e);
		}
		finally
		{
			try
			{
				service.close();
				stmt.close();
				rs.close();
			}
			catch(Exception ex)
			{
				stmt = null;
				rs = null;
			}
		}
		return (int) _key;
	}

	/**
	 * 执行更新sql(自身完成事务)
	 * 
	 * @param sql
	 * @return
	 */
	public int update(String sql)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ConnectionService service = new ConnectionService();
		try
		{
			service.createConnection();
			conn = service.getConn();
			stmt = conn.prepareStatement(sql);
			//log.debug("Executing sql : " + sql);
			result = stmt.executeUpdate();
			// 提交事务
			commit(conn);
			result=1;
		}
		catch (Exception e)
		{
			result = 0;
			rollback(conn);
			log.debug(e);
		}
		finally
		{
			try
			{
				service.close();
				stmt.close();
			}
			catch(Exception ex)
			{
				stmt = null;
			}
		}
		return result;
	}

	/**
	 * 执行删除SQL(自身完成事务)
	 * 
	 * @param sql
	 * @return
	 */
	public int delete(String sql)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ConnectionService service = new ConnectionService();
		try
		{
			service.createConnection();
			conn = service.getConn();
			stmt = conn.prepareStatement(sql);
			//log.debug("Executing sql : " + sql);
			result = stmt.executeUpdate();
			// 提交事务
			commit(conn);
		}
		catch (Exception e)
		{
			result = 0;
			rollback(conn);
			log.debug(e);
		}
		finally
		{
			try
			{
				service.close();
				stmt.close();
			}
			catch(Exception ex)
			{
				stmt = null;
			}
		}
		return result;
	}

	/**
	 * 批量更新（update, insert, delete）(自身完成事务)
	 * 
	 * @param sql
	 * @return
	 */
	public boolean batchExecute(ArrayList<String> sql)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ConnectionService service = new ConnectionService();
		try
		{
			service.createConnection();
			conn = service.getConn();
			for (String _sql : sql)
			{
				stmt = conn.prepareStatement(_sql);
				log.debug("_sql = " + _sql); 
				stmt.executeUpdate();
			}
			commit(conn);
			result = true;
			sql=null;
		}
		catch (Exception e)
		{
			rollback(conn);
			log.debug(e);
			return false;
		}
		finally
		{
			try
			{
				service.close();
				stmt.close();
			}
			catch(Exception ex)
			{
				stmt = null;
			}
		}
		return result;
	}
	public boolean batchExecute(ArrayList<String> sql, Connection conn)
	{
		boolean result = false;
		PreparedStatement stmt = null;
		try
		{
			for (String _sql : sql)
			{
				stmt = conn.prepareStatement(_sql);
				stmt.executeUpdate();
			}
			result = true;
			sql=null;
		}
		catch (Exception e)
		{
			rollback(conn);
			log.debug(e);
			return false;
		}
		finally
		{
			try
			{
				stmt.close();
			}
			catch(Exception ex)
			{
				stmt = null;
			}
		}
		return result;
	}
	
	/**
	 * 执行查询sql(自身完成事务)
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> select(String sql)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ConnectionService service = new ConnectionService();
		try
		{
			service.createConnection();
			conn = service.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			// 封装resultSet数据 ,利用ResultSetMetaData对象可获得表的结构
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next())
			{
				// 循环表
				// 每行记录放到一个map里！
				Map<String, Object> map = new HashMap<String, Object>();// 每行记录放到一个
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
					map.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
				}
				list.add(map);
				map=null;
			}
			// 提交事务
			commit(conn);
			rsmd=null;
			
		}
		catch (Exception e)
		{
			rollback(conn);
			log.debug("select exception " + e);
		}
		finally
		{
			try
			{
				service.close();
				rs.close();
				stmt.close();
			}
			catch(Exception ex)
			{
				stmt = null;
				rs = null;
			}
		}
		return list;
	}
}
