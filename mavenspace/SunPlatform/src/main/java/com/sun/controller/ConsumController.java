package com.sun.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sun.entity.ConsumEntity;
import com.sun.service.ConsumServiceI;

@Controller
@RequestMapping("/consumDelta")
public class ConsumController {
	/**
	 * 日期：2014-03-12
	 * 作者:caolei
	 * 作用:跳转查询消费页面 
	 */
	private ConsumServiceI consumService;
	

	@RequestMapping(value="/consumDelta_UI")
	public String consumDelta_UI(){
		//System.out.println("=============");
		return "consumDelta_UI";
	}
	
	/**
	 * 日期:2014-08-31
	 * 作者:caolei
	 * 作用:查询消费详细列表
	 */
	@RequestMapping(value="/consumDelta")
	public String consumDelta(HttpServletRequest request,@ModelAttribute("pojo") ConsumEntity pojo){
		List<ConsumEntity> ls= consumService.getWhereEntity(pojo);
		request.setAttribute("ls", ls);
		request.setAttribute("pojo",pojo);
		return "consumDelta_UI";
	}

	public ConsumServiceI getConsumService() {
		return consumService;
	}
	
	@Autowired
	public void setConsumService(ConsumServiceI consumService) {
		this.consumService = consumService;
	}
}