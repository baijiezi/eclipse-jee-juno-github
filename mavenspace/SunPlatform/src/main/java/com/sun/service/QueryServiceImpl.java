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
			if(job.getBoolean("success")==true){//处理成功登记记录
				System.out.println("查询成功，返回的数据为："+result);
			}else{
				System.out.println("查询出现错误:"+result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 作用:转换URL
	 * 日期:2014-03-28
	 * 作者:caolei
	 */
	public static String toUrl(String url,String json){
		System.out.println("获取的URL："+url+";获取的JSON："+json);
		JSONObject jsonob=JSONObject.fromObject(json);
		String newurl=url.replaceAll("\'id\'", jsonob.get("UserId").toString());
		System.out.println("转换后的URL："+newurl);
		return newurl;
	}
}
