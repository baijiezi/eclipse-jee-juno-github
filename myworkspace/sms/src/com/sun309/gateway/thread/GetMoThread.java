package com.sun309.gateway.thread;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sun309.gateway.dao.MasMoDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dao.MoDao;
import com.sun309.gateway.dao.SmsLogDao;
import com.sun309.gateway.dto.MessagesLog;
import com.sun309.gateway.dto.Mo;
import com.sun309.gateway.dto.SmsLog;
import com.sun309.gateway.dto.mas.MasMo;
import com.sun309.gateway.factory.MasMoDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.factory.MoDaoFactory;
import com.sun309.gateway.factory.SmsLogDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.Constants;
import com.sun309.gateway.util.DateUtils;
import com.sun309.gateway.util.HttpURLConnectionRequest;

public class GetMoThread extends Thread
{

	private LogService log = LogFactory.create(LogFactory.SEND_DATA, GetMoThread.class);

	public GetMoThread()
	{
		super("GetMoThread");
		log.debug("start GetMoThread");
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(Constants.GET_MO_DATA_THREA_SLEEP_TIME);
				this.sendMo();
			}
			catch (Exception e)
			{
				log.debug(e);
			}
		}
	}
	
	private void sendMo()
	{
		// 从接口中,获取回复
		// 移动回复
		// mas机上的回复
		MasMoDao masMoDao = MasMoDaoFactory.create();
		ArrayList<MasMo> list = masMoDao.findMoByCondition(" SM_ID<>0 ORDER BY MO_TIME", "gateway");

		if (list != null && list.size() > 0)
		{
			MessagesDao messagesDao = MessagesDaoFactory.create();
			MoDao moDao = MoDaoFactory.create();

			for (MasMo masMo : list)
			{
				log.debug("开始从MAS机获取所有的移动回复记录", "GetMoThread");
				log.debug(new StringBuffer(masMo.getMobile()).append("回复的内容： ").append(masMo.getContent()).toString(), masMo.getMobile());
				// 获取MAS机的回复，插入SMS中的MO表，删除MAS机记录
				Common c = new Common();
				// 从备份表中查询短信
				ArrayList<MessagesLog> mlList = messagesDao.findMessagesLogByCondition(new StringBuffer("ml.mo_id=").append(masMo.getSmId()).toString());
				if (mlList == null || mlList.size() <= 0)
				{
					log.debug(new StringBuffer("找不到原短信记录，删除MAS机上该回复记录masMo.getSmId()=" ).append( masMo.getSmId()).toString(), masMo.getMobile());
					boolean deleteMoResult = masMoDao.delete(masMo.getSmId().toString(), "gateway");
					log.debug(new StringBuffer("删除结果：").append( deleteMoResult ).toString(), masMo.getMobile());
				}
				else
				{
					MessagesLog ml = (MessagesLog) mlList.get(0);

					// 操作sms中的回复
					Mo mo = new Mo();
					mo.setContent(masMo.getContent());
					mo.setMoTime(c.stringDataToLongTime(masMo.getMoTime().toString()));
					mo.setMsgFmt(masMo.getMsgFmt().intValue());
					mo.setSmId(masMo.getSmId());
					mo.setInterfaceName("gateway");
					mo.setMessageId(ml.getMessageId());
					mo.setAddTime(DateUtils.getNowTime());
					mo.setMobile(ml.getMobile());
					mo.setEnable(0);
					mo.setInterfaceName(ml.getInterfaceName());
					mo.setSendTime(ml.getSendTime() == null ? 0 : ml.getSendTime());
					mo.setCallBackTime(ml.getCallBackTime() == null ? 0 : ml.getCallBackTime());
					boolean insertMoResult = moDao.insert(mo, "gateway");
					log.debug(new StringBuffer("备份回复的记录，备份结果：insertMoResult = ").append(insertMoResult).toString(), masMo.getMobile());

					// 删除mas中的回复
					log.debug(new StringBuffer("将要删除的MAS回复记录ID=" ).append( masMo.getSmId().longValue()).toString(), masMo.getMobile());
					String AUTOSNString = new String();
					if (masMo != null && masMo.getSmId() != null && masMo.getSmId().longValue() > 0)
					{
						boolean deleteMoResult = masMoDao.delete(masMo.getSmId().toString(), "gateway");
						log.debug(new StringBuffer("删除MAS机的回复记录，deleteMoResult = " ).append( deleteMoResult ).append( "; AUTOSNString = " ).append( AUTOSNString).toString(), masMo.getMobile());
					}
					// 回调URL
					if (ml.getMoCallBackUrl() != null && !"".equals(ml.getMoCallBackUrl()))
					{
						HttpURLConnectionRequest http = new HttpURLConnectionRequest();
						String sunCallBackXml = this.buildXmlToSun309(mo);
						String postUrl = ml.getMoCallBackUrl();
						Hashtable<String, String> params = new Hashtable<String, String>();
						params.put("xml", sunCallBackXml);
						http.doSendPostRequest(postUrl, params);
						log.debug(new StringBuffer(masMo.getMobile()).append("回复回调的地址：url = ").append(postUrl).append(" http状态:").append(http.getHttpStatus()).toString(), masMo.getMobile());
						if (http.getHttpStatus() != 200)
						{
							http.doSendPostRequest(postUrl, params);
						}
					}
					mo=null;
					ml=null;
				}
				mlList=null;
			}
			log.write();
		}
		list=null;
	}

	private String buildXmlToSun309(Mo mo)
	{
		StringBuffer _result = new StringBuffer();
		_result.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		_result.append("<root><message>");
		_result.append("<message_id>" ).append( mo.getMessageId().intValue() ).append( "</message_id>");
		_result.append("<mo_time>" ).append( mo.getMoTime() ).append( "</mo_time>");
		_result.append("<content>" ).append( mo.getContent() ).append( "</content>");
		_result.append("<mobile>" ).append( mo.getMobile() ).append( "</mobile>");
		_result.append("<call_back_time>" ).append( mo.getCallBackTime().toString() ).append( "</call_back_time>");
		_result.append("<is_overtime>0</is_overtime>");
		_result.append("</message></root>");

		SmsLog smsLog = new SmsLog();
		smsLog.setIp("");
		smsLog.setMessageId(mo.getMessageId().intValue());
		smsLog.setType("mo");
		smsLog.setXml(_result.toString());

		SmsLogDao smsLogDao = SmsLogDaoFactory.create();
		smsLogDao.insert(smsLog);

		return _result.toString();
	}
}
