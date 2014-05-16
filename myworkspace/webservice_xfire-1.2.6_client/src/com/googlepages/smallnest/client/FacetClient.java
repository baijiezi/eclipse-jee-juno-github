package com.googlepages.smallnest.client;

import com.googlepages.smallnest.facet.HelloServiceClient;
import com.googlepages.smallnest.facet.HelloServicePortType;

public class FacetClient 
{
	public static void main(String[] args)
	{
		HelloServiceClient client = new HelloServiceClient();
		HelloServicePortType helloService = client.getHelloServiceHttpPort();
		//调用服务
		String  result = helloService.hello("Juliet");
		System.out.println("结果：" + result);
	}
}
