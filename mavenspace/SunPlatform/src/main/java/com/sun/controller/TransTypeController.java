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
@RequestMapping("/TransType")
public class TransTypeController {
	public CountServiceI countService;
	
	/**
	 * 日期:2014-03-11 
	 * 作者:caolei 
	 * 作用:跳转交易类型统计页面
	 */
	@RequestMapping(value = "/tansType_UI")
	public String tansType_UI() {
		return "tansType_UI";
	}
	/**
	 * 日期:2014-08-11
	 * 作者:caolei
	 * 作用:查询交易类型统计数据
	 */
	@RequestMapping(value = "/queryDelta")
	public String queryDelta(HttpServletRequest request,@ModelAttribute("pojo") CountEntity pojo){
		List<CountEntity> ls=new ArrayList<CountEntity>();
		if(pojo.businessType.equals("1")){
			ls=countService.getBooking(pojo);
		}else if(pojo.businessType.equals("3")){
			ls=countService.getAutoPay(pojo);
		}
		request.setAttribute("ls",ls);
		request.setAttribute("pojo",pojo);
		return "tansType_UI";
	}
	public CountServiceI getCountService() {
		return countService;
	}
	@Autowired
	public void setCountService(CountServiceI countService) {
		this.countService = countService;
	}

}
