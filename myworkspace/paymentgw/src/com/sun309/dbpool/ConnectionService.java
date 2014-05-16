package com.sun309.dbpool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionService
{
	private Connection conn = null;
	private HashtableId id;
	private ResourceManager rm = ResourceManager.getInstance();

	public void createConnection() throws SQLException
	{
		HashtableId key = new HashtableId();
		conn = rm.getConnection(key);
		this.setId(key);
		if (conn != null)
		{
			conn.setAutoCommit(false);
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
