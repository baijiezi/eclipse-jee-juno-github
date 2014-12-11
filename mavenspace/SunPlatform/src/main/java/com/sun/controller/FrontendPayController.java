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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.common.BaseConditionVO;
import com.sun.common.ExcelUtil;
import com.sun.entity.FrontendPayRecordEntity;
import com.sun.entity.TransActionEntity;
import com.sun.entity.User;
import com.sun.service.FrontendPayRecordServiceI;


@Controller
@RequestMapping("/FrontendPay")
public class FrontendPayController {
	private FrontendPayRecordServiceI frontendPayRecordService;
	/**
	 * 日期:2014-03-11 
	 * 作者:caolei 
	 * 作用:跳转订单页面
	 */
	@RequestMapping(value = "/frontendPay_UI")
	public String frontendPay_UI() {
		return "frontendPay_UI";
	}
	/**
	 * 日期:2014-03-11 
	 * 作者:caolei 
	 * 作用:查询缴费记录数据
	 */
	@RequestMapping(value = "/frontendPayDelta")
	public String frontendPayDelta(HttpServletRequest request,
			@ModelAttribute("pojo")
			FrontendPayRecordEntity pojo,HttpSession se,BaseConditionVO vo, Model model) {
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		
		List<FrontendPayRecordEntity> userList = frontendPayRecordService.searchUser(vo);
		Integer totalCount = frontendPayRecordService.getCout(pojo);
		
		vo.setTotalCount(totalCount);
		model.addAttribute("userList", userList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("vo", vo);
		model.addAttribute("pageSize", vo.getPageSize());
		model.addAttribute("url","FrontendPay/frontendPayDelta.do");
		return "frontendPay_UI";
	}
	/**
	 * 日期:2014-10-27
	 * 作者:caolei
	 * 作用:导出缴费记录明细
	 * @return
	 */
	@RequestMapping(value = "/exprotExcel")
	public @ResponseBody
	String exprotExcel(HttpServletResponse response, FrontendPayRecordEntity njWorkLog,
			@RequestParam(value = "createUserIds", required = false)
			String createUserIds,
			@RequestParam(value = "workLogGrantUsersName", required = false)
			String workLogGrantUsersName,
			@RequestParam(value = "strWorkEndDate", required = false)
			String strWorkEndDate, @ModelAttribute("pojo")
			FrontendPayRecordEntity pojo,HttpSession se) {
		ExcelUtil<FrontendPayRecordEntity> excelUtil = new ExcelUtil<FrontendPayRecordEntity>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition","attachment; filename="+new String("缴费记录明细".getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = { "医院名称", "患者姓名", "诊疗卡", "手机号", "订单号", "支付金额","支付卡号", "支付类型", "支付号","支付流水","系统支付号","支付渠道","终端编号","终端流水","支付时间","业务类型","交易状态","备注" };
		String[] columns = { "hospitalName", "patientName", "medicalCardNo","mobile", "orderNo", "realPayFee", "payCardNo", "payType","paymentId","payTranLine","systemPayTranLine","optionPayments","operId","terminalTranLine","payTime","businessType","states","remark"};

		if (StringUtils.isNotBlank(createUserIds))
			;
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		List<FrontendPayRecordEntity> dataset = frontendPayRecordService.getWhereEntity(pojo);
		try {
			excelUtil.exportExcel("缴费记录明细报表", headers, columns, dataset, out,"");
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
	
	public FrontendPayRecordServiceI getFrontendPayRecordService() {
		return frontendPayRecordService;
	}
	@Autowired
	public void setFrontendPayRecordService(
			FrontendPayRecordServiceI frontendPayRecordService) {
		this.frontendPayRecordService = frontendPayRecordService;
	}

}