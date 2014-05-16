package com.sun309.gateway.dto;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TranData
{
	private Logger log = Logger.getLogger(TranData.class);
	
	private String mobile;
	private String sendTime;
	private String sendLevel;
	private String isTest;
	private String needCallBack;
	private String callBackTime;
	private String rptCallBackUrl;
	private String moCallBackUrl;
	private String comeFrom;
	private String interfaceName;
	private String messageContent;
	private String messageId;
	private String operName;
	
	public TranData() {}
	public TranData(String input) 
	{ 
		try
		{
			Document doc = DocumentHelper.parseText(input);
			Element root = doc.getRootElement();
			List node = root.elements();
			for(int i = 0; i < node.size(); i++)
			{
				Element ns = (Element) node.get(i);
				List nodeList = ns.elements();
				for(int j = 0; j < nodeList.size(); j++)
				{
					Element element = (Element) nodeList.get(j);
					if ("mobile".equals(element.getName())) 	try{this.setMobile(element.getText());}catch(Exception e){this.setMobile(""); log.debug("setMobile -- " + e); };
					if ("send_time".equals(element.getName())) 	try{this.setSendTime(element.getText());}catch(Exception e){this.setSendTime(""); log.debug("setSendTime -- " + e); };
					if ("send_level".equals(element.getName())) 	try{this.setSendLevel(element.getText());}catch(Exception e){this.setSendLevel(""); log.debug("setSendLevel -- " + e); };
					if ("is_test".equals(element.getName())) 	try{this.setIsTest(element.getText());}catch(Exception e){this.setIsTest(""); log.debug("setIsTest -- " + e); };
					if ("need_call_back".equals(element.getName())) 	try{this.setNeedCallBack(element.getText());}catch(Exception e){this.setNeedCallBack(""); log.debug("setNeedCallBack -- " + e); };
					if ("call_back_time".equals(element.getName())) 	try{this.setCallBackTime(element.getText());}catch(Exception e){this.setCallBackTime(""); log.debug("setCallBackTime -- " + e); };
					if ("rpt_call_back_url".equals(element.getName())) try{this.setRptCallBackUrl(element.getText());}catch(Exception e){this.setRptCallBackUrl(""); log.debug("setRptCallBackUrl -- " + e); };
					if ("mo_call_back_url".equals(element.getName())) 	try{this.setMoCallBackUrl(element.getText());}catch(Exception e){this.setMoCallBackUrl(""); log.debug("setMoCallBackUrl -- " + e); };
					if ("come_from".equals(element.getName())) 		try{this.setComeFrom(element.getText());}catch(Exception e){this.setComeFrom(""); log.debug("setComeFrom -- " + e); };
					if ("interface_name".equals(element.getName())) 	try{this.setInterfaceName(element.getText());}catch(Exception e){this.setInterfaceName(""); log.debug("setInterfaceName -- " + e); };
					if ("message_content".equals(element.getName())) try{this.setMessageContent(element.getText());}catch(Exception e){this.setMessageContent(""); log.debug("setMessageContent -- " + e); };
					if ("message_id".equals(element.getName())) 		try{this.setMessageId(element.getText());}catch(Exception e){this.setMessageId(""); log.debug("setMessageId -- " + e); };
					if ("oper_name".equals(element.getName())) 		try{this.setOperName(element.getText());}catch(Exception e){this.setOperName(""); log.debug("setOperName -- " + e); };
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getSendTime()
	{
		return sendTime;
	}
	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
	}
	public String getSendLevel()
	{
		return sendLevel;
	}
	public void setSendLevel(String sendLevel)
	{
		this.sendLevel = sendLevel;
	}
	public String getIsTest()
	{
		return isTest;
	}
	public void setIsTest(String isTest)
	{
		this.isTest = isTest;
	}
	public String getNeedCallBack()
	{
		return needCallBack;
	}
	public void setNeedCallBack(String needCallBack)
	{
		this.needCallBack = needCallBack;
	}
	public String getCallBackTime()
	{
		return callBackTime;
	}
	public void setCallBackTime(String callBackTime)
	{
		this.callBackTime = callBackTime;
	}
	public String getRptCallBackUrl()
	{
		return rptCallBackUrl;
	}
	public void setRptCallBackUrl(String rptCallBackUrl)
	{
		this.rptCallBackUrl = rptCallBackUrl;
	}
	public String getMoCallBackUrl()
	{
		return moCallBackUrl;
	}
	public void setMoCallBackUrl(String moCallBackUrl)
	{
		this.moCallBackUrl = moCallBackUrl;
	}
	public String getComeFrom()
	{
		return comeFrom;
	}
	public void setComeFrom(String comeFrom)
	{
		this.comeFrom = comeFrom;
	}
	public String getInterfaceName()
	{
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}
	public String getMessageContent()
	{
		return messageContent;
	}
	public void setMessageContent(String messageContent)
	{
		this.messageContent = messageContent;
	}
	public String getMessageId()
	{
		return messageId;
	}
	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}
	public String getOperName()
	{
		return operName;
	}
	public void setOperName(String operName)
	{
		this.operName = operName;
	}
}
