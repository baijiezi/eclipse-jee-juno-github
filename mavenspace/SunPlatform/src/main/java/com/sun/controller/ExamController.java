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
import com.sun.entity.AutoPayEntity;
import com.sun.entity.ExamEntity;
import com.sun.entity.User;
import com.sun.service.ExamServiceI;
import com.sun.service.UserServiceI;

@Controller
@RequestMapping("/Exam")
public class ExamController {
	
	private ExamServiceI examService;
	private UserServiceI userService;
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
	 * 作用:跳转诊疗卡记录明细页面
	 */
	@RequestMapping(value = "/examCard_UI")
	public String examCard_UI() {
		return "examCard_UI";
	}
	/**
	 * 日期:2014-09-18
	 * 作者:caolei
	 * 作用:查询诊疗卡记录
	 */
	@RequestMapping(value="/examDelta")
	public String examDelta(HttpServletRequest request,@ModelAttribute("pojo") ExamEntity pojo,HttpSession se,BaseConditionVO vo, Model model){
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		
		List<ExamEntity> ls = examService.searchUser(vo);
		Integer totalCount = examService.getCout(pojo);
		vo.setTotalCount(totalCount);
		model.addAttribute("userList", ls);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("vo", vo);
		model.addAttribute("pageSize", vo.getPageSize());
		model.addAttribute("url","Exam/examDelta.do");
		return "examCard_UI";
	}
	/**
	 * 日期:2014-10-27
	 * 作者:caolei
	 * 作用:导出诊疗卡明细
	 * @return
	 */
	@RequestMapping(value = "/exprotExcel")
	public @ResponseBody
	String exprotExcel(HttpServletResponse response, ExamEntity njWorkLog,
			@RequestParam(value = "createUserIds", required = false)
			String createUserIds,
			@RequestParam(value = "workLogGrantUsersName", required = false)
			String workLogGrantUsersName,
			@RequestParam(value = "strWorkEndDate", required = false)
			String strWorkEndDate, @ModelAttribute("pojo")
			ExamEntity pojo,HttpSession se) {
		ExcelUtil<ExamEntity> excelUtil = new ExcelUtil<ExamEntity>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();// 取得输出流
			// out.flush();
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ new String("诊疗卡记录明细".getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = {"医院名称","诊疗卡","姓名","性别","身份证","手机号","地址","终端编号","发卡日期"};
		String[] columns = {"hospitalName","medicalCardNo","patientName","patientSex","idCard","mobile","address","operId","createTime"};
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
		List<ExamEntity> dataset = examService.getWhereEntity(pojo);
		try {
			excelUtil.exportExcel("诊疗卡记录明细", headers, columns, dataset, out,"");
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
	
	public ExamServiceI getExamService() {
		return examService;
	}
	@Autowired
	public void setExamService(ExamServiceI examService) {
		this.examService = examService;
	}
	
}