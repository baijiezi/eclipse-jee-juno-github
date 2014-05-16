package com.sun309.gateway.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sun309.gateway.log.LogFactory;
import com.sun309.gateway.log.LogService;
import com.sun309.gateway.util.ProcessPassword;

public class SendMsgKzxx {
	private static LogService log = LogFactory.create(LogFactory.SEND_DATA, SendMsgKzxx.class);
	private static String wsname = "5503";
	private static String wspass = new ProcessPassword().createMD5String("kzxx@mas@201111"); 
	private static String wsParam = "";
	
	/****
	 * 康众信息的短信发送调用
	 * @param mobile
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static List<Map> send(String mobile,String context){
		String  wsurl_send = "http://218.16.124.108:8081/ema/http/SendSms";
		StringBuffer  sb = new StringBuffer();
		try{
			log.debug("【康众信息】发送短信参数mobile:"+mobile+";context:"+context,"SendMsgKzxx");
			context = java.net.URLEncoder.encode(context, "UTF-8");
			wsParam = "Account="+wsname+"&Password="+wspass+"&Phone="+
			mobile+"&Content="+context+"&SubCode=&SendTime=";
			URL hurl = new URL(wsurl_send);
			
			HttpURLConnection urlCon = (HttpURLConnection) hurl.openConnection();
			urlCon.setRequestMethod("POST");
			urlCon.setDoOutput(true);
			OutputStream out = urlCon.getOutputStream();
			out.write(wsParam.getBytes("GBK"));
			out.flush();
			out.close();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"GBK"));
			
			int ch ;
			while((ch =rd.read()) > -1){
				sb.append((char) ch);
			}
			log.debug("【康众信息】发送放回的结果:"+sb,"SendMsgKzxx");
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("【康众信息】短信发送"+ex.getMessage(),"SendMsgKzxx");
		}finally{
			log.write();
		}
		return xml2List(sb.toString());
		
	}
	
	/**
	 * 根据SMSID获取短信回执
	 * @param smsId
	 */
	public static List<Map> getSmsReport(String smsId){
		String wsurl_report = "http://218.16.124.108:8081/ema/http/GetReport";
		StringBuffer  sb = new StringBuffer();
		try{
			wsParam = "Account="+wsname+"&Password="+wspass+"&SmsID="+
			smsId;
			log.debug("【康众信息】获取短信回执"+wsParam,"SendMsgKzxx");
			URL hurl = new URL(wsurl_report);
			
			HttpURLConnection urlCon = (HttpURLConnection) hurl.openConnection();
			urlCon.setRequestMethod("POST");
			urlCon.setDoOutput(true);
			OutputStream out = urlCon.getOutputStream();
			out.write(wsParam.getBytes("GBK"));
			out.flush();
			out.close();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"GBK"));
			int ch ;
			while((ch =rd.read()) > -1){
				sb.append((char) ch);
			}
			log.debug("【康众信息】获取短信回执结果"+sb,"SendMsgKzxx");
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("【康众信息】短信发送"+ex.getMessage(),"SendMsgKzxx");
		}finally{
			log.write();
		}
		return xml2List(sb.toString());
	}
	

	/**将xml格式的字符串转为MAP对象的list集合 ***/
	public static List<Map> xml2List(String xmlstr){
		
		List<Map>  list = new ArrayList();
		if(null == xmlstr || "".equals(xmlstr)){
			return list;
		}
		try{
			Document doc = DocumentHelper.parseText(xmlstr);
			Element root = doc.getRootElement();
			List node = root.elements();
			for (int i = 0; i < node.size(); i++) {
				Element elment = (Element)node.get(i);
				if("sms".equals(elment.getName())){
					List n = elment.elements();
					Map msgrep = new HashMap();
					for (int j = 0; j < n.size(); j++) {
						Element _e = (Element) n.get(j);
						msgrep.put(_e.getName(), _e.getText());
					}
					list.add(msgrep);
				}
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	public static Map getBalance(){
		Map result = new HashMap();
		String wsurl_report = "http://218.16.124.108:8081/ema/http/GetBalance";
		StringBuffer  sb = new StringBuffer();
		try{
			wsParam = "Account="+wsname+"&Password="+wspass;
			log.debug("【康众信息】获取短信回执"+wsParam,"SendMsgKzxx");
			URL hurl = new URL(wsurl_report);
			
			HttpURLConnection urlCon = (HttpURLConnection) hurl.openConnection();
			urlCon.setRequestMethod("POST");
			urlCon.setDoOutput(true);
			OutputStream out = urlCon.getOutputStream();
			out.write(wsParam.getBytes("GBK"));
			out.flush();
			out.close();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"GBK"));
			int ch ;
			while((ch =rd.read()) > -1){
				sb.append((char) ch);
			}
			log.debug("【康众信息】获取短信回执结果"+sb,"SendMsgKzxx");
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("【康众信息】短信发送"+ex.getMessage(),"SendMsgKzxx");
			return null;
		}finally{
			log.write();
		}
		int first_i = sb.indexOf("sms");
		if(first_i == -1){
			first_i = sb.indexOf("response");
			int last_i = sb.lastIndexOf("response");
			result.put("Error", "1");
			result.put("Message", "康众信息短信发送发送失败代码："+ sb.substring(first_i+9, last_i-2));
			result.put("Value", sb.substring(first_i+9, last_i-2));
		}else{
			int last_i = sb.lastIndexOf("sms");
			result.put("Error", "0");
			result.put("Message",  "康众信息短信发送剩余额度："+ sb.substring(first_i+4, last_i-2));
			result.put("Value", sb.substring(first_i+4, last_i-2));
		}
		return result;
	}
	
	public static void main(String args []){
		List <Map> list = send("18933913689","testcc");
		System.out.println(list.toString()+"comere");
	}
	
}
