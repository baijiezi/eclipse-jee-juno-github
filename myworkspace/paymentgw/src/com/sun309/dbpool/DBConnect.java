package com.sun309.dbpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.Logger;

public class DBConnect
{
	private final static Logger log = Logger.getLogger(DBConnect.class);
	private static ConnectionCache conCache = ConnectionCache.getInstance();
	private int connId;
	private Connection conn;
	private boolean inUse;
	private static boolean supportTransaction = conCache.isSupportTransaction();

	public synchronized boolean isInUse()
	{
		return inUse;
	}

	public synchronized void setInUse(boolean inUse)
	{
		this.inUse = inUse;
	}

	public DBConnect()
	{
		log.info("Creates a new instance of DBConnect.");
	}

	public void createConnection() throws SQLException
	{
		try
		{
			conn = conCache.getConnection();
		}
		catch (Exception ex)
		{
			log.error("Error occurs when do method void createConnection() in DBConnect.class:\n" + ex.toString());
			ConnectionCache.getInstance().close();
			throw new SQLException("Error occurs when do method void createConnection() in DBConnect.class:\n" + ex.toString());
		}
	}

	public void closePreStmt(PreparedStatement prestmt) throws SQLException
	{
		if (prestmt != null)
		{
			try
			{
				prestmt.clearParameters();
				prestmt.close();
				prestmt = null;
			}
			catch (Exception ex)
			{
				log.error("Error occurs when executing the method of void closePreStmt(prestmt) in DBConnect.class:\n" + ex.toString());
			}
		}
	}

	public void closeStmt(Statement stmt) throws SQLException
	{
		if (stmt != null)
		{
			try
			{
				stmt.clearBatch();
				stmt.close();
				stmt = null;
			}
			catch (Exception ex)
			{
				log.error("Error occurs when executing the method of void closeStmt(stmt) in DBConnect.class:\n" + ex.toString());
			}
		}
	}

	public void closeResultSet(ResultSet rs) throws SQLException
	{
		if (rs != null)
		{
			try
			{
				rs.close();
				rs = null;
			}
			catch (Exception ex)
			{
				log.error("Error occurs when executing the method of void closeResultSet(rs) in DBConnect.class:\n" + ex.toString());
			}
		}
	}

	public void close()
	{
		try
		{
			if (conn != null)
			{
				try
				{
					log.info("Executes conn.close -- " + this);
					conn.close();
					conn = null;
				}
				catch (SQLException ex)
				{
					log.error("Error occurs when doing conn.close(), follow will do it again.");
					conn.close();
					conn = null;
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error occurs when closing the handle of the pool.");
			e.printStackTrace();
		}
		finally
		{
			log.debug("close dbConnect ---------- " + this);
			setInUse(false);
		}
	}

	public void rollBack() throws SQLException
	{
		if (supportTransaction)
			conn.rollback();
	}

	public Connection getConn()
	{
		return conn;
	}

	public void setConn(Connection conn)
	{
		this.conn = conn;
	}

	public int getConnId()
	{
		return connId;
	}

	public void setConnId(int connId)
	{
		this.connId = connId;
	}
}