package com.sun309.util;

import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HttpService
{
	private static Logger log = Logger.getLogger(HttpService.class);
	public void noCatch(HttpServletResponse response)
	{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0); 
	}
	
	public void xmlEncodeing(HttpServletResponse response)
	{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml");
	}
	
	/**
	 * 获取请求URL的参�数
	 * 
	 * @param request          
	 * @return
	 */
	public static String getUrlParam(HttpServletRequest request, String filterVar)
	{
		String url = "";
		String param = request.getQueryString();
		if("".equals(param) || param == null)
		{
			return "";
		}

		StringTokenizer s = new StringTokenizer(param, "&");
		while(s.hasMoreTokens())
		{
			String str = s.nextToken();
			if(str.indexOf(filterVar) == -1)
			{
				try
				{
					String temp = request.getParameter(str.split("=")[0]);
					url += str.split("=")[0] + "=" + java.net.URLEncoder.encode(temp, "utf-8") + "&";
				}
				catch (Exception e)
				{
					log.debug("get url exception " + e);
				}
			}
		}
		try
		{
			url = url.substring(0, url.length()-1);
		}
		catch(Exception e){ url = ""; }
		
		return url;
	}
	
	/**
	 * 获取请求页面的所有参�数(post get)
	 * 
	 * @param request
	 * @return
	 */
	public static String getParams(HttpServletRequest request) 
	{
		Enumeration en = request.getParameterNames();
		String re = "";
		String tmp = null;
		String tmp1 = null;
		
		while (en.hasMoreElements()) 
		{
			tmp = (String) en.nextElement();
			if (tmp != null) 
			{
				re += "&" + tmp + "=";
				tmp1 = request.getParameter(tmp);
				if (tmp1 != null)
				{
					re += tmp1;
				}
			}
		}
		if (re != "") { return re.substring(1); }
		else { return re; }
	}
	
	/**
	 * 得到当前请求页面的url
	 * 
	 * @param request
	 * @return
	 */
	public static String getThisUrl(HttpServletRequest request) 
	{
		return request.getRequestURL().toString();
	}
}
