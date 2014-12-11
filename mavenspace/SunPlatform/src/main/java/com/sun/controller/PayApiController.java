package com.sun.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.service.ChargeServiceI;
import com.sun.service.ConsumServiceI;
import com.sun.service.QueryServiceI;
import com.sun.service.RefundServiceI;
/**
 * ����:2014-03-17
 * ����:caolei
 * ����:֧���ӿڣ���������
 * @author Administrator
 *
 */
@Controller
public class PayApiController {
	
	private static final Logger logger = Logger.getLogger(PayApiController.class);
	
	private ConsumServiceI consumService;	//����Service��	
	public ConsumServiceI getConsumService() {
		return consumService;
	}	
	@Autowired
	public void setConsumService(ConsumServiceI consumService) {
		this.consumService = consumService;
	}
	
	private ChargeServiceI chargeService;	//��ֵService��
	
	public ChargeServiceI getChargeService() {
		return chargeService;
	}
	@Autowired
	public void setChargeService(ChargeServiceI chargeService) {
		this.chargeService = chargeService;
	}
	
	private RefundServiceI refundService;	//�˷�Service��
	
	public RefundServiceI getRefundService() {
		return refundService;
	}
	@Autowired
	public void setRefundService(RefundServiceI refundService) {
		this.refundService = refundService;
	}
	private QueryServiceI queryService;	//��ѯService��
	
	public QueryServiceI getQueryService() {
		return queryService;
	}
	@Autowired
	public void setQueryService(QueryServiceI queryService) {
		this.queryService = queryService;
	}
	
	/**
	 * ��Ҫ�޸�
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:��������ӿ�
	 * @return
	 */
	@RequestMapping(value="/sunConsume")
	@ResponseBody
	public String sunConsume(HttpServletRequest request,HttpSession session){
		System.out.println("X-Date:"+request.getHeader("X-Date"));
		System.out.println("ApiKey:"+request.getHeader("ApiKey"));
		String consumeline=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((consumeline=reader.readLine())!=null){
				json.append(consumeline);
			}
		} catch (IOException e) {
			logger.error("����JOSN���ݶ�ȡ����:"+e);
			e.printStackTrace();
		}
		System.out.println("�Ѿ����յ������������������Ϊ:"+json);
		consumService.Communication(json.toString());
		return "{'a':1}";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:�Զ��˷ѽӿ�
	 */
	@RequestMapping(value="/sunRefund")
	@ResponseBody
	public String sunRefund(HttpServletRequest request,HttpSession session){
		System.out.println("X-Date:"+request.getHeader("X-Date"));
		System.out.println("ApiKey:"+request.getHeader("ApiKey"));
		String refundLine=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((refundLine=reader.readLine())!=null){
				json.append(refundLine);
			}
		} catch (IOException e) {
			logger.error("����JOSN���ݶ�ȡ����:"+e);
			e.printStackTrace();
		}
		System.out.println("�Ѿ����յ������������������Ϊ:"+json);
		refundService.refundConn(json.toString());
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:�˻���ֵ�ӿ�
	 */
	@RequestMapping(value="/sunCharge")
	@ResponseBody
	public Object sunCharge(HttpServletRequest request,HttpSession session){
		//String requestJson=request.getParameter("json");
		System.out.println("X-Date:"+request.getHeader("X-Date"));
		System.out.println("ApiKey:"+request.getHeader("ApiKey"));
		String refundLine=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((refundLine=reader.readLine())!=null){
				json.append(refundLine);
			}
		} catch (IOException e) {
			logger.error("����JOSN���ݶ�ȡ����:"+e);
			e.printStackTrace();
		}
		System.out.println("�Ѿ����յ���ֵ�������������Ϊ:"+json);
		chargeService.Communication(json.toString());
		return "";
	}
	
	/**
	 * ����:2014-03-17
	 * ����:caolei
	 * ����:��ѯ�˻����ӿ�
	 */
	@RequestMapping(value="/sunQueryBalance")
	@ResponseBody
	public String sunQueryBalance(HttpServletRequest request,HttpSession session){
		System.out.println("X-Date:"+request.getHeader("X-Date"));
		System.out.println("ApiKey:"+request.getHeader("ApiKey"));
		String refundLine=null;
		StringBuffer json=new StringBuffer();
		try {
			BufferedReader reader=request.getReader();
			while((refundLine=reader.readLine())!=null){
				json.append(refundLine);
			}
		} catch (IOException e) {
			logger.error("����JOSN���ݶ�ȡ����:"+e);
			e.printStackTrace();
		}
		System.out.println("�Ѿ����յ���ѯ�������������Ϊ:"+json);
		String mpcquery=queryService.queryBalance(json.toString());
		return mpcquery;
	}
}
