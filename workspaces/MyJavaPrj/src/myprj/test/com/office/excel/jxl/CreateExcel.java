package myprj.test.com.office.excel.jxl;

//生成Excel的类

//jxl是一个韩国人写的java操作excel的工具, 在开源世界中，有两套比较有影响的API可供使用，一个是POI，
//一个是jExcelAPI。其中功能相对POI比较弱一点。但jExcelAPI对中文支持非常好，API是纯Java的，
//并不依赖Windows系统，即使运行在Linux下，它同样能够正确的处理Excel文件。 另外需要说明的是，
//这套API对图形和图表的支持很有限，而且仅仅识别PNG格式。jxl不支持2007版
import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CreateExcel {
  public static void main(String args[]) {
      try {
          // 打开文件
          WritableWorkbook book = Workbook.createWorkbook(new File("src\\myprj\\test\\com\\office\\excel\\jxl\\test.xls"));
          // 生成名为“第一页”的工作表，参数0表示这是第一页
          WritableSheet sheet = book.createSheet("第一页", 0);
          // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
          // 以及单元格内容为test
          Label label = new Label(0, 0, "test");

          // 将定义好的单元格添加到工作表中
          sheet.addCell(label);

          /*
           * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
           */
          jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
          sheet.addCell(number);

          // 写入数据并关闭文件
          book.write();
          book.close();

      } catch (Exception e) {
          System.out.println(e);
      }
  }
}