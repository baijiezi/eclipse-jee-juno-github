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
 * ����:2014-04-03
 * ���ã���ʱִ���ʼ�����
 * ����:caolei
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
	 * ����˵���������������ڲ��� <br>
	 * ��������� <br>
	 * �������ͣ� 
	 */
	//@Scheduled(cron="0 0-59 18 * * ?")
	@Scheduled(cron = "0 57 13 * * ?")
	public void jobSend(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		System.out.println("��ʼִ������ץȡ:"+df.format(new Date()));
		SelfSrvTermbServiceSoap service = new SelfSrvTermbService().getSelfSrvTermbServiceSoap();
		String inXml = "<request><machineno>898</machineno></request>";
		System.out.println("***"+inXml+"**");
		//String text="<Response><ResultCode>0</ResultCode><ErrorMsg>�ɹ�</ErrorMsg><ResultData><MedicineCode>1804000</MedicineCode><MedicineName>Ҷ��Ƭ *</MedicineName><Specification>5mg*100             </Specification><OutpatientUnit>ƿ        </OutpatientUnit><Price>0.0288</Price><PFPrice>0.0288</PFPrice><MedicineType>��ҩ      </MedicineType><State>ʹ��</State><YBType /><WBCode>KST       </WBCode><PYCode>YSP       </PYCode></ResultData><ResultData><MedicineCode>1503400</MedicineCode><MedicineName>�������հ�Ƭ *</MedicineName><Specification>5mg*100             </Specification><OutpatientUnit>ƿ        </OutpatientUnit><Price>0.0088</Price><PFPrice>0.0088</PFPrice><MedicineType>��ҩ      </MedicineType><State>ʹ��</State><YBType /><WBCode>LRRUET    </WBCode><PYCode>JYLPAP    </PYCode></ResultData></Response>";
		String result = service.gdaGetFeeitemDetail(inXml);//(inXml);
//		System.out.println("************************");
//		System.out.println(result);
//		System.out.println("************************");
		//String result ="<request><machineno>898;</machineno></request>";
		List<HashMap> ls = ConnUtil.readStringXmlOut(result);
//		for(int a=0;a<ls.size();a++){
//			DrugsEntity de=new DrugsEntity();
//            de.setMedicineCode(ls.get(a).get("MedicineCode").toString()); // �õ�ֵ
//            de.setMedicineName(ls.get(a).get("MedicineName").toString()); // �õ�ֵ
//            de.setSpecifiCation(ls.get(a).get("Unit").toString()); // �õ�ֵ
//           // de.setOutpatienTunit(ls.get(a).get("OutpatientUnit").toString()); // �õ�ֵ
//            de.setPrice(ls.get(a).get("Price").toString()); // �õ�ֵ
//           // de.setPfPrice(ls.get(a).get("PFPrice").toString()); // �õ�ֵ
//            de.setMedicineType(ls.get(a).get("MedicineType").toString()); // �õ�ֵ
//            //de.setState(ls.get(a).get("State").toString()); // �õ�ֵ
//            de.setWbCode(ls.get(a).get("WBCode").toString()); // �õ�ֵ
//            de.setPyCode(ls.get(a).get("PYCode").toString()); // �õ�ֵ
//            drugsService.insert(de);
//		}
		System.out.println("ץȡ���:һ��ץȡ��"+ls.size()+"��������");
	}
}

