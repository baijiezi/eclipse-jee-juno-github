package com.sun.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.entity.HospitalEntity;
import com.sun.entity.User;
import com.sun.service.HospitalServiceI;
import com.sun.service.UserServiceI;
/**
 * ����:2014-03-11
 * ���ߣ�caolei
 * ���ã���Ҫ�Ե�½������ز���
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	private String keywords = "";

	private UserServiceI userService;
	private HospitalServiceI hospitalService;
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	/**
	 * ����:2014-03-11
	 * ����:����
	 * ����:��֤�û��������롢��֤��
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request,HttpSession se,@ModelAttribute("pojo") User pojo){
		//System.out.println("�û�����"+pojo.getName()+";����:"+pojo.getPassword());
		User user=userService.getUserById(pojo);
		request.setAttribute("user",user);
		se.setAttribute("user1",user);
		if(user==null){
			request.setAttribute("loginer","�û��������������");
			return new ModelAndView("login");
		}else
		return new ModelAndView("indexTest");
	}
	/**
	 * ����:2013-03-11
	 * ����:����
	 * ����:��ת��ҳ���ɼ�¼����
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	
	@RequestMapping(value="/index")
	public ModelAndView index(String username,String password,String code){
		System.out.println("ִ��index����");
		return new ModelAndView("login");
	}
	
	/**
	 * ���ڣ�2014-08-07
	 * ����:caolei
	 * ����:������ʾ����û�����
	 */
	
	@RequestMapping(value="/addUser_UI")
	public ModelAndView addUser_UI(HttpServletRequest request){
		List<HospitalEntity> ls=hospitalService.getAll();
		request.setAttribute("ls", ls);
		return new ModelAndView("addUser_UI");
	}
	/**
	 * ����:2014-08-08
	 * ����:caolei
	 * ����:��������û�
	 */
	@RequestMapping(value="/addUser")
	public void addUser(PrintWriter printWriter,@ModelAttribute("pojo")
			User pojo){
		System.out.println("ִ��addUser����");
		userService.insert(pojo);
		printWriter.write("{\"statusCode\":\"200\",\"message\":\"\u64cd\u4f5c\u6210\u529f!\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		printWriter.flush();
		printWriter.close();
	}
	/**
	 * ����:2014-08-02
	 * ����:caolei
	 * ����:��ȫ�˳�
	 */
	@RequestMapping(value="/loginOut_UI")
	public ModelAndView loginOut_UI(){
		return new ModelAndView("login");
	}
	
	/**
	 * ����:2014-10-17
	 * ����:caolei
	 * ����:��ת�޸ĵ�ǰ�û�ҳ��
	 * @return
	 */
	@RequestMapping(value="/modifyUser_UI")
	public String modifyUser_UI(HttpServletRequest request,HttpSession se){
		request.setAttribute("user",(User)se.getAttribute("user1"));
		return "modifyUser_UI";
	}
	
	

	/**
	 * ����:2014-10-17
	 * ����:caolei
	 * ����:�����û�����
	 * @return
	 */
	@RequestMapping(value="/modifyUser")
	public String modifyUser(PrintWriter printWriter,HttpSession se,@ModelAttribute("pojo") User pojo){
		pojo.setId(((User)se.getAttribute("user1")).getId());
		userService.updateByPrimaryKeySelective(pojo);
		return "userFaild";
	}

	public HospitalServiceI getHospitalService() {
		return hospitalService;
	}
	@Autowired
	public void setHospitalService(HospitalServiceI hospitalService) {
		this.hospitalService = hospitalService;
	}
}