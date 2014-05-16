package utils.JUtils.chenbug.chineseren.mytable;
   
import javax.swing.table.*;   
import javax.swing.event.*;   
import java.util.*;   
   
/**  
 * <p>Title: MyTableModel</p>  
 * <p>Description: ʵ���Լ���TableModel,��������:</p>  
 * �̳�ԭ��DefaultTableModel�Ĺ���  
 * ʵ����TableModelListener�ӿ�,�Լ��������Լ�  
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
   * ����getColumnClass,��ΪĬ���Ƿ���Object.class  
   * @param column �к�  
   * @return Class ����: String Integer Boolean�ȵ�,Table����ʾ����ʽҲ��һ��  
   */   
  public Class getColumnClass(int column){   
    try {   
      return this.getValueAt(0, column).getClass();   
    } catch(Exception ex) {   
      return Object.class;   
    }   
  }   
   
  /**  
   * �������е�cell�Ƿ���Ա༭,Ĭ��Ϊfalse  
   * ��������̳�isCellEditable()  
   * @param row ����  
   * @param column ����  
   * @return false  
   */   
  public boolean isCellEditable(int row, int column){   
    return false;   
  }   
   
  //��������е�����Vector   
  public Vector getColumnIdentifiers(){   
    return this.columnIdentifiers;   
  }   
   
  /**  
   * ɾ����  
   * @param row �к�,��0��ʼ  
   */   
  public void removeRow(int row) {   
    int count = this.getRowCount();   
    if(0  count){//������   
      super.removeRow(row);   
      this.setRowCount(count-1);   
    }   
  }   
   
  /*public void setValueAt(Object value,int row,int column){  
    //super.setValueAt(value,row,column);  
    ((Vector)this.getDataVector().get(row)).set(column,value);  
    this.fireTableCellUpdated(row,column);  
  }*/   
  /*������̳�һ��,��ʽ����ʾ��Ӧ���ֶ�  
  public Object getValueAt(int row,int column){  
    //super.getValueAt(row,column);  
    //����ʵ��ҵ����ʾҪ��,��model�е�rowRata����Ӧ��ת��һ����ʾ  
    //����: long�ֶ���ʾ��dateTime��ʽ�ȵ�  
  }*/   
   
  /**  
   * ʵ���Լ������Լ�,Ĭ��ʲôҲ����  
   * ��7�з�ʽ�ֶ�������(����setValueAt()���Զ��������¼�):  
    this.fireTableCellUpdated();  
    this.fireTableChanged();  
    this.fireTableDataChanged();  
    this.fireTableRowsDeleted();  
    this.fireTableRowsInserted();  
    this.fireTableRowsUpdated();  
    this.fireTableStructureChanged();  
   * @param e �����¼�  
   */   
  public void tableChanged(TableModelEvent e){   
    /**@todo: something*/   
    //fireTableChanged(e);   
  }   
}  



