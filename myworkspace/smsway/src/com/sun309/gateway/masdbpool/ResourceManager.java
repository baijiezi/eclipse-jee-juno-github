package com.sun309.gateway.masdbpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class ResourceManager
{
	private DBConnect dbConnect;
	private int connKey;
	private DBConnectManager db = null;
	private static ResourceManager rm = null;

	private ResourceManager()
	{
	}

	public static ResourceManager getInstance()
	{
		if (rm == null)
		{
			rm = new ResourceManager();
		}
		return rm;
	}

	public synchronized Connection getConnection(HashtableId key) throws SQLException
	{
		db = DBConnectManager.getInstance();
		Hashtable<Integer, DBConnect> hashTable = db.getDBConnect();
		dbConnect = null;
		for (Integer _key : hashTable.keySet())
		{
			dbConnect = hashTable.get(_key);
			setConnKey(_key);
			key.setKey(_key);
		}
		return dbConnect.getConn();
	}

	public void close(int key)
	{
		db.close(key);
	}

	public void close(PreparedStatement stmt)
	{
		try
		{
			if (dbConnect != null)
				dbConnect.closePreStmt(stmt);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs)
	{
		try
		{
			if (dbConnect != null)
				dbConnect.closeResultSet(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public int getConnKey()
	{
		return connKey;
	}

	public void setConnKey(int connKey)
	{
		this.connKey = connKey;
	}

}
