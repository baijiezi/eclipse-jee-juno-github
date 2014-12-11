package com.sun.service;

import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.sun.common.ConnUtil;

@Service("queryService")
public class QueryServiceImpl implements QueryServiceI{

	public String queryBalance(String json) {
		Resource resource=new ClassPathResource("/config.properties");
		String result="";
		try {
			Properties proper=PropertiesLoaderUtils.loadProperties(resource);
			String url=QueryServiceImpl.toUrl(proper.get("balanceUrl").toString(), json);
			System.out.println("balanceUrl="+url+";balanceApiKey="+proper.get("balanceApiKey").toString());
			String apikey=proper.get("balanceApiKey").toString();
			String consumesecretKey=proper.get("balancesecretkey").toString();
			result=ConnUtil.getApiService(url, apikey, consumesecretKey).toString();
			JSONObject job=JSONObject.fromObject(result);
			if(job.getBoolean("success")==true){//����ɹ��ǼǼ�¼
				System.out.println("��ѯ�ɹ������ص�����Ϊ��"+result);
			}else{
				System.out.println("��ѯ���ִ���:"+result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ����:ת��URL
	 * ����:2014-03-28
	 * ����:caolei
	 */
	public static String toUrl(String url,String json){
		System.out.println("��ȡ��URL��"+url+";��ȡ��JSON��"+json);
		JSONObject jsonob=JSONObject.fromObject(json);
		String newurl=url.replaceAll("\'id\'", jsonob.get("UserId").toString());
		System.out.println("ת�����URL��"+newurl);
		return newurl;
	}
}
