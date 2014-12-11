package com.sun.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sun.entity.AutoPayEntity;
import com.sun.entity.ExamEntity;
import com.sun.entity.FrontendPayRecordEntity;
import com.sun.entity.MannerEntity;
import com.sun.entity.TransActionEntity;
import com.sun309.frontend.db.model.FrontendBookingOrderModel;
import com.sun309.frontend.db.model.FrontendConfirmAutoPayModel;
import com.sun309.frontend.db.model.FrontendMannerModel;
import com.sun309.frontend.db.model.FrontendPayRecordModel;
import com.sun309.frontend.db.model.FrontendRegUserModel;

/**
 * 作用:用于各个服务器连接工具类 
 * 日期:2013-03-24 
 * 作者:caolei
 * 
 * @author Administrator
 * 
 */
public class ConnUtil {
	public static String ConnApiService(String url,String ApiKey,String secretKey,String entity) {
		String line="";
		String date="2013-08-01 16:10:24";//new Date().toString();
		String signature=secretKey;//getHmac(date,secretKey);
		HttpClient hc = new DefaultHttpClient();
		HttpPost hp = new HttpPost(url);
		hp.addHeader("ApiKey", ApiKey);
		hp.addHeader("X-Date", date);
		hp.addHeader("Signature", signature);
		System.out.println("********"+"APIKEY:"+ApiKey+";X-Date:"+date+";Signature:"+signature+"********");
		hp.setHeader("Content-Type", "Application/json");
		try {
			StringEntity input = new StringEntity(entity);
			hp.setEntity(input);
		} catch (UnsupportedEncodingException e1) {
			System.out.println("设置实体失败");
			e1.printStackTrace();
		}
		try {
			HttpResponse hrp=hc.execute(hp);
			if(hrp.getStatusLine().getStatusCode()==200){
				BufferedReader rd=new BufferedReader(new InputStreamReader(hrp.getEntity().getContent()));
				StringBuilder result=new StringBuilder();
				while((line=rd.readLine())!=null){
					result.append(line);
				}
				line=new String(result.toString().getBytes("GBK"),"UTF-8");
			}else{
				System.out.println(hrp.getStatusLine().getStatusCode()+"========");
			}
		} catch (ClientProtocolException e) {
			System.out.println("连接失败:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("连接失败:");
			e.printStackTrace();
		}
		return line;
	}

	private static String getHmac(String salt, String secretKey) {
		String generateHmacSHA256Signature = null;
		try {
			generateHmacSHA256Signature = generateHmacSHA2356Singnature(salt,
					secretKey);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generateHmacSHA256Signature;
	}

	private static String generateHmacSHA2356Singnature(String date, String key)
			throws UnsupportedEncodingException {
		byte[] hmacData = null;
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"),
					"HmacSHA256");
			try {
				Mac mac = Mac.getInstance("HmacSHA256");
				try {
					mac.init(secretKey);
					hmacData = mac.doFinal(date.getBytes("UTF-8"));
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(hmacData), "UTF-8");
	}
	public static String getApiService(String url,String ApiKey,String secretKey) {
		String line="";
		String date="2013-08-01 16:10:24";//new Date().toString();
		String signature=secretKey;//getHmac(date,secretKey);
		HttpClient hc = new DefaultHttpClient();
		HttpGet hp = new HttpGet(url);
		hp.addHeader("ApiKey", ApiKey);
		hp.addHeader("X-Date", date);
		hp.addHeader("Signature", signature);
		System.out.println("********"+"APIKEY:"+ApiKey+";X-Date:"+date+";Signature:"+signature+"********");
		hp.setHeader("Content-Type", "Application/json");
		try {
			HttpResponse hrp=hc.execute(hp);
			if(hrp.getStatusLine().getStatusCode()==200){
				BufferedReader rd=new BufferedReader(new InputStreamReader(hrp.getEntity().getContent()));
				StringBuilder result=new StringBuilder();
				while((line=rd.readLine())!=null){
					result.append(line);
				}
				line=new String(result.toString().getBytes("GBK"),"UTF-8");
			}else{
				System.out.println(hrp.getStatusLine().getStatusCode()+"========");
			}
		} catch (ClientProtocolException e) {
			System.out.println("连接失败:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("连接失败:");
			e.printStackTrace();
		}
		return line;
	}
	/**
	 * 日期:2014-08-27
	 * 作者:caolei
	 * 作用:用于订单类型的转换
	 */
	public static TransActionEntity bookingconvert(FrontendBookingOrderModel bookingmodel){
		TransActionEntity tae=new TransActionEntity();
		tae.setHospitalId(bookingmodel.getHospitalId()==null?"":bookingmodel.getHospitalId().toString());
		tae.setHospitalName(bookingmodel.getHospitalName()==null?"":bookingmodel.getHospitalName());
		tae.setUserName(bookingmodel.getUserName()==null?"":bookingmodel.getUserName());
		tae.setScheduleId(bookingmodel.getScheduleId()==null?"":bookingmodel.getScheduleId());
		tae.setIdCard(bookingmodel.getIdCard()==null?"":bookingmodel.getIdCard());
		tae.setBirthday(bookingmodel.getBirthday()==null?"":bookingmodel.getBirthday().toString());
		tae.setMedicalCardNo(bookingmodel.getMedicalCardNo()==null?"":bookingmodel.getMedicalCardNo());
		tae.setMobile(bookingmodel.getMobile()==null?"":bookingmodel.getMobile());
		tae.setPatientName(bookingmodel.getPatientName()==null?"":bookingmodel.getPatientName());
		tae.setPatientSex(bookingmodel.getPatientSex()==null?"":bookingmodel.getPatientSex().toString());
		tae.setAddress(bookingmodel.getAddress()==null?"":bookingmodel.getAddress());
		tae.setDeptName(bookingmodel.getDeptName()==null?"":bookingmodel.getDeptName());
		tae.setDoctorName(bookingmodel.getDoctorName()==null?"":bookingmodel.getDoctorName().toString());
		tae.setOrderTime(bookingmodel.getOrderDateTime().toString());
		tae.setVisitTime(bookingmodel.getVisitDateTime());
		tae.setSerialNo(bookingmodel.getSerialNo()==null?"":bookingmodel.getSerialNo().toString());
		tae.setFee(bookingmodel.getFee()==null?"":bookingmodel.getFee().toString());
		//tae.setbookingmodel.getJzFee(); //就疹费用
		//tae.setJzFee(bookingmodel.getJzFee()==null?"":bookingmodel.getJzFee().toString());
		tae.setPayCardNo(bookingmodel.getPayCardNo()==null?"":bookingmodel.getPayCardNo().toString());
		tae.setSettlementType(bookingmodel.getSettlementType()==null?"":bookingmodel.getSettlementType().toString());
		tae.setPaymentId(bookingmodel.getPaymentId()==null?"":bookingmodel.getPaymentId().toString());
		tae.setOptionPayments(bookingmodel.getOptionPayments()==null?"":bookingmodel.getOptionPayments());
		tae.setOrderNo(bookingmodel.getOrderNo()==null?"":bookingmodel.getOrderNo());
		tae.setOperId(bookingmodel.getOperId()==null?"":bookingmodel.getOperId());
		tae.setStates(bookingmodel.getStates()==null?"":bookingmodel.getStates().toString());
		tae.setRemark(bookingmodel.getRemark()==null?"":bookingmodel.getRemark().toString());
		tae.setIsToday(bookingmodel.getIsToday()==null?"":bookingmodel.getIsToday().toString());
		tae.setFrontOrderNo(bookingmodel.getFrontOrderNo()==null?"":bookingmodel.getFrontOrderNo());
		tae.setJzFee(bookingmodel.getJzFee()==null?"":bookingmodel.getJzFee().toString());
		return tae;
	}
	/**
	 * 日期：2014-09-19
	 * 作者:caolei
	 * 作用:转换发卡记录实体
	 */
	public static ExamEntity examconvert(FrontendRegUserModel regUsermodel){
		ExamEntity exam=new ExamEntity();
		exam.setHospitalId(regUsermodel.getHospitalId()==null?"":regUsermodel.getHospitalId());
		exam.setHospitalName(regUsermodel.getHospitalName()==null?"":regUsermodel.getHospitalName());
		exam.setPatientName(regUsermodel.getPatientName()==null?"":regUsermodel.getPatientName());
		exam.setPatientSex(regUsermodel.getPatientSex()==null?"":regUsermodel.getPatientSex().toString());
		exam.setIdCard(regUsermodel.getIdCard()==null?"":regUsermodel.getIdCard());
		exam.setBirthDay(regUsermodel.getBirthday());
		exam.setAddress(regUsermodel.getAddress()==null?"":regUsermodel.getAddress().toString());
		exam.setMobile(regUsermodel.getMobile()==null?"":regUsermodel.getMobile());
		exam.setMedicalCardNo(regUsermodel.getMedicalCardNo()==null?"":regUsermodel.getMedicalCardNo());
		exam.setPassword(regUsermodel.getPassword()==null?"":regUsermodel.getPassword());
		exam.setOperId(regUsermodel.getOperId()==null?"":regUsermodel.getOperId());
		exam.setFrontOrderNo(regUsermodel.getFrontOrderNo()==null?"":regUsermodel.getFrontOrderNo());
		exam.setCreateTime(regUsermodel.getCreateTime()==null?null:regUsermodel.getCreateTime().toString());
		return exam;
	}
	/**
	 * 日期:2014-09-22
	 * 作者:caolei
	 * 作用:自助缴费转换
	 */
	public static AutoPayEntity autoPayConvert(FrontendConfirmAutoPayModel cap){
		AutoPayEntity apc=new AutoPayEntity();
		apc.setFrontOrderNo(cap.getFrontOrderNo()==null?"":cap.getFrontOrderNo());
		//apc.set(cap.getHospitalId()==null?"":cap.getHospitalId());
		apc.setHospitalName(cap.getHospitalName()==null?"":cap.getHospitalName());
		apc.setUserName(cap.getUserName()==null?"":cap.getUserName());
		//cap.getPatientName();
		apc.setMedicalCardNo(cap.getMedicalCardNo()==null?"":cap.getMedicalCardNo());
		apc.setMobile(cap.getMobile()==null?"":cap.getMobile());
		apc.setOrderNo(cap.getOrderNo()==null?"":cap.getOrderNo());
		//apc.setcap.getRealPayFee();
		apc.setPayCardNo(cap.getPayCardNo()==null?"":cap.getPayCardNo());
		apc.setPayTradeLine(cap.getPayTranLine()==null?"":cap.getPayTranLine());
		apc.setSystemPayTranLine(cap.getSystemPayTranLine()==null?"":cap.getSystemPayTranLine().toString());
		apc.setPayMoney(cap.getRealPayFee()==null?"":cap.getRealPayFee().toString());
		apc.setPayDate(cap.getPayTime()==null?"":cap.getPayTime().toString());
		apc.setStates(cap.getStates()==null?"":cap.getStates().toString());
		apc.setRemark(cap.getRemark()==null?"":cap.getRemark());
		apc.setOperId(cap.getOperId()==null?"":cap.getOperId());
		return apc;
	}
	/**
	 * 日期:2014-10-08
	 * 作者:caolei
	 * 作用:同步缴费记录实体转换
	 */
	public static FrontendPayRecordEntity payrecordConvert(FrontendPayRecordModel frm){
		FrontendPayRecordEntity  fre=new FrontendPayRecordEntity();
		fre.setFrontOrderNo(frm.getFrontOrderNo()==null?"":frm.getFrontOrderNo());
		fre.setHospitalId(frm.getHospitalId()==null?"":frm.getHospitalId().toString());
		fre.setHospitalName(frm.getHospitalName()==null?"":frm.getHospitalName());
		fre.setUserName(frm.getUserName()==null?"":frm.getUserName());
		fre.setPatientName(frm.getPatientName()==null?"":frm.getPatientName());
		fre.setMobile(frm.getMobile()==null?"":frm.getMobile());
		fre.setOrderNo(frm.getOrderNo()==null?"":frm.getOrderNo());
		fre.setRealPayFee(frm.getRealPayFee()==null?"":frm.getRealPayFee().toString());
		fre.setPayCardNo(frm.getPayCardNo()==null?"":frm.getPayCardNo());
		fre.setPayType(frm.getPayType()==null?"":frm.getPayType());
		fre.setSettlementType(frm.getSettlementType()==null?"":frm.getSettlementType().toString());
		fre.setPaymentId(frm.getPaymentId()==null?"":frm.getPaymentId());
		fre.setPayTranLine(frm.getPayTranLine()==null?"":frm.getPayTranLine());
		fre.setSystemPayTranLine(frm.getSystemPayTranLine()==null?"":frm.getSystemPayTranLine());
		fre.setOptionPayments(frm.getOptionPayments()==null?"":frm.getOptionPayments());
		fre.setOperId(frm.getOperId()==null?"":frm.getOperId());
		fre.setTerminalTranLine(frm.getTerminalTranLine()==null?"":frm.getTerminalTranLine());
		fre.setSettleId(frm.getSettleId()==null?"":frm.getSettleId());
		fre.setPayTime(frm.getPayTime()==null?"":frm.getPayTime().toString());
		fre.setStates(frm.getStates()==null?"":frm.getStates().toString());
		fre.setRemark(frm.getRemark()==null?"":frm.getRemark());
		fre.setMedicalCardNo(frm.getMedicalCardNo()==null?"":frm.getMedicalCardNo());
		fre.setBusinessType(frm.getBusinessType()==null?"":frm.getBusinessType());
		fre.setStates(frm.getStates()==null?"":frm.getStates().toString());
		return fre;
	}
	/**
	 * 日期:2014-10-13
	 * 作者:caolei
	 * 作用:同步缴费记录实体转换
	 */
	
	public static MannerEntity MannerConvert(FrontendMannerModel frm){
		MannerEntity  mne=new MannerEntity();
		mne.setMannerChargeId(frm.getMannerChargeId());
		mne.setMannerChargeRemark(frm.getMannerChargeRemark());
		mne.setMannerCheckId(frm.getMannerCheckId());
		mne.setMannerCheckRemark(frm.getMannerCheckRemark());
		mne.setMannerDoctId(frm.getMannerDoctId());
		mne.setMannerDoctRemark(frm.getMannerDoctRemark());
		mne.setMannerNursetId(frm.getMannerNursetId());
		mne.setMannerNursetRemark(frm.getMannerNursetRemark());
		mne.setMannerPhamacyId(frm.getMannerPhamacyId());
		mne.setMannerPhamacyRemark(frm.getMannerPhamacyRemark());
		//mne.setMannerTime(frm.getMannerTime());
		frm.getMannerVisitId();
		frm.getMannerVisitRemark();
		frm.getMedicalCardNo();
		frm.getMobile();
		frm.getPatientName();
		frm.getUserName();
		return mne;
	}
	public static List readStringXmlOut(String xml) {
		List<HashMap> ls=new ArrayList();
       // Map map = new HashMap();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            Iterator iter = rootElt.elementIterator("ResultData"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
            	 Map map = new HashMap();
                Element recordEle = (Element) iter.next();
                String MedicineCode = recordEle.elementTextTrim("MedicineCode"); // 拿到head节点下的子节点title值
                map.put("MedicineCode", MedicineCode);
               // System.out.println("MedicineCode:"+MedicineCode);
                String MedicineName = recordEle.elementTextTrim("MedicineName"); // 拿到head节点下的子节点title值
                map.put("MedicineName", MedicineName);
               // System.out.println("MedicineName:"+MedicineName);
                String Specification = recordEle.elementTextTrim("Specification"); // 拿到head节点下的子节点title值
                map.put("Specification", Specification);
                String Unit = recordEle.elementTextTrim("UNIT"); // 拿到head节点下的子节点title值
                map.put("Unit", Unit);
                String Price = recordEle.elementTextTrim("Price"); // 拿到head节点下的子节点title值
                map.put("Price", Price);
//                String PFPrice = recordEle.elementTextTrim("PFPrice"); // 拿到head节点下的子节点title值
//                map.put("PFPrice", PFPrice);
                String MedicineType = recordEle.elementTextTrim("MedicineType"); // 拿到head节点下的子节点title值
                map.put("MedicineType", MedicineType);
//                String State = recordEle.elementTextTrim("State"); // 拿到head节点下的子节点title值
//                map.put("State", State);
                String WBCode = recordEle.elementTextTrim("WBCode"); // 拿到head节点下的子节点title值
                map.put("WBCode", WBCode);
                String PYCode = recordEle.elementTextTrim("PYCode"); // 拿到head节点下的子节点title值
                map.put("PYCode", PYCode);
                ls.add((HashMap) map);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
}
