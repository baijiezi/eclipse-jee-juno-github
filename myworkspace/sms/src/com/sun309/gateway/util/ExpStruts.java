package com.sun309.gateway.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;
import org.apache.velocity.tools.struts.StrutsUtils;


public class ExpStruts
{
	public static String getMessage(ServletContext context, HttpServletRequest request, String bundle, String key)
	{
		if(key == null || StringUtils.isEmpty(key))
		{
			return null;
		}
		MessageResources resource = StrutsUtils.getMessageResources(request, context, bundle);
		if(resource == null)
		{
			return null;
		}
		return resource.getMessage(key);
	}
}
