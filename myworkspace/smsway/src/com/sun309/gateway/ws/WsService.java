package com.sun309.gateway.ws;

public interface WsService
{
	public String send(String input);
	public String simpleSend(String input);
	public String delete(String input);
	
	public String sysTest(String name,String age);
}
