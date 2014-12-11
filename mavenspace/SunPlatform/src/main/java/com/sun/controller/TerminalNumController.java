package com.sun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.entity.CountEntity;
import com.sun.service.CountServiceI;

@Controller
@RequestMapping("/TerminalNum")
public class TerminalNumController {
	public CountServiceI countService;
	/**
	 * 日期:2014-03-11 
	 * 作者:caolei 
	 * 作用:跳转终端编号统计页面
	 */
	@RequestMapping(value = "/terminalNum_UI")
	public String terminalNum_UI() {
		return "terminalNum_UI";
	}
	
	/**
	 * 日期:2014-08-20
	 * 作者:caolei
	 * 作用:查询终端统计出来数据
	 */
	@RequestMapping(value = "/terminalDelta")
	public String terminalDelta(HttpServletRequest request,@ModelAttribute("pojo") CountEntity pojo) {
		List<CountEntity> ls=new ArrayList<CountEntity>();
		System.out.println("======terminalNum_UI========");
		
		if(pojo.businessType.equals("1")){
			ls=countService.getBookingTerminal(pojo);
		}else if(pojo.businessType.equals("3")){
			ls=countService.getAutoPayTerminal(pojo);
		}
		request.setAttribute("ls",ls);
		request.setAttribute("pojo",pojo);
		return "terminalNum_UI";
	}

	public CountServiceI getCountService() {
		return countService;
	}
	@Autowired
	public void setCountService(CountServiceI countService) {
		this.countService = countService;
	}
}
