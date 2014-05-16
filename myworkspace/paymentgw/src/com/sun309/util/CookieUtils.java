package com.sun309.util;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.Cookie;

public class CookieUtils
{
	/**
	 * 获取cookie
	 * 
	 * @param cookies
	 * @param key
	 * @return
	 */
	public static Hashtable<String, String> getCookieValue(Cookie cookies[], ArrayList<String> key)
	{
		if (cookies == null)
			return null;
		Hashtable<String, String> ht = new Hashtable<String, String>();
		for (int i = 0; i < cookies.length; i++)
		{
			for (String k : key)
			{
				if (k.equals(cookies[i].getName()))
				{
					ht.put(k, cookies[i].getValue());
				}
			}
		}
		return ht;
	}
}
