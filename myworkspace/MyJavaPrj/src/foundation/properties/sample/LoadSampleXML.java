package foundation.properties.sample;

import java.io.FileInputStream;
import java.util.Properties;

//��XML�ļ��������Ե�Sample����
//XML�ļ���һЩ�ڵ㡢�������Ʊ������Ҫ�󣬲���ʹ�� Properties�����
public class LoadSampleXML 
{
	 public static void main(String[] args) throws Exception 
	 {
	        Properties prop = new Properties();

	        FileInputStream fis = new FileInputStream("src/foundation/properties/sample/sample.xml");

	        prop.loadFromXML(fis);

	        prop.list(System.out);

	        System.out.println("/nThe foo property: " + prop.getProperty("foo"));

	    }
}
