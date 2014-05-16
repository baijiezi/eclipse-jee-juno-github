package foundation.properties;

import java.io.FileInputStream;
import java.util.Properties;

//�������Ե�Sample����
public class LoadSampleProperties 
{
	public static void main(String[] args) throws Exception 
	{
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("src/foundation/properties/config.properties");

        prop.load(fis);

        prop.list(System.out);

        System.out.println("\nThe foo property: " + prop.getProperty("foo"));

    }
}
