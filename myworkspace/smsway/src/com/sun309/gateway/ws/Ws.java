package com.sun309.gateway.ws;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.sun309.gateway.dao.BlackListDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dao.OperatorDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.TranData;
import com.sun309.gateway.factory.BlackListDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.factory.OperatorDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Common;
import com.sun309.gateway.util.Xml;

public class Ws implements WsService
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, Ws.class);
	private TranData tranData;
	
	public String delete(String input)
	{
		Xml xml = new Xml();
		log.debug(input, "DELETE");
		TranData tranData = setTranData(input);
		String messageId = tranData.getMessageId();
		String mobile = tranData.getMobile();
		try
		{
			if (messageId == null || new Integer(messageId).intValue() <= 0)
			{
				log.debug("701 -- 短信ID不能为空", "DELETE");
				return xml.createSimpleXml("1", "短信ID不能为空[701]");
			}
			if (mobile == null || StringUtils.isEmpty(mobile))
			{
				log.debug("702 -- 手机号为空不能为空", "DELETE");
				return xml.createSimpleXml("1", "手机号为空不能为空[702]");
			}
			MessagesDao messagesDaoService = MessagesDaoFactory.create();
			ArrayList<Messages> list = messagesDaoService.findMessagesByCondition(new StringBuffer(" AND m.message_id=").append(messageId).toString());
			if (list == null || list.size() <= 0)
			{
				log.debug("703 -- 短信记录不存在", "DELETE");
				return xml.createSimpleXml("1", "短信记录不存在[703]");
			}
			Messages message = (Messages) list.get(0);
			if (!mobile.equals(message.getMobile()))
			{
				log.debug("704 -- 对不起，该短信的手机号与你传入的手机号不匹配", "DELETE");
				return xml.createSimpleXml("1", "对不起，该短信的手机号与你传入的手机号不匹配[704]");
			}
			boolean result = messagesDaoService.delete(new Integer(messageId).intValue());
			if (result)
			{
				log.debug("000 -- 删除成功", "DELETE");
				return xml.createSimpleXml("1", "删除成功[000]");
			}
			else
			{
				log.debug("999 -- 对不起，删除失败", "DELETE");
				return xml.createSimpleXml("1", "对不起，删除失败[999]");
			}
		}
		catch (Exception e)
		{
			log.debug("999 -- 对不起，删除失败", "DELETE");
			return xml.createSimpleXml("1", "对不起，删除失败[999]");
		}
		finally
		{
			log.write();
		}
	}

	public String send(String input)
	{
		Xml xml = new Xml();
		log.debug(input, "SEND");
		TranData tranData = setTranData(input);

		Common c = new Common();
		try
		{
			String operName = tranData.getOperName();
			log.debug("开始校验数据[operName]", "MessagesAction");
			if(operName == null || StringUtils.isEmpty(operName))
			{
				log.debug("709 - 操作员不能为空", "MessagesAction");
				return xml.createSimpleXml("1", "操作员不能为空[709]");
			}
			
			OperatorDao o = OperatorDaoFactory.create();
			if(!o.login(operName))
			{
				log.debug("710 - 操作员验证失败", "MessagesAction");
				return xml.createSimpleXml("1", "操作员验证失败[710]");
			}
			
			if (StringUtils.isEmpty(tranData.getMobile()))
			{
				log.debug("701 - 手机号为空", "MessagesAction");
				return xml.createSimpleXml("1", "手机号为空[701]");
			}else{  //手机号码格式验证
				Pattern pattern = Pattern.compile("^1\\d{10}$");
				Matcher matcher = pattern.matcher(tranData.getMobile());
				if(!matcher.find()){
					log.debug("700 - 手机号不是11位有效数字", "MessagesAction");
					return xml.createSimpleXml("1", "手机号不是11位有效数字[700]");
				}
			}
			BlackListDao bl = BlackListDaoFactory.create();
			boolean exist = bl.checkMobileIsBlackList(tranData.getMobile());
			if(exist)
			{
				log.debug("708 - 手机号列入黑名单", "MessagesAction");
				return xml.createSimpleXml("1", "手机号列入黑名单[708]");
			}
			if (StringUtils.isEmpty(tranData.getRptCallBackUrl()))
			{
				log.debug("回执地址为空[702]", "MessagesAction");
				return xml.createSimpleXml("1", "回执地址为空[702]");
			}
			if (StringUtils.isEmpty(tranData.getMessageContent()))
			{
				log.debug("内容为空[704]", "MessagesAction");
				return xml.createSimpleXml("1", "内容为空[704]");
			}
			if (StringUtils.isEmpty(tranData.getInterfaceName()))
			{
				log.debug("发送接口为空[705]", "MessagesAction");
				return xml.createSimpleXml("1", "发送接口为空[705]");
			}
			if (new Integer(tranData.getSendLevel()).intValue() == 1 || new Integer(tranData.getSendLevel()).intValue() == 0)
			{
				if (StringUtils.isEmpty(tranData.getSendTime()))
				{
					log.debug("发送时间为空[707]", "MessagesAction");
					return xml.createSimpleXml("1", "发送时间为空[707]");
				}
			}

			Messages message = new Messages();
			message.setCallBackTime(c.stringDataToLongTime(tranData.getCallBackTime()));
			message.setComeFrom(tranData.getComeFrom());
			message.setInterfaceName(tranData.getInterfaceName());
			message.setIsTest(new Integer(tranData.getIsTest()));
			message.setMobile(tranData.getMobile());
			message.setMoCallBackUrl(tranData.getMoCallBackUrl());
			message.setNeedCallBack(new Integer(tranData.getNeedCallBack()));
			message.setRptCallBackUrl(tranData.getRptCallBackUrl());
			message.setSendLevel(new Integer(tranData.getSendLevel()));
			message.setSendTime(new Integer(tranData.getSendLevel()).intValue() == 2 ? Common.getNowTime() : c.stringDataToLongTime(tranData.getSendTime()));
			message.setType(0);
			message.setMethed("webservice");
			message.setOperName(operName);

			MessagesContent messagesContent = new MessagesContent();
			messagesContent.setMessageContent(tranData.getMessageContent());
			message.setContent(messagesContent);

			MessagesDao messagesDao = MessagesDaoFactory.create();
			int messageId = messagesDao.insert(message);
			StringBuffer res = new StringBuffer();
			if(messageId == -1){
				log.debug("数据提交数据库异常[711]", "MessagesAction");
				return xml.createSimpleXml("1", "数据提交数据库异常![711]");
			}
			message.setMessageId(messageId);

			res.append("<root>");
			res.append("<error>0</error>");
			res.append("<message>成功发送短信网关</message>");
			res.append("<mobile>" ).append( message.getMobile() ).append( "</mobile>");
			res.append("<message_id>" ).append( message.getMessageId() ).append( "</message_id>");
			res.append("</root>");
			return res.toString();
		}
		catch (Exception e)
		{
			log.debug("发送短信，短信网关异常:" + e, "MessagesAction");
			return xml.createSimpleXml("1", "发送短信，短信网关异常:" + e);
		}
		finally
		{
			log.write();
		}
	}

	public String simpleSend(String input)
	{
		return null;
	}

	private TranData setTranData(String input)
	{
		tranData = new TranData(input);
		return tranData;
	}
	
	public String sysTest(String name,String age){
		return name+"您好你的年龄是"+age;
	}
}
