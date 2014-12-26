package com.sun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.entity.DrugsEntity;
import com.sun.service.DrugsServiceI;

@Controller
public class DrugsController {
	private DrugsServiceI drugsService;
	/**
	 * 日期:2014-09-18
	 * 作者:caolei
	 * 作用:查询西药列表
	 */
	@RequestMapping(value="/drugsDelta")
	public String examDelta(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
		List<DrugsEntity> ls=drugsService.getWhereEntity(pojo);
		
		request.setAttribute("ls", ls);
		//request.setAttribute("pojo", pojo);
		return "drugs_UI";
	}
	
	/**
	 * 日期:2014-10-23
	 * 作者:caolei
	 * 作用:查询常规列表
	 * @return
	 */
	@RequestMapping(value="/drugsCgDelta")
	public String drugsCgDelta(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
		List<DrugsEntity> ls=drugsService.getCgEntity(pojo);
		request.setAttribute("ls", ls);
		return "drugs_UI";
	}
	
	/**
	 * 日期:2014-10-23
	 * 作者:caolei
	 * 作用:查询化验列表
	 * @return
	 */
	@RequestMapping(value="/drugsHyDelta")
	public String drugsHyDelta(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
		List<DrugsEntity> ls=drugsService.getHyEntity(pojo);
		request.setAttribute("ls", ls);
		return "drugs_UI";
	}
	
	/**
	  * 日期:2014-10-23
	 * 作者:caolei
	 * 作用:查询放射列表
	 * @return
	 */
	@RequestMapping(value="/drugsFsDelta")
	public String drugsFsDelta(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
		List<DrugsEntity> ls=drugsService.getFsEntity(pojo);
		request.setAttribute("ls", ls);
		return "drugs_UI";
	}
	
	/**
	  * 日期:2014-10-23
	 * 作者:caolei
	 * 作用:查询超声列表
	 * @return
	 */
	@RequestMapping(value="/drugsCsDelta")
	public String drugsCsDelta(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
		List<DrugsEntity> ls=drugsService.getCsEntity(pojo);
		request.setAttribute("ls", ls);
		request.setAttribute("pojo", pojo);
		return "drugs_UI";
	}

	/**
	  * 日期:2014-10-23
	 * 作者:caolei
	 * 作用:药名查询列表
	 * @return
	 */
	@RequestMapping(value="/drugsNameDelta")
	public String drugsNameDelta(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
		List<DrugsEntity> ls=drugsService.getNameEntity(pojo);
		request.setAttribute("ls", ls);
		return "drugs_UI";
	}
	
	public DrugsServiceI getDrugsService() {
		return drugsService;
	}
	@Autowired
	public void setDrugsService(DrugsServiceI drugsService) {
		this.drugsService = drugsService;
	}
	
	
	
	/**
	  * 日期:2014-10-23
	 * 作者:caolei
	 * 作用:测试批量插入
	 * @return
	 */
	@RequestMapping(value="/batchInsert")
	public String batchInsert(HttpServletRequest request,@ModelAttribute("pojo") DrugsEntity pojo){
////		List<DrugsEntity> ls=drugsService.getFsEntity(pojo);
////		request.setAttribute("ls", ls);
////		return "drugs_UI";
//		List<DrugsEntity> orders = new ArrayList<DrugsEntity>();
//        for(int a=0;a<=100;a++){
//        	DrugsEntity dr1=new DrugsEntity();
//        	dr1.setMedicineCode(""+a);
//        	drugsService.insert(dr1);
//        }
//		//int d=drugsService.batchInsertB2B(orders);
//		//System.out.println(d);
		return null;
	}
}