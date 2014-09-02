package foundation.properties.sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

//更新XML文件中的属性值
//XML文件的一些节点、属性名称必须符合要求，才能使用 Properties类解析
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
