package com.sun309.thread;

import org.apache.log4j.Logger;

public class ThreadLoad
{
	private static Logger log = Logger.getLogger(ThreadLoad.class);

	public void startThread()
	{
		try
		{ 
			/** 排班数据分发线程 		*/
			DistributeThread dt = new DistributeThread();
			dt.start();
			
			/** 多支付结果分发线程	*/
			NetPayDistributeThread nt = new NetPayDistributeThread();
			nt.start();
			
			/**　多支付异常处理线程	*/
			NetPayExceptionDistributeThread excep = new NetPayExceptionDistributeThread();
			excep.start();
		} 
		catch (Exception e)
		{
			log.debug(new StringBuffer("线程开始时，出现异常!").append(e).toString());
		}
	}
}
