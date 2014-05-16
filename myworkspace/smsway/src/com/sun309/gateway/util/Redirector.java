package com.sun309.gateway.util;

public class Redirector
{
	public static String goToRedirect(String page, String msg)
	{
		String string = "";
		string += "<script language='javascript'>";
		if(msg != null || msg.equals(""))
		{
			string += "alert('" + msg + "');";
		}
		string += "window.location.href='" + page + "'";
		string += "</script>";
		return string;
	}
    public static String goBackRedirect(String msg)
	{
		String string = "";
		string += "<script language='javascript'>";
		if(msg != null || msg.equals(""))
		{
			string += "alert('" + msg + "');";
		}
		string += "window.history.back();";
		string += "</script>";
		return string;
	}
    public static String alertMsg(String msg)
    {
		String string = "";
		string += "<script language='javascript'>";
		string += "alert('" + msg + "');";
		string += "</script>";
		return string;
    }
}
