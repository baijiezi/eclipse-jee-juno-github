package com.sun.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.common.ExcelUtil;
import com.sun.entity.PayRecordEntity;
import com.sun.service.PayRecordServiceI;
import com.sun.service.UserServiceI;


/**
 * 日期:2014-03-11
 * 作者:caolei
 * 作用:主要针对充值记录明细功能
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/payrecord")
public class PayRecController {
	private UserServiceI userService;
	private PayRecordServiceI payRecordService;	//充值记录查询service层
	
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	public PayRecordServiceI getPayRecordService() {
		return payRecordService;
	}
	@Autowired
	public void setPayRecordService(PayRecordServiceI payRecordService) {
		this.payRecordService = payRecordService;
	}
	
	/**
	 * 日期：2014-03-12
	 * 作者:caolei
	 * 作用:跳转查询充值页面
	 */
	@RequestMapping(value="/queryDelta_UI")
	public String queryDelta_UI(){
		return "queryDelta_UI";
	}
	/**
	 * 日期:2014-03-12
	 * 作者:caolei
	 * 作用:查询充值数据
	 */
	@RequestMapping(value="/queryDelta")
	public String queryDelta(HttpServletRequest request,@ModelAttribute("pojo") PayRecordEntity pojo){
		List<PayRecordEntity> ls=payRecordService.getWhereEntity(pojo);
		request.setAttribute("ls",ls);
		request.setAttribute("pojo",pojo);
		return "queryDelta_UI";
	}
	/**
	 * 日期:2014-03-25
	 * 作者:caolei
	 * 作用:用于导出excel数据
	 */
	@RequestMapping(value="/exportPayRec")
	public @ResponseBody String exportPayRec(HttpServletResponse response, PayRecordEntity njWorkLog,
			@RequestParam(value = "createUserIds", required = false)
			String createUserIds,
			@RequestParam(value = "workLogGrantUsersName", required = false)
			String workLogGrantUsersName,
			@RequestParam(value = "strWorkEndDate", required = false)
			String strWorkEndDate, @ModelAttribute("pojo")
			PayRecordEntity pojo){
		ExcelUtil<PayRecordEntity> excelUtil = new ExcelUtil<PayRecordEntity>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();// 取得输出流
			// out.flush();
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ new String("充值记录明细报表".getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = { "订单号", "支付卡号", "康众卡号", "用户名","金额", "终端号","状态", "充值日期" };
		String[] columns = { "extOrder", "payCardNo", "memberCard","userName","fee", "operId", "states","createTime" };
		if ("on".equals(njWorkLog.getPayCardNo()))
			njWorkLog.setUserName("1");//setHospitalName("1");
		else
			njWorkLog.setPayCardNo("");
		if (StringUtils.isNotBlank(createUserIds))
			;
		// njWorkLog.setOrderNum(createUserIds.split(","));
//		User us=(User)se.getAttribute("user1");
//		if(!us.getUser_level().equals("1")){
//			pojo.setHospitalName(us.getDepartment());
//		}
		List<PayRecordEntity> dataset = payRecordService.getWhereEntity(pojo);
		try {
			excelUtil.exportExcel("充值记录明细报表", headers, columns, dataset, out,"");
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
