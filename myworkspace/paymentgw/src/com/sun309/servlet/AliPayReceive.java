package com.sun309.servlet;
/**
 * @author WormwoodLeaf OPC_GROUP
 * 支付宝POST数据接收实现类
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun309.dto.AliPayResult;
import com.sun309.dto.NetPayData;
import com.sun309.factory.Factory;
import com.sun309.impl.AliPayBaseRequest;
import com.sun309.impl.BaseResultProcess;
import com.sun309.impl.NetPayLogServiceImpl;
import com.sun309.service.LogService;
import com.sun309.util.Constants;
import com.sun309.util.NetPayFormatTools;
import com.sun309.util.Validate;
import com.sun309.util.Validater;

public class AliPayReceive extends HttpServlet 
{
	private static final long serialVersionUID = 4348833262164638094L;
	
	private Logger 			syslog 	= Logger.getLogger(AliPayReceive.class);
	private LogService 		logger 	= (LogService)Factory.create(NetPayLogServiceImpl.class);
	private BaseResultProcess process =	(BaseResultProcess)Factory.create(BaseResultProcess.class);
	
	private AliPayResult result = null;
	
	
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
			result = new AliPayResult();
			
			/** 直接取数据 */
			result.setNotifyId(request.getParameter("notify_id").toString());
			result.setTradeNo(request.getParameter("trade_no").toString());
			result.setOutTradeNo(request.getParameter("out_trade_no").toString());
			result.setTradeStatus(request.getParameter("trade_status").toString());
			result.setTotalFee(NetPayFormatTools.FromAliPayTotalFee(request.getParameter("total_fee").toString()));
			
			/** 设置验证工具 */
			result.setValidator(validate);
			/** 写数据接收的初始化日志 */
			writeLog(result.getInitInfo());
			
			/** 验证数据是否为空 */
			if(validater.validate(result))
			{
				/** 写入Log 说明不为空验证成功 */
				writeLog(new StringBuilder("对初步接收的数据进行非空验证成功 详细::").append(result.getValidateInfo()));
				
				/** 初始化信息处理基础类 并SET通知NOTIFY_ID,用于验证数据的真实性*/
				AliPayBaseRequest 	base 		= new AliPayBaseRequest();
				base.setNotifyId(result.getNotifyId());
				
				/** 验证数据的真实性 */
				if(base.verifyReceieData())
				{
					/** 保存历史记录数据 到详细信息表 获取数据*/
					result.setNotifyType(request.getParameter("notify_type").toString());
					result.setNotifyTime(request.getParameter("notify_time").toString());
					result.setSign(request.getParameter("sign").toString());
					result.setSignType(request.getParameter("sign_type").toString());
					result.setTradeNo(request.getParameter("trade_no").toString());
					result.setSubject(request.getParameter("subject").toString());
					result.setPrice(request.getParameter("price").toString());
					result.setQuatity(request.getParameter("quantity").toString());
					result.setUseCoupon(request.getParameter("use_coupon").toString());
					result.setIsTotalFeeAjust(request.getParameter("is_total_fee_adjust").toString());
					result.setSellerEmail(request.getParameter("seller_email").toString());
					result.setSellerId(request.getParameter("seller_id").toString());
					result.setBuyerEmail(request.getParameter("buyer_email").toString());
					result.setBuyerId(request.getParameter("buyer_id").toString());
					result.setPaymentType(request.getParameter("payment_type").toString());
					result.setDiscount(request.getParameter("discount").toString());
					result.setBody(request.getParameter("body").toString());
					result.setGmtCreateTime(request.getParameter("gmt_create").toString());
					result.setGmtPaymentTime(request.getParameter("gmt_payment").toString());
					result.setGmtClose(request.getParameter("gmt_close").toString());
					//result.setGmtSendGoodsTime(request.getParameter("gmt_send_goods_time").toString());
					//result.setGmtRefund(request.getParameter("gmt_refund").toString());
					
					/** 保存历史记录数据 到详细信息表 保存数据*/
					process.processSaveDetail(result, Constants.ALI_PAY);
					
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
					writeLog(new StringBuilder("向支付宝返回数据接收并处理成功 多支付流水号：").append(result.getOutTradeNo()));
					out.print("success");
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
		this.doPost(request, response);
	}
}
