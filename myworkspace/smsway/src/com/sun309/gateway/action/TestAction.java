package com.sun309.gateway.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun309.gateway.dto.ResponseResult;

public class TestAction extends Action
{
	private Logger log = Logger.getLogger(TestAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		String act = request.getParameter("act");
		String xml = request.getParameter("xml");
		
		ResponseResult rr = new ResponseResult();
		rr.setXml(xml);
		if("mo".equals(act))
		{
			log.debug("mo");
		}
		if("rpt".equals(act))
		{
			log.debug("rpt");
		}
		return null;
	}
}