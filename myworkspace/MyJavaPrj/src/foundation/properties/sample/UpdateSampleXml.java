package foundation.properties.sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

//����XML�ļ��е�����ֵ
//XML�ļ���һЩ�ڵ㡢�������Ʊ������Ҫ�󣬲���ʹ�� Properties�����
public class UpdateSampleXml 
{
	public static void main(String[] args) throws Exception 
	{
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/foundation/properties/sample/sample.xml");
        prop.loadFromXML(fis);
        prop.list(System.out);
        System.out.println("The foo property: " + prop.getProperty("foo") + "\n");

        prop.setProperty("foo", "Hello World!");
        prop.setProperty("new-name", "new-value");
        FileOutputStream fos = new FileOutputStream("src/foundation/properties/sample/sample.xml");
        prop.storeToXML(fos, "Store Sample");
        fos.close();

        fis = new FileInputStream("src/foundation/properties/sample/sample.xml");
        prop.loadFromXML(fis);
        prop.list(System.out);
        System.out.println("The foo property: " + prop.getProperty("foo"));
    }
}
