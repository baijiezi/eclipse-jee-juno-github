package com.sun.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.sun.common.ConnUtil;
import com.sun.dao.RefundMapper;
import com.sun.entity.RefundEntity;

@Service("refundService")
public class RefundServiceImpl implements RefundServiceI {
	
	private RefundMapper refundMapper;
	public int insert(RefundEntity re) {
		return refundMapper.insert(re);
	}
	public RefundMapper getRefundMapper() {
		return refundMapper;
	}
	@Autowired
	public void setRefundMapper(RefundMapper refundMapper) {
		this.refundMapper = refundMapper;
	}
	
	/**
	 * 作用:退费连接MPC接口，提交参数
	 * @param json
	 * @return
	 */
	public String refundConn(String json){
		Resource resource=new ClassPathResource("/config.properties");
		String result="";
		try {
			Properties proper=PropertiesLoaderUtils.loadProperties(resource);
			String url=RefundServiceImpl.toUrl(proper.get("refundUrl").toString(),json);
			System.out.println("refundUrl="+url+";refundApikey="+proper.get("refundApiKey").toString());
			String apikey=proper.get("refundApiKey").toString();
			String consumesecretKey=proper.get("refundApiKey").toString();
			String entity="";
			result=ConnUtil.ConnApiService(url, apikey, consumesecretKey, entity).toString();
			System.out.println("返回数据:"+result);
			//String success="{\"success\":true,\"ExtOrder\":\"YX20232311111\",\"Password\":\"22222\",\"Total\":7}";
			JSONObject job=JSONObject.fromObject(result);
			
			if(job.getBoolean("success")==true){//处理成功登记记录
//				ConsumServiceImpl.toJson(Json).put(key, value)
//				ConsumEntity ce=new ConsumEntity();
//				ce.setPayMentId("789555421");
//				ce.setExtOrder("47895454");
//				ce.setCreateTime(new Date());
//				ce.setTerminalId("13245654");
//				ce.setKzCardNo("987654321");
//				RefundMapper.insert(ce);
				System.out.println("支付成功");
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
//	public static JSONObject toJson(String Json){
//		//jsonObj.get("Total")
//		JSONObject jsonObj=JSONObject.fromObject(Json);
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("Id",jsonObj.get("UserId"));
//		map.put("PaymentId",jsonObj.get("PaymentId"));
//		JSONObject js=JSONObject.fromObject(map);
//		System.out.println("转换后的数据为:"+js.toString());
//		return js;
//	}
	
	/**
	 * 作用:转换URL
	 * 日期:2014-03-28
	 * 作者:caolei
	 */
	public static String toUrl(String url,String json){
		System.out.println("获取的URL："+url+";获取的JSON："+json);
		JSONObject jsonob=JSONObject.fromObject(json);
		String newurl=url.replaceAll("\'id\'", jsonob.get("UserId").toString()).replaceAll("\'paymentld\'",jsonob.get("PaymentId").toString());
		System.out.println("转换后的URL："+newurl);
		return newurl;
	}
	public List<RefundEntity> getWhereEntity(RefundEntity refundEntity) {
		// TODO Auto-generated method stub
		return refundMapper.getWhereEntity(refundEntity);
	}
}
