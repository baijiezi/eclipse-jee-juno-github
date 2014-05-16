package com.sun309.gateway.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dao.MessagesIdDao;
import com.sun309.gateway.dao.MtDao;
import com.sun309.gateway.dbpool.ConnectionService;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.MessagesId;
import com.sun309.gateway.dto.MessagesLog;
import com.sun309.gateway.dto.Mt;
import com.sun309.gateway.dto.RptStatus;
import com.sun309.gateway.dto.mas.MasMt;
import com.sun309.gateway.factory.MessagesIdDaoFactory;
import com.sun309.gateway.factory.MtDaoFactory;
import com.sun309.gateway.jdbc.Dao;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.thread.type.SendPortMapping;
import com.sun309.gateway.thread.type.TypeToMas;
import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.Constants;
import com.sun309.gateway.util.DateUtils;
import com.sun309.gateway.util.HttpURLConnectionRequest;

public class MessagesDaoImpl extends Dao implements MessagesDao
{
	private StringBuffer MESSAGES_MAIN_COLUMN = new StringBuffer();
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, TypeToMas.class);
	private StringBuffer RPT_STATUS_MAIN_COLUMN = new StringBuffer();

	public MessagesDaoImpl()
	{
		MESSAGES_MAIN_COLUMN.append("`message_id`, ");
		MESSAGES_MAIN_COLUMN.append("`mobile`, ");
		MESSAGES_MAIN_COLUMN.append("`send_time`, ");
		MESSAGES_MAIN_COLUMN.append("`send_level`, ");
		MESSAGES_MAIN_COLUMN.append("`type`, ");
		MESSAGES_MAIN_COLUMN.append("`add_time`, ");
		MESSAGES_MAIN_COLUMN.append("`receive_time`, ");
		MESSAGES_MAIN_COLUMN.append("`is_test`, ");
		MESSAGES_MAIN_COLUMN.append("`status`, ");
		MESSAGES_MAIN_COLUMN.append("`send_counter`, ");
		MESSAGES_MAIN_COLUMN.append("`need_call_back`, ");
		MESSAGES_MAIN_COLUMN.append("`call_back_time`, ");
		MESSAGES_MAIN_COLUMN.append("`enable`, ");
		MESSAGES_MAIN_COLUMN.append("`is_resend`, ");
		MESSAGES_MAIN_COLUMN.append("`rpt_call_back_url`, ");
		MESSAGES_MAIN_COLUMN.append("`mo_call_back_url`, ");
		MESSAGES_MAIN_COLUMN.append("`come_from`, ");
		MESSAGES_MAIN_COLUMN.append("`interface_name`, ");
		MESSAGES_MAIN_COLUMN.append("`methed`, ");
		MESSAGES_MAIN_COLUMN.append("`oper_name`, ");
		MESSAGES_MAIN_COLUMN.append("`access` ");

		RPT_STATUS_MAIN_COLUMN.append("`message_id`, ");
		RPT_STATUS_MAIN_COLUMN.append("`get_rpt_counter`, ");
		RPT_STATUS_MAIN_COLUMN.append("`read_time`, ");
		RPT_STATUS_MAIN_COLUMN.append("`status`, ");
		RPT_STATUS_MAIN_COLUMN.append("`mt_id`, ");
		RPT_STATUS_MAIN_COLUMN.append("`mo_id`, ");
		RPT_STATUS_MAIN_COLUMN.append("`rpt_id`, ");
		RPT_STATUS_MAIN_COLUMN.append("`enable`, ");
		RPT_STATUS_MAIN_COLUMN.append("`is_resend`, ");
		RPT_STATUS_MAIN_COLUMN.append("`interface_name`, ");
		RPT_STATUS_MAIN_COLUMN.append("`rpt_call_back_url`");
	}

	/**
	 * 添加短信到messages表中
	 * 
	 * @param message
	 * 
	 * @return
	 */
	public int insert(Messages message)
	{
		ConnectionService connection = new ConnectionService();
		MessagesIdDao messagesIdDao = null;
		try
		{
			connection.createConnection();
			// 获取messages_id
			MessagesId messagesId = new MessagesId();
			messagesIdDao = MessagesIdDaoFactory.create();
			messagesId.setMessageId(messagesIdDao.findMessageId(connection));

			// 获取mt
			MtDao mtDao = MtDaoFactory.create();
			Mt mt = mtDao.findMt(connection);
			
			String port = SendPortMapping.getMapping(message.getOperName());

			// 插入短信
			ArrayList<String> _sql = new ArrayList<String>();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO messages (");
			sql.append(MESSAGES_MAIN_COLUMN);
			sql.append(") VALUES ( ");
			sql.append("'");
			sql.append(messagesId.getMessageId());
			sql.append("','");
			sql.append(message.getMobile());
			sql.append("','");
			sql.append(message.getSendTime());
			sql.append("','");
			sql.append(message.getSendLevel());
			sql.append("','");
			sql.append(message.getType());
			sql.append("','");
			sql.append(Common.getNowTime());
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(message.getIsTest());
			sql.append("','");
			sql.append(Constants.MESSAGE_INIT_STATUS);
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(message.getNeedCallBack());
			sql.append("','");
			sql.append(message.getCallBackTime());
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(message.getRptCallBackUrl());
			sql.append("','");
			sql.append(message.getMoCallBackUrl());
			sql.append("','");
			sql.append(message.getComeFrom());
			sql.append("','");
			sql.append("gateway");
			sql.append("','");
			sql.append(message.getMethed());
			sql.append("','");
			sql.append(message.getOperName());			
			sql.append("','");
			sql.append(port);
			sql.append("'");
			sql.append(")");
			_sql.add(sql.toString());

			// 插入短信内容
			sql = new StringBuffer();
			String m_content = new String();
			if (Common.isUnicomMobile(message.getMobile()) && message.getNeedCallBack().intValue() == 1)
			{
				m_content = new StringBuffer(message.getContent().getMessageContent()).append("联通及电信手机客户回复:8#").append(mt.getSrcId().longValue()).append("#短信内容").toString();
			}
			else
			{
				m_content = message.getContent().getMessageContent(); 
			}
			
			if(Common.isUnicomMobile(message.getMobile()))
			{
				m_content = new StringBuffer(m_content).append("").toString();
			}
			
			sql.append("INSERT INTO messages_content ( message_id, message_content )");
			sql.append(" VALUES ( '");
			sql.append(messagesId.getMessageId());
			sql.append("','");
			sql.append(m_content);
			sql.append("')");
			_sql.add(sql.toString());

			// 插入发送记录
			sql = new StringBuffer();
			sql.append("INSERT INTO rpt_status(");
			sql.append(RPT_STATUS_MAIN_COLUMN);
			sql.append(") VALUES (");
			sql.append("'");
			sql.append(messagesId.getMessageId());
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(Constants.MESSAGE_INIT_STATUS);
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(mt.getSrcId().longValue());
			sql.append("','");
			sql.append(mt.getSmId().longValue());
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(message.getInterfaceName());
			sql.append("','");
			sql.append(message.getRptCallBackUrl());
			sql.append("'");
			sql.append(")");
			_sql.add(sql.toString());

			boolean isS = this.batchExecute(_sql, connection.getConn());
			if(!isS){//短信数据保存不成功则返回失败
				return -1;
			}
			_sql = null;
			
			return messagesId.getMessageId().intValue();
		}
		catch (Exception e)
		{
			System.out.println(e);
			this.rollback(connection.getConn());
			messagesIdDao.findMessageId(connection);
			return -1;
		}
		finally
		{
			connection.close();
		}
	}

	/**
	 * 查询出要发送出去的短信 同时更新短信的状态为发送出去
	 * 
	 * @param conditions
	 * 
	 * @return
	 */
	public ArrayList<Messages> findSendMessages(String condition)
	{
		ConnectionService connection = new ConnectionService();
		ArrayList<String> _sql = new ArrayList<String>();
		try
		{
			connection.createConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("m.message_id, m.mobile, m.send_time, m.type, m.add_time, m.receive_time, m.is_test, m.status, m.send_counter, m.need_call_back, m.call_back_time, m.enable, m.is_resend, m.rpt_call_back_url, m.mo_call_back_url, m.come_from, m.interface_name, m.methed, m.oper_name, m.access");
			sql.append(", rs.mt_id, rs.mo_id, rs.rpt_id");
			sql.append(" FROM messages m JOIN rpt_status rs WHERE m.message_id=rs.message_id AND ");
			sql.append(condition);
			List<Map<String, Object>> _list = this.select(sql.toString(), connection.getConn());
			if(_list == null || _list.size() <= 0) return null;
			ArrayList<Messages> list = new ArrayList<Messages>();
			for (Map<String, Object> map : _list)
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
				message.setMethed(map.get("methed").toString());
				message.setOperName(this.ObjectToString(map, "oper_name"));
				message.setAccess(this.ObjectToString(map, "access")); 

				RptStatus rptStatus = new RptStatus();
				rptStatus.setMoId(Long.parseLong(map.get("mo_id").toString()));
				rptStatus.setRptId(Long.parseLong(map.get("rpt_id").toString()));
				rptStatus.setMtId(Long.parseLong(map.get("mt_id").toString()));
				message.setRptStatus(rptStatus);

				// 更新messages表中的记录,更新表中的状态:发送到业务服务器
				
				sql = new StringBuffer();
				sql.append("UPDATE messages SET status='");
				sql.append(Constants.MESSAGE_BUSSINESS_PROCESS_SUCCESS);
				sql.append("', send_counter=send_counter+1 WHERE message_id='");
				sql.append(message.getMessageId().intValue());
				sql.append("'");
				_sql.add(sql.toString());

				// 更新rpt_status表中的记录
				sql = new StringBuffer();
				sql.append("UPDATE rpt_status SET status='");
				sql.append(Constants.MESSAGE_BUSSINESS_PROCESS_SUCCESS);
				sql.append(new StringBuffer("', read_time='").append(Common.getNowTime()).append("' WHERE message_id='").toString());
				sql.append(message.getMessageId().intValue());
				sql.append("'");
				_sql.add(sql.toString());

				this.batchExecute(_sql, connection.getConn());

				message.setStatus(Constants.MESSAGE_BUSSINESS_PROCESS_SUCCESS.intValue());
				message.setSendCounter(message.getSendCounter() + 1);
				list.add(message);
			}
			_list = null;
			return list;
		}
		catch (Exception e)
		{
			System.out.println(e);
			this.rollback(connection.getConn());
			return null;
		}
		finally
		{
			connection.close();
		}
	}

	/**
	 * 根据ID查询出短信的内容
	 * 
	 * @param condition
	 * 
	 * @return
	 */
	public MessagesContent findMessagesContentByMessageId(int messageId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("message_id, message_content");
		sql.append(" FROM messages_content WHERE message_id=");
		sql.append(messageId);
		
		List<Map<String, Object>> _list = this.select(sql.toString());
		if(_list == null || _list.size() <= 0) return null;
		MessagesContent content = new MessagesContent();
		for (Map<String, Object> map : _list)
		{
			content.setMessageContent(map.get("message_content").toString());
			content.setMessageId(Integer.parseInt(map.get("message_id").toString()));
		}
		_list = null;
		return content;
	}

	public MessagesLog findMessagesLogByMessageId(int mid)
	{
		ArrayList<MessagesLog> list = findMessagesLogByCondition(new StringBuffer("ml.message_id='").append(mid).append("'").toString());
		if (list.size() > 0)
		{
			MessagesLog messagesLog = (MessagesLog) list.get(0);
			list = null;
			return messagesLog;
		}
		list = null;
		return null;
	}

	public ArrayList<MessagesLog> findMessagesLogByCondition(String condition)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ml.message_id, ml.mobile, ml.type, ml.is_test, ml.is_call_back, ml.need_call_back, ml.call_back_time, ml.is_resend, ml.rpt_call_back_url, ml.mo_call_back_url, ml.come_from, ml.interface_name, ml.methed FROM messages_log ml WHERE ");
		sql.append(condition);
		List<Map<String, Object>> list = this.select(sql.toString());
		ArrayList<MessagesLog> _list = new ArrayList<MessagesLog>();
		if (list != null && list.size() > 0)
		{
			for (Map<String, Object> map : list)
			{
				MessagesLog messagesLog = new MessagesLog();
				messagesLog.setMessageId(Integer.parseInt(map.get("message_id").toString()));
				messagesLog.setMobile(map.get("mobile").toString());
				messagesLog.setType(Short.parseShort(map.get("type").toString()));
				messagesLog.setIsTest(Byte.parseByte(Boolean.parseBoolean(map.get("is_test").toString()) ? "1" : "0"));
				messagesLog.setIsCallBack(Byte.parseByte(Boolean.parseBoolean(map.get("is_call_back").toString()) ? "1" : "0"));
				messagesLog.setNeedCallBack(Integer.parseInt(map.get("need_call_back").toString()));
				messagesLog.setCallBackTime(Long.parseLong(map.get("call_back_time").toString()));
				messagesLog.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
				messagesLog.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
				messagesLog.setMoCallBackUrl(map.get("mo_call_back_url").toString());
				messagesLog.setComeFrom(map.get("come_from").toString());
				messagesLog.setInterfaceName(map.get("interface_name").toString());
				messagesLog.setMethed(map.get("methed").toString());
				_list.add(messagesLog);
			}
			
			list = null;
		}
		return _list;
	}

	/**
	 * 更新rpt_status表中的mt_id字段
	 * 
	 * @param masMt
	 * @param messageId
	 * 
	 * @return
	 */
	public void updateRptStatusMtId(MasMt masMt, int messageId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE rpt_status SET mt_id='");
		sql.append(masMt.getAutoSn());
		sql.append("' WHERE message_id='");
		sql.append(messageId);
		sql.append("'");
		this.update(sql.toString());
	}

	/**
	 * 回执时, 1、将messsages插入messages_log 2、删除messsages 3、删除rpt_status
	 * 
	 * @param rptId
	 * @param rptCode
	 * @param rptTime
	 * 
	 * @return
	 */
	public boolean doRpt(String rptId, String rptCode, String rptTime, Messages _message)
	{
		ConnectionService connection = new ConnectionService();
		ArrayList<String> _sql = new ArrayList<String>();
		try
		{
			connection.createConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("m.message_id, m.mobile, m.send_time, m.send_level, m.type, m.add_time, m.receive_time, m.is_test AS IS_T, m.status, m.send_counter, m.need_call_back, m.call_back_time, m.enable, m.is_resend, m.rpt_call_back_url, m.mo_call_back_url, m.come_from, m.interface_name, m.methed, m.oper_name, m.access");
			sql.append(", rs.read_time, rs.mt_id, rs.mo_id, rs.rpt_id ");
			sql.append(" FROM messages");
			sql.append(" m JOIN ");
			sql.append(" rpt_status rs");
			sql.append(" WHERE m.message_id=rs.message_id");
			sql.append(" AND rs.rpt_id ='").append(rptId).append("'");
			List<Map<String, Object>> list = this.select(sql.toString(), connection.getConn());
			if(list == null || list.size() <= 0) return false;
			RptStatus rptStatus = new RptStatus();
			Common c = new Common();
			for (Map<String, Object> map : list)
			{
				_message.setMessageId(Integer.parseInt(map.get("message_id").toString()));
				_message.setMobile(map.get("mobile").toString());
				_message.setSendTime(Long.parseLong(map.get("send_time").toString()));
				_message.setSendLevel(Integer.parseInt(map.get("send_level").toString()));
				_message.setType(Integer.parseInt(map.get("type").toString()));
				_message.setAddTime(Long.parseLong(map.get("add_time").toString()));
				_message.setReceiveTime(Long.parseLong(map.get("receive_time").toString()));
				_message.setIsTest(Integer.parseInt(map.get("IS_T").toString()));
				_message.setStatus(Integer.parseInt(map.get("status").toString()));
				_message.setSendCounter(Integer.parseInt(map.get("send_counter").toString()));
				_message.setNeedCallBack(Integer.parseInt(map.get("need_call_back").toString()));
				_message.setCallBackTime(Long.parseLong(map.get("call_back_time").toString()));
				_message.setEnable(Integer.parseInt(map.get("enable").toString()));
				_message.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
				_message.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
				_message.setMoCallBackUrl(map.get("mo_call_back_url").toString());
				_message.setComeFrom(map.get("come_from").toString());
				_message.setInterfaceName(map.get("interface_name").toString());
				_message.setMethed(map.get("methed").toString());
				_message.setAccess(this.ObjectToString(map, "access"));
				_message.setOperName(this.ObjectToString(map, "oper_name")); 

				rptStatus.setMtId(Long.parseLong(map.get("mt_id").toString()));
				rptStatus.setMoId(Long.parseLong(map.get("mo_id").toString()));
				rptStatus.setRptId(Long.parseLong(map.get("rpt_id").toString()));
				rptStatus.setReadTime(Long.parseLong(map.get("read_time").toString()));
				_message.setRptStatus(rptStatus);
			}
			list = null;
			
			sql = new StringBuffer();
			sql.append("INSERT INTO messages_log (`message_id`, `mobile`, `send_time`, `type`, add_time, `status`, receive_time, mt_id, mo_id, rpt_id, is_test, is_call_back, need_call_back, call_back_time, is_resend, rpt_call_back_url, mo_call_back_url, come_from, interface_name, `methed`, `oper_name`, `access`) VALUES ( ");
			sql.append("'");
			sql.append(_message.getMessageId());
			sql.append("','");
			sql.append(_message.getMobile());
			sql.append("','");
			sql.append(_message.getSendTime());
			sql.append("','");
			sql.append(_message.getType());
			sql.append("','");
			sql.append(_message.getAddTime());
			sql.append("','");
			sql.append(rptCode);
			sql.append("','");
			sql.append(c.stringDataToLongTime(rptTime));
			sql.append("','");
			sql.append(rptStatus.getMtId());
			sql.append("','");
			sql.append(rptStatus.getMoId());
			sql.append("','");
			sql.append(rptStatus.getRptId());
			sql.append("','");
			sql.append(_message.getIsTest());
			sql.append("','");
			sql.append(0);
			sql.append("','");
			sql.append(_message.getNeedCallBack());
			sql.append("','");
			sql.append(_message.getCallBackTime());
			sql.append("','");
			sql.append(_message.getIsResend());
			sql.append("','");
			sql.append(_message.getRptCallBackUrl());
			sql.append("','");
			sql.append(_message.getMoCallBackUrl());
			sql.append("','");
			sql.append(_message.getComeFrom());
			sql.append("','");
			sql.append(_message.getInterfaceName());
			sql.append("','");
			sql.append(_message.getMethed());
			sql.append("','");
			sql.append(_message.getOperName());
			sql.append("','");
			sql.append(_message.getAccess());			
			sql.append("'");
			sql.append(")");
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("DELETE FROM messages WHERE message_id='").append(_message.getMessageId()).append("'");
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("DELETE FROM rpt_status WHERE message_id='").append(_message.getMessageId()).append("'");
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("INSERT INTO messages_content_log(message_id, message_content) SELECT message_id, message_content FROM messages_content WHERE message_id='").append(_message.getMessageId()).append("'");
			_sql.add(sql.toString());
			
			sql = new StringBuffer();
			sql.append("DELETE FROM messages_content WHERE message_id='").append(_message.getMessageId()).append("'");
			_sql.add(sql.toString());
			
			return this.batchExecute(_sql, connection.getConn());
		}
		catch (Exception e)
		{
			System.out.println(e);
			this.rollback(connection.getConn());
			return false;
		}
		finally
		{
			connection.close();
			_sql = null;
		}
	}
	
	// delete messages
	public boolean deleteMessages(int messageId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM messages WHERE message_id='").append(messageId).append("'");
		return this.delete(sql.toString()) > 0 ? true : false;
	}
	
	// delete messages_status
	public boolean deleteRptStatus(int messageId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM rpt_status WHERE message_id='").append(messageId).append("'");
		return this.delete(sql.toString()) > 0 ? true : false;
	}
	
	/**
	 * 如果回执的状态为8、102重发
	 * ID不变，更新原来短信的状态
	 * 
	 * @return
	 */
	public boolean resend8And102Status(long rptId)
	{
		ConnectionService connection = new ConnectionService();
		try
		{
			connection.createConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT message_id FROM rpt_status WHERE rpt_id='").append(rptId).append("'");
			List<Map<String, Object>> list = this.select(sql.toString(), connection.getConn());
			if(list == null || list.size() <= 0) return true;
			
			int message_id = 0;
			for(Map<String, Object> map : list)
			{
				message_id = this.ObjectToInteger(map, "message_id");
			}
			list = null;
			ArrayList <String> _sql = new ArrayList <String>();
			//更新messages的状态
			sql = new StringBuffer();
			sql.append("UPDATE messages SET status=")
			   .append(Constants.MESSAGE_INIT_STATUS)
			   .append(", send_counter=0, enable=0, is_resend=0 WHERE message_id=")
			   .append(message_id);
			_sql.add(sql.toString());
			//更新rpt_status的状态
			sql = new StringBuffer();
			sql.append("UPDATE rpt_status SET get_rpt_counter=get_rpt_counter+1, read_time=0, status=")
			   .append(Constants.MESSAGE_INIT_STATUS)
			   .append(", enable=0, is_resend=0 WHERE message_id=")
			   .append(message_id);
			_sql.add(sql.toString());
			//插入重发表
			sql = new StringBuffer();
			sql.append("INSERT INTO resend_messages(old_messages_id, add_time, new_messages_id, resend_desc) VALUES('")
			   .append(message_id)
			   .append("','")
			   .append(DateUtils.getNowTime())
			   .append("','")
			   .append(message_id)
			   .append("','状态为8或102重发')");
			
			_sql.add(sql.toString());
			this.batchExecute(_sql, connection.getConn());
			_sql = null;
			return true;
		}
		catch(Exception e)
		{
			this.rollback(connection.getConn());
			return false;
		}
		finally
		{
			connection.close();
		}
	}

	public boolean delete(int messageId)
	{
		ArrayList<String> _sql = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM messages WHERE message_id='").append(messageId).append("'");
		_sql.add(sql.toString());

		sql = new StringBuffer();
		sql.append("DELETE FROM messages_content WHERE message_id='").append(messageId).append("'");
		_sql.add(sql.toString());

		sql = new StringBuffer();
		sql.append("DELETE FROM rpt_status WHERE message_id='").append(messageId).append("'");
		_sql.add(sql.toString());

		boolean result = this.batchExecute(_sql);
		_sql = null;
		return result;
	}
	
	public ArrayList<RptStatus> findResendMessages()
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT message_id, get_rpt_counter, read_time, status, mt_id, mo_id, rpt_id, enable, is_resend, interface_name, rpt_call_back_url FROM rpt_status WHERE read_time<");
		sql.append(Common.getNowTime() - Constants.RE_SEND_MESSAGES_TIME);
		sql.append(" AND enable=0 AND is_resend=0 AND status IN (");
		sql.append(Constants.MESSAGE_BUSSINESS_PROCESS_SUCCESS);
		sql.append(", ");
		sql.append(Constants.MESSAGE_CHECK_SEND_TO_BUSSINESS_SUCCESS);
		sql.append(")");
		
		List<Map<String, Object>> list = this.select(sql.toString());
		if(list == null || list.size() <= 0) return null;
		
		ArrayList<RptStatus> _list = new ArrayList<RptStatus>();
		for (Map<String, Object> map : list)
		{
			RptStatus rpt = new RptStatus();
			rpt.setEnable(Integer.parseInt(map.get("enable").toString()));
			rpt.setGetRptCounter(Integer.parseInt(map.get("get_rpt_counter").toString()));
			rpt.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
			rpt.setMessageId(Integer.parseInt(map.get("message_id").toString()));
			rpt.setMoId(Long.parseLong(map.get("mo_id").toString()));
			rpt.setMtId(Long.parseLong(map.get("mt_id").toString()));
			rpt.setReadTime(Long.parseLong(map.get("read_time").toString()));
			rpt.setRptId(Long.parseLong(map.get("rpt_id").toString()));
			rpt.setStatus(Short.parseShort(map.get("status").toString()));
			rpt.setInterfaceName(map.get("interface_name").toString());
			rpt.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
			_list.add(rpt);
		}
		list = null;
		return _list;
	}

	public boolean timeOutResend(RptStatus rpt)
	{
		ConnectionService connection = new ConnectionService();
		ArrayList <String> _sql = new ArrayList <String>();
		try
		{
			connection.createConnection();
			int message_id = rpt.getMessageId();
			
			//更新messages的状态
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE messages SET status=");
			sql.append(Constants.MESSAGE_INIT_STATUS);
			sql.append(", send_counter=1, enable=0, is_resend=1 WHERE message_id=");
			sql.append(message_id);
			_sql.add(sql.toString());
			//更新rpt_status的状态
			sql = new StringBuffer();
			sql.append("UPDATE rpt_status SET get_rpt_counter=0, status=");
			sql.append(Constants.MESSAGE_INIT_STATUS );
			sql.append(", enable=0, is_resend=1 WHERE message_id=");
			sql.append(message_id);
			_sql.add(sql.toString());
			//插入重发表
			sql = new StringBuffer();
			sql.append("INSERT INTO resend_messages(old_messages_id, add_time, new_messages_id, resend_desc) VALUES('");
			sql.append(message_id);
			sql.append("','");
			sql.append(DateUtils.getNowTime());
			sql.append("','");
			sql.append(message_id);
			sql.append("','超过时，未收到回执，重发')");
			_sql.add(sql.toString());
			return this.batchExecute(_sql, connection.getConn());
		}
		catch(Exception e)
		{
			this.rollback(connection.getConn());
			return false;
		}
		finally
		{
			connection.close();
			_sql = null;
		}
	}
	
	public ArrayList<Messages> findMessagesByCondition(String condition)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("m.message_id, m.mobile, m.send_time, m.type, m.add_time, m.receive_time, m.is_test, m.status, m.send_counter, m.need_call_back, m.call_back_time, m.enable, m.is_resend, m.rpt_call_back_url, m.mo_call_back_url, m.come_from, m.interface_name, m.methed");
		sql.append(",mc.message_content");
		sql.append(" FROM messages ");
		sql.append(" m join messages_content ");
		sql.append(" mc");
		sql.append(" WHERE m.message_id=mc.message_id");
		sql.append(condition);

		List<Map<String, Object>> _list = this.select(sql.toString());
		ArrayList<Messages> list = new ArrayList<Messages>();
		for (Map<String, Object> map : _list)
		{
			Messages message = new Messages();
			message.setMessageId(Integer.parseInt(map.get("message_id").toString()));
			message.setAddTime(Long.parseLong(map.get("add_time").toString()));
			message.setCallBackTime(Long.parseLong(map.get("call_back_time").toString()));
			message.setComeFrom(map.get("come_from").toString());
			MessagesContent content = new MessagesContent();
			content.setMessageContent(map.get("message_content").toString());
			content.setMessageId(Integer.parseInt(map.get("message_id").toString()));
			message.setContent(content);
			message.setEnable(Integer.parseInt(map.get("enable").toString()));
			message.setInterfaceName(map.get("interface_name").toString());
			message.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
			message.setIsTest(Integer.parseInt(map.get("is_test").toString()));
			message.setMobile(map.get("mobile").toString());
			message.setMoCallBackUrl(map.get("mo_call_back_url").toString());
			message.setNeedCallBack(Integer.parseInt(map.get("need_call_back").toString()));
			message.setReceiveTime(Long.parseLong(map.get("receive_time").toString()));
			message.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
			message.setRptStatus(null);
			message.setSendCounter(Integer.parseInt(map.get("send_counter").toString()));
			message.setSendTime(Long.parseLong(map.get("send_time").toString()));
			message.setStatus(Integer.parseInt(map.get("status").toString()));
			message.setType(Integer.parseInt(map.get("type").toString()));
			list.add(message);
		}
		_list = null;
		return list;
	}

	public void sp1DoRpt(Object[] success, Object[] failure)
	{
		this.sp1DoRptSuccess(success);
		this.sp1DoRptFailure(failure);
	}
	
	private void sp1DoRptSuccess(Object[] success)
	{
		ArrayList<String> _sql = new ArrayList<String>();
		try
		{
			if(success==null || success.length <= 0) return;
			StringBuffer spMessageId = new StringBuffer();
			for(Object _s : success) 
			{
				spMessageId.append("'").append(_s.toString()).append("',");
			}
			String id = spMessageId.substring(0, spMessageId.toString().length()-1);
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT message_id, sp_message_id, access from sp_message_mapping WHERE sp_message_id IN (").append(id).append(")");
			List<Map<String, Object>> __list = this.select(sql.toString());
			
			StringBuffer messageIdString = new StringBuffer();
			for(Map<String, Object> map : __list)
			{
				messageIdString.append(this.ObjectToString(map, "message_id")).append(","); 
			}
			String resultString = messageIdString.substring(0, messageIdString.length()-1);
			sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("m.message_id, m.mobile, m.send_time, m.send_level, m.type, m.add_time, m.receive_time, m.is_test AS IS_T, m.status, m.send_counter, m.need_call_back, m.call_back_time, m.enable, m.is_resend, m.rpt_call_back_url, m.mo_call_back_url, m.come_from, m.interface_name, m.methed, m.oper_name, m.access");
			sql.append(", rs.read_time, rs.mt_id, rs.mo_id, rs.rpt_id,mp.sp_message_id ");
			sql.append(" FROM messages");
			sql.append(" m , rpt_status rs,sp_message_mapping mp");
			sql.append(" WHERE m.message_id=rs.message_id AND m.message_id=mp.message_id");
			sql.append(" AND m.message_id IN (").append(resultString).append(")"); 
				
			List<Map<String, Object>> list = this.select(sql.toString());
			if(list == null || list.size() <= 0) return ;
			RptStatus rptStatus = new RptStatus();
			Messages _message = new Messages();
			for (Map<String, Object> map : list)
			{
				_message.setMessageId(Integer.parseInt(map.get("message_id").toString()));
				_message.setMobile(map.get("mobile").toString());
				_message.setSendTime(Long.parseLong(map.get("send_time").toString()));
				_message.setSendLevel(Integer.parseInt(map.get("send_level").toString()));
				_message.setType(Integer.parseInt(map.get("type").toString()));
				_message.setAddTime(Long.parseLong(map.get("add_time").toString()));
				_message.setReceiveTime(Long.parseLong(map.get("receive_time").toString()));
				_message.setIsTest(Integer.parseInt(map.get("IS_T").toString()));
				_message.setStatus(Integer.parseInt(map.get("status").toString()));
				_message.setSendCounter(Integer.parseInt(map.get("send_counter").toString()));
				_message.setNeedCallBack(Integer.parseInt(map.get("need_call_back").toString()));
				_message.setCallBackTime(Long.parseLong(map.get("call_back_time").toString()));
				_message.setEnable(Integer.parseInt(map.get("enable").toString()));
				_message.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
				_message.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
				_message.setMoCallBackUrl(map.get("mo_call_back_url").toString());
				_message.setComeFrom(map.get("come_from").toString());
				_message.setInterfaceName(map.get("interface_name").toString());
				_message.setMethed(map.get("methed").toString());
				_message.setAccess(this.ObjectToString(map, "access"));
				_message.setOperName(this.ObjectToString(map, "oper_name")); 

				rptStatus.setMtId(Long.parseLong(map.get("mt_id").toString()));
				rptStatus.setMoId(Long.parseLong(map.get("mo_id").toString()));
				rptStatus.setRptId(Long.parseLong(map.get("rpt_id").toString()));
				rptStatus.setReadTime(Long.parseLong(map.get("read_time").toString()));
				_message.setRptStatus(rptStatus);
				
				list = null;
				
				sql = new StringBuffer();
				log.debug("开始插入log记录到mapping log", "MessagesDaoImpl");
				sql.append("INSERT INTO sp_message_mapping_log SELECT * FROM sp_message_mapping WHERE message_id = ").append(_message.getMessageId());
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("INSERT INTO messages_log (`message_id`, `mobile`, `send_time`, `type`, add_time, `status`, receive_time, mt_id, mo_id, rpt_id, is_test, is_call_back, need_call_back, call_back_time, is_resend, rpt_call_back_url, mo_call_back_url, come_from, interface_name, `methed`, `oper_name`, `access`) VALUES ( ");
				sql.append("'");
				sql.append(_message.getMessageId());
				sql.append("','");
				sql.append(_message.getMobile());
				sql.append("','");
				sql.append(_message.getSendTime());
				sql.append("','");
				sql.append(_message.getType());
				sql.append("','");
				sql.append(_message.getAddTime());
				sql.append("','");
				sql.append(1);
				sql.append("','");
				sql.append(DateUtils.getNowTime());
				sql.append("','");
				sql.append(rptStatus.getMtId());
				sql.append("','");
				sql.append(rptStatus.getMoId());
				sql.append("','");
				sql.append(rptStatus.getRptId());
				sql.append("','");
				sql.append(_message.getIsTest());
				sql.append("','");
				sql.append(0);
				sql.append("','");
				sql.append(_message.getNeedCallBack());
				sql.append("','");
				sql.append(_message.getCallBackTime());
				sql.append("','");
				sql.append(_message.getIsResend());
				sql.append("','");
				sql.append(_message.getRptCallBackUrl());
				sql.append("','");
				sql.append(_message.getMoCallBackUrl());
				sql.append("','");
				sql.append(_message.getComeFrom());
				sql.append("','");
				sql.append(_message.getInterfaceName());
				sql.append("','");
				sql.append(_message.getMethed());
				sql.append("','");
				sql.append(_message.getOperName());
				sql.append("','");
				sql.append(_message.getAccess());			
				sql.append("'");
				sql.append(")");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				log.debug("删除message表中的记录", "MessagesDaoImpl");
				sql.append("DELETE FROM messages WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("DELETE FROM rpt_status WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("INSERT INTO messages_content_log(message_id, message_content) SELECT message_id, message_content FROM messages_content WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("DELETE FROM messages_content WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
				
				sql = new StringBuffer();
				sql.append("DELETE FROM sp_message_mapping WHERE  message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
				
				if (_message.getRptCallBackUrl() != null && !"".equals(_message.getRptCallBackUrl()))
				{
					HttpURLConnectionRequest http = new HttpURLConnectionRequest();
					String sunCallBackXml = this.buildXmlToSun309(map.get("sp_message_id").toString(), _message.getMessageId().intValue());
					String postUrl = _message.getRptCallBackUrl();
					Hashtable<String, String> params = new Hashtable<String, String>();
					params.put("xml", sunCallBackXml);
					http.doSendPostRequest(postUrl, params);
					log.debug(new StringBuffer("开始回调，地址url=").append(postUrl).append(";http状态:").append(http.getHttpStatus()).toString(), _message.getMobile());
					if (http.getHttpStatus() != 200)
					{
						http.doSendPostRequest(postUrl, params);
					}
				}
			}
				
			this.batchExecute(_sql);
		}
		catch (Exception e)
		{
			log.debug("sp1DoRptSuccess" + e.getMessage());
		}
	}
	
	
	/****  组建回调数据   ***/
	private String buildXmlToSun309(String psMessageId, int messageId)
	{
		Common c = new Common();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		StringBuffer _result = new StringBuffer();
		_result.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		_result.append("<root><message>");
		_result.append("<message_id>" ).append( messageId ).append( "</message_id>");
		_result.append("<rpt_time>" ).append( c.stringDataToLongTime(date.toString()) ).append( "</rpt_time>");
		_result.append("<rpt_desc>" ).append("发送成功").append( "</rpt_desc>");
		_result.append("<rpt_code>" ).append(psMessageId).append( "</rpt_code>");
		_result.append("<get_rpt_time>" ).append( c.stringDataToLongTime(date.toString()) ).append( "</get_rpt_time>");
		_result.append("</message></root>");
		return _result.toString();
	}
	
	private void sp1DoRptFailure(Object[] failure)
	{
		ArrayList<String> _sql = new ArrayList<String>();
		try
		{
			if(failure==null || failure.length <= 0) return;
			StringBuffer spMessageId = new StringBuffer();
			for(Object _s : failure) 
			{
				spMessageId.append("'").append(_s.toString()).append("',");
			}
			String id = spMessageId.substring(0, spMessageId.toString().length()-1);
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT message_id, sp_message_id, access from sp_message_mapping WHERE sp_message_id IN (").append(id).append(")");
			List<Map<String, Object>> __list = this.select(sql.toString());
			
			StringBuffer messageIdString = new StringBuffer();
			for(Map<String, Object> map : __list)
			{
				messageIdString.append(this.ObjectToString(map, "message_id")).append(","); 
			}
			String strResult = messageIdString.substring(0, messageIdString.length()-1);
			sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("m.message_id, m.mobile, m.send_time, m.send_level, m.type, m.add_time, m.receive_time, m.is_test AS IS_T, m.status, m.send_counter, m.need_call_back, m.call_back_time, m.enable, m.is_resend, m.rpt_call_back_url, m.mo_call_back_url, m.come_from, m.interface_name, m.methed, m.oper_name, m.access");
			sql.append(", rs.read_time, rs.mt_id, rs.mo_id, rs.rpt_id ");
			sql.append(" FROM messages");
			sql.append(" m JOIN ");
			sql.append(" rpt_status rs");
			sql.append(" WHERE m.message_id=rs.message_id");
			sql.append(" AND m.message_id in(").append(messageIdString).append(")"); 
				
			List<Map<String, Object>> list = this.select(sql.toString());
			if(list == null || list.size() <= 0) return ;
			RptStatus rptStatus = new RptStatus();
			Messages _message = new Messages();
			for (Map<String, Object> map : list)
			{
				_message.setMessageId(Integer.parseInt(map.get("message_id").toString()));
				_message.setMobile(map.get("mobile").toString());
				_message.setSendTime(Long.parseLong(map.get("send_time").toString()));
				_message.setSendLevel(Integer.parseInt(map.get("send_level").toString()));
				_message.setType(Integer.parseInt(map.get("type").toString()));
				_message.setAddTime(Long.parseLong(map.get("add_time").toString()));
				_message.setReceiveTime(Long.parseLong(map.get("receive_time").toString()));
				_message.setIsTest(Integer.parseInt(map.get("IS_T").toString()));
				_message.setStatus(Integer.parseInt(map.get("status").toString()));
				_message.setSendCounter(Integer.parseInt(map.get("send_counter").toString()));
				_message.setNeedCallBack(Integer.parseInt(map.get("need_call_back").toString()));
				_message.setCallBackTime(Long.parseLong(map.get("call_back_time").toString()));
				_message.setEnable(Integer.parseInt(map.get("enable").toString()));
				_message.setIsResend(Integer.parseInt(map.get("is_resend").toString()));
				_message.setRptCallBackUrl(map.get("rpt_call_back_url").toString());
				_message.setMoCallBackUrl(map.get("mo_call_back_url").toString());
				_message.setComeFrom(map.get("come_from").toString());
				_message.setInterfaceName(map.get("interface_name").toString());
				_message.setMethed(map.get("methed").toString());
				_message.setAccess(this.ObjectToString(map, "access"));
				_message.setOperName(this.ObjectToString(map, "oper_name")); 

				rptStatus.setMtId(Long.parseLong(map.get("mt_id").toString()));
				rptStatus.setMoId(Long.parseLong(map.get("mo_id").toString()));
				rptStatus.setRptId(Long.parseLong(map.get("rpt_id").toString()));
				rptStatus.setReadTime(Long.parseLong(map.get("read_time").toString()));
				_message.setRptStatus(rptStatus);
				
				sql = new StringBuffer();
				sql.append("INSERT INTO sp_message_mapping_log SELECT * FROM sp_message_mapping WHERE message_id = ").append(_message.getMessageId());
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("INSERT INTO messages_log (`message_id`, `mobile`, `send_time`, `type`, add_time, `status`, receive_time, mt_id, mo_id, rpt_id, is_test, is_call_back, need_call_back, call_back_time, is_resend, rpt_call_back_url, mo_call_back_url, come_from, interface_name, `methed`, `oper_name`, `access`) VALUES ( ");
				sql.append("'");
				sql.append(_message.getMessageId());
				sql.append("','");
				sql.append(_message.getMobile());
				sql.append("','");
				sql.append(_message.getSendTime());
				sql.append("','");
				sql.append(_message.getType());
				sql.append("','");
				sql.append(_message.getAddTime());
				sql.append("','");
				sql.append(0);
				sql.append("','");
				sql.append(DateUtils.getNowTime());
				sql.append("','");
				sql.append(rptStatus.getMtId());
				sql.append("','");
				sql.append(rptStatus.getMoId());
				sql.append("','");
				sql.append(rptStatus.getRptId());
				sql.append("','");
				sql.append(_message.getIsTest());
				sql.append("','");
				sql.append(0);
				sql.append("','");
				sql.append(_message.getNeedCallBack());
				sql.append("','");
				sql.append(_message.getCallBackTime());
				sql.append("','");
				sql.append(_message.getIsResend());
				sql.append("','");
				sql.append(_message.getRptCallBackUrl());
				sql.append("','");
				sql.append(_message.getMoCallBackUrl());
				sql.append("','");
				sql.append(_message.getComeFrom());
				sql.append("','");
				sql.append(_message.getInterfaceName());
				sql.append("','");
				sql.append(_message.getMethed());
				sql.append("','");
				sql.append(_message.getOperName());
				sql.append("','");
				sql.append(_message.getAccess());			
				sql.append("'");
				sql.append(")");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("DELETE FROM messages WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("DELETE FROM rpt_status WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("INSERT INTO messages_content_log(message_id, message_content) SELECT message_id, message_content FROM messages_content WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
					
				sql = new StringBuffer();
				sql.append("DELETE FROM messages_content WHERE message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
				
				sql = new StringBuffer();
				sql.append("DELETE FROM sp_message_mapping WHERE  message_id='").append(_message.getMessageId()).append("'");
				_sql.add(sql.toString());
			}
				
			this.batchExecute(_sql);
		}
		catch (Exception e)
		{
			log.debug("sp1DoRptFailure" + e.getMessage());
		}
	}
	
	
}
