package com.sun309.servlet;
/**
 * @author WormwoodLeaf OPC_GROUP
 * 财付通GET数据接收实现类
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun309.dto.NetPayData;
import com.sun309.dto.TenPayResult;
import com.sun309.factory.Factory;
import com.sun309.impl.BaseResultProcess;
import com.sun309.impl.NetPayLogServiceImpl;
import com.sun309.impl.TenPayBaseRequest;
import com.sun309.service.LogService;
import com.sun309.util.Constants;
import com.sun309.util.Validate;
import com.sun309.util.Validater;

public class TenPayReceive extends HttpServlet 
{
	private static final long serialVersionUID = 4348833262164638094L;
	
	private Logger 			syslog 	= Logger.getLogger(TenPayReceive.class);
	private LogService 		logger 	= (LogService)Factory.create(NetPayLogServiceImpl.class);
	private BaseResultProcess process =	(BaseResultProcess)Factory.create(BaseResultProcess.class);
	
	private TenPayResult result = null;
	
	
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
			result = new TenPayResult();
			
			/** 直接取数据 */
			result.setCmdNo(request.getParameter("cmdno").toString());
			result.setPayResult(request.getParameter("pay_result").toString());
			result.setDate(request.getParameter("date").toString());
			result.setTransactionId(request.getParameter("transaction_id").toString());
			result.setBargainorID(request.getParameter("bargainor_id").toString());
			result.setSpBillNo(request.getParameter("sp_billno").toString());
			result.setFeeType(request.getParameter("fee_type").toString());
			result.setSign(request.getParameter("sign").toString());
			Double fee = Double.valueOf(request.getParameter("total_fee").toString());
			result.setTotalFee(fee.longValue());
			result.setAttach(request.getParameter("attach").toString());
			
			/** 设置验证工具 */
			result.setValidator(validate);
			/** 写数据接收的初始化日志 */
			writeLog(result.getInitInfo());
			
			/** 验证数据是否为空 */
			if(validater.validate(result))
			{
				/** 写入Log 说明不为空验证成功 */
				writeLog(new StringBuilder("对初步接收的数据进行非空验证成功 详细:").append(result.getValidateInfo()));
				
				/** 初始化信息处理基础类 并SET数据,用于验证数据的真实性*/
				TenPayBaseRequest 	base 		= new TenPayBaseRequest();
				base.setService(result.getCmdNo());
				base.setOrderStatus(result.getPayResult());
				base.setDate(result.getDate());
				base.setTradeNo(result.getTransactionId());
				base.setID(result.getSpBillNo());
				base.setTotalFee(result.getTotalFee());
				base.setCuryId(result.getFeeType());
				base.setPrivateData(result.getAttach());
				base.setSign(result.getSign());
				
				/** 验证数据的真实性 */
				if(base.verifyReceieData())
				{
					/** 保存历史记录数据 到详细信息表 保存数据*/
					process.processSaveDetail(result, Constants.TEN_PAY);
					/** 按实际支付系统外部订单号[多支付流水号] 查找 这个订单的初始化交易记录*/
					NetPayData data = process.processAfterValidate(result);
					/** 为空验证  记录超时后再收到数据的异常情况 */
					if(data == null)
					{
						process.processExceptionData(result);
						showSucceed(out,"http://sun309.com/usercenter/");
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
						showSucceed(out,data.getReturnURL());
					}
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
	
	private void showSucceed(PrintWriter out,String data)
	{
		StringBuilder succeed = new StringBuilder("<html><head>\r\n")
			.append("<meta name=\"TENCENT_ONLINE_PAYMENT\" content=\"China TENCENT\">\r\n")
			.append("<script language=\"javascript\">\r\n")
			.append("window.location.href=\"").append(data).append("\"\r\n")
			.append("</script>\r\n").append("</head><body></body></html>");
		out.println(succeed.toString());
		out.flush();
		out.close();
		out = null;
		data = null;
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
		String from = request.getRemoteAddr();
		writeLog(new StringBuilder("GET 请求 来自：").append(from));
		if(from.equals("119.147.64.148") || from.equals("219.133.61.234"))
		{
			doPost(request,response);
		}
		else
		{
			response.sendRedirect("http://sun309.com/usercenter/");
		}
	}
}
