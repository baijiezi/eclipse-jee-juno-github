package com.sun309.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sun309.dbpool.ConnectionService;
import com.sun309.dto.Id;
import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.IdService;
import com.sun309.util.DateUtils;

public class IdServiceImpl implements IdService
{
	private JdbcDaoService jdbc = JdbcDaoFactory.create();
	public synchronized String createId()
	{
		ConnectionService connection = new ConnectionService();
		try
		{
			connection.createConnection();
			StringBuffer sql = new StringBuffer();
			String d = DateUtils.getNowDate();
			sql.append("SELECT `id`, `date_time` FROM id ").append(" WHERE `date_time`='").append(d).append("'");

			List<Map<String, Object>> list = jdbc.select(sql.toString(), connection.getConn());
			Id id = new Id();
			sql = null;
			boolean _result = false;
			if (list != null && list.size() > 0)
			{
				for (Map<String, Object> map : list)
				{
					id.setId(jdbc.ObjectToLong(map, "id"));
					id.setDateTime(jdbc.ObjectToString(map, "date_time"));
					map.clear();
					map = null;
					_result = true;
				}
				list = null;
			}
			else
			{
				sql = new StringBuffer();
				d = DateUtils.getNowDate();
				sql.append("INSERT INTO id").append("(`id`, `date_time`) VALUES ('1', '").append(d).append("')");

				id.setId((long) 1);
				id.setDateTime(d);
				_result = jdbc.insert(sql.toString(), connection.getConn()) > 0 ? true : false;
			}

			// 更新订单号
			sql = new StringBuffer();
			sql.append("UPDATE id ").append(" SET id=id+1 WHERE ").append(" `date_time`='").append(id.getDateTime()).append("'  LIMIT 1");
			jdbc.update(sql.toString(), connection.getConn());
			
			String _id = null;
			if (_result)
			{
				_id = this._createId(id.getId());
			}
			return _id;
		}
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			connection.close();
		}
	}

	private String _createId(Long id)
	{
		String __id = id.toString();
		StringBuffer zero = new StringBuffer();
		int orderNoLength = 9;
		for (int i = __id.length(); i < orderNoLength; i++)
		{
			zero.append("0");
		}
		zero.append(id.toString());
		Date date = new Date();
		return new StringBuffer("PAYGW").append(new SimpleDateFormat("yyyy").format(date)).append(new SimpleDateFormat("MM").format(date)).append(new SimpleDateFormat("dd").format(date)).append(zero).toString();
	}

	public static void main(String[] args)
	{
		IdServiceImpl i = new IdServiceImpl();
		System.out.println(i.createId());
	}
}
