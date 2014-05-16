package utils.JUtils.chenbug.chineseren.mytable;
  
import javax.swing.table.*;  
import javax.swing.event.*;  
import java.util.*;  
  
/** 
 * <p>Title: MyTableColumnModel</p> 
 * <p>Description: 实现自己的TableColumnModel,自己监听自己</p> 
 * 由于JTable设置TableColumnModel后是不能正常运行的,所以不采用该类!! 
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
    //默认的SelectionModel是Single模式  
    this.setSelectionModel(new MyTableSelectionModel());  
    this.addColumnModelListener(this);  
  }  
  
  /** 
   * 删除列,出错则不执行动作 
   * @param column 列号,从0开始 
   */  
  public void removeColumn(int column) {  
    int count = this.getColumnCount();  
    if(0  count){//还有列  
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



