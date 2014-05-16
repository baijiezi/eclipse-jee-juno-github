package com.sun309.gateway.thread;

import org.apache.log4j.Logger;

public class ThreadLoad
{
	private static Logger log = Logger.getLogger(ThreadLoad.class);

	public void startThread()
	{
		try
		{
			SendDataThread sendDate = new SendDataThread();
			GetMoThread mo = new GetMoThread();
			GetRptDataThread rpt = new GetRptDataThread();
			GetUnicomMoThread unicom = new GetUnicomMoThread();
			ResendMessagesThread resend = new ResendMessagesThread();
			//GetSp1RptDataThread getSp1RptDataThread = new GetSp1RptDataThread();

			sendDate.start();
			mo.start();
			rpt.start();
			unicom.start();
			resend.start();
			//getSp1RptDataThread.start();
			
		} catch (Exception e)
		{
			log.debug("线程开始时，出现异常!" + e);
		}
	}
}
