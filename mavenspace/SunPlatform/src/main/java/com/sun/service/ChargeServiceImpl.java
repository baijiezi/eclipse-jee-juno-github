package com.sun.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.sun.common.ConnUtil;
import com.sun.dao.ChargeMapper;
import com.sun.entity.ChargeEntity;

@Service("chargeService")
public class ChargeServiceImpl implements ChargeServiceI {
	
	private ChargeMapper chargeMapper;
	
	public int insert(ChargeEntity ce) {
		return chargeMapper.insert(ce);
	}

	public ChargeMapper getChargeMapper() {
		return chargeMapper;
	}
	@Autowired
	public void setChargeMapper(ChargeMapper chargeMapper) {
		this.chargeMapper = chargeMapper;
	}
	
	/**
	 * 作用:充值转发报文
	 * 日期:2014-03-31
	 * 作者:caolei
	 * @throws IOException
	 */
	public String Communication(String json){
		Resource resource=new ClassPathResource("/config.properties");
		String result="";
		String mpcresult="";
		try {
			Properties proper=PropertiesLoaderUtils.loadProperties(resource);
			String url=proper.get("consumeUrl").toString();
			String apikey=proper.get("consumeApiKey").toString();
			String consumesecretKey=proper.get("consumeApiKey").toString();
			String entity="";
			if(json!=null&&json!=""){
				entity=ChargeServiceImpl.uniontoJson(json).toString();
			}
			result=ConnUtil.ConnApiService(url, apikey, consumesecretKey, entity);
			result="{\"success\":true,\"data\":\"YX20232311111\"}";
			JSONObject job=JSONObject.fromObject(result);
			if(job.getBoolean("success")==true){//处理成功登记记录
				String mpcEntity=ChargeServiceImpl.toJson(json,job.get("data").toString()).toString();
				String mpcurl=ChargeServiceImpl.toUrl(proper.get("mpcchargeUrl").toString(),json);//proper.get("mpcchargeUrl").toString();
				String mpcapikey=proper.get("mpcchargeApiKey").toString();
				String mpcsecretKey=proper.get("mpcchargesecretkey").toString();
				mpcresult=ConnUtil.ConnApiService(mpcurl, mpcapikey, mpcsecretKey, mpcEntity);
				System.out.println("MPC返回的数据为:"+mpcresult);
				JSONObject mpcjob=JSONObject.fromObject(mpcresult);
				if(mpcjob.getBoolean("success")==true){
					System.out.println("===========================");
					System.out.println("*****************************");
					System.out.println("===========================");
				}
			}else{
				System.out.println("支付出现错误:"+result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 作用:转换JSON格式
	 * @param Json
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject toJson(String Json,String extOrder){
		//jsonObj.get("Total")
		JSONObject jsonObj=JSONObject.fromObject(Json);
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("Id",jsonObj.get("UserId"));	//用户ID
		map.put("extOrder",extOrder);//
		map.put("total",jsonObj.get("Fee"));			//充值金额
		map.put("optionPayments",jsonObj.get("OptionPayments"));
		JSONObject js=JSONObject.fromObject(map);
		System.out.println("转换后的数据为:"+js.toString());
		return js;
	}
	/**
	 * 作用:转换金额 分转元
	 * 日期:2014-03-21
	 * 作者:caolei
	 * @param total
	 * @return
	 */
	public static String changF2Y(String total){
		String CURRENCY_FEN_REGEX="\\-?[0-9]+";
		if(!total.matches(CURRENCY_FEN_REGEX)){
			try {
				throw new Exception("金额格式有误");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return BigDecimal.valueOf(Long.valueOf(total)).divide(new BigDecimal(100)).toString();
	}
	/**
	 * 作用:转换银联所需要的json
	 * 日期:2014-03-21
	 * 作者:caolei
	 */
	public static JSONObject uniontoJson(String ujson){
		JSONObject jsonObj=JSONObject.fromObject(ujson);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberCard",jsonObj.get("MemberCard"));	//卡号
		map.put("password",jsonObj.get("Password"));	//密码
		map.put("total",jsonObj.get("Fee"));			//金额
		map.put("terminalId",jsonObj.get("TerminalId"));//操作者ID
		map.put("traderId",jsonObj.get("OptionPayments"));//结算商户ID
		map.put("extOrder",jsonObj.get("CreateTime"));	//外部订单号
		JSONObject js=JSONObject.fromObject(map);
		System.out.println("转换后的数据为:"+js.toString());
		return js;		
	}
	
	/**
	 * 作用:转换URL
	 * 日期:2014-03-28
	 * 作者:caolei
	 */
	public static String toUrl(String url,String json){
		System.out.println("获取的URL："+url+";获取的JSON："+json);
		JSONObject jsonob=JSONObject.fromObject(json);
		String newurl=url.replaceAll("\'id\'", jsonob.get("UserId").toString());
		System.out.println("转换后的URL："+newurl);
		return newurl;
	}
}
