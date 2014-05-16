package foundation.properties.sample;

import java.io.FileInputStream;
import java.util.Properties;

//从XML文件加载属性的Sample程序
//XML文件的一些节点、属性名称必须符合要求，才能使用 Properties类解析
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
