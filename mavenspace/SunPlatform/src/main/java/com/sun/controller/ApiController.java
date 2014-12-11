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
import com.sun.entity.AutoPayEntity;
import com.sun.entity.ExamEntity;
import com.sun.entity.FrontendPayRecordEntity;
import com.sun.entity.TransActionEntity;
import com.sun.service.AutoPayServiceI;
import com.sun.service.ExamServiceI;
import com.sun.service.FrontendPayRecordServiceI;
import com.sun.service.MannerServiceI;
import com.sun.service.TransActionServiceI;
import com.sun309.frontend.db.model.FrontendBookingOrderModel;
import com.sun309.frontend.db.model.FrontendConfirmAutoPayModel;
import com.sun309.frontend.db.model.FrontendPayRecordModel;
import com.sun309.frontend.db.model.FrontendRegUserModel;

/**
 * ����:2014-03-17
 * ����:caolei
 * ����:���ڸ������ն����ӿ�
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
	public AutoPayServiceI getAutoPayService() {
		return autoPayService;
	}
	@Autowired
	public void setAutoPayService(AutoPayServiceI autoPayService) {
		this.autoPayService = autoPayService;
	}

	/**
	 * ���ڣ�2014-03-17
	 * ����:caolei
	 * ����:���ڽ���ǰ�û���ѯ�Һſ������Ͻӿ�
	 * 
	 * @return
	 */
	@RequestMapping(value="/sunGetDept")
	@ResponseBody
	public String sunGetDept(){
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:����ͬ�������ɷ�
	 */
	@RequestMapping(value="/sunApplyPay")
	@ResponseBody
	public String sunApplyPay(){
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:����ǰ�û���ѯ�Ű���Դ
	 */
	@RequestMapping(value="/sunGetShedule")
	@ResponseBody
	public String sunGetShedule(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===�յ����󣬿�ʼ����====");
		try{
		try{
			InputStream inputStream = request.getInputStream();
	        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
	        Object obj = objectInputStream.readObject();
	        objectInputStream.close();
	        System.out.println("���յ�����"+obj + "");
	        //System.out.println("====="+((FrontendBookingOrderModel)obj).getAddress());
			}catch(Exception ex){
				ex.printStackTrace();
				isIE = true;
			}
			 if (!isIE) {
	                // ���
	                ServletOutputStream outputStream = response.getOutputStream();
	                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
	                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
	                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"���ճɹ�\"}");
	                objectOutputStream.close();
	            }else {
	                response.setContentType("text/html;charset=UTF-8");
	                PrintWriter out=response.getWriter();   
	                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"����ʧ��\"}"); 
	                out.flush();
	                out.close();
	            }
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:ǰ�û��¹ҺŶ���
	 */
	@RequestMapping(value="/sunBooking")
	@ResponseBody
	public String sunBooking(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===�յ��ҺŶ�������ʼ����====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("���յ�����"+obj + "");
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
		                // ���
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"���ճɹ�\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"����ʧ��\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:��ȡ�û�����
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
			//logger.error("����JOSN���ݶ�ȡ����:"+e);
			e.printStackTrace();
		}
		System.out.println("sunBooking�Ѿ����յ���ѯ�������������Ϊ:"+json);
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:�Ǽ��û����� WX1401032305
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
		System.out.println("sunBooking�Ѿ����յ���ѯ�������������Ϊ:"+json);
		return "";
	}
	
	/**
	 * ���ڣ�2014-03-17
	 * ����:caolei
	 * ����:δ���ﶩ���б�
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
			//logger.error("����JOSN���ݶ�ȡ����:"+e);
			e.printStackTrace();
		}
		System.out.println("sunBooking�Ѿ����յ���ѯ�������������Ϊ:"+json);
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:�����ɷѶ����б�
	 * @return
	 */
	public String getAutoPaySuccess(){
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:ȷ�������ɷѳɹ�
	 */
	@RequestMapping(value="/confirmAutoPaySuccess")
	@ResponseBody
	public String confirmAutoPaySuccess(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===�յ��ҺŶ�������ʼ����====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("���յ�����"+obj + "");
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
		                // ���
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"���ճɹ�\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"����ʧ��\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	
	/**
	 * ���ڣ�2014-09-19
	 * ���ߣ�caolei
	 * ����:ͬ��������¼��ϸ
	 * @return
	 */
	@RequestMapping(value="/examApi")
	@ResponseBody
	public String examApi(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===�յ��ҺŶ�������ʼ����====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("���յ�����"+obj + "");
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
		                // ���
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"���ճɹ�\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"����ʧ��\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}

	/**
	 * ���ڣ�2014-10-08
	 * ���ߣ�caolei
	 * ����:ͬ���ɷѼ�¼
	 * @return
	 */
	@RequestMapping(value="/payRecordApi")
	@ResponseBody
	public String payRecordApi(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===�յ��ҺŶ�������ʼ����====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("���յ�����"+obj + "");
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
		                // ���
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"���ճɹ�\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"����ʧ��\"}"); 
		                out.flush();
		                out.close();
		            }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
	}
	
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:ǰ�û��¹ҺŶ���
	 */
	@RequestMapping(value="/sunManner")
	@ResponseBody
	public String sunManner(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		boolean isIE = false;
		System.out.println("===�յ��ҺŶ�������ʼ����====");
		try{
			try{
				InputStream inputStream = request.getInputStream(); 
		        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
		        Object obj = objectInputStream.readObject();
		        objectInputStream.close();
		        System.out.println("���յ�����"+obj + "");
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
		                // ���
		                ServletOutputStream outputStream = response.getOutputStream();
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		                objectOutputStream.writeObject("{\"Error\":\"0\",\"ErrorMessage\":\"���ճɹ�\"}");
		                objectOutputStream.close();
		            }else {
		                response.setContentType("text/html;charset=UTF-8");
		                PrintWriter out=response.getWriter();
		                out.print("{\"Error\":\"1\",\"ErrorMessage\":\"����ʧ��\"}"); 
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
		
}
