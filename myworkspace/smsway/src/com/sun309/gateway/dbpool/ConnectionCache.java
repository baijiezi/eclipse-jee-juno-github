package com.sun309.gateway.dbpool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import com.sun309.gateway.util.Constants;
import common.Logger;

public class ConnectionCache
{
	private final static Logger log = Logger.getLogger(ConnectionCache.class);
	private BasicDataSource basicDS;
	private int connectionMode;
	private boolean supportTransaction;
	private final static ConnectionCache conCacheInstance = new ConnectionCache();

	private ConnectionCache()
	{
		initializeConnectionCache();
	}

	public static ConnectionCache getInstance()
	{
		log.info("Gets a new instance of ConnectionCache.");
		return conCacheInstance;
	}

	private void initializeConnectionCache()
	{
		if (basicDS == null)
		{
			log.info("Initialize ConnectCache.");
			try
			{
				log.debug("Loads config file(db.properties) into the Object of Properties.");
				InputStream is = getClass().getResourceAsStream(Constants.DB_PROPERTIES);
				Properties dbProps = new Properties();
				dbProps.load(is);
				String dbDriver = dbProps.getProperty("dbDriver");
				String dbUrl = dbProps.getProperty("dbUrl");
				String dbUsername = dbProps.getProperty("dbUsername");
				String dbPassword = dbProps.getProperty("dbPassword");
				String dbValidationQuery = dbProps.getProperty("dbValidationQuery");
				boolean dbRemoveAbandoned = Boolean.valueOf(dbProps.getProperty("dbRemoveAbandoned", "false")).booleanValue();
				boolean dbPoolPreparedStatements = Boolean.valueOf(dbProps.getProperty("dbPoolPreparedStatements", "false")).booleanValue();
				int dbMaxOpenPreparedStatements = Integer.parseInt(dbProps.getProperty("dbMaxOpenPreparedStatements", "50"));
				int dbInitialSize = Integer.parseInt(dbProps.getProperty("dbInitialSize", "3"));
				int dbMaxActive = Integer.parseInt(dbProps.getProperty("dbMaxActive", "20"));
				int dbMaxIdle = Integer.parseInt(dbProps.getProperty("dbMaxIdle", "10"));
				int dbMinIdle = Integer.parseInt(dbProps.getProperty("dbMinIdle", "1"));
				long dbMaxWait = Long.parseLong(dbProps.getProperty("dbMaxWait", "-1"));
				supportTransaction = Boolean.valueOf(dbProps.getProperty("dbSupportTransaction", "false")).booleanValue();
				setConnectionMode(dbProps.getProperty("connectionMode", "dbcp"));
				is.close();
				dbProps.clear();

				log.debug("Ends loading config file into the Object of Properties.");
				log.debug("Sets the value of The BasicDataSource.");

				basicDS = new BasicDataSource();
				basicDS.setDriverClassName(dbDriver);
				basicDS.setUrl(dbUrl);
				basicDS.setUsername(dbUsername);
				basicDS.setPassword(dbPassword);
				basicDS.setPoolPreparedStatements(dbPoolPreparedStatements);
				basicDS.setMaxOpenPreparedStatements(dbMaxOpenPreparedStatements);
				basicDS.setInitialSize(dbInitialSize);
				basicDS.setMaxActive(dbMaxActive);
				basicDS.setMaxIdle(dbMaxIdle);
				basicDS.setMinIdle(dbMinIdle);
				basicDS.setMaxWait(dbMaxWait);
				if (dbValidationQuery != null && dbValidationQuery.length() > 0)
				{
					basicDS.setTestOnBorrow(true);
					basicDS.setValidationQuery(dbValidationQuery);
				}
				if (dbRemoveAbandoned)
				{
					log.debug("The value of dbRemoveAbandoned is set true, Program will log the abandoned object, Timeout is 30s.");
				}
			}
			catch (Exception ex)
			{
				log.fatal("Error occurs while doing the method of void initializeConnectionCache() in ConnectionCache.class: " + ex.toString());
			}
		}
	}

	/**
	 * 0-dbcp,2-tomcat,3-weblogic,4-nopool
	 * 
	 * @param mode
	 */
	private void setConnectionMode(String mode)
	{
		if (mode.equalsIgnoreCase("dbcp"))
		{
			connectionMode = 0;
		}
		else if (mode.equalsIgnoreCase("tomcat"))
		{
			connectionMode = 2;
		}
		else if (mode.equalsIgnoreCase("weblogic"))
		{
			connectionMode = 3;
		}
		else if (mode.equalsIgnoreCase("nopool"))
		{
			connectionMode = 4;
		}
		else
		{
			connectionMode = 0;
		}
	}

	public Connection getConnection() throws SQLException
	{

		log.debug("Gets a Connection from pool.");
		return basicDS.getConnection();
	}

	public void reset()
	{
		close();
		log.info("Reinitialize the basic data source.");
		initializeConnectionCache();
	}

	public synchronized void close()
	{
		log.debug("Closes all Connections in pool.");
		try
		{
			if (basicDS != null)
			{
				log.debug("Executes basicDS.close().");
				basicDS.close();
				basicDS = null;
			}
		}
		catch (Exception ex)
		{
			log.fatal("Error occurs while doing the method of void close() in ConnectionCache.class: " + ex.toString());
		}
	}

	public void prtStatus()
	{
		System.out.println(getStatus());
	}

	public String getStatus()
	{
		if (basicDS != null)
		{
			StringBuffer sb = new StringBuffer(30);
			sb.append("==> NumActive: ");
			sb.append(basicDS.getNumActive());
			sb.append(" NumIdle: ");
			sb.append(basicDS.getNumIdle());
			log.info(sb);
			return sb.toString();
		}
		return "Can't get Connection Status.";
	}

	public int getConnectionMode()
	{
		return connectionMode;
	}

	public boolean isSupportTransaction()
	{
		return supportTransaction;
	}
}