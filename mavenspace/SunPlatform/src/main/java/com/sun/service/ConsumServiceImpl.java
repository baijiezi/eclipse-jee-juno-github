package com.sun.service;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
import com.sun.dao.ConsumeMapper;
import com.sun.entity.ConsumEntity;

@Service("consumService")
public class ConsumServiceImpl implements ConsumServiceI {
	private ConsumeMapper consumeMapper;
	public int insert(ConsumEntity ce) {
		return consumeMapper.insert(ce);
	}
	
	/**
	 * 作用:转发报文
	 * 日期:2014-03-21
	 * 作者:caolei
	 * @throws IOException
	 */
	public String Communication(String json){
		Resource resource=new ClassPathResource("/config.properties");
		String result="";
		try {
			Properties proper=PropertiesLoaderUtils.loadProperties(resource);
			String url=proper.get("consumeUrl").toString();
			System.out.println("conSumeUrl="+url+";consumeApikey="+proper.get("consumeApiKey").toString());
			String apikey=proper.get("consumeApiKey").toString();
			String consumesecretKey=proper.get("consumeApiKey").toString();
			String entity="";
			if(json!=null&&json!=""){
				entity=ConsumServiceImpl.toJson(json).toString();
			}
			result=ConnUtil.ConnApiService(url, apikey, consumesecretKey, entity).toString();
			String success="{\"success\":true,\"ExtOrder\":\"YX20232311111\",\"Password\":\"22222\",\"Total\":7}";
			JSONObject job=JSONObject.fromObject(success);
			if(job.getBoolean("success")==true){//处理成功登记记录
				//ConsumServiceImpl.toJson(Json).put(key, value)
				ConsumEntity ce=new ConsumEntity();
//				ce.setPayMentId("789555421");
//				ce.setExtOrder("47895454");
//				ce.setCreateTime(new Date());
//				ce.setTerminalId("13245654");
//				ce.setKzCardNo("987654321");
				consumeMapper.insert(ce);
			}else{
				System.out.println("支付出现错误:"+result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public ConsumeMapper getConsumeMapper() {
		return consumeMapper;
	}
	@Autowired
	public void setConsumeMapper(ConsumeMapper consumeMapper) {
		this.consumeMapper = consumeMapper;
	}
	/**
	 * 作用:转换JSON格式
	 * @param Json
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject toJson(String Json){
		//jsonObj.get("Total")
		JSONObject jsonObj=JSONObject.fromObject(Json);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberCard",jsonObj.get("MemberCard"));
		map.put("total",ConsumServiceImpl.changF2Y(jsonObj.get("Total").toString()));
		map.put("password",jsonObj.get("Password"));
		map.put("terminalId",jsonObj.get("TerminalId"));
		map.put("traderId",jsonObj.get("TraderId"));
		map.put("extOrder",jsonObj.get("ExtOrder"));
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

	public List<ConsumEntity> getWhereEntity(ConsumEntity consumEntity) {
		// TODO Auto-generated method stub
		return consumeMapper.getWhereEntity(consumEntity);
	}
}
