package com.sun309.factory;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.sun309.impl.AliPayBaseRequest;
import com.sun309.impl.ChinaPayBaseRequest;
import com.sun309.impl.TenPayBaseRequest;
import com.sun309.impl.TenPayPackageBaseRequest;
import com.sun309.service.BaseRequest;
import com.sun309.util.Constants;

public class BaseRequestFactory 
{
	private  Hashtable<Integer, Class> ht = new Hashtable<Integer, Class>();
	private  BaseRequest service = null;
	private  Logger log = Logger.getLogger(BaseRequestFactory.class);
	BaseRequestFactory()
	{
		ht.put(new Integer(Constants.ALI_PAY), AliPayBaseRequest.class);
		ht.put(new Integer(Constants.CHINA_PAY), ChinaPayBaseRequest.class);
		ht.put(new Integer(Constants.TEN_PAY), TenPayBaseRequest.class);
		ht.put(new Integer(Constants.TENPAYPACKAGE_PAY), TenPayPackageBaseRequest.class);
	}
	public  BaseRequest create(Integer className)
	{
		if(ht.get(className) != null)
		{
			try
			{
				service = (BaseRequest)ht.get(className).newInstance();
			}
			catch (InstantiationException e)
			{
				log.debug(e);
			}
			catch (IllegalAccessException e)
			{
				log.debug(e);
			}
		}
		return service;
	}
}
