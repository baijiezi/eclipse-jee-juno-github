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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.common.BaseConditionVO;
import com.sun.common.ExcelUtil;
import com.sun.common.Page;
import com.sun.entity.TransActionEntity;
import com.sun.entity.User;
import com.sun.service.TransActionServiceI;
import com.sun.service.UserServiceI;

/**
 * ����:2014-03-11
 * ����:caolei
 * ����:��Ҫ��Զ�����ϸ��ѯ
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/transorder")
public class TransController extends BaseController {
	
	List<TransActionEntity> ls=null;
	private UserServiceI userService;
	private TransActionServiceI transActionServiceI;
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	/**
	 * ����:2014-03-11
	 * ����:caolei
	 * ����:��ת������ϸ��ѯҳ��
	 */
	@RequestMapping(value="/transAction_UI",method=RequestMethod.GET)
	public String transAction_UI(){
		return "transAction_UI";
	}
	
	/**
	 * ����:2014-03-11
	 * ����:caolei
	 * ����:��ѯ������ϸ
	 * @return
	 */
	@RequestMapping(value="/transAction_query")
	public String transAction_Qury(HttpServletRequest request,@ModelAttribute("pojo") TransActionEntity pojo,HttpSession se,BaseConditionVO vo, Model model){
		List<TransActionEntity> userList = transActionServiceI.searchUser(vo);
		Integer totalCount = transActionServiceI.getCout(pojo);
		vo.setTotalCount(totalCount);
		model.addAttribute("userList", userList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("vo", vo);
		model.addAttribute("pageSize", vo.getPageSize());
		model.addAttribute("url","transorder/transAction_query.do");
		return "transAction_UI";
	}
	/**
	 * ����:2014-03-25
	 * ����:caolei
	 * ����:���ڵ���excel����
	 */
	@RequestMapping(value="/exportTrans")
	public @ResponseBody String exportTrans(HttpServletResponse response, TransActionEntity njWorkLog,
			@RequestParam(value = "createUserIds", required = false)
			String createUserIds,
			@RequestParam(value = "workLogGrantUsersName", required = false)
			String workLogGrantUsersName,
			@RequestParam(value = "strWorkEndDate", required = false)
			String strWorkEndDate, @ModelAttribute("pojo")
			TransActionEntity pojo,HttpSession se){
		ExcelUtil<TransActionEntity> excelUtil = new ExcelUtil<TransActionEntity>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();// ȡ�������
			// out.flush();
			response.reset();// ��������
			response.setHeader("Content-disposition", "attachment; filename="+ new String("ԤԼ�Һ���ϸ����".getBytes("GB2312"), "8859_1")+ ".xls");// �趨����ļ�ͷ
			response.setContentType("application/msexcel");// �����������
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] headers = {"ҽԺ����","������","���ƿ�","�Һŷ�","�µ�ʱ��","����ʱ��","����","����","ҽ��","���֤","�ֻ�����","��������","�Ա�","��ַ","֧������","֧������"};
		String[] columns = {"hospitalName","orderNo", "medicalCardNo","fee","orderTime", "visitTime", "serialNo","deptName","doctorName","idCard","mobile","patientName","patientSex","address","payCardNo","settlementType"};
		if ("on".equals(njWorkLog.getPayCardNo()))
			njWorkLog.setUserName("1");//setHospitalName("1");
		else
			njWorkLog.setPayCardNo("");
		if (StringUtils.isNotBlank(createUserIds));
		// njWorkLog.setOrderNum(createUserIds.split(","));
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		List<TransActionEntity> dataset = transActionServiceI.getWhereEntity(pojo);
		try {
			excelUtil.exportExcel("ԤԼ�Һ���ϸ����", headers, columns, dataset, out,"");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TransActionServiceI getTransActionServiceI() {
		return transActionServiceI;
	}
	@Autowired
	public void setTransActionServiceI(TransActionServiceI transActionServiceI) {
		this.transActionServiceI = transActionServiceI;
	}

	public List<TransActionEntity> getLs() {
		return ls;
	}

	public void setLs(List<TransActionEntity> ls) {
		this.ls = ls;
	}

}
