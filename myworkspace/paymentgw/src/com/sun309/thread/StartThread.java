
package com.sun309.thread;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StartThread extends HttpServlet
{
	public void destroy()
	{
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}

	public void init() throws ServletException
	{
		ThreadLoad tl = new ThreadLoad();
		System.out.println("开始线程...");
		tl.startThread();
	}

}