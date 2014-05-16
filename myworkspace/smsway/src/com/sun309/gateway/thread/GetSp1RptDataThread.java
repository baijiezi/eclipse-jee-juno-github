package com.sun309.gateway.thread;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sun309.gateway.api.SpSmsGateway;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Constants;

/**
 * 获取SP1回执
 * @author caiyuerui
 *
 */
public class GetSp1RptDataThread extends Thread
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, GetRptDataThread.class);
	public GetSp1RptDataThread()
	{
		super("GetSp1RptDataThread");
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				System.out.println(" --- GetSp1RptDataThread Start --- "); 
				Thread.sleep(Constants.GET_SP1_RPT_DATA_THREAD_SLEEP_TIME);
				Hashtable<String, String> ht = SpSmsGateway.rpt(10);
				ArrayList<String> success = new ArrayList<String>();
				ArrayList<String> failure = new ArrayList<String>();
				for(String key : ht.keySet())
				{
					if("0".equals(ht.get(key)))
					{
						success.add(key);
						//成功
					}
					else
					{
						failure.add(key);
						//失败
					}
				}
				MessagesDao service = MessagesDaoFactory.create();
				service.sp1DoRpt(success.toArray(), success.toArray());
				System.out.println(" --- GetSp1RptDataThread end --- "); 
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
