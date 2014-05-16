package utils.JUtils.chenbug.chineseren.mytable;
   
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   
import javax.swing.table.*;   
import javax.swing.event.*;   
import java.util.*;   
   
/**  
 * <p>Title: MyTable</p>  
 * <p>Description: ʵ���Լ���Table,��������:</p>  
 * ����JTable����TableColumnModel���ǲ����������е�,���Բ�����MyTableColumnModel��!!  
 * �̳�ԭ��JTable�Ĺ���  
 * �Լ��������Լ�(TableModelListener,TableColumnModelListener, ListSelectionListener)  
 * ʵ�����л�������Sorter(��MyTableSotter���г�ʼ��)  
 * ʵ������: ��ʼ��,�����п�,ѡ��,����,��������,˫��,�Ҽ��˵�,����Enter,����Del,����F5ˢ��  
 * Ĭ��ʱ������MyTableSorter�����  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class MyTable   
    extends JTable   
    implements MouseListener, KeyListener {   
   
  //���MyTableModel������������   
  public Vector getColumnIdentifiers(){   
    return this.getMyTableModel().getColumnIdentifiers();   
  }   
  public void setColumnIdentifiers(Object[] columnIdentifiers){   
    this.getMyTableModel().setColumnIdentifiers(columnIdentifiers);   
    //this.revalidate();   
  }   
  public void setColumnIdentifiers(Vector columnIdentifiers){   
    this.getMyTableModel().setColumnIdentifiers(columnIdentifiers);   
    //this.revalidate();   
  }   
     
  //MyTable�Ĺ��߷���: ��ȡdataVector�е�row����¼�ĵ�column�е�ֵ   
  protected static Object getValueAtVectors(Vector dataVector,int row, int column){   
    return ((Vector)dataVector.get(row)).get(column);   
  }   
     
  //�ÿ����ķ�ʽ��dataVectorת����Tbl��ʾ��ʽ,Ĭ��ʲôҲ��ת�����������ʵ��   
  protected Object[][] formatVectors(Object[][] dataVector){   
    /** @todo: ��������ת��dataVector */   
    return dataVector;   
  }   
  //�ÿ����ķ�ʽ��dataVectorת����Tbl��ʾ��ʽ,Ĭ��ʲôҲ��ת�����������ʵ��   
  protected Vector formatVectors(Vector dataVector){   
    /** @todo: ��������ת��dataVector */   
    /*int iRows = dataVector.size();  
    Vector rows = new Vector();  
    for (int i = 0; i  iRows; i++) {  
      Vector row = new Vector();  
      row.add(getValueAtVectors(dataVector,i,0));  
      row.add(getValueAtVectors(dataVector,i,1));  
      row.add(getValueAtVectors(dataVector,i,4));  
      row.add(getValueAtVectors(dataVector,i,2));  
      row.add(getValueAtVectors(dataVector,i,3));  
      rows.add(row);  
    }  
    return rows;*/   
    return dataVector;   
  }   
   
  //���MyTableModel��dataVector   
  public Vector getDataVector(){   
    return this.getMyTableModel().getDataVector();   
  }   
  public void setDataVector(Vector dataVector,Vector columnIdentifiers){   
    this.getMyTableModel().setDataVector(this.formatVectors(dataVector),columnIdentifiers);   
    this.selectFirstRow();   
    this.setColumnWidth();   
    //this.revalidate();   
  }   
  public void setDataVector(Object[][] dataVector,Object[] columnIdentifiers){   
    this.getMyTableModel().setDataVector(this.formatVectors(dataVector),columnIdentifiers);   
    this.selectFirstRow();   
    this.setColumnWidth();   
    //this.revalidate();   
  }   
  public void setDataVector(Vector dataVector){   
    this.getMyTableModel().setDataVector(this.formatVectors(dataVector),this.getColumnIdentifiers());   
    this.selectFirstRow();   
    this.setColumnWidth();   
    //this.revalidate();   
  }   
   
  //���ָ����row������(Vector)rowValue   
  public Vector getRowValue(int row){   
    return (Vector)this.getDataVector().get(row);   
  }   
  //��������Ƚ���(����<5��)ʱ������,���޸�����ʱ�����޸���������!!??���Ҳ������û��϶���   
  public void setRowValue(int row,Object[] rowValue){   
    if(null == rowValue){   
      return;   
    }   
    for (int i = 0; i  rowValue.length; i++) {   
      this.setValueAt(rowValue[i],row,i);   
    }   
    //ѡ�б��޸ĵ���   
    this.selectRow(row);   
  }   
  public void setRowValue(int row,Vector rowValue){   
    this.setRowValue(row,rowValue.toArray());   
  }   
   
  //ѡ�е�row��,�����򲻶���   
  public void selectRow(int row){   
    int count = this.getRowCount();   
    if(0 >= count || 0 > row || row >= count){   
      return;   
    }   
    this.setRowSelectionInterval(row,row);   
  }   
  //ѡ�е�һ��,�����򲻶���   
  public void selectFirstRow(){   
    this.selectRow(0);   
  }   
   
  //�ڵ�row������һ��rowValue   
  public void addRow(int row,Vector rowValue) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
    this.getMyTableModel().insertRow(row,rowValue);   
    //ѡ����ӵ���   
    this.selectRow(row);   
   
    //�����Լ����¼�,�Լ�����   
    this.onRowAdded(row);   
  }   
  public void addRow(int row,Object[] rowValue) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
    this.getMyTableModel().insertRow(row,rowValue);   
    //ѡ����ӵ���   
    this.selectRow(row);   
   
    //�����Լ����¼�,�Լ�����   
    this.onRowAdded(row);   
  }   
  public void addRow(int row,java.util.List rowValue) {   
    this.addRow(row,rowValue.toArray());   
  }   
  public void addRow(Vector rowValue) {   
    this.addRow(this.getRowCount(),rowValue);   
  }   
  public void addRow(Object[] rowValue) {   
    this.addRow(this.getRowCount(),rowValue);   
  }   
  public void addRow(java.util.List rowValue) {   
    this.addRow(this.getRowCount(),rowValue);   
  }   
   
  //�ӵ�row�п�ʼ�������rowValues��   
  public void addRows(int row,Vector rowValues) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
   
    count = rowValues.size();   
    for (int i = 0; i  count; i++) {   
      //rowValues�д�ŵ�Ҳ��Vector   
      Vector rowValue = (Vector)rowValues.get(i);   
      this.addRow(row+i,rowValue);   
    }   
    //ѡ����ӵ���   
    this.selectRow(row);   
   
    //�����Լ����¼�,�Լ�����   
    this.onRowAdded(row);   
  }   
  public void addRows(int row,Object[] rowValues) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
   
    count = rowValues.length;   
    for (int i = 0; i  count; i++) {   
      //rowValues�д�ŵ�Ҳ��Object[]   
      Object[] rowValue = (Object[])rowValues[i];   
      this.addRow(row+i,rowValue);   
    }   
    //ѡ����ӵ���   
    this.selectRow(row);   
   
    //�����Լ����¼�,�Լ�����   
    this.onRowAdded(row);   
  }   
  public void addRows(int row,java.util.List rowValues) {   
    //rowValues�д�ŵ�Ҳ��Object[]   
    this.addRows(row,rowValues.toArray());   
  }   
  public void addRows(Vector rowValues) {   
    this.addRows(this.getRowCount(),rowValues);   
  }   
  public void addRows(Object[] rowValues) {   
    this.addRows(this.getRowCount(),rowValues);   
  }   
  public void addRows(java.util.List rowValues) {   
    this.addRows(this.getRowCount(),rowValues);   
  }   
   
  /**  
   * ɾ����  
   * @param row �к�,��0��ʼ  
   */   
  public void removeRow(int row) {   
    Vector rowValue = this.getRowValue(row);   
    this.getMyTableModel().removeRow(row);   
    int count = this.getRowCount();   
    //ѡ����һ��   
    if(0  count) { //������   
      if(row == count){//ɾ���������һ��   
        this.selectRow(row-1);   
      }else{   
        this.selectRow(row);   
      }   
    }   
   
    //�����Լ����¼�,�Լ�����   
    this.onRowRemoved(row,rowValue);   
  }   
   
  //����̳�,�������row����֮��   
  public void onRowAdded(int row){   
    /**@todo: is onRowAdded && do something */   
  }   
  //����̳�,��ɾ����row����֮��   
  public void onRowRemoved(int row,Vector rowValue){   
    /**@todo: is onRowRemoved && do something */   
  }   
   
  /**  
   * ɾ����,������ִ�ж���  
   * @param column �к�,��0��ʼ  
   */   
  public void removeColumn(int column) {   
    int count = this.getColumnCount();   
    if(0  count){//������   
      TableColumn columnObj = this.getColumnModel().getColumn(column);   
      super.removeColumn(columnObj);   
    }   
  }   
   
  //��MyTable�ķ����г�ʼ��,�ɹ��캯������   
  protected void initTable(){   
    this.setColumnWidth();   
    this.setFont(new Font("Serif",0,14));   
    this.setRowHeight(20);   
    this.selectFirstRow();//ʵ�� ѡ�е�һ��   
    this.getTableHeader().setReorderingAllowed(false);//ʵ�� Ĭ�ϲ������û��϶���   
    try {   
      this.addMouseListenerToHeader(); //ʵ�� ����   
    } catch(Exception ex) {   
      //˵��TableModel����MyTableSorter   
    }   
    this.addMouseListener(this);//ʵ�� ˫��,�Ҽ��˵�   
    this.addKeyListener(this);//ʵ�� ����Enter,����Del,����F5ˢ��   
  }   
  public MyTable() {   
    super();   
    //Ĭ����ʵ��MyTableSorter��   
    this.setModel(new MyTableSorter());   
    //this.setColumnModel(new MyTableColumnModel());   
    this.setSelectionModel(new MyTableSelectionModel());   
    this.initTable();   
  }   
   
  public MyTable(Dimension size) {   
    this();   
    this.setPreferredScrollableViewportSize(size);   
  }   
   
  public MyTable(MyTableModel dm) {   
    super(dm);   
    //this.setColumnModel(new MyTableColumnModel());   
    this.setSelectionModel(new MyTableSelectionModel());   
    this.initTable();   
  }   
   
  public MyTable(MyTableModel dm, MyTableSelectionModel sm) {   
    super(dm);   
    this.setSelectionModel(sm);   
    this.initTable();   
  }   
   
  /*public MyTable(MyTableModel dm, MyTableColumnModel cm) {  
    super(dm,cm);  
    this.setSelectionModel(new MyTableSelectionModel());  
    this.init();  
  }  
  
  public MyTable(MyTableModel dm, MyTableColumnModel cm, MyTableSelectionModel sm) {  
    super(dm,cm,sm);  
    this.init();  
  }*/   
   
  public MyTable(int numRows, int numColumns) {   
    this(new MyTableSorter(numRows,numColumns));   
  }   
   
  public MyTable(Vector rowData, Vector columnNames) {   
    this(new MyTableSorter(rowData,columnNames));   
  }   
   
  public MyTable(Object[][] rowData, Object[] columnNames) {   
    this(new MyTableSorter(rowData,columnNames));   
  }   
   
  //Ĭ�ϴ���һ��JScrollPane   
  private JScrollPane tablePane = new JScrollPane();   
  /**  
   * ���table��scrollPane  
   * @param size table�Ĵ�С  
   * @return JScrollPane(������MyTable)  
   */   
  public JScrollPane getMyTablePane(Dimension size){   
    this.tablePane.getViewport().add(this);   
    this.tablePane.setPreferredSize(size);   
    return this.tablePane;   
  }   
  public JScrollPane getMyTablePane(int width, int height){   
    return this.getMyTablePane(new Dimension(this.getWidth(),this.getHeight()));   
  }   
  public JScrollPane getMyTablePane(){   
    return this.getMyTablePane(this.getWidth(),this.getHeight());   
  }   
   
  /**  
   * �趨�п�  
   */   
  public void setColumnWidth() {   
    /**@todo: ����table��ʵ�������������(���������̳�,��Ĭ�Ϲ��캯������)*/   
  }   
  public void setColumnWidth(int[] widths) {   
    TableColumn column = null;   
    int colCount = this.getColumnCount();   
    if(null == widths || colCount > widths.length){   
      return;   
    }   
   
    for(int i = 0; i  colCount; i++) {   
      column = this.getColumnModel().getColumn(i);   
      column.setPreferredWidth(widths[i]);   
    }   
  }   
   
  //���MyTableModel   
  public MyTableModel getMyTableModel(){   
    return (MyTableModel)this.getModel();   
  }   
   
  //���MyTableSorter   
  public MyTableSorter getMyTableSorter(){   
    return (MyTableSorter)this.getModel();   
  }   
   
  //���MyTableColumnModel   
  public MyTableColumnModel getMyTableColumnModel(){   
    return (MyTableColumnModel)this.getColumnModel();   
  }   
   
  //���MyTableSelectionModel   
  public MyTableSelectionModel getMyTableSelectionModel(){   
    return (MyTableSelectionModel)this.getSelectionModel();   
  }   
   
  // Add a mouse listener to the Table to trigger a table sort   
  // when a column heading is clicked in the JTable.   
  public void removeMouseListenerFromHeader(){   
    //ȫ���Ƴ�   
    JTableHeader th = this.getTableHeader();   
    MouseListener[] ls = th.getMouseListeners();   
    if(null != ls){   
      for (int i = 0; i  ls.length; i++) {   
        th.removeMouseListener(ls[i]);   
      }   
    }   
  }   
  public void addMouseListenerToHeader() {   
    this.getMyTableSorter().addMouseListenerToHeaderInTable(this);   
  }   
   
  //MyTable�а�����һ��JPopupMenu,��������滻����   
  private JPopupMenu popupMenu = new JPopupMenu("���˵�");   
  public JPopupMenu getPopupMenu(){   
    return this.popupMenu;   
  }   
  public void setPopupMenu(JPopupMenu popupMenu){   
    this.popupMenu = popupMenu;   
  }   
  public void addMenuItemToPopupMenu(JMenuItem menuItem){   
    this.popupMenu.add(menuItem);   
  }   
   
  //ʵ��MouseListener: ˫��,�Ҽ��˵�   
  public void mouseClicked(MouseEvent e) {   
    if(e.getClickCount() == 2 && this.getSelectedRow() != -1) {   
      this.onDoubleClicked(e);   
    }else{   
      this.onClicked(e);   
    }   
  }   
  public void onDoubleClicked(MouseEvent e){//˫��֮��,��ʾ��ϸ��ϢDlg,�޸�   
    /**@todo: is double click && do something */   
  }   
   
  public void onClicked(MouseEvent e) { //����֮��,ѡ����,���ܵ����Ҽ��˵�   
    //let's select it!   
    int rowSeled = this.rowAtPoint(new Point(e.getX(), e.getY()));   
    if( -1 != rowSeled) {   
      this.selectRow(rowSeled);   
    }   
    if(-1 != this.getSelectedRow() && e.isPopupTrigger()) {   
      this.popupMenu.show(e.getComponent(),e.getX(),e.getY());   
    }   
  }   
  public void mousePressed(MouseEvent e) {   
    onClicked(e);   
  }   
  public void mouseReleased(MouseEvent e) {   
    onClicked(e);   
  }   
  public void mouseEntered(MouseEvent e) {   
    /**@todo: is mouseEntered && do something */   
  }   
  public void mouseExited(MouseEvent e) {   
    /**@todo: is mouseExited && do something */   
  }   
   
  //ɾ����֮ǰ�Ƿ�Ҫѯ��,Ĭ��Ҫ��   
  private boolean isAsk = true;   
  public boolean getIsAsk(){   
    return isAsk;   
  }   
  public void setIsAsk(boolean isAsk){   
    this.isAsk = isAsk;   
  }   
   
  //ʵ��KeyListener: ����Enter,����Del,����F5ˢ��   
  public void keyTyped(KeyEvent e) {   
    /**@todo: is keyTyped && do something */   
  }   
  public void keyReleased(KeyEvent e) {   
    /**@todo: is keyReleased && do something */   
  }   
  public void keyPressed(KeyEvent e) {   
    int key = e.getKeyCode();   
    switch(key) {   
      case KeyEvent.VK_ENTER:   
        if(-1 != this.getSelectedRow()) {   
          onKeyEnter(e);   
        }   
        return;   
      case KeyEvent.VK_DELETE:   
        int row = this.getSelectedRow();   
        if( -1 != row && (!this.isAsk ||   
            JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,   
            "��ȷʵҪɾ���� " + row + " ��������?", "ɾ������",   
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))) {   
          onKeyDelete(e);//doBussiness(),û�п��ǵ��������,���緵�ز����Ƿ�ɹ�   
          this.removeRow(row);//ɾ����   
        }   
        return;   
      case KeyEvent.VK_F5:   
        this.revalidate();   
        onKeyF5(e);   
        return;   
      default:   
        return;   
    }   
  }   
   
  //����Enter,����Del,����F5ˢ��   
  public void onKeyEnter(KeyEvent e){//�൱��˫�����onDoubleClick   
    onDoubleClicked(null);   
  }   
  public void onKeyDelete(KeyEvent e){//�൱��ɾ����֮ǰ��ʾ֮����"��"   
    /**@todo: is onKeyDelete && do something */   
  }   
  public void onKeyF5(KeyEvent e){//�൱��ˢ��MyTable,���²�ѯ����   
    this.refresh();   
  }   
   
  //F5ˢ��MyTable   
  public void refresh(){   
    this.setDataVector(this.getDataVector());   
    this.selectFirstRow();   
    this.revalidate();   
  }   
}  



