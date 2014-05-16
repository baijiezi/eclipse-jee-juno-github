package com.sun309.thread;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun309.dto.Payment;
import com.sun309.dto.TranData;
import com.sun309.factory.Factory;
import com.sun309.impl.LogServiceImpl;
import com.sun309.impl.PaymentServiceImpl;
import com.sun309.service.LogService;
import com.sun309.service.PaymentService;
import com.sun309.util.Constants;
import com.sun309.util.DateUtils;
import com.sun309.util.HttpURLConnectionRequest;

public class DistributeThread extends Thread
{
	private LogService log = (LogService) Factory.create(LogServiceImpl.class);
	public DistributeThread()
	{
		super("DistributeThread");
	}
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(Constants.DISTRIBUTE_THREAD_SLEEP_TIME);
				System.out.println("DistributeThread Start...");
				this.doDistribte();
				this.doMove();
				log.write();
			}
			catch(Exception e)
			{
				System.out.println(e); 
			}
		}
	}
	private void doMove()
	{
		PaymentService ps = (PaymentService) Factory.create(PaymentServiceImpl.class);
		ArrayList<Payment> list = ps.findPaymentByCondition(new StringBuffer("(`Status` IN (").append(Constants.PAYMENT_STATUS_3).append(") AND Add_Time < ").append(DateUtils.getNowTime() - 30l * 60l * 1000l).append(" AND TryTimes >= 4) LIMIT 1").toString());
		if(list==null || list.size() <= 0) return;
		for (Payment payment : list)
		{
			log.debug(new StringBuffer(payment.getId()).append("处理失败，移入LOG表中.").toString());
			ps.moveToLog(payment.getId());
		}
	}
	private void doDistribte()
	{
		try
		{
			PaymentService ps = (PaymentService) Factory.create(PaymentServiceImpl.class);
			// 状态：初始化、失败 AND TryTimes小于等于3
			// 状态：请求中 and Add_Time < 现在30分钟前
			ArrayList<Payment> list = ps.findPaymentByCondition(new StringBuffer("(`Status` IN (").append(Constants.PAYMENT_STATUS_0).append(",").append(Constants.PAYMENT_STATUS_3).append(") AND `TryTimes`<=3) OR (`Status` IN (").append(Constants.PAYMENT_STATUS_1).append(") AND Add_Time < ").append(DateUtils.getNowTime() - 30l * 60l * 1000l).append(") LIMIT 1").toString());
			if(list==null || list.size() <= 0) return;
			Hashtable<String, String> params = new Hashtable<String, String>();
			for (Payment payment : list)
			{
				String _xml = createXml(payment);
				log.debug(new StringBuffer("传出：").append(_xml).toString());
				params.put("xml", _xml);
				String result = new String();
				ps.updateStatus(payment.getId(), Constants.PAYMENT_STATUS_1, true);
				result = this.doRequest(payment, params);
				log.debug(new StringBuffer("传入:").append(result).toString());
				//result:
				//<Item>
				//   <MidID></MidID>
				//   <error>0:成功;1:失败</error>   
				//   <message>提示</message>
				//</Item>
				//有回复信息
				System.out.println("result ============== " + result); 
				if(result!=null && !StringUtils.isEmpty(result))
				{
					TranData td = new TranData(result);
					//没有收到结果
					if(td.getList()==null || td.getList().size() <= 0)
					{
						ps.updateStatus(payment.getId(), Constants.PAYMENT_STATUS_3, false);
					}
					else
					{
						TranData _td = td.getList().get(0);
						System.out.println("_td.getError() == " + _td.getError()); 
						if(_td.getError() != null && _td.getError().intValue() == 0)
						{
							ps.updateStatus(payment.getId(), Constants.PAYMENT_STATUS_2, false);
							ps.moveToLog(payment.getId());
						}
						else
						{
							ps.updateStatus(payment.getId(), Constants.PAYMENT_STATUS_3, false);
						}
					}
				}
				//回复信息为空
				//失败
				else
				{
					ps.updateStatus(payment.getId(), Constants.PAYMENT_STATUS_3, false);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private String createXml(Payment payment)
	{
		StringBuffer xml = new StringBuffer();
		xml.append("<Items><Item>");

		xml.append("<TransType>").append(payment.getTransType()).append("</TransType>");
		xml.append("<PayOrderID>").append(payment.getPayOrderId()).append("</PayOrderID>");
		xml.append("<ExtOrderID>").append(payment.getExtOrderId()).append("</ExtOrderID>");
		xml.append("<CardNO>").append(payment.getCardNo()).append("</CardNO>");
		xml.append("<Flag>").append(payment.getFlag()).append("</Flag>");
		xml.append("<Message>").append(payment.getMessage()).append("</Message>");
		xml.append("<MidID>").append(payment.getId()).append("</MidID>");
		
		xml.append("</Item></Items>");
		return xml.toString();
	}
	
	private String doRequest(Payment payment, Hashtable<String, String> params)
	{ 
		String result = new String();
		SAXReader reader = new SAXReader();
		try
		{   
			String path = Constants.TRAN_DATA_FILE_PATH;
			Document doc = reader.read(this.getClass().getClassLoader().getResourceAsStream(path));
			
//			FileUtils f = new FileUtils();
//			String input = f.readFileContent(path);
//			Document doc = DocumentHelper.parseText(input);
			
			Element root = doc.getRootElement();
			if (root != null) 
			{
				List node = root.elements();
				for(int i = 0; i < node.size(); i++)
				{
					Element foo = (Element) node.get(i);
					if("items".equals(foo.getName()))
					{
						List cNode = foo.elements();
						for (int ii = 0; ii < cNode.size(); ii++)
						{
							Element hospitalElement = (Element) cNode.get(ii);
							List hospitalNode = hospitalElement.elements();
							
							boolean doBread = false;
							String hno = new String();
							String key = new String();
							String url = new String();
							
							for(int iii = 0; iii < hospitalNode.size(); iii++)
							{
								Element leafageElement = (Element) hospitalNode.get(iii);
								if ("key".equals(leafageElement.getName())) 	 	 try { key=leafageElement.getText(); } catch(Exception e) {}
								if ("url".equals(leafageElement.getName())) 	 	 try { url=leafageElement.getText(); } catch(Exception e) {}
							}
							log.debug(new StringBuffer("key=").append(key).append("; url=").append(url).append(" ExtOrderId=").append(payment.getExtOrderId()).toString());  
							if (payment.getExtOrderId().indexOf(key) != -1)
							{
								HttpURLConnectionRequest http = new HttpURLConnectionRequest();
								String postUrl = url;
								result = http.doSendPostRequest(postUrl, params);
								log.debug(new StringBuffer("result=").append(result).toString()); 
								return result;
							}
						}
						cNode=null;
					}
					foo=null;
				}
				node=null;
			}
			doc=null;
			root=null;
		}
		catch (DocumentException e) 
		{
			System.out.println(e);
		}
		return null;
	}
	
	public static void main(String[] args)
	{
//		Payment payment = new Payment();
//		payment.setExtOrderId("CZ10000000005137");
//		Hashtable<String, String> params = null;
 		DistributeThread d = new DistributeThread();
//		d.doRequest(payment, params);
//		
		d.start();
	}
}