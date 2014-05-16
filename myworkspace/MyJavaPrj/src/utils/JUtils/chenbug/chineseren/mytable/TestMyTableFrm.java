package utils.JUtils.chenbug.chineseren.mytable;
   
import java.util.*;   
import javax.swing.*;   
import javax.swing.event.*;   
import javax.swing.table.*;   
import java.awt.*;   
import java.awt.event.*;   
import testSwing.mytable.*;   
import org.jr.swing.util.SwingUtil;   
   
/**  
 * <p>Title: TestMyTableFrm</p>  
 * <p>Description: 测试TestMyTableFrm</p>  
 * 测试内容: 初始化,重设数据,设置列宽,选择,排序,增减行列,双击,右键菜单,键盘Enter,键盘Del,键盘F5刷新  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class TestMyTableFrm extends JFrame {   
  BorderLayout borderLayout1 = new BorderLayout();   
  JSplitPane jSplitPane1 = new JSplitPane();   
  JPanel jPanel1 = new JPanel();   
  //JPanel jPanel2 = new JPanel();   
  JButton jButton1 = new JButton();   
  JButton jButton2 = new JButton();   
  JButton jButton3 = new JButton();   
  JButton jButton4 = new JButton();   
  JButton jButton5 = new JButton();   
  JButton jButton6 = new JButton();   
  JButton jButton7 = new JButton();   
  JButton jButton8 = new JButton();   
  MyTableSelectionModel myTableSelectionModel = new MyTableSelectionModel(){   
    //重载MyTableSelectionModel的一些方法进行测试   
  };   
  //MyTableColumnModel myTableColumnModel = new MyTableColumnModel(){   
    //重载MyTableColumnModel的一些方法进行测试   
  //};   
  MyTableSorter myTableSorter = new MyTableSorter(rowValueVectors,columnNames){   
    //重载MyTableSorter的一些方法进行测试   
    //显示日期时间类型的列值   
    public Object getValueAt(int row,int column){   
      if(4 == column){   
        long dateLng = ((Long)super.getValueAt(row,column)).longValue();   
        return (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(dateLng));   
      }else{   
        return super.getValueAt(row,column);   
      }   
    }   
  };   
  MyTableModel myTableModel = new MyTableModel(rowValueVectors,columnNames){   
    //重载MyTableSorter的一些方法进行测试   
    //显示日期时间类型的列值   
    public Object getValueAt(int row,int column){   
      if(4 == column){   
        long dateLng = ((Long)super.getValueAt(row,column)).longValue();   
        return (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(dateLng));   
      }else{   
        return super.getValueAt(row,column);   
      }   
    }   
  };   
  MyTable myTable1 = new MyTable(myTableSorter,myTableSelectionModel){   
    //重载MyTable的一些方法进行测试   
    public void onDoubleClicked(MouseEvent e){//双击之后,显示详细信息Dlg,修改   
      JOptionPane.showMessageDialog(this,"It is MyTable.onDoubleClicked!","MyTableEvent",JOptionPane.INFORMATION_MESSAGE);   
    }   
    public void onKeyDelete(KeyEvent e){//相当于删除行之前提示之后按了"是"   
      JOptionPane.showMessageDialog(this,"It is MyTable.onKeyDelete!","MyTableEvent",JOptionPane.INFORMATION_MESSAGE);   
    }   
    /*public void onRowAdded(int row){  
      JOptionPane.showMessageDialog(this,"It is MyTable.onRowAdded! "+row,"MyTableEvent",JOptionPane.INFORMATION_MESSAGE);  
    }*/   
    public void onRowRemoved(int row,Vector rowValue){   
      JOptionPane.showMessageDialog(this,"It is MyTable.onRowRemoved! "+row+"row: "+rowValue.get(0),"MyTableEvent",JOptionPane.INFORMATION_MESSAGE);   
    }   
    public void columnRemoved(TableModelEvent e){   
      JOptionPane.showMessageDialog(this,"It is MyTable.columnRemoved! "+e.getFirstRow(),"MyTableEvent",JOptionPane.INFORMATION_MESSAGE);   
    }   
    public void setColumnWidth() {   
      TableColumn column = null;   
      int colCount = this.getColumnCount();   
      for(int i = 0; i  colCount; i++) {   
        column = this.getColumnModel().getColumn(i);   
        column.setPreferredWidth(50+i*2);   
      }   
    }   
  };   
  //private JScrollPane tablePane = new JScrollPane();   
   
  //和MyTable相关的数据   
  private static Dimension tableSize = new Dimension(500,500);   
  private static Vector columnNames = new Vector();   
  static{   
    columnNames.add("字符串");   
    columnNames.add("整型数字");   
    columnNames.add("长整型数");   
    columnNames.add("小数点数");   
    columnNames.add("日期时间");   
    columnNames.add("开关数值");   
  }   
  private static int[] columnWidths = new int[]{50,60,70,80,90,100};   
  private static JMenuItem menuItem = new JMenuItem("Delete");   
  private static TableColumn tableColumn = new TableColumn();   
  static{   
    tableColumn.setIdentifier("New Column");   
    tableColumn.setPreferredWidth(50);   
  }   
  private static ArrayList rowValueList = new ArrayList();   
  static{   
    rowValueList.add("String");   
    rowValueList.add(Integer.valueOf("10"));   
    rowValueList.add(Long.valueOf("100"));   
    rowValueList.add(Double.valueOf("10.236"));   
    rowValueList.add(new Long(System.currentTimeMillis()));   
    rowValueList.add(Boolean.valueOf("true"));   
  }   
  private static Object[] rowValueArray = new Object[]{"String11",Integer.valueOf("2"),   
      Long.valueOf("1002"),Double.valueOf("120.2136"),new Long(System.currentTimeMillis()),   
      Boolean.valueOf("false")};   
  private static Object[] rowValueArrays = new Object[]{rowValueArray,rowValueArray,rowValueArray};   
  private static Vector rowValueVector = new Vector();   
  static{   
    rowValueVector.add("String22");   
    rowValueVector.add(Integer.valueOf("101"));   
    rowValueVector.add(Long.valueOf("1001"));   
    rowValueVector.add(Double.valueOf("101.2361"));   
    rowValueVector.add(new Long(System.currentTimeMillis()));   
    rowValueVector.add(Boolean.valueOf("true"));   
  }   
  private static Vector rowValueVector0 = new Vector();   
  static{   
    rowValueVector0.add("String21");   
    rowValueVector0.add(Integer.valueOf("22011"));   
    rowValueVector0.add(Long.valueOf("22101"));   
    rowValueVector0.add(Double.valueOf("201.3368"));   
    rowValueVector0.add(new Long(System.currentTimeMillis()+10000));   
    rowValueVector0.add(Boolean.valueOf("false"));   
  }   
  private static Vector rowValueVectors = new Vector();   
  static {   
    rowValueVectors.add(rowValueVector);   
    rowValueVectors.add(rowValueVector0);   
    rowValueVectors.add(rowValueVector);   
    rowValueVectors.add(rowValueVector0);   
    rowValueVectors.add(rowValueVector);   
  }   
  private static Object[] rowValue = new Object[] {   
      "999", Integer.valueOf("999"),   
      Long.valueOf("999"), Double.valueOf("999.999"),   
      new Long("999"),Boolean.valueOf("true")};   
   
  public TestMyTableFrm() {   
    try {   
      jbInit();   
      initMyTable();   
    }   
    catch(Exception e) {   
      e.printStackTrace();   
    }   
  }   
  public static void main(String[] args) {   
    TestMyTableFrm testMyTableFrm = new TestMyTableFrm();   
  }   
  private void initMyTable() {   
    this.myTable1.getMyTableSorter().setDownIcon(new ImageIcon("images/downarrow.gif"));   
    this.myTable1.getMyTableSorter().setUpIcon(new ImageIcon("images/uparrow.gif"));   
    //this.myTable1.getMyTableSorter().setDownIconPath("test/images/downarrow.gif");   
    //this.myTable1.getMyTableSorter().setUpIconPath("test/images/uparrow.gif");   
    //this.myTable1.setColumnIdentifiers(this.columnNames);   
    //this.myTable1.setDataVector(rowValueVectors);   
    this.myTable1.addMenuItemToPopupMenu(this.menuItem);   
    this.myTable1.setIsAsk(false);   
    //this.tablePane.getViewport().add(this.myTable1,null);   
    //this.myTable1.setVisible(true);   
  }   
  private void jbInit() throws Exception {   
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());   
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
    this.setTitle("TestMyTable");   
    this.getContentPane().setLayout(borderLayout1);   
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);   
    jButton1.setActionCommand("初始化");   
    jButton1.setText("初始化");   
    jButton1.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton1_actionPerformed(e);   
      }   
    });   
    jButton2.setActionCommand("重设数据");   
    jButton2.setText("重设数据");   
    jButton2.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton2_actionPerformed(e);   
      }   
    });   
    jButton3.setActionCommand("设置列宽");   
    jButton3.setText("设置列宽");   
    jButton3.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton3_actionPerformed(e);   
      }   
    });   
    jButton4.setActionCommand("增行");   
    jButton4.setText("增行");   
    jButton4.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton4_actionPerformed(e);   
      }   
    });   
    jButton5.setActionCommand("减行");   
    jButton5.setText("减行");   
    jButton5.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton5_actionPerformed(e);   
      }   
    });   
    jButton6.setActionCommand("增列");   
    jButton6.setText("增列");   
    jButton6.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton6_actionPerformed(e);   
      }   
    });   
    jButton7.setText("减列");   
    jButton7.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton7_actionPerformed(e);   
      }   
    });   
    jButton8.setText("修改行值");   
    jButton8.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        jButton8_actionPerformed(e);   
      }   
    });   
    menuItem.addActionListener(new java.awt.event.ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        menuItem_actionPerformed(e);   
      }   
    });   
    this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);   
    jSplitPane1.add(jPanel1, JSplitPane.TOP);   
    jPanel1.add(jButton1, null);   
    jPanel1.add(jButton2, null);   
    jPanel1.add(jButton3, null);   
    jPanel1.add(jButton4, null);   
    jPanel1.add(jButton5, null);   
    jPanel1.add(jButton6, null);   
    jPanel1.add(jButton7, null);   
    jPanel1.add(jButton8, null);   
    jSplitPane1.add(myTable1.getMyTablePane(this.tableSize), JSplitPane.BOTTOM);   
    //jSplitPane1.add(jPanel2, JSplitPane.BOTTOM);   
    //jPanel2.add(myTable1.getMyTablePane(this.tableSize), null);   
    //jPanel2.add(tablePane, BorderLayout.CENTER);   
    jSplitPane1.setDividerLocation(50);   
    this.show();   
    this.setSize(new Dimension(500,500));   
    this.pack();   
    SwingUtil.centerWindow(this);   
  }   
   
  void jButton1_actionPerformed(ActionEvent e) {//初始化   
    this.initMyTable();   
  }   
   
  void jButton2_actionPerformed(ActionEvent e) {//重设数据   
    this.myTable1.setDataVector(rowValueVectors);   
  }   
   
  void jButton3_actionPerformed(ActionEvent e) {//设置列宽   
    this.myTable1.setColumnWidth(this.columnWidths);   
  }   
   
  void jButton4_actionPerformed(ActionEvent e) {//增行   
    this.myTable1.addRow(0,rowValueList);   
    this.myTable1.addRow(this.myTable1.getRowCount(),rowValueArray);   
    this.myTable1.addRow(rowValueVector);   
    this.myTable1.addRows(0,rowValueVectors);   
    this.myTable1.addRows(this.myTable1.getRowCount(),rowValueArrays);   
  }   
   
  void jButton5_actionPerformed(ActionEvent e) {//减行   
    this.myTable1.removeRow(this.myTable1.getRowCount()-1);   
  }   
   
  void jButton6_actionPerformed(ActionEvent e) {//增列   
    this.myTable1.addColumn(this.tableColumn);   
  }   
   
  void jButton7_actionPerformed(ActionEvent e) {//减列   
    this.myTable1.removeColumn(this.myTable1.getColumnCount()-1);   
  }   
   
  void jButton8_actionPerformed(ActionEvent e) {//修改行值   
    this.myTable1.setRowValue(this.myTable1.getRowCount()-1,rowValue);   
  }   
   
  void menuItem_actionPerformed(ActionEvent e) {   
    this.myTable1.removeRow(this.myTable1.getRowCount()-1);   
  }   
   
}  



