package com.sun309.servlet;
/**
 * @author WormwoodLeaf OPC_GROUP
 * 财付通小钱包 GET数据接收实现类
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.dto.TenPayPackageResult;
import com.sun309.factory.Factory;
import com.sun309.impl.BaseResultProcess;
import com.sun309.impl.NetPayLogServiceImpl;
import com.sun309.impl.TenPayPackageBaseRequest;
import com.sun309.service.LogService;
import com.sun309.util.Constants;
import com.sun309.util.Validate;
import com.sun309.util.Validater;

public class TenPayPackageReceive extends HttpServlet 
{
	private static final long serialVersionUID = 4348833262164638094L;
	
	private Logger 			syslog 	= Logger.getLogger(TenPayPackageReceive.class);
	private LogService 		logger 	= (LogService)Factory.create(NetPayLogServiceImpl.class);
	private BaseResultProcess process =	(BaseResultProcess)Factory.create(BaseResultProcess.class);
	
	private TenPayPackageResult result = null;
	
	
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
		writeLog(new StringBuilder("POST 请求 来自：").append(request.getRemoteAddr()));
		/** 取得外部输出流 用于响应POST结果请求 */
		PrintWriter out= response.getWriter();
		try
		{
			/** 初始化验证工具 */
			Validate			validate	= (Validate)Factory.create(Validate.class);
			Validater			validater	= (Validater)Factory.create(Validater.class);
			
			/** 新建数据接收类实例 */
			result = new TenPayPackageResult();
			TreeMap<String,String> params = new TreeMap<String,String>();
			
			StringBuilder receiveStringB = new StringBuilder("接收到的小钱包参数名称：");
			Enumeration names = request.getParameterNames();
			while(names.hasMoreElements())
			{
				String pa = names.nextElement().toString();
				String value = request.getParameter(pa).toString();
				params.put(pa,value);
				receiveStringB.append(pa).append("=").append(value).append("|");
			}
			writeLog(receiveStringB);
			
			/** 直接取数据 */
			result.setBankType(request.getParameter("bank_type").toString());
			result.setTradeMode(request.getParameter("trade_mode").toString());
			result.setDiscount(request.getParameter("discount").toString());
			result.setInputCharset(request.getParameter("input_charset").toString());
			result.setTotalFee(request.getParameter("total_fee").toString());
			result.setTransportFee(request.getParameter("transport_fee").toString());
			result.setNotifyId(request.getParameter("notify_id").toString());
			result.setTransactionId(request.getParameter("transaction_id").toString());
			result.setSign(request.getParameter("sign").toString());
			result.setPartner(request.getParameter("partner").toString());
			result.setProductFee(request.getParameter("product_fee").toString());
			result.setTimeEnd(request.getParameter("time_end").toString());
			result.setOutTradeNo(request.getParameter("out_trade_no").toString());
			result.setTradeState(request.getParameter("trade_state").toString());
			result.setSignType(request.getParameter("sign_type").toString());
			result.setFeeType(request.getParameter("fee_type").toString());
			
			/** 设置验证工具 */
			result.setValidater(validate);
			/** 写数据接收的初始化日志 */
			writeLog(result.getInitInfo());
			
			/** 验证数据是否为空 */
			if(validater.validate(result))
			{
				/** 写入Log 说明不为空验证成功 */
				writeLog(new StringBuilder("对初步接收的数据进行非空验证成功 详细:").append(result.getValidateInfo()));
				
				/** 初始化信息处理基础类 并SET数据,用于验证数据的真实性*/
				TenPayPackageBaseRequest 	base 		= new TenPayPackageBaseRequest();
				base.setParameters(params);
				
				/** 验证数据的真实性 */
				if(base.verifyReceieData())
				{
					/** 保存历史记录数据 到详细信息表 保存数据*/
					process.processSaveDetail(result, Constants.TENPAYPACKAGE_PAY);
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
					out.print("success");
					out.flush();
					writeLog(new StringBuilder("向财付通返回数据接收并处理成功 多支付流水号：").append(result.getOutTradeNo()));
				}
				else
				{
					writeLog(new StringBuilder("数据真实性验证失败  外部订单号：").append(result.getOutTradeNo()));
					out.print("fail");
				}
				base = null;
			}
			else
			{
				writeLog(new StringBuilder("对初步接收的数据进行非空验证失败 详细:").append(result.getValidateInfo()));
				out.print("fail");
			}
			params = null;
			validate = null;
			validater = null;
		}
		catch(Exception $e)
		{
			out.print("fail");
			writeLog(new StringBuilder("处理过程中出现异常--名称").append($e.getMessage())
					.append($e.getStackTrace()).append($e.getLocalizedMessage()).append($e.getClass()));
		}
		finally
		{
			if(out != null)
			{
				out.close();
				out = null;
			}
			result=null;
			logger.write();
		}
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
		try
		{
			String from = request.getRemoteAddr();
			
			writeLog(new StringBuilder("GET 请求 来自：").append(from).append(request.getQueryString()));

			doPost(request,response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
