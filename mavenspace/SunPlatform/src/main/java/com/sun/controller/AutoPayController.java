package com.sun.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sun.entity.AutoPayEntity;
import com.sun.entity.User;
import com.sun.service.AutoPayServiceI;
import com.sun.service.UserServiceI;

@Controller
@RequestMapping("/AutoPay")
public class AutoPayController {

	private AutoPayServiceI autopayService;
	private UserServiceI userService;

	public AutoPayServiceI getAutopayService() {
		return autopayService;
	}

	@Autowired
	public void setAutopayService(AutoPayServiceI autopayService) {
		this.autopayService = autopayService;
	}

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	/**
	 * 日期:2014-03-11 
	 * 作者:caolei 
	 * 作用:跳转自动补缴订单页面
	 */
	@RequestMapping(value = "/autoPayment_UI")
	public String autoPayment_UI() {
		return "autoPayment_UI";
	}

	/**
	 * 日期:2014-03-11 
	 * 作者:caolei 
	 * 作用:查询自动补缴数据
	 */
	@RequestMapping(value = "/autoPayment")
	public String autoPayment(HttpServletRequest request,
			@ModelAttribute("pojo")
			AutoPayEntity pojo,HttpSession se,BaseConditionVO vo, Model model) {
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		
		List<AutoPayEntity> ls = autopayService.searchAutoPay(vo);
		Integer totalCount = autopayService.getCout(pojo);
		vo.setTotalCount(totalCount);
		model.addAttribute("userList", ls);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("vo", vo);
		model.addAttribute("pageSize", vo.getPageSize());
		model.addAttribute("url","AutoPay/autoPayment.do");
		return "autoPayment_UI";
	}

	/**
	 * 日期:2014-03-25 作者:caolei 作用:用于导入excel数据
	 */
	@RequestMapping(value = "/exportAutoPay")
	public void exportAutoPay(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		String filePath = "D:\\data.xlsx";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ctxPath = "已退货-"+ sdf.format(new Date(System.currentTimeMillis())).toString()+ ".xls";
		response.setHeader("Content-Disposition", "attachment;fileName="+ ctxPath);
		OutputStream os = null;
		InputStream inputStream = null;
		try {
			File file = new File(filePath);
			System.out.println(file.getAbsolutePath());
			inputStream = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
			}
		}
	}
	@RequestMapping(value = "/exprotExcel")
	public @ResponseBody
	String exprotExcel(HttpServletResponse response, AutoPayEntity njWorkLog,
			@RequestParam(value = "createUserIds", required = false)
			String createUserIds,
			@RequestParam(value = "workLogGrantUsersName", required = false)
			String workLogGrantUsersName,
			@RequestParam(value = "strWorkEndDate", required = false)
			String strWorkEndDate, @ModelAttribute("pojo")
			AutoPayEntity pojo,HttpSession se) {
		ExcelUtil<AutoPayEntity> excelUtil = new ExcelUtil<AutoPayEntity>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();// 取得输出流
			// out.flush();
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ new String("自助缴费报表".getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = { "医院名称", "诊疗卡", "身份证", "订单号", "支付金额", "支付卡号","支付类型", "支付流水", "支付时间" };
		String[] columns = { "hospitalName", "medicalCardNo", "idCard","orderNo", "payMoney", "payCardNo", "payType", "payTradeLine","payDate" };
		if ("on".equals(njWorkLog.getIdCard()))
			njWorkLog.setHospitalName("1");
		else
			njWorkLog.setIdCard("");
		if (StringUtils.isNotBlank(createUserIds))
			;
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		List<AutoPayEntity> dataset = autopayService.getWhereEntity(pojo);
		try {
			excelUtil.exportExcel("自助缴费明细报表", headers, columns, dataset, out,"");
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
