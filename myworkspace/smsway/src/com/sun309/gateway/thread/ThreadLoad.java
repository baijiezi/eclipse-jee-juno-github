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
//			GetMoThread mo = new GetMoThread();  //Mas机的短信回复
//			GetRptDataThread rpt = new GetRptDataThread();   //取Mas机上的短信回执
//			GetZjhtRptDataThread zrpt = new GetZjhtRptDataThread();  //取【中经汇通】的短信回执
			GetKzxxRptDataThread krpt = new GetKzxxRptDataThread();  //取【康众信息】的短信回执
//			GetUnicomMoThread unicom = new GetUnicomMoThread();      //获取电信、联通  Mas机上的短信回复
			ResendMessagesThread resend = new ResendMessagesThread();   // 获取需要重新发送的短信 再发送
//			GetSp1RptDataThread getSp1RptDataThread = new GetSp1RptDataThread();
			
			sendDate.start();
//			mo.start();
//			rpt.start();
//			zrpt.start();
			krpt.start();
//			unicom.start();
			resend.start();
//			getSp1RptDataThread.start();
			
		} catch (Exception e)
		{
			log.debug("线程开始时，出现异常!" + e);
		}
	}
}
