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
	 * 日期:2014-10-14 
	 * 作者:caolei 
	 * 作用:跳转用户评价页面
	 */
	@RequestMapping(value = "/manner_UI")
	public String manner_UI() {
		return "manner_UI";
	}
	/**
	 * 日期：2014-10-14
	 * 作者:caolei
	 * 作用:插入用户提交数据
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/manner_IN")
	public String manner_IN(HttpServletRequest request,
			@ModelAttribute("pojo")
			MannerEntity pojo,HttpSession se) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		pojo.setMannerTime(df.format(new Date()));// new Date()为获取当前系统时间
		try {
			pojo.setPatientName(java.net.URLDecoder.decode(pojo.getPatientName(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("URLDecoder出错了");
			e.printStackTrace();
		}
		int a=mannerService.insert(pojo);
		if(a==1){
			return "paysucess";
		}else
		return "wrongpassword";
	}
	
	/**
	 * 日期:2014-10-14
	 * 作者:caolei
	 * 作用:查询用户评价数据
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
