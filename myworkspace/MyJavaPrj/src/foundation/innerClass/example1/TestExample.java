package foundation.innerClass.example1;

/**
 * 1.�ڲ�����Ժܺõ�ʵ������
 * ���ǿͻ��˵��õĴ��룬����δ���������ֻ֪��Example��getIn()�����ܷ���һ��
 * InterfaceTest ʵ�����Ҳ���֪�����ʵ������ôʵ�ֵġ���������InsideClass ��private�ģ�
 * �������������������Ļ������������������������֣�����˵�����Ժܺõ�ʵ�����ء�
 * 
 */
public class TestExample 
{
	 public static void main(String args[])
	 {
	    Example a=new Example();
	    InterfaceTest a1=a.getIn();
	    a1.test();
	 }
}
