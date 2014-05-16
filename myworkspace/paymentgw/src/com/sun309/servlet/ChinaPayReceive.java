package com.sun309.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun309.dto.ChinaPayResult;
import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.impl.BaseResultProcess;
import com.sun309.impl.ChinaPayBaseRequest;
import com.sun309.impl.NetPayLogServiceImpl;
import com.sun309.service.LogService;
import com.sun309.util.Constants;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.Validate;
import com.sun309.util.Validater;

public class ChinaPayReceive extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;
	private ChinaPayResult result = null;
	private Logger 			syslog 	= Logger.getLogger(ChinaPayReceive.class);
	private LogService 		logger 	= (LogService)Factory.create(NetPayLogServiceImpl.class);
	private BaseResultProcess process = (BaseResultProcess)Factory.create(BaseResultProcess.class);
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/** 取得外部输出流 用于响应POST结果请求 */
		PrintWriter out= response.getWriter();
		try
		{
			/** 初始化验证工具 */
			Validate			validate	= (Validate)Factory.create(Validate.class);
			Validater			validater	= (Validater)Factory.create(Validater.class);
			
			/** 新建数据接收类实例 */
			result = new ChinaPayResult();
			
			/** 直接取数据 */
			result.setTransDate(request.getParameter("transdate").toString());
			result.setMerId(request.getParameter("merid").toString());
			result.setPriv1(request.getParameter("Priv1").toString());
			result.setOrderId(request.getParameter("orderno").toString());
			result.setTransType(request.getParameter("transtype").toString());
			result.setCuryId(request.getParameter("currencycode").toString());
			result.setOrderStatus(request.getParameter("status").toString());
			result.setChkValue(request.getParameter("checkvalue").toString());
			Double fee = Double.valueOf(request.getParameter("amount").toString());
			result.setTransAmt(fee.longValue());
			
			result.setOutTradeNo(NetPayFormatTools.ChinaPayBackID(result.getOrderId()));
			
			/** 设置验证工具 */
			result.setValidator(validate);
			/** 写数据接收的初始化日志 */
			writeLog(result.getInitInfo());
			
			/** 验证数据是否为空 */
			if(validater.validate(result))
			{
				/** 写入Log 说明不为空验证成功 */
				writeLog(new StringBuilder("对初步接收的数据进行非空验证成功 详细::").append(result.getValidateInfo()));
				
				/** 初始化信息处理基础类 并SET数据,用于验证数据的有效性*/
				ChinaPayBaseRequest 	base 		= new ChinaPayBaseRequest();
				base.setMerId(result.getMerId());
				base.setOrderId(result.getOrderId());
				base.setOutTradeNo(result.getOutTradeNo());
				base.setTotalFee(result.getTransAmt());
				base.setCuryId(result.getCuryId());
				base.setTransDate(result.getTransDate());
				base.setTransType(result.getTransType());
				base.setOrderStatus(result.getOrderStatus());
				base.setCheckValue(result.getChkValue());
				if(base.verifyReceieData())
				{
					/** 保存历史记录数据 到详细信息表 保存数据*/
					result.setGateId(request.getParameter("GateId").toString());
					process.processSaveDetail(result,Constants.CHINA_PAY);
					
					/** 按实际支付系统外部订单号[多支付流水号] 查找 这个订单的初始化交易记录*/
					NetPayData data = process.processAfterValidate(result);
					
					/** 为空验证  记录超时后再收到数据的异常情况 */
					if(data == null)
					{
						process.processExceptionData(result);
					}
					else
					{
						/** 交易状态验证 */
						if(result.validateTradeStatus())
						{
							/** 交易状态验证成功	  再次验证总金额 */
							process.processValidateSuccess(result,data);
						}
						else
						{
							/** 交易状态验证失败	 再次验证总金额 	*/
							process.processValidateFail(result,data);
						}
					}
					String resultForChinaPay = "success";
					writeLog(new StringBuilder("向银联网银返回数据接收并处理成功 多支付流水号：").append(result.getOutTradeNo())
							.append("返回的数据：").append(resultForChinaPay));
					out.print(resultForChinaPay);
				}
				else
				{
					writeLog(new StringBuilder("数据真实性验证失败 信息").append(base.getValidateInfo()));
					out.print("fail");
				}
				base = null;
			}
			else
			{
				writeLog(new StringBuilder("接收到的数据空指针验证失败 信息").append(result.getValidateInfo()));
				out.print("fail");
			}
		}
		catch(Exception $e)
		{
			out.print("fail");
			writeLog(new StringBuilder("处理过程中出现异常--名称").append($e.getMessage())
					.append($e.getStackTrace()).append($e.getLocalizedMessage()).append($e.getClass()));
		}
		finally
		{
			out.flush();
			result=null;
			if(out != null)
			{
				out.close();
				out = null;
			}
			logger.write();
		}
	}
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}
	
	private void writeLog(Object obj)
	{
		if(Constants.LOG_STATUS == Boolean.TRUE)
		{
			syslog.debug(obj);
		}
		if(Constants.DBLOG_STATUS == Boolean.TRUE)
		{
			logger.debug(obj.toString());
		}
		obj=null;
	}
}
