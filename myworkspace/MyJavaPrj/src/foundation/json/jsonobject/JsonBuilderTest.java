package foundation.json.jsonobject;

import net.sf.json.JSONObject;

public class JsonBuilderTest 
{
//	public static void main(String[] args)
//	{
//		ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		ArrayList<Map<String, Object>> dataList2 = new ArrayList<Map<String, Object>>();
//		map.put("key1", "value");
//		map.put("key2", "value");
//		map.put("key3", dataList2);
//		
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("childKey1", "value");
//		map2.put("childKey2", "value");
//		dataList2.add(map2);
//		
//		dataList.add(map);
//		System.out.println(dataList.toString());
//	}
	
	
	
	public static void main(String[] args) {
		JSONObject object = new JSONObject();
		JSONObject js = object.fromObject("{Data=[{TotalFee='9147', Status='0', DeptName='����', DeptId='02', ScheduleDate='2014-12-04 14:45:50', ClinicTranLine='206292530', SelfFee='9147', DoctorName='���ʺ�', RegTypeName='10', Orders=[{Items=[{ItemCode='0406', Number='600', Spec='1mg*60Ƭ/ƿ', ItemName='������ͪ���Ƭ', UnitPrice='10', Remark='', ItemOrderNo='3', Unit='Ƭ'}, {ItemCode='01667', Number='600', Spec='150mg*12��/��', ItemName='�޺�ù�ؽ���(����)', UnitPrice='195', Remark='', ItemOrderNo='5', Unit='��'}, {ItemCode='04248', Number='100', Spec='100ml/ƿ', ItemName='�������޿ڷ�Һ(��̹��)', UnitPrice='2648', Remark='', ItemOrderNo='4', Unit='ƿ'}, {ItemCode='04316', Number='300', Spec='5mg*5Ƭ/��', ItemName='��³˾���ƾ׽�Ƭ(����ƽ)', UnitPrice='645', Remark='', ItemOrderNo='1', Unit='Ƭ'}, {ItemCode='26015', Number='100', Spec='0.12g*12��/��', ItemName='�����峦������(��ŵ)(��ͯװ)', UnitPrice='3312', Remark='', ItemOrderNo='2', Unit='��'}], TotalFee='9137', OperDoctorId='1470', OrderTypeCode='10', Date='2014-12-04', OrderNo='22272238', OperDoctorName='������', OperDeptId='66', OperDeptName='���¿�'}, {Items=[{ItemCode='96711', Number='100', Spec='', ItemName='һ��������ҩ������װԼ��ҩ����', UnitPrice='9', Remark='', ItemOrderNo='7', Unit='��'}], TotalFee='10', OperDoctorId='1470', OrderTypeCode='90', Date='2014-12-04 14:49:51', OrderNo='22272239', OperDoctorName='������', OperDeptId='66', OperDeptName='���¿�'}], DoctorId='0799', SegmentCode=''}], ErrorMessage='���׳ɹ�!'}");
		System.out.println(js.get("eror"));
		System.out.println(js.toString());
    }
	
	
	
	
	
	
	
}
