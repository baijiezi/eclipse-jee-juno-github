package com.sun309.gateway.dbpool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionService
{
	private Connection conn = null;
	private HashtableId id;
	private ResourceManager rm = ResourceManager.getInstance();
	private Logger log = Logger.getLogger(ConnectionService.class);
	
	public void createConnection() throws SQLException
	{
		HashtableId key = new HashtableId();
		conn = rm.getConnection(key);
		this.setId(key);
		if (conn != null)
		{
			conn.setAutoCommit(false);
			//log.debug("成功开启事务!");
		}
	}
	
	public Connection getConn()
	{
		return conn;
	}


	public void setConn(Connection conn)
	{
		this.conn = conn;
	}


	public void close()
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.commit();
				//log.debug("成功提交事务!");
			}
		}
		catch (SQLException e)
		{
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		try
		{
			this.getRm().close(this.getId().getKey());
		}
		catch(Exception e){}
	}
	public HashtableId getId()
	{
		return id;
	}
	public void setId(HashtableId id)
	{
		this.id = id;
	}
	public ResourceManager getRm()
	{
		return rm;
	}
}
