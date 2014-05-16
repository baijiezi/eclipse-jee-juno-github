package utils.JUtils.chenbug.chineseren.mytable;
   
import javax.swing.table.*;   
import javax.swing.event.*;   
import java.util.*;   
   
/**  
 * <p>Title: MyTableModel</p>  
 * <p>Description: 实现自己的TableModel,功能如下:</p>  
 * 继承原有DefaultTableModel的功能  
 * 实现了TableModelListener接口,自己监听了自己  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class MyTableModel   
    extends DefaultTableModel   
    implements TableModelListener{   
   
  public MyTableModel() {   
    this.addTableModelListener(this);   
  }   
   
  public MyTableModel(int rowCount, int columnCount) {   
    super(rowCount, columnCount);   
    this.addTableModelListener(this);   
  }   
   
  public MyTableModel(Vector columnNames, int rowCount) {   
    super(columnNames, rowCount);   
    this.addTableModelListener(this);   
  }   
   
  public MyTableModel(Object[] columnNames, int rowCount) {   
    super(columnNames, rowCount);   
    this.addTableModelListener(this);   
  }   
   
  public MyTableModel(Vector data, Vector columnNames) {   
    super(data, columnNames);   
    this.addTableModelListener(this);   
  }   
   
  public MyTableModel(Object[][] data, Object[] columnNames) {   
    super(data, columnNames);   
    this.addTableModelListener(this);   
  }   
   
  /**  
   * 重载getColumnClass,因为默认是返回Object.class  
   * @param column 列号  
   * @return Class 比如: String Integer Boolean等等,Table中显示的样式也不一样  
   */   
  public Class getColumnClass(int column){   
    try {   
      return this.getValueAt(0, column).getClass();   
    } catch(Exception ex) {   
      return Object.class;   
    }   
  }   
   
  /**  
   * 设置所有的cell是否可以编辑,默认为false  
   * 或者子类继承isCellEditable()  
   * @param row 无用  
   * @param column 无用  
   * @return false  
   */   
  public boolean isCellEditable(int row, int column){   
    return false;   
  }   
   
  //获得所有列的名称Vector   
  public Vector getColumnIdentifiers(){   
    return this.columnIdentifiers;   
  }   
   
  /**  
   * 删除行  
   * @param row 行号,从0开始  
   */   
  public void removeRow(int row) {   
    int count = this.getRowCount();   
    if(0  count){//还有行   
      super.removeRow(row);   
      this.setRowCount(count-1);   
    }   
  }   
   
  /*public void setValueAt(Object value,int row,int column){  
    //super.setValueAt(value,row,column);  
    ((Vector)this.getDataVector().get(row)).set(column,value);  
    this.fireTableCellUpdated(row,column);  
  }*/   
  /*由子类继承一下,格式化显示相应的字段  
  public Object getValueAt(int row,int column){  
    //super.getValueAt(row,column);  
    //根据实际业务显示要求,将model中的rowRata的相应列转化一下显示  
    //比如: long字段显示成dateTime格式等等  
  }*/   
   
  /**  
   * 实现自己监听自己,默认什么也不做  
   * 有7中方式手动触发它(但是setValueAt()会自动触发该事件):  
    this.fireTableCellUpdated();  
    this.fireTableChanged();  
    this.fireTableDataChanged();  
    this.fireTableRowsDeleted();  
    this.fireTableRowsInserted();  
    this.fireTableRowsUpdated();  
    this.fireTableStructureChanged();  
   * @param e 触发事件  
   */   
  public void tableChanged(TableModelEvent e){   
    /**@todo: something*/   
    //fireTableChanged(e);   
  }   
}  



