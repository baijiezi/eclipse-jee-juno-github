package com.sun309.gateway.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.api.SendMsgZjht;
import com.sun309.gateway.dao.MessagesDao;
import com.sun309.gateway.factory.MessagesDaoFactory;
import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.Constants;
import com.sun309.webservice.sms.RptItem;

/**
 * 获取【中经汇通】回执
 * @author sky bug
 */
public class GetZjhtRptDataThread extends Thread
{
	private LogService log = LogFactory.create(LogFactory.SEND_DATA, GetRptDataThread.class);
	public GetZjhtRptDataThread()
	{
		super("GetZjhtRptDataThread");
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(Constants.GET_ZJHT_RPT_DATA_THREAD_SLEEP_TIME);
				List<Map> replist = SendMsgZjht.rpt(10);
				log.debug("【中经汇通】短信信息回执取回"+(replist!=null?replist.size():"0")+"条！","GetZjhtRptDataThread");
				if(replist != null && replist.size()> 0){
					ArrayList<String> success = new ArrayList<String>(8);
					ArrayList<String> failure = new ArrayList<String>(8);
					for(Map repmap : replist)
					{
						if("0".equals(repmap.get("obj1").toString())){
							success.add(repmap.get("obj5").toString());
						}else{
							failure.add(repmap.get("obj5").toString());
						}
					}
					MessagesDao service = MessagesDaoFactory.create();
					log.debug("【中经汇通】短信信息回执取回"+replist.size()+"条！","GetZjhtRptDataThread");
					service.sp1DoRpt(success.toArray(), success.toArray());
				}
			}
			catch(Exception e)
			{
				
			}finally{
				log.write();
			}
		}
	}
}
