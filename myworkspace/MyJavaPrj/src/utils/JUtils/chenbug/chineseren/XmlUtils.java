package utils.JUtils.chenbug.chineseren;
   
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
   
/**  
 * <p>Title: XmlUtils</p>  
 * <p>Description: These xmlUtils method written by chineseren on 20040421 for ExportDB</p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: PUBINFO</p>  
 * @author jjw  
 * @version 1.0  
 */   
   
public class XmlUtils {   
  /* ����unix��ʹ��xmlDom.asXml()���ص��ַ������,���Բ���Transformer����ת��,��windows��Ҳ����ʹ�þ���Ч�ʵ�  
   TransformerFactory factory = TransformerFactory.newInstance();  
   Transformer transformer = null;  
   try {  
     transformer = factory.newTransformer();  
   } catch (Exception e) {  
     Tools.log.error(e.toString());  
     return null;  
   }  
   //ת��  
   DocumentSource source = new DocumentSource(dom);  
   StringWriter writer = new StringWriter();  
   StreamResult result = new StreamResult(writer);  
   try {  
     transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "gb2312");  
     transformer.transform(source, result);  
     return writer.toString();  
   } catch (Exception e) {  
     Tools.log.error(e.toString());  
     return null;  
   }*/   
   
  /**  
   * ��xmlPath���document  
   * @param xmlPath xml�ļ����·��  
   * @return Document  
   * @throws Exception  
   */   
  public static Document getDocument(String xmlPath) throws Exception {   
    return new SAXReader().read(new File(xmlPath));   
  }   
   
  /**  
   * ��node���document  
   * @param node Node  
   * @return Document  
   * @throws Exception  
   */   
  public static Document getDocument(Node node) throws Exception {   
    return new SAXReader().read(new ByteArrayInputStream((   
        "<?xml version=\"1.0\" encoding=\"GBK\"?>" +   
        node.asXML()).getBytes("GBK")));   
  }   
   
  /**  
   * ��document��ȡ��strNode���dom  
   * @param dom Document  
   * @param strNodeXPth Node��xpath  
   * @return Document  
   * @throws Exception  
   */   
  public static Document getDocument(Document dom,   
      String strNodeXPth) throws Exception {   
    return XmlUtils.getDocument(dom.selectSingleNode(strNodeXPth));   
  }   
   
  /**  
   * ��node��ȡ��attribute  
   * @param element Element  
   * @param strAttrib Attribute�����  
   * @return String  
   * @throws Exception  
   */   
  public static String getAttribute(Element element,   
      String strAttrib) throws Exception {   
    return element.attribute(strAttrib).getValue();   
  }   
   
  /**  
   * ��document�е�strNodeȡ��attribute  
   * @param dom Document  
   * @param strNodeXPath Node��xpath  
   * @param strAttrib Attribute�����  
   * @return String  
   * @throws Exception  
   */   
  public static String getAttribute(Document dom, String strNodeXPath,   
      String strAttrib) throws Exception {   
    return XmlUtils.getAttribute(((Element)dom.selectSingleNode(strNodeXPath)),   
        strAttrib);   
  }   
   
  /**  
   *  
   * @param dom Document  
   * @param strNodeXPath Node��xpath  
   * @return String  
   * @throws Exception  
   */   
  public static String getText(Document dom,   
      String strNodeXPath) throws Exception {   
    return dom.selectSingleNode(strNodeXPath).getText();   
  }   
   
  public static void main(String[] args) {   
//      XmlUtils util = new XmlUtils();  
//      Document doc = util.loadXml("cfg.xml");  
//      Element ele = doc.getRootElement();  
//      List list = ele.getChildren();  
//      for(int i=0; i<list.size(); i++) 
//      {
//    	  Element c = (Element)list.get(i);  
//    	  Attribute a = new Attribute("HELLO", "WORD");  
//    	  c.addAttribute(a);  
//      }
//         // ele.addAttribute(a);  
//         //ele.setText("--");  
//         util.outXml(doc, com.pub.util.FetchProp.getProperties("", ""));  
    }
}  



