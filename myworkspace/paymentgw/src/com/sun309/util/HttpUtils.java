package com.sun309.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils
{
	/**
	 * 取得客户端的IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request)
	{
		return request.getRemoteHost();
	}
}
