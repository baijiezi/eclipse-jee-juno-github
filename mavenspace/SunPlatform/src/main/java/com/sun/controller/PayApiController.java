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
 * 日期:2014-03-17
 * 作者:caolei
 * 作用:支付接口，独立处理
 * @author Administrator
 *
 */
@Controller
public class PayApiController {
	
	private static final Logger logger = Logger.getLogger(PayApiController.class);
	
	private ConsumServiceI consumService;	//消费Service层	
	public ConsumServiceI getConsumService() {
		return consumService;
	}	
	@Autowired
	public void setConsumService(ConsumServiceI consumService) {
		this.consumService = consumService;
	}
	
	private ChargeServiceI chargeService;	//充值Service层
	
	public ChargeServiceI getChargeService() {
		return chargeService;
	}
	@Autowired
	public void setChargeService(ChargeServiceI chargeService) {
		this.chargeService = chargeService;
	}
	
	private RefundServiceI refundService;	//退费Service层
	
	public RefundServiceI getRefundService() {
		return refundService;
	}
	@Autowired
	public void setRefundService(RefundServiceI refundService) {
		this.refundService = refundService;
	}
	private QueryServiceI queryService;	//查询Service层
	
	public QueryServiceI getQueryService() {
		return queryService;
	}
	@Autowired
	public void setQueryService(QueryServiceI queryService) {
		this.queryService = queryService;
	}
	
	/**
	 * 需要修改
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:消费请求接口
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
			logger.error("请求JOSN数据读取出错:"+e);
			e.printStackTrace();
		}
		System.out.println("已经接收到消费请求，请求的数据为:"+json);
		consumService.Communication(json.toString());
		return "{'a':1}";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:自动退费接口
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
			logger.error("请求JOSN数据读取出错:"+e);
			e.printStackTrace();
		}
		System.out.println("已经接收到消费请求，请求的数据为:"+json);
		refundService.refundConn(json.toString());
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:账户充值接口
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
			logger.error("请求JOSN数据读取出错:"+e);
			e.printStackTrace();
		}
		System.out.println("已经接收到充值请求，请求的数据为:"+json);
		chargeService.Communication(json.toString());
		return "";
	}
	
	/**
	 * 日期:2014-03-17
	 * 作者:caolei
	 * 作用:查询账户余额接口
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
			logger.error("请求JOSN数据读取出错:"+e);
			e.printStackTrace();
		}
		System.out.println("已经接收到查询请求，请求的数据为:"+json);
		String mpcquery=queryService.queryBalance(json.toString());
		return mpcquery;
	}
}
