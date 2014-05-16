package utils.JUtils.chenbug.chineseren.mytable;
  
import javax.swing.table.*;  
import javax.swing.event.*;  
import java.util.*;  
  
/** 
 * <p>Title: MyTableColumnModel</p> 
 * <p>Description: ʵ���Լ���TableColumnModel,�Լ������Լ�</p> 
 * ����JTable����TableColumnModel���ǲ����������е�,���Բ����ø���!! 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: pubinfo</p> 
 * @author chineseren 
 * @version 1.0 
 */  
  
public class MyTableColumnModel  
    extends DefaultTableColumnModel  
    implements TableColumnModelListener{  
  public MyTableColumnModel() {  
    super();  
    //Ĭ�ϵ�SelectionModel��Singleģʽ  
    this.setSelectionModel(new MyTableSelectionModel());  
    this.addColumnModelListener(this);  
  }  
  
  /** 
   * ɾ����,������ִ�ж��� 
   * @param column �к�,��0��ʼ 
   */  
  public void removeColumn(int column) {  
    int count = this.getColumnCount();  
    if(0  count){//������  
      TableColumn columnObj = this.getColumn(column);  
      super.removeColumn(columnObj);  
    }  
  }  
  
  public void columnAdded(TableColumnModelEvent e) {  
    /**@todo: something*/  
  }  
  
  public void columnRemoved(TableColumnModelEvent e) {  
    /**@todo: something*/  
  }  
  
  public void columnMoved(TableColumnModelEvent e) {  
    /**@todo: something*/  
  }  
  
  public void columnMarginChanged(ChangeEvent e) {  
    /**@todo: something*/  
  }  
  
  public void columnSelectionChanged(ListSelectionEvent e) {  
    /**@todo: something*/  
  }  
}  



