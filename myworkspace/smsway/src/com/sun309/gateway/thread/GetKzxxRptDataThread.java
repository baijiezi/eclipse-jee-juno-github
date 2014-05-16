package com.sun309.gateway.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.api.SendMsgKzxx;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.dao.SpMessageMappingDao;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.factory.SpMessageMappingDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Constants;

/**
 * 获取【康众信息】短信回执 
 * @author caiyuerui
 * 
 */
public class GetKzxxRptDataThread extends Thread
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, GetKzxxRptDataThread.class);
	private String access = "Kzxx";

	public GetKzxxRptDataThread()
	{
		super("GetKzxxRptDataThread");
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(Constants.GET_KZXX_RPT_DATA_THREAD_SLEEP_TIME);
				SpMessageMappingDao spMessageMappingDao = SpMessageMappingDaoFactory.create();
				List<Map<String, Object>> spMessageMapingList = spMessageMappingDao.getTop10List(access);
				ArrayList<String> success = new ArrayList<String>();
				ArrayList<String> failure = new ArrayList<String>();
				log.debug("开始去【康众信息】获取短信回执 " + spMessageMapingList.size(), "GetKzxxRptDataThread");
				for (Map spMessageMaping : spMessageMapingList)
				{
					String spid = spMessageMaping.get("sp_message_id").toString();
					List<Map> sendMsgList = SendMsgKzxx.getSmsReport(spid);

					if (sendMsgList != null && sendMsgList.size() > 0)
					{
						Map sendMsg = sendMsgList.get(0);
						if ("0".equals(sendMsg.get("stat").toString()))
						{
							success.add(spid);
						}
						else
						{
							failure.add(spid);
						}
					}
					Thread.sleep(Constants.GET_KZXXF_RPT_DATA_THREAD_SLEEP_TIME);
				}
				log.debug("【康众信息】成功的列表有:" + success.toString() + "失败的列表有：" + failure.toString(), "GetKzxxRptDataThread");
				MessagesDao service = MessagesDaoFactory.create();
				service.sp1DoRpt(success.toArray(), failure.toArray());
			}
			catch (Exception ex)
			{
				log.debug("【康众信息】异常" + ex.getMessage(), "GetKzxxRptDataThread");
			}
			finally
			{
				log.write();
			}
		}
	}

	public static void main(String args[])
	{
		ArrayList<String> success = new ArrayList<String>();
		ArrayList<String> failure = new ArrayList<String>();
		success.add("5a90fcec33ce62e20133d85b14f76a1d");
		MessagesDao service = MessagesDaoFactory.create();
		service.sp1DoRpt(success.toArray(), failure.toArray());

	}
}
