package foundation.xml.dom4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


//MXL �� Map �໥ת��
public class XmlToMapUtils {
	public static void main(String[] args){
		String xml = "<Response><ResultCode>0</ResultCode><ErrorMsg>�ɹ�</ErrorMsg><DepaInfo><DeptId>111</DeptId><DeptName>�۱�������</DeptName></DepaInfo><DepaInfo><DeptId>2062</DeptId><DeptName>�۱�������2</DeptName></DepaInfo></Response>";
		Map<String, Object> map = parse(xml);
		System.out.println(map);
	}
	
	
	
	
	
	//һ����XML������Map �� List ����Ĺ����࣬
	//���һ���ڵ����ӽڵ㣬��Ѹýڵ㵱��һ��List,
	//��������ĸ���HIS���ҽӿڷ��ؽ���У�DepaInfo���������� List
//		<Response>
//			<ResultCode>0</ResultCode>
//			<ErrorMsg>�ɹ�</ErrorMsg>
//			<DepaInfo>
//				<DeptId>206</DeptId>
//				<DeptName>�۱�������</DeptName>
//			</DepaInfo>
//		</Response>
	public static Map<String, Object> parse(String xml){
		Document doc = null;
		try
		{
			doc = DocumentHelper.parseText(xml);
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		Element rootEle = doc.getRootElement();
		List<Element> rootNode = rootEle.elements();
		Map<String, Object> map = _parse(rootNode);
		return map; 
	}
	
	private static Map<String, Object> _parse(List<Element> nodes){
		Map<String, Object> map = new HashMap<String, Object>();
		for(Element element : nodes){
			List<?> list = element.elements();
			if(list!=null && list.size() > 0)
			{
				String key = element.getName();
				ArrayList<Map<String, Object>> li = null;
				if(map.get(key) != null){
					li = (ArrayList<Map<String, Object>>)map.get(key);
				}
				else{
					li = new ArrayList<Map<String, Object>>(2);
					map.put(element.getName(), li);
				}
				Map<String, Object> m = _parse(element.elements());
				li.add(m);
			}
			else
			{
				map.put(element.getName(), element.getText());
			}
		}
		return map;
	}
	
	
	
	//�� Map ����ת���� XML
	public static String createXml(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		for(String key : map.keySet()){
			sb.append("<").append(key).append(">");
			sb.append(map.get(key));
			sb.append("</").append(key).append(">");
		}
		return sb.toString();
	}
	
	
	
}
