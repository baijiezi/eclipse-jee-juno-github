package com.sun309.gateway.impl;

import java.util.ArrayList;

import com.sun309.gateway.dao.MoDao;
import com.sun309.gateway.dto.Mo;
import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.util.Common;

public class MoDaoImpl extends Dao implements MoDao
{
	/**
	 * 操作SMS： 1、查询出messages_log 2、将回复备份到mo表中 3、删除mo_status的记录
	 * 4、更新messages_log表中字段is_call_back,已回复
	 * 
	 * @param mo
	 */
	public boolean insert(Mo mo, String interfaceName)
	{
		ArrayList<String> _sql = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO mo( message_id, SM_ID, MO_TIME, MSG_FMT, CONTENT, add_time, send_time, call_back_time, mobile, enable, interface_name ) VALUES ('")
		   .append(mo.getMessageId())
		   .append("', '")
		   .append(mo.getSmId())
		   .append("', '")
		   .append(mo.getMoTime())
		   .append("', '")
		   .append(mo.getMsgFmt())
		   .append("', '")
		   .append(mo.getContent())
		   .append("', '")
		   .append(Common.getNowTime())
		   .append("', '")
		   .append(mo.getSendTime())
		   .append("', '")
		   .append(mo.getCallBackTime())
		   .append("', '")
		   .append(mo.getMobile())
		   .append("', '")
		   .append(mo.getEnable())
		   .append("', '")
		   .append(interfaceName)
		   .append("')");
		
		_sql.add(sql.toString());
		sql = new StringBuffer();
		sql.append(new StringBuffer("UPDATE messages_log SET is_call_back=1 WHERE message_id='").append(mo.getMessageId()).append("'").toString());
		_sql.add(sql.toString());
		return this.batchExecute(_sql);
	}
}
