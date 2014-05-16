package foundation.xml.dom4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


//MXL 和 Map 相互转换
public class XmlToMapUtils {
	public static void main(String[] args){
		String xml = "<Response><ResultCode>0</ResultCode><ErrorMsg>成功</ErrorMsg><DepaInfo><DeptId>111</DeptId><DeptName>眼表病正教授</DeptName></DepaInfo><DepaInfo><DeptId>2062</DeptId><DeptName>眼表病正教授2</DeptName></DepaInfo></Response>";
		Map<String, Object> map = parse(xml);
		System.out.println(map);
	}
	
	
	
	
	
	//一个把XML解析成Map 和 List 对象的工具类，
	//如果一个节点有子节点，则把该节点当成一个List,
	//例如下面的妇儿HIS科室接口返回结果中，DepaInfo将被解析成 List
//		<Response>
//			<ResultCode>0</ResultCode>
//			<ErrorMsg>成功</ErrorMsg>
//			<DepaInfo>
//				<DeptId>206</DeptId>
//				<DeptName>眼表病正教授</DeptName>
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
	
	
	
	//把 Map 对象转换成 XML
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
