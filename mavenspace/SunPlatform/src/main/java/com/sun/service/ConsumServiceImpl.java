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
	 * ����:ת������
	 * ����:2014-03-21
	 * ����:caolei
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
			if(job.getBoolean("success")==true){//����ɹ��ǼǼ�¼
				//ConsumServiceImpl.toJson(Json).put(key, value)
				ConsumEntity ce=new ConsumEntity();
//				ce.setPayMentId("789555421");
//				ce.setExtOrder("47895454");
//				ce.setCreateTime(new Date());
//				ce.setTerminalId("13245654");
//				ce.setKzCardNo("987654321");
				consumeMapper.insert(ce);
			}else{
				System.out.println("֧�����ִ���:"+result);
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
	 * ����:ת��JSON��ʽ
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
		System.out.println("ת���������Ϊ:"+js.toString());
		return js;
	}
	/**
	 * ����:ת����� ��תԪ
	 * ����:2014-03-21
	 * ����:caolei
	 * @param total
	 * @return
	 */
	public static String changF2Y(String total){
		String CURRENCY_FEN_REGEX="\\-?[0-9]+";
		if(!total.matches(CURRENCY_FEN_REGEX)){
			try {
				throw new Exception("����ʽ����");
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
