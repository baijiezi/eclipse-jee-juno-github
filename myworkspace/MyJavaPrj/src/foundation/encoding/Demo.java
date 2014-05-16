package foundation.encoding;

public class Demo {
	public static void main(String args[])throws Exception {
		String str = "�����ַ�";
		System.out.println("original string---" + str);// ���������ԭʼ��
		/**
		* str.getBytes();//��������в�дcharset������õ���Sytem.getProperty("file.encoding"),����ǰ�ļ��ı��뷽ʽ��
		* �ܶ���д����ϵͳ��Ĭ�ϱ��룬ͨ��������Բ�����ˣ�ʵ�ʵõ������ļ��ı��뷽ʽ*
		*  
		* str.getBytes("charset");//ָ��charset�������ײ�洢��Unicode�����Ϊcharset�����ʽ���ֽ����鷽ʽ
		*  
		* String new_str=new String(str.getBytes("utf-8"),"gbk"));
		* //���Ѿ������������ֽ�����ת��Ϊgbk�����ʽ���ַ��������ڴ��м�Ϊgbk��ʽ���ֽ�����תΪUnicodeȥ��������
		*/
		String new_str = new String(str.getBytes("utf-8"), "gbk");
		    /**
		     * ��ʱ����������룬��UTF-8��file.encoding�����gbk��ʽ�����ݿ϶�������,����new_str��ȷ��gbk����ʽ��
		     * ��ʱ������Դ��encoding��������gbk��ʽ��new_str�������ݲ�û������,ͨ�������ת��Ҳ���Կ��ó���
		     */
		System.out.println("new string----" + new_str);
		String final_str = new String(new_str.getBytes("gbk"), "utf-8");//�˴��ĺ��������ϱߵ�ע����һ�µĲ�������
		/**
		*����������ģ���ʱ��gbk�����ʽ��new_str�ַ�������gbk���charsetȥ��������Ȼ����utf-8��ת��һ�Σ�
		    ��Ϊnew_strȷʵ��gbk��ʽ�ģ����ܾ���utf-8����õ�������������ʾ��
		*/
		System.out.println("final string---"+final_str);
	}
}
