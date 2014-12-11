package com.sun.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.common.ExcelUtil;
import com.sun.entity.PayOrderEntity;
import com.sun.entity.User;
import com.sun.service.PayOrderServiceI;
import com.sun.service.UserServiceI;

@Controller
@RequestMapping("/paymentorder")
public class PayMentController {
	private PayOrderServiceI payOrderService;
	private UserServiceI userService;
	
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	public PayOrderServiceI getPayOrderService() {
		return payOrderService;
	}
	@Autowired
	public void setPayOrderService(PayOrderServiceI payOrderService) {
		this.payOrderService = payOrderService;
	}
	/**
	 * 日期:2014-03-11
	 * 作者:caolei
	 * 作用：跳转查询补缴订单明细页面
	 */
	@RequestMapping(value="/paymentorder_UI")
	public String paymentorder_UI(){
		return "paymentorder_UI";
	}
	/**
	 * 日期:2014-03-11
	 * 作者:caolei
	 * 作用:查询补缴订单明细页面
	 */
	@RequestMapping(value="/paymentorder")
	public String paymentorder(HttpServletRequest request,@ModelAttribute("pojo") PayOrderEntity pojo,HttpSession se){
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		List<PayOrderEntity> ls=payOrderService.getWhereEntity(pojo);
		System.out.println("当前一共查询出来:"+ls.size());

		request.setAttribute("ls",ls);
		request.setAttribute("pojo",pojo);
		System.out.println("执行transAction_Qury方法");
		return "paymentorder_UI";
	}
	/**
	 * 日期：2014-03-25
	 * 作者:caolei
	 * 作用:导出明细
	 */
	@RequestMapping(value="/exportPayMent")
	public @ResponseBody String exportPayMent(HttpServletResponse response, PayOrderEntity njWorkLog,
			@RequestParam(value = "createUserIds", required = false)
			String createUserIds,
			@RequestParam(value = "workLogGrantUsersName", required = false)
			String workLogGrantUsersName,
			@RequestParam(value = "strWorkEndDate", required = false)
			String strWorkEndDate, @ModelAttribute("pojo")
			PayOrderEntity pojo,HttpSession se){
		ExcelUtil<PayOrderEntity> excelUtil = new ExcelUtil<PayOrderEntity>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();// 取得输出流
			// out.flush();
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ new String("补缴订单明细报表".getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = { "医院名称", "订单号", "诊疗卡", "身份证","手机号码", "挂号费","支付卡号", "帐户类型", "支付日期" };
		String[] columns = { "hospitalName", "orderNo", "medicalCardNo","idCard", "mobile", "fee", "payCardNo", "payMent","createTime" };
		if ("on".equals(njWorkLog.getIdCard()))
			njWorkLog.setHospitalName("1");
		else
			njWorkLog.setIdCard("");
		if (StringUtils.isNotBlank(createUserIds))
			;
		// njWorkLog.setOrderNum(createUserIds.split(","));
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		List<PayOrderEntity> dataset = payOrderService.getWhereEntity(pojo);
		try {
			excelUtil.exportExcel("补缴订单明细报表", headers, columns, dataset, out,"");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
