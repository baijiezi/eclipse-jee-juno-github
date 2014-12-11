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
		JSONObject js = object.fromObject("{Data=[{TotalFee='9147', Status='0', DeptName='儿科', DeptId='02', ScheduleDate='2014-12-04 14:45:50', ClinicTranLine='206292530', SelfFee='9147', DoctorName='吕彩红', RegTypeName='10', Orders=[{Items=[{ItemCode='0406', Number='600', Spec='1mg*60片/瓶', ItemName='富马酸酮替芬片', UnitPrice='10', Remark='', ItemOrderNo='3', Unit='片'}, {ItemCode='01667', Number='600', Spec='150mg*12粒/盒', ItemName='罗红霉素胶囊(仁苏)', UnitPrice='195', Remark='', ItemOrderNo='5', Unit='粒'}, {ItemCode='04248', Number='100', Spec='100ml/瓶', ItemName='氨溴特罗口服液(易坦静)', UnitPrice='2648', Remark='', ItemOrderNo='4', Unit='瓶'}, {ItemCode='04316', Number='300', Spec='5mg*5片/盒', ItemName='孟鲁司特钠咀嚼片(白三平)', UnitPrice='645', Remark='', ItemOrderNo='1', Unit='片'}, {ItemCode='26015', Number='100', Spec='0.12g*12粒/盒', ItemName='桉柠蒎肠溶软胶囊(切诺)(儿童装)', UnitPrice='3312', Remark='', ItemOrderNo='2', Unit='盒'}], TotalFee='9137', OperDoctorId='1470', OrderTypeCode='10', Date='2014-12-04', OrderNo='22272238', OperDoctorName='靳立功', OperDeptId='66', OperDeptName='人事科'}, {Items=[{ItemCode='96711', Number='100', Spec='', ItemName='一次性消毒药袋（分装约塑药袋）', UnitPrice='9', Remark='', ItemOrderNo='7', Unit='个'}], TotalFee='10', OperDoctorId='1470', OrderTypeCode='90', Date='2014-12-04 14:49:51', OrderNo='22272239', OperDoctorName='靳立功', OperDeptId='66', OperDeptName='人事科'}], DoctorId='0799', SegmentCode=''}], ErrorMessage='交易成功!'}");
		System.out.println(js.get("eror"));
		System.out.println(js.toString());
    }
	
	
	
	
	
	
	
}
