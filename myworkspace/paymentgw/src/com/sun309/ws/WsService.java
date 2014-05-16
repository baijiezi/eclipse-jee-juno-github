package com.sun309.ws;

public interface WsService
{
	public String receive(String input);
	public String getPayInfo(String input);
	public String setPayInfo(String input);
	public String getPayInfoAsync(String input);
	public String getPayInfoResultAsync(String input);
}
