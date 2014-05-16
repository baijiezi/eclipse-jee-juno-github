/**
 * 
 */
package com.sun309.impl;

import java.util.ArrayList;

import com.sun309.jdbc.JdbcDaoFactory;
import com.sun309.jdbc.JdbcDaoService;
import com.sun309.service.LogService;
import com.sun309.util.Common;
import com.sun309.util.DateUtils;

/**
 * @author Wormwood OPC_GROUP
 *
 */
public class NetPayExceptionLogServiceImpl implements LogService {

	private static ArrayList<String> list = new ArrayList<String>();
	private JdbcDaoService jdbc = JdbcDaoFactory.create();
	/* (non-Javadoc)
	 * @see com.sun309.service.LogService#debug(java.lang.String)
	 */
	public void debug(String content) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO net_pay_exception_log (`log_content`, `add_time`) VALUES ('");
		sql.append(Common.htmlEscaping(content)).append("','");
		sql.append(DateUtils.getNowTime());
		sql.append("')");
		list.add(sql.toString());
	}

	/* (non-Javadoc)
	 * @see com.sun309.service.LogService#write()
	 */
	public void write() {
		// TODO Auto-generated method stub
		if( list!=null && list.size() > 0 )
			jdbc.batchExecute(list);
		list = null;
		list = new ArrayList<String>();
	}
}
