package com.sun.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.common.BaseConditionVO;
import com.sun.entity.AutoPayEntity;
import com.sun.entity.ExamEntity;
import com.sun.entity.MannerEntity;
import com.sun.entity.User;
import com.sun.service.MannerServiceI;

@Controller
@RequestMapping("/manner")
public class MannerController {
	private MannerServiceI mannerService;
	/**
	 * ����:2014-10-14 
	 * ����:caolei 
	 * ����:��ת�û�����ҳ��
	 */
	@RequestMapping(value = "/manner_UI")
	public String manner_UI() {
		return "manner_UI";
	}
	/**
	 * ���ڣ�2014-10-14
	 * ����:caolei
	 * ����:�����û��ύ����
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/manner_IN")
	public String manner_IN(HttpServletRequest request,
			@ModelAttribute("pojo")
			MannerEntity pojo,HttpSession se) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		pojo.setMannerTime(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		try {
			pojo.setPatientName(java.net.URLDecoder.decode(pojo.getPatientName(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("URLDecoder������");
			e.printStackTrace();
		}
		int a=mannerService.insert(pojo);
		if(a==1){
			return "paysucess";
		}else
		return "wrongpassword";
	}
	
	/**
	 * ����:2014-10-14
	 * ����:caolei
	 * ����:��ѯ�û���������
	 * @return
	 */
	@RequestMapping(value = "/mannerDelta")
	public String mannerDelta(HttpServletRequest request,
			@ModelAttribute("pojo")
			MannerEntity pojo,HttpSession se,BaseConditionVO vo, Model model) {
		User us=(User)se.getAttribute("user1");
		if(!us.getUser_level().equals("1")){
			pojo.setHospitalName(us.getDepartment());
		}
		List<MannerEntity> ls = mannerService.searchUser(vo);
		Integer totalCount = mannerService.getCout(pojo);
		vo.setTotalCount(totalCount);
		model.addAttribute("userList", ls);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("vo", vo);
		model.addAttribute("pageSize", vo.getPageSize());
		model.addAttribute("url","manner/mannerDelta.do");
		
//		
//		
//		List<MannerEntity> ls=mannerService.getWhereEntity(pojo);
//		request.setAttribute("ls",ls);
//		request.setAttribute("pojo", pojo);
		return "manner_UI";
	}
	
	public MannerServiceI getMannerService() {
		return mannerService;
	}
	@Autowired
	public void setMannerService(MannerServiceI mannerService) {
		this.mannerService = mannerService;
	}
	
}
