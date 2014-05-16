package com.sun309.gateway.action;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun309.gateway.dao.BlackListDao;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dao.OperatorDao;
import com.sun309.gateway.dao.SmsLogDao;
import com.sun309.gateway.dto.Messages;
import com.sun309.gateway.dto.MessagesContent;
import com.sun309.gateway.dto.SmsLog;
import com.sun309.gateway.factory.BlackListDaoFactory;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.factory.OperatorDaoFactory;
import com.sun309.gateway.factory.SmsLogDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Common;

/**
 * xml格式:
 * 
 * 接收XML <root> <message> <mobile>发送手机号码</mobile> <send_time>发送时间(0000-00-00
 * 00:00:00)</send_time> <send_level>发送紧急急别</send_level>
 * <is_test>是否测试数据</is_test> <need_call_back>是否需要回复</need_call_back>
 * <call_back_time>要求回复时间(0000-00-00 00:00:00)</call_back_time>
 * <rpt_call_back_url>回执返回地址url</rpt_call_back_url>
 * <mo_call_back_url>回复返回地址url</mo_call_back_url>
 * <come_from>来自（总平台预约挂号、总平台取消预约挂号、其他 ...）</come_from>
 * <interface_name>接口名称</interface_name> <message_content>短信内容</message_content>
 * </message> </root>
 * 
 * 响应XML <root> <message> <mobile>手机号码</mobile> <message_id>短信ID</message_id>
 * </message> <message> <mobile>手机号码</mobile> <message_id>短信ID</message_id>
 * </message> <message> <mobile>手机号码</mobile> <message_id>短信ID</message_id>
 * </message> </root>
 * 
 * @author caiyuerui
 * 
 */
public class MessagesAction extends Action
{
	private LogService dlog = LogFactory.create(LogFactory.SEND_DATA, MessagesAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		StringBuffer res = new StringBuffer();
		Common c = new Common();
		String data = request.getParameter("xml");
		try
		{
			dlog.debug(data, "MessagesAction");
			if (data == null || StringUtils.isEmpty(data))
			{
				try
				{
					PrintWriter out = response.getWriter();
					dlog.debug("700 - xml格式出错", "MessagesAction");
					out.print("700");
				}
				catch (Exception e)
				{
				}
				return null;
			}
			Document doc = c.string2Document(data);
			if (doc == null)
			{
				try
				{
					PrintWriter out = response.getWriter();
					dlog.debug("700 - xml格式出错", "MessagesAction");
					out.print("700");
				}
				catch (Exception e)
				{
				}
				return null;
			}
			NodeList messageNoteList = doc.getElementsByTagName("message");
			boolean flag = false;
			for (int j = 0; j < messageNoteList.getLength(); j++)
			{
				Node messageNode = messageNoteList.item(j);
				if (messageNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element messageElement = (Element) messageNode;
					if (messageElement != null)
					{
						String mobile = new String();
						String sendTime = new String();
						String sendLevel = new String();
						String isTest = new String();
						String needCallBack = new String();
						String callBackTime = new String();
						String rptCallBackUrl = new String();
						String moCallBackUrl = new String();
						String comeFrom = new String();
						String interfaceName = new String();
						String messageContent = new String();
						String operName = new String();

						if (messageElement.getElementsByTagName("mobile").item(0).getFirstChild() != null)
							mobile = messageElement.getElementsByTagName("mobile").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("send_time").item(0).getFirstChild() != null)
							sendTime = messageElement.getElementsByTagName("send_time").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("send_level").item(0).getFirstChild() != null)
							sendLevel = messageElement.getElementsByTagName("send_level").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("is_test").item(0).getFirstChild() != null)
							isTest = messageElement.getElementsByTagName("is_test").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("need_call_back").item(0).getFirstChild() != null)
							needCallBack = messageElement.getElementsByTagName("need_call_back").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("call_back_time").item(0).getFirstChild() != null)
							callBackTime = messageElement.getElementsByTagName("call_back_time").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("rpt_call_back_url").item(0).getFirstChild() != null)
							rptCallBackUrl = messageElement.getElementsByTagName("rpt_call_back_url").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("mo_call_back_url").item(0).getFirstChild() != null)
							moCallBackUrl = messageElement.getElementsByTagName("mo_call_back_url").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("come_from").item(0).getFirstChild() != null)
							comeFrom = messageElement.getElementsByTagName("come_from").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("interface_name").item(0).getFirstChild() != null)
							interfaceName = messageElement.getElementsByTagName("interface_name").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("message_content").item(0).getFirstChild() != null)
							messageContent = messageElement.getElementsByTagName("message_content").item(0).getFirstChild().getNodeValue();
						if (messageElement.getElementsByTagName("oper_name").item(0).getFirstChild() != null)
							operName = messageElement.getElementsByTagName("oper_name").item(0).getFirstChild().getNodeValue();
						PrintWriter out = response.getWriter();
						try
						{
							
							
							dlog.debug("开始校验数据[operName]", "MessagesAction");
							
							if(operName == null || StringUtils.isEmpty(operName))
							{
								dlog.debug("709 - 操作员不能为空", "MessagesAction");
								out.print("709");
								return null;
							}
							
							OperatorDao o = OperatorDaoFactory.create();
							if(!o.login(operName))
							{
								dlog.debug("710 - 操作员验证失败", "MessagesAction");
								out.print("710");
								return null;
							}
							
							if (StringUtils.isEmpty(mobile))
							{
								dlog.debug("701 - 手机号为空", "MessagesAction");
								out.print("701"); // 手机号为空
								return null;
							}else{  //手机号码正则表达式验证号码格式
								Pattern pattern = Pattern.compile("^1\\d{10}$");
								Matcher matcher = pattern.matcher(mobile);
								if(!matcher.find()){ 
									dlog.debug("700 - 手机号不是11位有效数字", "MessagesAction");
									out.print("700"); // 手机号为空
									return null;
								}
							}
							
							BlackListDao bl = BlackListDaoFactory.create();
							boolean exist = bl.checkMobileIsBlackList(mobile);
							if(exist)
							{
								dlog.debug("708 - 手机号列入黑名单", "MessagesAction");
								out.print("708"); // 手机号为空
								return null;
							}
							
							if (StringUtils.isEmpty(rptCallBackUrl))
							{
								dlog.debug("702 - 回执地址为空", "MessagesAction");
								out.print("702"); // 回执地址为空
								return null;
							}
							if ("1".equals(needCallBack))
							{
								if (StringUtils.isEmpty(moCallBackUrl))
								{
									dlog.debug("703 - 回复地址为空", "MessagesAction");
									out.print("703"); // 回复地址为空
									return null;
								}
								if (c.stringDataToLongTime(callBackTime) < (Common.getNowTime() + 1000 * 1 * 60 * 60))
								{
									dlog.debug("706 - 回复时间必须大于现在时间加上１小时间", "MessagesAction");
									out.print("706"); // 回复时间必须大于现在时间加上１小时间
									return null;
								}
							}
							if (StringUtils.isEmpty(messageContent))
							{
								dlog.debug("704 - 内容为空", "MessagesAction");
								out.print("704"); // 内容为空
								return null;
							}
							if (StringUtils.isEmpty(interfaceName))
							{
								dlog.debug("705 - 发送接口为空", "MessagesAction");
								out.print("705"); // 发送接口为空
								return null;
							}
							if (new Integer(sendLevel).intValue() == 1 || new Integer(sendLevel).intValue() == 0)
							{
								if (StringUtils.isEmpty(sendTime))
								{
									dlog.debug("707 - 发送时间为空", "MessagesAction");
									out.print("707");
									return null;
								}
							}

						}
						catch (Exception e)
						{
							return null;
						}

						Messages message = new Messages();
						message.setCallBackTime(0l);
						message.setComeFrom(comeFrom);
						message.setInterfaceName(interfaceName);
						message.setIsTest(new Integer(isTest));
						message.setMobile(mobile);
						message.setMoCallBackUrl(moCallBackUrl);
						message.setNeedCallBack(new Integer(needCallBack));
						message.setRptCallBackUrl(rptCallBackUrl);
						message.setSendLevel(new Integer(sendLevel));
						message.setSendTime(new Integer(sendLevel).intValue() == 2 ? Common.getNowTime() : c.stringDataToLongTime(sendTime));
						message.setType(0);
						message.setMethed("http");
						message.setOperName(operName);

						MessagesContent messagesContent = new MessagesContent();
						messagesContent.setMessageContent(messageContent);
						message.setContent(messagesContent);

						MessagesDao messagesDao = MessagesDaoFactory.create();
						int messageId = messagesDao.insert(message);
						if(messageId == -1){ //短信发送数据保存数据库失败
							dlog.debug("711 - 数据提交数据库异常", "MessagesAction");
							out.print("711");
							return null;
						}
						SmsLog smsLog = new SmsLog();
						smsLog.setIp(Common.getIP(request));
						smsLog.setMessageId(messageId);
						smsLog.setType("mt");
						smsLog.setXml(data);

						SmsLogDao smsLogDao = SmsLogDaoFactory.create();
						smsLogDao.insert(smsLog);

						res.append("<message>");
						res.append(new StringBuffer("<mobile>").append(mobile).append("</mobile>").toString());
						res.append(new StringBuffer("<message_id>").append(messageId).append("</message_id>").toString());
						res.append("</message>");

						flag = true;
					}
					messageElement = null;
				}
				messageNode = null;
			}
			String resResult = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><root>").append(res.toString()).append("</root>").toString();
			if (flag)
			{
				try
				{
					PrintWriter out = response.getWriter();
					if (resResult != null)
					{
						dlog.debug(resResult, "MessagesAction");
						out.print(URLEncoder.encode(resResult.toString(), "utf-8"));
					}
					else
					{
						out.print("");
					}
				}
				catch (Exception e)
				{
					dlog.debug(e.toString(), "MessagesAction");
				}
			}
			
			doc = null;
			messageNoteList = null;
		}
		catch (Exception ex)
		{
			dlog.debug(ex.toString(), "MessagesAction");
		}
		finally
		{
			dlog.write();
		}
		return null;
	}
}
