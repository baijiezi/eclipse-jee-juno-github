package com.sun309.gateway.dbpool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.Logger;

public class DBConnect
{
	private final static Logger log = Logger.getLogger(DBConnect.class);
	private static ConnectionCache conCache = ConnectionCache.getInstance();
	private int connId;
	private Connection conn;
	private boolean inUse;
	private static int connectionMode = conCache.getConnectionMode();
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
			if (connectionMode == 0)
			{
				conn = conCache.getConnection();
			}
			else if (connectionMode == 2)
			{
				Context initCtx = new InitialContext();
				Context ctx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) ctx.lookup("jndi-lybbs");
				conn = ds.getConnection();
			}
			else if (connectionMode == 3)
			{
				Hashtable<String, String> ht = new Hashtable<String, String>();
				ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
				ht.put(Context.PROVIDER_URL, "t3://127.0.0.1:80");
				Context ctx = new InitialContext(ht);
				DataSource ds = (DataSource) ctx.lookup("jndi-lybbs");
				conn = ds.getConnection();
			}
			else if (connectionMode == 4)
			{
				InputStream is = getClass().getResourceAsStream("/db.properties");
				Properties dbProps = new Properties();
				dbProps.load(is);
				String dbDriver = dbProps.getProperty("dbDriver");
				String dbUrl = dbProps.getProperty("dbUrl");
				String dbUsername = dbProps.getProperty("dbUsername");
				String dbPassword = dbProps.getProperty("dbPassword");
				is.close();
				dbProps.clear();
				Class.forName(dbDriver).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			}
			else
			{
				throw new SQLException("The value of conectionMode[" + connectionMode + "] is not correct, Please correct it in the config file.");
			}
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