package utils.JUtils.chenbug.chineseren.mytable;
   
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   
import javax.swing.table.*;   
import javax.swing.event.*;   
import java.util.*;   
   
/**  
 * <p>Title: MyTable</p>  
 * <p>Description: 实现自己的Table,功能如下:</p>  
 * 由于JTable设置TableColumnModel后是不能正常运行的,所以不采用MyTableColumnModel类!!  
 * 继承原有JTable的功能  
 * 自己监听了自己(TableModelListener,TableColumnModelListener, ListSelectionListener)  
 * 实现了切换排序功能Sorter(用MyTableSotter进行初始化)  
 * 实现内容: 初始化,设置列宽,选择,排序,增减行列,双击,右键菜单,键盘Enter,键盘Del,键盘F5刷新  
 * 默认时都采用MyTableSorter的组件  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class MyTable   
    extends JTable   
    implements MouseListener, KeyListener {   
   
  //获得MyTableModel的所有列名称   
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
     
  //MyTable的工具方法: 获取dataVector中第row条记录的第column列的值   
  protected static Object getValueAtVectors(Vector dataVector,int row, int column){   
    return ((Vector)dataVector.get(row)).get(column);   
  }   
     
  //用拷贝的方式将dataVector转化成Tbl显示格式,默认什么也不转换由子类具体实现   
  protected Object[][] formatVectors(Object[][] dataVector){   
    /** @todo: 比如这样转化dataVector */   
    return dataVector;   
  }   
  //用拷贝的方式将dataVector转化成Tbl显示格式,默认什么也不转换由子类具体实现   
  protected Vector formatVectors(Vector dataVector){   
    /** @todo: 比如这样转化dataVector */   
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
   
  //获得MyTableModel的dataVector   
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
   
  //获得指定行row的数据(Vector)rowValue   
  public Vector getRowValue(int row){   
    return (Vector)this.getDataVector().get(row);   
  }   
  //如果行数比较少(比如<5行)时有问题,当修改这行时还会修改其它几行!!??而且不能让用户拖动列   
  public void setRowValue(int row,Object[] rowValue){   
    if(null == rowValue){   
      return;   
    }   
    for (int i = 0; i  rowValue.length; i++) {   
      this.setValueAt(rowValue[i],row,i);   
    }   
    //选中被修改的行   
    this.selectRow(row);   
  }   
  public void setRowValue(int row,Vector rowValue){   
    this.setRowValue(row,rowValue.toArray());   
  }   
   
  //选中第row行,出错则不动作   
  public void selectRow(int row){   
    int count = this.getRowCount();   
    if(0 >= count || 0 > row || row >= count){   
      return;   
    }   
    this.setRowSelectionInterval(row,row);   
  }   
  //选中第一行,出错则不动作   
  public void selectFirstRow(){   
    this.selectRow(0);   
  }   
   
  //在第row行增加一行rowValue   
  public void addRow(int row,Vector rowValue) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
    this.getMyTableModel().insertRow(row,rowValue);   
    //选中添加的行   
    this.selectRow(row);   
   
    //触发自己的事件,自己监听   
    this.onRowAdded(row);   
  }   
  public void addRow(int row,Object[] rowValue) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
    this.getMyTableModel().insertRow(row,rowValue);   
    //选中添加的行   
    this.selectRow(row);   
   
    //触发自己的事件,自己监听   
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
   
  //从第row行开始批量添加rowValues行   
  public void addRows(int row,Vector rowValues) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
   
    count = rowValues.size();   
    for (int i = 0; i  count; i++) {   
      //rowValues中存放的也是Vector   
      Vector rowValue = (Vector)rowValues.get(i);   
      this.addRow(row+i,rowValue);   
    }   
    //选中添加的行   
    this.selectRow(row);   
   
    //触发自己的事件,自己监听   
    this.onRowAdded(row);   
  }   
  public void addRows(int row,Object[] rowValues) {   
    int count = this.getRowCount();   
    if(row > count){   
      row = count;   
    }   
   
    count = rowValues.length;   
    for (int i = 0; i  count; i++) {   
      //rowValues中存放的也是Object[]   
      Object[] rowValue = (Object[])rowValues[i];   
      this.addRow(row+i,rowValue);   
    }   
    //选中添加的行   
    this.selectRow(row);   
   
    //触发自己的事件,自己监听   
    this.onRowAdded(row);   
  }   
  public void addRows(int row,java.util.List rowValues) {   
    //rowValues中存放的也是Object[]   
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
   * 删除行  
   * @param row 行号,从0开始  
   */   
  public void removeRow(int row) {   
    Vector rowValue = this.getRowValue(row);   
    this.getMyTableModel().removeRow(row);   
    int count = this.getRowCount();   
    //选中下一行   
    if(0  count) { //还有行   
      if(row == count){//删除的是最后一行   
        this.selectRow(row-1);   
      }else{   
        this.selectRow(row);   
      }   
    }   
   
    //触发自己的事件,自己监听   
    this.onRowRemoved(row,rowValue);   
  }   
   
  //子类继承,当添加完row动作之后   
  public void onRowAdded(int row){   
    /**@todo: is onRowAdded && do something */   
  }   
  //子类继承,当删除完row动作之后   
  public void onRowRemoved(int row,Vector rowValue){   
    /**@todo: is onRowRemoved && do something */   
  }   
   
  /**  
   * 删除列,出错则不执行动作  
   * @param column 列号,从0开始  
   */   
  public void removeColumn(int column) {   
    int count = this.getColumnCount();   
    if(0  count){//还有列   
      TableColumn columnObj = this.getColumnModel().getColumn(column);   
      super.removeColumn(columnObj);   
    }   
  }   
   
  //对MyTable的风格进行初始化,由构造函数调用   
  protected void initTable(){   
    this.setColumnWidth();   
    this.setFont(new Font("Serif",0,14));   
    this.setRowHeight(20);   
    this.selectFirstRow();//实现 选中第一行   
    this.getTableHeader().setReorderingAllowed(false);//实现 默认不能让用户拖动列   
    try {   
      this.addMouseListenerToHeader(); //实现 排序   
    } catch(Exception ex) {   
      //说明TableModel不是MyTableSorter   
    }   
    this.addMouseListener(this);//实现 双击,右键菜单   
    this.addKeyListener(this);//实现 键盘Enter,键盘Del,键盘F5刷新   
  }   
  public MyTable() {   
    super();   
    //默认是实现MyTableSorter的   
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
   
  //默认带了一个JScrollPane   
  private JScrollPane tablePane = new JScrollPane();   
  /**  
   * 获得table的scrollPane  
   * @param size table的大小  
   * @return JScrollPane(包含了MyTable)  
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
   * 设定列宽  
   */   
  public void setColumnWidth() {   
    /**@todo: 根据table的实际情况单个设置(让子类来继承,由默认构造函数调用)*/   
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
   
  //获得MyTableModel   
  public MyTableModel getMyTableModel(){   
    return (MyTableModel)this.getModel();   
  }   
   
  //获得MyTableSorter   
  public MyTableSorter getMyTableSorter(){   
    return (MyTableSorter)this.getModel();   
  }   
   
  //获得MyTableColumnModel   
  public MyTableColumnModel getMyTableColumnModel(){   
    return (MyTableColumnModel)this.getColumnModel();   
  }   
   
  //获得MyTableSelectionModel   
  public MyTableSelectionModel getMyTableSelectionModel(){   
    return (MyTableSelectionModel)this.getSelectionModel();   
  }   
   
  // Add a mouse listener to the Table to trigger a table sort   
  // when a column heading is clicked in the JTable.   
  public void removeMouseListenerFromHeader(){   
    //全部移除   
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
   
  //MyTable中包含了一个JPopupMenu,子类可以替换掉它   
  private JPopupMenu popupMenu = new JPopupMenu("表格菜单");   
  public JPopupMenu getPopupMenu(){   
    return this.popupMenu;   
  }   
  public void setPopupMenu(JPopupMenu popupMenu){   
    this.popupMenu = popupMenu;   
  }   
  public void addMenuItemToPopupMenu(JMenuItem menuItem){   
    this.popupMenu.add(menuItem);   
  }   
   
  //实现MouseListener: 双击,右键菜单   
  public void mouseClicked(MouseEvent e) {   
    if(e.getClickCount() == 2 && this.getSelectedRow() != -1) {   
      this.onDoubleClicked(e);   
    }else{   
      this.onClicked(e);   
    }   
  }   
  public void onDoubleClicked(MouseEvent e){//双击之后,显示详细信息Dlg,修改   
    /**@todo: is double click && do something */   
  }   
   
  public void onClicked(MouseEvent e) { //单击之后,选中行,可能弹出右键菜单   
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
   
  //删除行之前是否要询问,默认要问   
  private boolean isAsk = true;   
  public boolean getIsAsk(){   
    return isAsk;   
  }   
  public void setIsAsk(boolean isAsk){   
    this.isAsk = isAsk;   
  }   
   
  //实现KeyListener: 键盘Enter,键盘Del,键盘F5刷新   
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
            "您确实要删除第 " + row + " 条数据吗?", "删除数据",   
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE))) {   
          onKeyDelete(e);//doBussiness(),没有考虑到事务操作,比如返回操作是否成功   
          this.removeRow(row);//删除行   
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
   
  //键盘Enter,键盘Del,键盘F5刷新   
  public void onKeyEnter(KeyEvent e){//相当于双击鼠标onDoubleClick   
    onDoubleClicked(null);   
  }   
  public void onKeyDelete(KeyEvent e){//相当于删除行之前提示之后按了"是"   
    /**@todo: is onKeyDelete && do something */   
  }   
  public void onKeyF5(KeyEvent e){//相当于刷新MyTable,重新查询数据   
    this.refresh();   
  }   
   
  //F5刷新MyTable   
  public void refresh(){   
    this.setDataVector(this.getDataVector());   
    this.selectFirstRow();   
    this.revalidate();   
  }   
}  



