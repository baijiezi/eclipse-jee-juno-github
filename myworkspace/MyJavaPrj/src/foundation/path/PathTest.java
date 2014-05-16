package foundation.path;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

//java工程中,目录可以从 src 开始，
//例如  File file = new File("src/foundation/xml/cfg.xml")
/**
 * "文件名" 表示：默认直接从根目录开始，即等于 MyJavaPrj/文件名
 * "." 表示：
 * "/" 表示：在 getClass().getResourceAsStream("/") 中，表示 src 目录下，具体看下面的load方法
 * "./" 表示：从根目录开始，即等于 MyJavaPrj/文件名
 * "../" 表示：在Web 项目中表示上一级目录，在一般java工程中无效，在linux中，表示上一级目录
 * 直接src开始，如：src/foundation/xml/cfg.xml
 * 绝对路径，如：E\:\\programs\\HospitalFrontEnd\\FEFE\\src\\mapping.xml 
 */
public class PathTest 
{
	public static void main(String[] args)
	{
		Document doc = null;
		try 
		{
			//java 中的相对路径有两种，一种是相对于 classpath 的路径，
			//如 "E:\MyEclipse 7.1\myworkspace\MyJavaPrj\bin" ，其中 bin 是本项目的 classpath
			
			//另一种是相对于当前用户目录，就是相对于 System.getProperty("user.dir") 返回的目录
			//对于一般项目，这是项目的根目录，对于J2EE服务器，可能是某个路径，并没有规范，所以要谨慎使用
			System.out.println(System.getProperty("user.dir"));
			
			
//			System.out.println("测试目录：src/foundation/xml/cfg.xml");
//			doc = new SAXReader().read(new File("src/foundation/xml/cfg.xml"));
//			System.out.println("测试完成");
			
			
//			System.out.println("测试目录：pathTest1.xml");
//			doc = new SAXReader().read(new File("pathTest1.xml"));
//			System.out.println("测试完成");
			
			
			System.out.println("测试目录：pathTest2.xml");
			doc = new SAXReader().read(new File("./pathTest2.xml"));
			System.out.println("测试完成");
			
			
//			System.out.println("测试目录：pathTest2.xml");
//			doc = new SAXReader().read(new File("../pathTest2.xml"));
//			System.out.println("测试完成，'../' 无效");
			
			
			PathTest pathTest = new PathTest();
			pathTest.load();
			
			
			
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void load()
	{
		SAXReader reader = new SAXReader();
		
		//这里的  "/" 表示从 src 开始
		//getClass().getResourceAsStream()  能识别  包  中的路径
		InputStream is = getClass().getResourceAsStream("/pathTest2.xml");
		try 
		{
			Document doc = reader.read(is);
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}
}
