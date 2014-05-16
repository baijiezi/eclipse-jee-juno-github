package com.googlepages.smallnest.facet;

public class HelloServiceImpl implements HelloService
{
	public String hello(String name)
	{
		if (name==null || name.equals(""))
		{
			return "Hello Guest";
		}
		
		return "Hello " + name;
	}
}
