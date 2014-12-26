package com.sun.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.common.ConnUtil;
import com.sun.entity.AutoDetailEntity;
import com.sun.entity.AutoPayEntity;
import com.sun.entity.ExamEntity;
import com.sun.entity.FrontendPayRecordEntity;
import com.sun.entity.TransActionEntity;
import com.sun.service.AutoDetailServiceI;
import com.sun.service.AutoPayServiceI;
import com.sun.service.ExamServiceI;
import com.sun.service.FrontendPayRecordServiceI;
import com.sun.service.MannerServiceI;
import com.sun.service.TransActionServiceI;
import com.sun309.frontend.db.model.FrontendBookingOrderModel;
import com.sun309.frontend.db.model.FrontendConfirmAutoPayModel;
import com.sun309.frontend.db.model.FrontendGetAutoPayOrdersModel;
import com.sun309.frontend.db.model.FrontendPayRecordModel;
import com.sun309.frontend.db.model.FrontendRegUserModel;

/**
 * 日期:2014-03-17
 * 作者:caolei
 * 作用:用于各个接收订单接口
 * @author Administrator
 *
 */
@Controller
public class ApiController {
	private TransActionServiceI transActionService;
	private ExamServiceI examService;
	private AutoPayServiceI autoPayService;
	private FrontendPayRecordServiceI frontendPayRecordServiceI;
	private MannerServiceI mannerService;
	private AutoDetailServiceI autoDetailService;
	public AutoPayServiceI getAutoPayService() {
		return autoPayService;
	}
	@Autowired
	public void setAutoPayService(AutoPayServiceI autoPayService) {
		this.autoPayService = autoPayService;
	}

	/**
	 * 日期：2014-03-17
	 * 作者:caolei
	 * 作用:用于接收前置机查询挂号科室资料接口
	 * 
	 * @return
	 */
	@RequestMapping(value="/sunGetDept")
	@ResponseBody
	public String sunGetDept(){
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:用于同步订单缴费
	 */
	@RequestMapping(value="/sunApplyPay")
	@ResponseBody
	public String sunApplyPay(){
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:用于前置机查询排班资源
	 */
	@RequestMapping(value="/sunGetShedule")
	@ResponseBody
	public String sunGetShedule(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到请求，开始处理====");
		try{
		try{
			InputStream inputStream = request.getInputStream();
	        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
	        Object obj = objectInputStream.readObject();
	        objectInputStream.close();
	        System.out.println("接收到对象："+obj + "");
			}catch(Exception ex){
				ex.printStackTrace();
				isIE = true;
			}
			 if (!isIE) {
	                // 输出
	                ServletOutputStream outputStream = response.getOutputStream();
	                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
	                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
	                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
	                objectOutputStream.close();
	            }else {
	                response.setContentType("text/html;charset=UTF-8");
	                PrintWriter out=response.getWriter();   
	                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}"); 
	                out.flush();
	                out.close();
	            }
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:前置机下挂号订单
	 */
	@RequestMapping(value="/sunBooking")
	@ResponseBody
	public String sunBooking(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到挂号订单请求开始处理====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("接收到对象："+obj + "");
		        TransActionEntity pojo=ConnUtil.bookingconvert((FrontendBookingOrderModel)obj);
		        TransActionEntity pojonull=transActionService.getBookingById(pojo);
		        if(pojonull==null){
		        	transActionService.insert(pojo);
		        }
				}catch(Exception ex){
					ex.printStackTrace();
					isIE = true;
				}
				 if (!isIE) {
		                // 输出
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}");
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:获取用户资料
	 */
	@RequestMapping(value="/sunGetUserInfo")
	@ResponseBody
	public String sunGetUserInfo(HttpServletRequest request,HttpSession session){
		String refundLine=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((refundLine=reader.readLine())!=null){
				json.append(refundLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sunBooking已经接收到查询请求，请求的数据为:"+json);
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:登记用户资料 WX1401032305
	 */
	@RequestMapping(value="/sunRegUserInfo")
	@ResponseBody
	public String sunRegUserInfo(HttpServletRequest request,HttpSession session){
		String refundLine=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((refundLine=reader.readLine())!=null){
				json.append(refundLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sunBooking已经接收到查询请求，请求的数据为:"+json);
		return "";
	}
	
	/**
	 * 日期：2014-03-17
	 * 作者:caolei
	 * 作用:未就诊订单列表
	 */
	
	public String getAutoPayOrders(HttpServletRequest request,HttpSession session){
		String refundLine=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((refundLine=reader.readLine())!=null){
				json.append(refundLine);
			}
		} catch (IOException e) {
			//logger.error("请求JOSN数据读取出错:"+e);
			e.printStackTrace();
		}
		System.out.println("sunBooking已经接收到查询请求，请求的数据为:"+json);
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:自助缴费订单列表
	 * @return
	 */
	public String getAutoPaySuccess(){
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:确认自助缴费成功
	 */
	@RequestMapping(value="/confirmAutoPaySuccess")
	@ResponseBody
	public String confirmAutoPaySuccess(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到挂号订单请求开始处理====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("接收到对象："+ obj + "");
		        AutoPayEntity pojo=ConnUtil.autoPayConvert((FrontendConfirmAutoPayModel)obj);
		        AutoPayEntity pojonull = autoPayService.getAutoById(pojo);
		        if(pojonull==null){
		        	autoPayService.insert(pojo);
		        }
				}catch(Exception ex){
					ex.printStackTrace();
					isIE = true;
				}
				 if (!isIE) {
		                // 输出
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	
	/**
	 * 日期：2014-09-19
	 * 作者：caolei
	 * 作用:同步发卡记录明细
	 * @return
	 */
	@RequestMapping(value="/examApi")
	@ResponseBody
	public String examApi(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到挂号订单请求开始处理====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("接收到对象："+obj + "");
		        ExamEntity pojo=ConnUtil.examconvert((FrontendRegUserModel)obj);
		        ExamEntity pojonull = examService.getExamById(pojo);
		        if(pojonull==null){
		        	examService.insert(pojo);
		        }		        
				}catch(Exception ex){
					ex.printStackTrace();
					isIE = true;
				}
				 if (!isIE) {
		                // 输出
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}

	/**
	 * 日期：2014-10-08
	 * 作者：caolei
	 * 作用:同步缴费记录
	 * @return
	 */
	@RequestMapping(value="/payRecordApi")
	@ResponseBody
	public String payRecordApi(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到挂号订单请求开始处理====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("接收到对象："+obj + "");
		        FrontendPayRecordEntity pojo=ConnUtil.payrecordConvert((FrontendPayRecordModel)obj);
		        FrontendPayRecordEntity pojonull = frontendPayRecordServiceI.getExamById(pojo);
		        if(pojonull==null){
		        	frontendPayRecordServiceI.insert(pojo);
		        }		        
				}catch(Exception ex){
					ex.printStackTrace();
					isIE = true;
				}
				 if (!isIE) {
		                // 输出
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:前置机下挂号订单
	 */
	@RequestMapping(value="/sunManner")
	@ResponseBody
	public String sunManner(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到挂号订单请求开始处理====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("接收到对象："+obj + "");
		        TransActionEntity pojo=ConnUtil.bookingconvert((FrontendBookingOrderModel)obj);
		        TransActionEntity pojonull=transActionService.getBookingById(pojo);
		        if(pojonull==null){
		        	transActionService.insert(pojo);
		        }
				}catch(Exception ex){
					ex.printStackTrace();
					isIE = true;
				}
				 if (!isIE) {
		                // 输出
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	@RequestMapping(value="/detailApi")
	@ResponseBody
	public String detailApi(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===收到挂号订单请求detailApi开始处理====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("接收到对象："+obj + "");
		        AutoDetailEntity pojo=ConnUtil.detailConvert((FrontendGetAutoPayOrdersModel)obj);
		        AutoDetailEntity pojonull=autoDetailService.getAutoById(pojo);
		        if(pojonull==null){
		        	autoDetailService.insert(pojo);
		        }else{
		        	autoDetailService.updateByPrimaryKeySelective(pojo);
		        }
				}catch(Exception ex){
					ex.printStackTrace();
					isIE = true;
				}
				 if (!isIE) {
		                // 输出
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"接收成功\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"接收失败\"}");
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	
	public TransActionServiceI getTransActionService() {
		return transActionService;
	}
	
	@Autowired
	public void setTransActionService(TransActionServiceI transActionService) {
		this.transActionService = transActionService;
	}

	public ExamServiceI getExamService() {
		return examService;
	}
	
	@Autowired
	public void setExamService(ExamServiceI examService) {
		this.examService = examService;
	}
	public FrontendPayRecordServiceI getFrontendPayRecordServiceI() {
		return frontendPayRecordServiceI;
	}
	@Autowired
	public void setFrontendPayRecordServiceI(
			FrontendPayRecordServiceI frontendPayRecordServiceI) {
		this.frontendPayRecordServiceI = frontendPayRecordServiceI;
	}
	public MannerServiceI getMannerService() {
		return mannerService;
	}
	@Autowired
	public void setMannerService(MannerServiceI mannerService) {
		this.mannerService = mannerService;
	}
	public AutoDetailServiceI getAutoDetailService() {
		return autoDetailService;
	}
	@Autowired
	public void setAutoDetailService(AutoDetailServiceI autoDetailService) {
		this.autoDetailService = autoDetailService;
	}
		
}
