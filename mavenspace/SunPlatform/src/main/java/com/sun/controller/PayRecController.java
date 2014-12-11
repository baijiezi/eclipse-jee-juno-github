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
 * ����:2014-03-11
 * ����:caolei
 * ����:��Ҫ��Գ�ֵ��¼��ϸ����
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/payrecord")
public class PayRecController {
	private UserServiceI userService;
	private PayRecordServiceI payRecordService;	//��ֵ��¼��ѯservice��
	
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
	 * ���ڣ�2014-03-12
	 * ����:caolei
	 * ����:��ת��ѯ��ֵҳ��
	 */
	@RequestMapping(value="/queryDelta_UI")
	public String queryDelta_UI(){
		return "queryDelta_UI";
	}
	/**
	 * ����:2014-03-12
	 * ����:caolei
	 * ����:��ѯ��ֵ����
	 */
	@RequestMapping(value="/queryDelta")
	public String queryDelta(HttpServletRequest request,@ModelAttribute("pojo") PayRecordEntity pojo){
		List<PayRecordEntity> ls=payRecordService.getWhereEntity(pojo);
		request.setAttribute("ls",ls);
		request.setAttribute("pojo",pojo);
		return "queryDelta_UI";
	}
	/**
	 * ����:2014-03-25
	 * ����:caolei
	 * ����:���ڵ���excel����
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
			out = response.getOutputStream();// ȡ�������
			// out.flush();
			response.reset();// ��������
			response.setHeader("Content-disposition", "attachment; filename="+ new String("��ֵ��¼��ϸ����".getBytes("GB2312"), "8859_1")+ ".xls");// �趨����ļ�ͷ
			response.setContentType("application/msexcel");// �����������
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = { "������", "֧������", "���ڿ���", "�û���","���", "�ն˺�","״̬", "��ֵ����" };
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
			excelUtil.exportExcel("��ֵ��¼��ϸ����", headers, columns, dataset, out,"");
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
