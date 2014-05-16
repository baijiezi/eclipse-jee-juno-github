package com.sun309.gateway.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeleteMessagesAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
//		Common c = new Common();
//		String data = request.getParameter("xml");
//		log.debug(data);
//		if (data == null || StringUtils.isEmpty(data))
//		{
//			try
//			{
//				PrintWriter out = response.getWriter();
//				out.print("700");
//			}
//			catch (Exception e)
//			{
//			}
//			return null;
//		}
//		Document doc = c.string2Document(data);
//		if (doc == null)
//		{
//			try
//			{
//				PrintWriter out = response.getWriter();
//				out.print("700");
//			}
//			catch (Exception e)
//			{
//			}
//			return null;
//		}
//		NodeList messageNoteList = doc.getElementsByTagName("message");
//		boolean flag = false;
//		for (int j = 0; j < messageNoteList.getLength(); j++)
//		{
//			Node messageNode = messageNoteList.item(j);
//			if (messageNode.getNodeType() == Node.ELEMENT_NODE)
//			{
//				Element messageElement = (Element) messageNode;
//				if (messageElement != null)
//				{
//					String mobile = new String();
//					String messageId = new String();
//
//					if (messageElement.getElementsByTagName("mobile").item(0).getFirstChild() != null)
//						mobile = messageElement.getElementsByTagName("mobile").item(0).getFirstChild().getNodeValue();
//					if (messageElement.getElementsByTagName("message_id").item(0).getFirstChild() != null)
//						messageId = messageElement.getElementsByTagName("message_id").item(0).getFirstChild().getNodeValue();
//
//					if (messageId == null || new Integer(messageId).intValue() <= 0)
//					{
//						try
//						{
//							PrintWriter out = response.getWriter();
//							out.print("701");
//						}
//						catch (Exception e)
//						{
//						}
//						return null;
//					}
//					MessagesDao messagesDaoService = MessagesDaoFactory.create();
//					ArrayList<Messages> list = messagesDaoService.findMessagesByCondition(" AND m.message_id=" + messageId);
//					System.out.println("DeleteMessagesAction list ------------ " + list);
//					if(list == null || list.size() <= 0) 
//					{
//						try
//						{
//							PrintWriter out = response.getWriter();
//							out.print("702");
//						}
//						catch (Exception e)
//						{
//						}
//						return null;
//					}
//					Messages message = (Messages)list.get(0);
//					if(!mobile.equals(message.getMobile()))
//					{
//						try
//						{
//							PrintWriter out = response.getWriter();
//							out.print("703");
//						}
//						catch (Exception e)
//						{
//						}
//						return null;
//					}
//					boolean result = messagesDaoService.delete(new Integer(messageId).intValue());
//					if(result)
//					{
//						try
//						{
//							PrintWriter out = response.getWriter();
//							out.print("000");
//						}
//						catch (Exception e)
//						{
//						}
//						return null;
//					}
//					else
//					{
//						try
//						{
//							PrintWriter out = response.getWriter();
//							out.print("704");
//						}
//						catch (Exception e)
//						{
//						}
//						return null;
//					}
//				}
//			}
//		}
		return null;
	}
}