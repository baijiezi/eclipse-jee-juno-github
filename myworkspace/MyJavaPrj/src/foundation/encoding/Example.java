package foundation.encoding;

//���������˵������Ҫ���������Ķ�
public class Example {
	public static void main(String[] args){
		try{
			String s = "���Ŷ!";
			System.out.println( new String(s.getBytes(),"UTF-8")); 
			
			//System.out.println( new String(s.getBytes(),"GBK"));
			//System.out.println( new String(s.getBytes("UTF-8"),"UTF-8"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

/**
	Java ��ȷ�����ַ�������ת��
	�ַ������ڲ���ʾ��
	�ַ�����java��ͳһ��unicode��ʾ( ��utf-16 LE) , 
	���� String s = "���Ŷ!";
	���Դ���ļ���GBK����, ����ϵͳ��windows��Ĭ�ϵĻ�������ΪGBK����ô����ʱ,  JVM�� ����GBK���뽫�ֽ�����������ַ���Ȼ���ַ�ת��Ϊunicode��ʽ���ֽ����飬��Ϊ�ڲ��洢��
	����ӡ����ַ���ʱ��JVM ���ݲ���ϵͳ���ص����Ի�������unicodeת��ΪGBK��Ȼ�����ϵͳ��GBK��ʽ��������ʾ������
	��Դ���ļ���UTF-8, ������Ҫ֪ͨ������Դ��ĸ�ʽ��javac -encoding utf-8 ... , ����ʱ��JVM����utf-8 �������ַ���Ȼ��ת��Ϊunicode��ʽ���ֽ����飬 ��ô����Դ���ļ���ʲô��ʽ��ͬ�����ַ��������õ���unicode�ֽ���������ȫһ�µģ���ʾ��ʱ��Ҳ��ת��GBK����ʾ����OS�����йأ�
	 
	 
	������β����� �����϶������� �ַ���ԭ���ı����ʽ �� ��ȡʱ�����õı����ʽ��һ�µ��µġ�
	���磺
	String s = "���Ŷ!";
	System.out.println( new String(s.getBytes(),"UTF-8")); //������ΪgetBytes()Ĭ��ʹ��GBK���룬 ������ʱʹ��UTF-8���룬�϶�����
	���� getBytes() �ǽ�unicode ת��Ϊ����ϵͳĬ�ϵĸ�ʽ���ֽ����飬��"���Ŷ"�� GBK��ʽ��
	new String (bytes, Charset) �е�charset ��ָ����ȡ bytes �ķ�ʽ������ָ��ΪUTF-8,����bytes�����ݵ���UTF-8 ��ʽ�Դ���
	�������ַ�ʽ��������ȷ�Ľ������Ϊ���ǵ�Դ���ݱ���ͽ����õı�����һ�µġ�
	System.out.println( new String(s.getBytes(),"GBK"));
	System.out.println( new String(s.getBytes("UTF-8"),"UTF-8"));
	 
	 
	��ô���������getBytes �� new String() �����б���ת���أ�  ����������һ�ִ���ķ���:
	GBK--> UTF-8:    new String( s.getBytes("GBK") , "UTF-8);   ,���ַ�ʽ����ȫ����ģ���ΪgetBytes �ı�����  UTF-8 ��һ�£��϶������롣
	����Ϊʲô��tomcat �£�ʹ�� new String(s.getBytes("iso-8859-1") ,"GBK") ȴ�������أ� ���ǣ�
	tomcat Ĭ��ʹ��iso-8859-1���룬 Ҳ����˵�����ԭ���ַ�����GBK�ģ�tomcat��������У���GBKת��iso-8859-1�ˣ�
	Ĭ������£�ʹ��iso-8859-1��ȡ���Ŀ϶���������ģ���ô������Ҫ��iso-8859-1 ��ת��GBK�� ��iso-8859-1 �ǵ��ֽڱ���ģ�
	������Ϊһ���ֽ���һ���ַ��� ��ô����ת�������ԭ�����ֽ��������κθı䣬��Ϊ�ֽ����鱾�������ɵ����ֽ���ɵģ�
	���֮ǰ��GBK���룬��ôת��iso-8859-1�����������ȫû�䣬 �� s.getBytes("iso-8859-1")  ʵ���ϻ���ԭ��GBK�ı�������
	�� new String(s.getBytes("iso-8859-1") ,"GBK")  �Ϳ�����ȷ�����ˡ� ����˵����һ���ɺϡ�
	 
	 
	�����ȷ�Ľ�GBKתUTF-8 ? ��ʵ������unicodeתUTF-8)
	String gbkStr = "���Ŷ!"; //Դ���ļ���GBK��ʽ����������ַ����Ǵ�GBK�ļ��ж�ȡ������, ת��Ϊstring ���unicode��ʽ
	//����getBytes��unicode�ַ���ת��UTF-8��ʽ���ֽ�����
	byte[] utf8Bytes = gbkStr.getBytes("UTF-8"); 
	//Ȼ����utf-8 ������ֽ����������µ��ַ���
	String utf8Str = new String(utf8Bytes, "UTF-8");
	�򻯺����:
	unicodeToUtf8 (String s) {
	return new String( s.getBytes("utf-8") , "utf-8");
	}
	UTF-8 תGBKԭ��Ҳ��һ��
	return new String( s.getBytes("GBK") , "GBK");
	 
	 ��ʵ���Ĺ�������  getBytes(charset) ���ˡ�
	getBytes ��JDK ������Encodes this String into a sequence of bytes using the named charset, storing the result into a new byte array.
	 
	 
	������ڶ�д�ļ������ⲿ�����ȡ���ݣ�Ӧ���ڹ�������������ʱָ������
	���������ӿڻ�ȡ���ݣ�BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	��д�ļ���OutputStreamWriter w1 = new OutputStreamWriter(new FileOutputStream("D:\\file1.txt"),"UTF-8");
	InputStreamReader( stream, charset)
	���԰����������ɵİ���ָ�������д�ļ���

**/