package com.sun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.entity.RefundEntity;
import com.sun.service.RefundServiceI;

@Controller
@RequestMapping("/refunDelta")
public class RefunController {
	/**
	 * 日期：2014-03-12
	 * 作者:caolei
	 * 作用:跳转查询充值页面
	 */
	private RefundServiceI refundService;
	public RefundServiceI getRefundService() {
		return refundService;
	}
	@Autowired
	public void setRefundService(RefundServiceI refundService) {
		this.refundService = refundService;
	}

	@RequestMapping(value="/refunDelta_UI")
	public String refunDelta_UI(){
		//System.out.println("=============");
		return "refunDelta_UI";
	}
	
	/**
	 * 日期:2014-08-31
	 * 作者:caolei
	 * 作用:查询退费详细列表
	 */
	@RequestMapping(value="/refunDelta")
	public String cancelDelta(HttpServletRequest request,@ModelAttribute("pojo") RefundEntity pojo){
		//System.out.println("=============");
		List<RefundEntity> ls= refundService.getWhereEntity(pojo);
		//System.out.println("===[====]==="+ls.size());
		request.setAttribute("ls", ls);
		request.setAttribute("pojo",pojo);
		return "refunDelta_UI";
	}
}