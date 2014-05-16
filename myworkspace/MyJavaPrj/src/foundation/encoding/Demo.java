package foundation.encoding;

public class Demo {
	public static void main(String args[])throws Exception {
		String str = "中文字符";
		System.out.println("original string---" + str);// 会正常输出原始串
		/**
		* str.getBytes();//如果括号中不写charset，则采用的是Sytem.getProperty("file.encoding"),即当前文件的编码方式，
		* 很多人写的是系统的默认编码，通过代码测试并非如此，实际得到的是文件的编码方式*
		*  
		* str.getBytes("charset");//指定charset，即将底层存储的Unicode码解析为charset编码格式的字节数组方式
		*  
		* String new_str=new String(str.getBytes("utf-8"),"gbk"));
		* //将已经解析出来的字节数据转化为gbk编码格式的字符串，在内存中即为gbk格式的字节数组转为Unicode去交互传递
		*/
		String new_str = new String(str.getBytes("utf-8"), "gbk");
		    /**
		     * 此时的输出是乱码，在UTF-8的file.encoding下输出gbk格式的数据肯定是乱码,但是new_str的确是gbk编码式的
		     * 此时的乱码源于encoding不符，但gbk格式的new_str本身数据并没有问题,通过下面的转换也可以看得出来
		     */
		System.out.println("new string----" + new_str);
		String final_str = new String(new_str.getBytes("gbk"), "utf-8");//此处的含意与最上边的注释是一致的参数含意
		/**
		*输出是正常的，此时将gbk编码格式的new_str字符串，用gbk这个charset去解析它，然后用utf-8再转码一次，
		    因为new_str确实是gbk格式的，才能经过utf-8编码得到正常的数据显示。
		*/
		System.out.println("final string---"+final_str);
	}
}
