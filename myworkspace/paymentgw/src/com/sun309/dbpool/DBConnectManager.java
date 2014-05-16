package com.sun309.dbpool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.sun309.util.Constants;
import common.Logger;

public class DBConnectManager
{
	private final static Logger log = Logger.getLogger(DBConnectManager.class);
	private static int initSize = Constants.INITDBCONNECTSIZE;
	private static List<Hashtable<Integer, DBConnect>> list = new ArrayList<Hashtable<Integer, DBConnect>>(initSize);
	private static DBConnectManager dbConnectManager = new DBConnectManager();
	private static int nowNumber = 0;

	private DBConnectManager()
	{

	}

	public static DBConnectManager getInstance()
	{
		if (list.size() <= 0)
		{
			for (int i = 0; i < initSize; i++)
			{
				DBConnect dbConnect = new DBConnect();
				try
				{
					dbConnect.createConnection();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				dbConnect.setInUse(false);
				dbConnect.setConnId(dbConnect.hashCode());
				Hashtable<Integer, DBConnect> ht = new Hashtable<Integer, DBConnect>();
				ht.put(dbConnect.hashCode(), dbConnect);
				list.add(ht);
				//log.debug("成功创建数据库连接：" + dbConnect);
			}
		}
		return dbConnectManager;
	}

	public synchronized Hashtable<Integer, DBConnect> getDBConnect()
	{
		Hashtable<Integer, DBConnect> ht = new Hashtable<Integer, DBConnect>();
		for (int i = 0, n = list.size(); i < n; i++)
		{
			ht = (Hashtable<Integer, DBConnect>) (list.get(nowNumber));
			int _key = 0;
			for (Integer key : ht.keySet())
			{
				_key = key;
			}
			DBConnect dbConnect = (DBConnect) ht.get(_key);
			if (!dbConnect.isInUse())
			{
				try
				{
					if (dbConnect.getConn() != null && !dbConnect.getConn().isClosed())
					{
						nowNumber++;
						if (nowNumber == initSize)
						{
							nowNumber = 0;
						}
						dbConnect.setInUse(true);
						
						//log.debug("\n 当前有：" + list.size() + "个连接，当用引用了第" + nowNumber + "个, 连接：" + dbConnect);
						break;
					}
					else
					{
						if (dbConnect.getConn() != null)
						{
							dbConnect.getConn().close();
						}
						dbConnect = null;
						list.remove(nowNumber);
						if (list.size() >= initSize)
						{
							emptyFirstList();
						}
						dbConnect = new DBConnect();
						dbConnect.setInUse(false);
						dbConnect.setConnId(dbConnect.hashCode());
						ht = new Hashtable<Integer, DBConnect>();
						ht.put(dbConnect.hashCode(), dbConnect);
						list.add(ht);
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (i == list.size() - 1)
			{
				log.debug("等待所有连接都被占用");
			}
			nowNumber++;
			if (nowNumber == initSize)
			{
				nowNumber = 0;
			}
		}
		return ht;
	}

	public synchronized void reset()
	{
		ConnectionCache.getInstance().reset();
		emptyList();
	}

	private void emptyList()
	{
		list.clear();
	}

	private void emptyFirstList()
	{
		DBConnect dbConnect = null;
		try
		{
			Hashtable<Integer, DBConnect> ht = (Hashtable<Integer, DBConnect>) list.get(0);
			for (Integer key : ht.keySet())
			{
				dbConnect = ht.get(key);
			}
			dbConnect.close();
			list.remove(0);
		}
		finally
		{
			list.remove(0);
			dbConnect = null;
		}
	}

	public void close(int key)
	{
		for (int i = 0, n = list.size(); i < n; i++)
		{
			Hashtable<Integer, DBConnect> ht = (Hashtable<Integer, DBConnect>) (list.get(i));
			for (Integer _key : ht.keySet())
			{
				if (key == _key.intValue())
				{
					DBConnect db = (DBConnect) ht.get(key);
					db.setInUse(false);
					//log.debug("把第[" + key + "]连接放入数据库连接池：" + db + "\n");
				}
			}
		}
	}

	public static void main(String[] args)
	{
	}
}
