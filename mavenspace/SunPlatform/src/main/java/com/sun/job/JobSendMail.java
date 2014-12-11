package com.sun.job;

import his.SelfSrvTermbService;
import his.SelfSrvTermbServiceSoap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.common.ConnUtil;
import com.sun.entity.DrugsEntity;
import com.sun.service.DrugsServiceI;

/**
 * 日期:2014-04-03
 * 作用：定时执行邮件发送
 * 作者:caolei
 * @author Administrator 
 *
 */
@Component("taskJob")
public class JobSendMail {
	private DrugsServiceI drugsService;
	public DrugsServiceI getDrugsService() {
		return drugsService;
	}
	@Autowired
	public void setDrugsService(DrugsServiceI drugsService) {
		this.drugsService = drugsService;
	}
	/**
	 * <br>
	 * 方法说明：主方法，用于测试 <br>
	 * 输入参数： <br>
	 * 返回类型： 
	 */
	//@Scheduled(cron="0 0-59 18 * * ?")
	@Scheduled(cron = "0 57 13 * * ?")
	public void jobSend(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("开始执行数据抓取:"+df.format(new Date()));
		SelfSrvTermbServiceSoap service = new SelfSrvTermbService().getSelfSrvTermbServiceSoap();
		String inXml = "<request><machineno>898</machineno></request>";
		System.out.println("***"+inXml+"**");
		//String text="<Response><ResultCode>0</ResultCode><ErrorMsg>成功</ErrorMsg><ResultData><MedicineCode>1804000</MedicineCode><MedicineName>叶酸片 *</MedicineName><Specification>5mg*100             </Specification><OutpatientUnit>瓶        </OutpatientUnit><Price>0.0288</Price><PFPrice>0.0288</PFPrice><MedicineType>西药      </MedicineType><State>使用</State><YBType /><WBCode>KST       </WBCode><PYCode>YSP       </PYCode></ResultData><ResultData><MedicineCode>1503400</MedicineCode><MedicineName>甲氧氯普胺片 *</MedicineName><Specification>5mg*100             </Specification><OutpatientUnit>瓶        </OutpatientUnit><Price>0.0088</Price><PFPrice>0.0088</PFPrice><MedicineType>西药      </MedicineType><State>使用</State><YBType /><WBCode>LRRUET    </WBCode><PYCode>JYLPAP    </PYCode></ResultData></Response>";
		String result = service.gdaGetFeeitemDetail(inXml);//(inXml);
//		System.out.println("************************");
//		System.out.println(result);
//		System.out.println("************************");
		//String result ="<request><machineno>898;</machineno></request>";
		List<HashMap> ls = ConnUtil.readStringXmlOut(result);
//		for(int a=0;a<ls.size();a++){
//			DrugsEntity de=new DrugsEntity();
//            de.setMedicineCode(ls.get(a).get("MedicineCode").toString()); // 拿到值
//            de.setMedicineName(ls.get(a).get("MedicineName").toString()); // 拿到值
//            de.setSpecifiCation(ls.get(a).get("Unit").toString()); // 拿到值
//           // de.setOutpatienTunit(ls.get(a).get("OutpatientUnit").toString()); // 拿到值
//            de.setPrice(ls.get(a).get("Price").toString()); // 拿到值
//           // de.setPfPrice(ls.get(a).get("PFPrice").toString()); // 拿到值
//            de.setMedicineType(ls.get(a).get("MedicineType").toString()); // 拿到值
//            //de.setState(ls.get(a).get("State").toString()); // 拿到值
//            de.setWbCode(ls.get(a).get("WBCode").toString()); // 拿到值
//            de.setPyCode(ls.get(a).get("PYCode").toString()); // 拿到值
//            drugsService.insert(de);
//		}
		System.out.println("抓取完成:一共抓取【"+ls.size()+"】条数据");
	}
}

