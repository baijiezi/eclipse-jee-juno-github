package com.sun309.gateway.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.InvalidDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.Constants;

public class InvalidDaoImpl extends Dao implements InvalidDao
{
	/**
	 * 移动数据,从
	 * messages => messages_invalid_log
	 * rpt_status => rpt_status_invalid_log
	 * 
	 * 并删除原来
	 * messages
	 * rpt_status
	 * 的数据
	 * 
	 * 移动条件,10天以前的无效的记录(1004, 1008)
	 */
	public void move()
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT message_id, mobile, send_time, send_level, `type` , add_time, receive_time, is_test,`status` , send_counter, need_call_back, call_back_time, enable, is_resend, rpt_call_back_url, mo_call_back_url, come_from, interface_name, methed FROM messages ");
		sql.append(" WHERE  send_time < ").append((Common.getNowTime() - (1000 * 60 * 60 * 24 * 10)));
		sql.append(" AND status IN (");
		sql.append(Constants.MESSAGE_BUSSINESS_PROCESS_SUCCESS);
		sql.append(",");
		sql.append(Constants.MESSAGE_CHECK_SEND_TO_BUSSINESS_SUCCESS);
		sql.append(")");
		ArrayList<String> _sql = new ArrayList<String>();
		List<Map <String, Object>> list = this.select(sql.toString());
		
		if(list == null || list.size() <=0) return ;
		
		for (Map<String, Object> map : list)
		{
			Messages message = new Messages();
			message.setMessageId(Integer.parseInt(map.get("message_id").toString()));
			message.setAddTime(Long.parseLong(map.get("add_time").toString()));
			message.setCallBackTime(Long.parseLong(map.get("call_back_time").toString()));
			message.setComeFrom(map.get("come_from").toString());
			message.setEnable(Integer.parseInt(map.get("enable").toString()));
			message.setInterfaceName(map.get("interface_name").toString());
			message.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
			message.setIsTest(Integer.parseInt(map.get("is_test").toString()));
			message.setMobile(map.get("mobile").toString());
			message.setMoCallBackUrl(map.get("mo_call_back_url").toString());
			message.setNeedCallBack(Integer.parseInt(map.get("need_call_back").toString()));
			message.setReceiveTime(Long.parseLong(map.get("receive_time").toString()));
			message.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
			message.setSendCounter(Integer.parseInt(map.get("send_counter").toString()));
			message.setSendTime(Long.parseLong(map.get("send_time").toString()));
			message.setStatus(Integer.parseInt(map.get("status").toString()));
			message.setType(Integer.parseInt(map.get("type").toString()));
			message.setSendLevel(Integer.parseInt(map.get("send_level").toString()));
			message.setMethed(map.get("methed").toString());
			
			sql = new StringBuffer();
			sql.append("INSERT INTO messages_invalid_log( message_id, mobile, send_time, send_level, `type` , add_time, receive_time, is_test, `status`, send_counter, need_call_back, call_back_time, enable, is_resend, rpt_call_back_url, mo_call_back_url, come_from, interface_name, methed) VALUES (");
			sql.append("'" ).append( message.getMessageId().intValue() ).append( "',");
			sql.append("'" ).append( message.getMobile() ).append( "',");
			sql.append("'" ).append( message.getSendTime() ).append( "',");
			sql.append("'" ).append( message.getSendLevel() ).append( "',");
			sql.append("'" ).append( message.getType() ).append( "',");
			sql.append("'" ).append( message.getAddTime() ).append( "',");
			sql.append("'" ).append( message.getReceiveTime() ).append( "',");
			sql.append("'" ).append( message.getIsTest() ).append( "',");
			sql.append("'" ).append( message.getStatus() ).append( "',");
			sql.append("'" ).append( message.getSendCounter() ).append( "',");
			sql.append("'" ).append( message.getNeedCallBack() ).append( "',");
			sql.append("'" ).append( message.getCallBackTime() ).append( "',");
			sql.append("'" ).append( message.getEnable() ).append( "',");
			sql.append("'" ).append( message.getIsResend() ).append( "',");
			sql.append("'" ).append( message.getRptCallBackUrl() ).append( "',");
			sql.append("'" ).append( message.getMoCallBackUrl() ).append( "',");
			sql.append("'" ).append( message.getComeFrom() ).append( "',");
			sql.append("'" ).append( message.getInterfaceName() ).append( "',");
			sql.append("'" ).append( message.getMethed() ).append( "'");
			sql.append(")");
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("DELETE FROM messages WHERE message_id=" ).append( message.getMessageId().intValue());
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("INSERT INTO rpt_status_invalid_log(message_id, get_rpt_counter, read_time, status, mt_id, mo_id, rpt_id, enable, is_resend, interface_name, rpt_call_back_url) ");
			sql.append(" SELECT message_id, get_rpt_counter, read_time, status, mt_id, mo_id, rpt_id, enable, is_resend, interface_name, rpt_call_back_url  FROM rpt_status WHERE message_id=" ).append( message.getMessageId().intValue());
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("DELETE FROM rpt_status WHERE message_id=" ).append( message.getMessageId().intValue());
			_sql.add(sql.toString());
		}
		if(_sql != null && _sql.size() > 0)
			this.batchExecute(_sql);
		
		_sql = null;
		list = null;
	}
}
