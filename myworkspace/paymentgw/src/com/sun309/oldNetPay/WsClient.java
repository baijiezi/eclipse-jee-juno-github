package com.sun309.oldNetPay;

import java.net.URL;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

public class WsClient {
	
	String url = "http://localhost:8080/NetPay/services/ChinaPay?wsdl";
	//String url = "http://localhost:8080/NetPay/services/AliPay?wsdl";
	
	public void sign()
	{
		//String input = "<?xml version='1.0' encoding='utf-8' ?><root><body>电话心理咨询</body><out_trade_no>201005l2ydub_53</out_trade_no><show_url>http://dev-marry5.sun309.com</show_url><total_fee>0.01</total_fee><subject>电话心理咨询</subject><notify_url>http://dev-marry5.sun309.com/plus/manage.php?payment=alipay</notify_url><return_url>http://dev-marry5.sun309.com/plus/success.php?payment=alipay</return_url></root>";
		String input = "<?xml version='1.0' encoding='utf-8'?><root><body>体检</body><out_trade_no>PE201006280000000168</out_trade_no><show_url>http://opc.sun309.com/userCenter.php?c=product/Product-orderDetailorderno=PE201006280000000168</show_url><total_fee>30.00</total_fee><subject>体检支付</subject><notify_url>http://opc.sun309.com/default.php?c=api/product/AliPay-postUrl</notify_url><return_url>http://opc.sun309.com/default.php?c=api/product/AliPay-returnUrl</return_url></root>";
		//String input = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><merId>808080051799539</merId><orderNo>0000995390000002</orderNo><amount>000000000001</amount><curyId>156</curyId><transDate>20100520</transDate><transType>0001</transType><priv1>test</priv1></root>";
		String method = "sign";
		Client client = null;
		try
		{
			client = new Client(new URL(url));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpMethodParams.USE_EXPECT_CONTINUE,Boolean.FALSE);
			//设置ws连接超时时间
			params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)60000); 
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,params);
		}
		catch(Exception e)
		{
			System.out.println(e);
		};
		String[] r = new String[]{input};
		Object[] results = null;
		try
		{
			results = client.invoke(method, r);
		}
		catch (Exception e)
		{
			System.out.println(e + "---");
		}
		System.out.println((results!=null && results.length > 0)?results[0]:"");
	}
	
	public void verify()
	{
		//String v = "214995deb130dd85572b72b73e9aebf6";
		//String input = "<?xml version='1.0' encoding='utf-8' ?><root><notify_id>ba65026ee0ec7f283576d887d009f9c3</notify_id></root>";
		String input = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><merId>808080051799539</merId><orderNo>2000995390000185</orderNo><amount>000000000001</amount><currencyCode>156</currencyCode><transDate>20100722</transDate><transType>0001</transType><status>1001</status><checkValue>84074D00A8B99007B489115A0E55A8CBA4F5734AF654673984C47A9526CD15A25BB84C33F77063F2D639F5083CAE97441E18BB203995BF00A45A5D73B2C5A2B23A37B8C0991F77A0D2605107E600BCFC3CFAEC7EB487F13B38AF033B5EFDD81362542476CBD485304234348A37FF4E7C5B6A3F0130471A98EF23F2C22596A49B</checkValue></root>";
		String method = "verify";
		Client client = null;
		try
		{
			client = new Client(new URL(url));
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpMethodParams.USE_EXPECT_CONTINUE,Boolean.FALSE);
			//设置ws连接超时时间
			params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, (long)60000); 
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,params);
		}
		catch(Exception e)
		{
			System.out.println(e);
		};
		String[] r = new String[]{input};
		Object[] results = null;
		try
		{
			results = client.invoke(method, r);
		}
		catch (Exception e)
		{
			System.out.println(e + "---");
		}
		System.out.println((results!=null && results.length > 0)?results[0]:"");
	}
	
	public static void main(String[] args) {
		WsClient w = new WsClient();
		//w.sign();
		w.verify();
	}
}
