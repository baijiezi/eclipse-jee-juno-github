package com.sun309.gateway.thread;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sun309.gateway.dao.MasRptDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dao.SmsLogDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.SmsLog;
import com.sun309.gateway.dto.mas.MasRpt;
import com.sun309.gateway.factory.MasRptDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.factory.SmsLogDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.Constants;
import com.sun309.gateway.util.HttpURLConnectionRequest;

/**
 * 获取业务服务器上面的回执信息
 * 
 * @author caiyuerui
 * 
 */
public class GetRptDataThread extends Thread
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, GetRptDataThread.class);

	public GetRptDataThread()
	{
		super("GetRptDataThread");
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(Constants.GET_RPT_DATA_THREAD_SLEEP_TIME);
				MasRptDao masRptDao = MasRptDaoFactory.create();
				ArrayList<MasRpt> list = masRptDao.findRptByCondition("", "gateway");
				
				if (list != null && list.size() > 0)
				{
					MessagesDao messagesDao = MessagesDaoFactory.create();
					for (MasRpt masRpt : list)
					{
						// 发送成功，移入备份表
						Messages _message = new Messages();
						boolean result = messagesDao.doRpt(masRpt.getSmId().toString(), masRpt.getRptCode().toString(), masRpt.getRptTime().toString(), _message);
						log.debug(new StringBuffer(masRpt.getMobile()).append("1、将messsages插入messages_log 2、删除messsages 3、删除rpt_status，结果：").append(result).toString(), masRpt.getMobile());
						
						if(!result)
						{
							log.debug(new StringBuffer("移入备份表, 删除messsages, 删除rpt_status，结果").append(result).toString()); 
						}
						
						// 删除MAS机上的记录
						boolean deleteRptResult = masRptDao.deleteRpt(masRpt.getSmId().toString(), "gateway");
						log.debug(new StringBuffer("删除MAS机上的回执记录deleteRptResult=").append(deleteRptResult).toString(), masRpt.getMobile());

						// 回调
						if (result)
						{
							if (_message.getRptCallBackUrl() != null && !"".equals(_message.getRptCallBackUrl()))
							{
								HttpURLConnectionRequest http = new HttpURLConnectionRequest();
								String sunCallBackXml = this.buildXmlToSun309(masRpt, _message.getMessageId().intValue());
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
					}
					log.write();
				}
			}
			catch (Exception e)
			{
			}
		}
	}

	private String buildXmlToSun309(MasRpt masRpt, int messageId)
	{
		Common c = new Common();
		StringBuffer _result = new StringBuffer();
		_result.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		_result.append("<root><message>");
		_result.append("<message_id>" ).append( messageId ).append( "</message_id>");
		_result.append("<rpt_time>" ).append( c.stringDataToLongTime(masRpt.getRptTime().toString()) ).append( "</rpt_time>");
		_result.append("<rpt_desc>" ).append( masRpt.getRptDesc() ).append( "</rpt_desc>");
		_result.append("<rpt_code>" ).append( masRpt.getRptCode() ).append( "</rpt_code>");
		_result.append("<get_rpt_time>" ).append( c.stringDataToLongTime(masRpt.getRptTime().toString()) ).append( "</get_rpt_time>");
		_result.append("</message></root>");

		SmsLog smsLog = new SmsLog();
		smsLog.setIp("");
		smsLog.setMessageId(messageId);
		smsLog.setType("rpt");
		smsLog.setXml(_result.toString());

		SmsLogDao smsLogDao = SmsLogDaoFactory.create();
		smsLogDao.insert(smsLog);

		return _result.toString();
	}
}
