package myprj.test.com.office.excel.jxl;

//����Excel����

//jxl��һ��������д��java����excel�Ĺ���, �ڿ�Դ�����У������ױȽ���Ӱ���API�ɹ�ʹ�ã�һ����POI��
//һ����jExcelAPI�����й������POI�Ƚ���һ�㡣��jExcelAPI������֧�ַǳ��ã�API�Ǵ�Java�ģ�
//��������Windowsϵͳ����ʹ������Linux�£���ͬ���ܹ���ȷ�Ĵ���Excel�ļ��� ������Ҫ˵�����ǣ�
//����API��ͼ�κ�ͼ���֧�ֺ����ޣ����ҽ���ʶ��PNG��ʽ��jxl��֧��2007��
import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CreateExcel {
  public static void main(String args[]) {
      try {
          // ���ļ�
          WritableWorkbook book = Workbook.createWorkbook(new File("src\\myprj\\test\\com\\office\\excel\\jxl\\test.xls"));
          // ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
          WritableSheet sheet = book.createSheet("��һҳ", 0);
          // ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
          // �Լ���Ԫ������Ϊtest
          Label label = new Label(0, 0, "test");

          // ������õĵ�Ԫ����ӵ���������
          sheet.addCell(label);

          /*
           * ����һ���������ֵĵ�Ԫ�� ����ʹ��Number��������·�����������﷨���� ��Ԫ��λ���ǵڶ��У���һ�У�ֵΪ789.123
           */
          jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
          sheet.addCell(number);

          // д�����ݲ��ر��ļ�
          book.write();
          book.close();

      } catch (Exception e) {
          System.out.println(e);
      }
  }
}