package utils.JUtils.chenbug.chineseren.mytable;
   
import java.util.*;   
import java.awt.event.*;   
import javax.swing.*;   
import javax.swing.table.*;   
import javax.swing.event.*;   
   
/**  
 * <p>Title: MyTableSorter</p>  
 * <p>Description: 实现自己的TableSoter,功能如下:</p>  
 * 继承原有MyTableModel的功能  
 * 实现了切换排序功能Sorter(排序的图片默认)  
 * <p>Copyright: Copyright (c) 2003</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
   
public class MyTableSorter extends MyTableModel {   
   
  public MyTableSorter() {   
    reallocateIndexes();   
  }   
   
  public MyTableSorter(int rowCount, int columnCount) {   
    super(rowCount, columnCount);   
    reallocateIndexes();   
  }   
   
  public MyTableSorter(Vector columnNames, int rowCount) {   
    super(columnNames, rowCount);   
    reallocateIndexes();   
  }   
   
  public MyTableSorter(Object[] columnNames, int rowCount) {   
    super(columnNames, rowCount);   
    reallocateIndexes();   
  }   
   
  public MyTableSorter(Vector data, Vector columnNames) {   
    super(data, columnNames);   
    reallocateIndexes();   
  }   
   
  public MyTableSorter(Object[][] data, Object[] columnNames) {   
    super(data, columnNames);   
    reallocateIndexes();   
  }   
   
  /**  
   * A sorter for TableModels. The sorter has a model (conforming to TableModel)  
   * and itself implements TableModel. TableSorter does not store or copy  
   * the data in the TableModel, instead it maintains an array of  
   * integers which it keeps the same size as the number of rows in its  
   * model. When the model changes it notifies the sorter that something  
   * has changed eg. "rowsAdded" so that its internal array of integers  
   * can be reallocated. As requests are made of the sorter (like  
   * getValueAt(row, col) it redirects them to its model via the mapping  
   * array. That way the TableSorter appears to hold another copy of the table  
   * with the rows in a different order. The sorting algorthm used is stable  
   * which means that it does not move around rows when its comparison  
   * function returns 0 to denote that they are equivalent.  
   *  
   * @version 1.5 12/17/97  
   * @author Philip Milne, modified by me  
   */   
  private final boolean debug = false;   
  private static final int UP = 0;   
  private static final int DOWN = 1;   
  private ImageIcon upIcon = new ImageIcon("images/uparrow.gif");//10*10.gif   
  private ImageIcon downIcon = new ImageIcon("images/downarrow.gif");   
  public ImageIcon getUpIcon(){   
    return this.upIcon;   
  }   
  public void setUpIcon(ImageIcon upIcon){   
    this.upIcon = upIcon;   
  }   
  public ImageIcon getDownIcon(){   
    return this.downIcon;   
  }   
  public void setDownIcon(ImageIcon downIcon){   
    this.downIcon = downIcon;   
  }   
  /*private String upIconPath = "images/uparrow.gif";//比如:"test/images/downarrow.gif"  
  private String downIconPath = "images/downarrow.gif";  
  public String getUpIconPath(){  
    return this.upIconPath;  
  }  
  public void setUpIconPath(String upIconPath){  
    this.upIconPath = upIconPath;  
  }  
  public String getDownIconPath(){  
    return this.downIconPath;  
  }  
  public void setDownIconPath(String downIconPath){  
    this.downIconPath = downIconPath;  
  }*/   
   
  private int[] indexes;   
  private Vector sortingColumns = new Vector();   
  private boolean ascending = true;   
  private int compares;   
  //For the table column header sort icon   
  int       sortOrder = DOWN; //expected sort order   
  int       sortColumn = -1; //sort which model column   
  int       sortViewColumn = -1; //sort which view column   
   
  public int[] getIndices() {   
    return indexes;   
  }   
  // The mapping only affects the contents of the data rows.   
  // Pass all requests to these rows through the mapping array: "indexes".   
  public Object getValueAt(int aRow, int aColumn) {   
    checkModel();   
    return super.getValueAt(aRow, aColumn);   
  }   
   
  public void setValueAt(Object aValue, int aRow, int aColumn) {   
    checkModel();   
    super.setValueAt(aValue, aRow, aColumn);   
  }   
   
  public void tableChanged(TableModelEvent e) {   
    //System.out.println("Sorter: tableChanged");   
    reallocateIndexes();   
    super.tableChanged(e);   
  }   
   
  //生成列的带图片的HeaderRenderer   
  private DefaultTableCellRenderer createHeaderRenderer(String colName,Icon icon){   
    DefaultTableCellRenderer picLabel = new DefaultTableCellRenderer();   
    picLabel.setText(colName);   
    picLabel.setBorder(UIManager.getBorder("TableHeader.CellBorder"));   
    picLabel.setHorizontalAlignment(JLabel.CENTER);   
    picLabel.setBackground(java.awt.Color.lightGray);   
    picLabel.setForeground(java.awt.Color.blue);   
    if(null != icon){   
      picLabel.setIcon(icon);   
    }   
    return picLabel;   
  }   
   
  // Add a mouse listener to the Table to trigger a table sort   
  // when a column heading is clicked in the JTable.   
  public void addMouseListenerToHeaderInTable(JTable table) {   
    final MyTableSorter sorter = this;   
    final JTable tableView = table;   
    //turn off this, otherwise, it would highlight the column.   
    //tableView.setColumnSelectionAllowed(false);   
    MouseAdapter listMouseListener = new MouseAdapter() {   
      public void mouseClicked(MouseEvent e) {   
        TableColumnModel columnModel = tableView.getColumnModel();   
        int viewColumn = columnModel.getColumnIndexAtX(e.getX());   
        debug("=======================================");   
        debug("view column=" + viewColumn);   
        int column = tableView.convertColumnIndexToModel(viewColumn);   
        debug("model column=" + column);   
        debug("old sorting column=" + sortColumn);   
        debug("old sorting view column=" + sortViewColumn);   
        debug("old sorting order=" + sortOrder);   
        if(e.getClickCount() == 1 && viewColumn != -1) {   
          // jump between columns, ascending and descending   
          sorter.sortByColumn(column,(sortColumn != column) || (sortOrder == DOWN));   
          sortColumn = column;   
          // now memorize this sorting column   
          // clear all column headers. Since we don't know which one   
          // has the sorting flag, so clear them all.   
          // don't try to memorize that flag(view column) because   
          // users can switch view columns without notifying you!   
   
          //目前列头即能采用JLabel显示成图片格式,也能用<img/>显示,而且还要用"file:///"相对于当前盘符的路径   
          DefaultTableCellRenderer picLabel;   
          /*int len = columnModel.getColumnCount();  
          for(int i = 0; i  len; i++) {  
            picLabel = sorter.createHeaderRenderer(tableView.getColumnName(i),null);  
            //columnModel.getColumn(i).setHeaderValue(picLabel.getText());  
            columnModel.getColumn(i).setHeaderRenderer(picLabel);  
          }*/   
          // now set the correct icon for the column   
          String columntitle = tableView.getColumnName(viewColumn);   
          columntitle = "<html><font color=blue><B>" + columntitle + "</B></font></html>";   
          if(sortViewColumn != viewColumn) {   
            //columntitle = "<html><img src='file:///"+sorter.downIconPath+"' border='0' width='10' height='10'><font color=blue>"   
            //+ columntitle + "</font></html>";   
            picLabel = sorter.createHeaderRenderer(columntitle,downIcon);   
            sortOrder = UP;   
          } else if(sortOrder == DOWN) {   
            //columntitle = "<html><img src='file:///"+sorter.downIconPath+"' border='0' width='10' height='10'><font color=blue>"   
            //+ columntitle + "</font></html>";   
            picLabel = sorter.createHeaderRenderer(columntitle,downIcon);   
            sortOrder = UP;   
          } else {   
            //columntitle = "<html><img src='file:///"+sorter.upIconPath+"' border='0' width='10' height='10'><font color=blue>"   
            //+ columntitle + "</font></html>";   
            picLabel = sorter.createHeaderRenderer(columntitle,upIcon);   
            sortOrder = DOWN;   
          }   
          tableView.getColumnModel().getColumn(viewColumn).setHeaderRenderer(picLabel);   
          //tableView.getColumnModel().getColumn(viewColumn).setHeaderValue(columntitle);   
          sortViewColumn = viewColumn;   
          // these two lines are working, so we need to figure out the height   
          tableView.getTableHeader().setPreferredSize(new java.awt.Dimension(0,25));   
          tableView.getTableHeader().resizeAndRepaint();   
          // Setting the preferred size on the column header renderer will not   
          // work when you are using rowheaders and corner components in the   
          // containing scrollpane. If the corner component is taller than your   
          // table header, it will not display fully. So instead, set the preferred   
          // size of the viewport containing your table/column header like this:   
          // scrollpane.getColumnHeader().setPreferredSize(new java.awt.Dimension(0, 100));   
          // however, for now, we just set this:   
          // JLabel l=(JLabel)tableView.getColumnModel().getColumn(viewColumn).getHeaderRenderer();   
          // l.setPreferredSize(new java.awt.Dimension(0,100));   
          // however, it's working for this app, since   
          // System.out.println(tableView.getColumnModel().getColumn(viewColumn).getHeaderRenderer().getClass().getName());   
          // returns   
          // lib.swing.table.JComponentTableCellRenderer   
          //MyTable排序之后会将各个列宽度平均化,所以要重新调整   
          try {   
            ((MyTable)tableView).setColumnWidth();   
          } catch(Exception ex) {   
            //说明该JTable不MyTable   
          }   
        }   
      }   
    };   
    JTableHeader th = tableView.getTableHeader();   
    th.addMouseListener(listMouseListener);   
  }   
   
  private final void debug(String s) {   
    if(debug == true && s != null && !s.trim().equals(""))   
      System.out.println("TableSorter: -- " + s);   
  }   
  // private implementation starts here   
  // ======================================================================   
  private void checkModel() {   
    if(indexes.length != this.getRowCount()) {   
      debug("Sorter not informed of a change in model.");   
    }   
  }   
   
  private void reallocateIndexes() {   
    int rowCount = this.getRowCount();   
    // Set up a new array of indexes with the right number of elements   
    // for the new data model.   
    indexes = new int[rowCount];   
    // Initialise with the identity mapping.   
    for(int row = 0; row  rowCount; row++) {   
      indexes[row] = row;   
    }   
  }   
  //sort starts from here. The following functions are called one inside another.   
  private void sortByColumn(int column) {   
    sortByColumn(column, true);   
  }   
   
  private void sortByColumn(int column, boolean ascending) {   
    this.ascending = ascending;   
    sortingColumns.removeAllElements();   
    sortingColumns.addElement(new Integer(column));   
    sort(this);   
    //changed by chineseren for resort Table rows by indexes[]   
    //super.tableChanged(new TableModelEvent(this));   
    sortRows();   
  }   
   
  private void sortRows(){   
    Vector dataVector = this.getDataVector();   
    Vector newData = new Vector();   
    for (int i = 0; i  indexes.length; i++) {   
      //dataVector重新排序   
      newData.add(i,dataVector.get(indexes[i]));   
    }   
    this.setDataVector(newData,this.getColumnIdentifiers());   
    dataVector = null;   
    reallocateIndexes();   
  }   
   
  private void sort(Object sender) {   
    checkModel();   
    compares = 0;   
    //changed by chineseren for sort indexes   
    shuttlesort((int[])indexes.clone(), indexes, 0, indexes.length);   
    //sortIndexes();   
  }   
  /*private void sortIndexes(){  
    //从第n-1个开始向上冒泡排序,上面的是"小"的  
    for (int i = 0; i  indexes.length-1; i++) {//开始冒泡第i个  
      for (int j = indexes.length-1; j > i; j--) {//没趟从第indexes.length-1个开始比较到第i个  
        int ret = compare(indexes[j], indexes[j-1]);  
        if(0 > ret){  
          int tmp = indexes[j];  
          indexes[j] = indexes[j-1];  
          indexes[j-1] = tmp;  
        }  
      }  
    }  
  }*/   
   
  // This is a home-grown implementation which we have not had time   
  // to research - it may perform poorly in some circumstances. It   
  // requires twice the space of an in-place algorithm and makes   
  // NlogN assigments shuttling the values between the two   
  // arrays. The number of compares appears to vary between N-1 and   
  // NlogN depending on the initial order but the main reason for   
  // using it here is that, unlike qsort, it is stable.   
  private void shuttlesort(int from[], int to[], int low, int high) {   
    if(high - low  2)   
      return;   
    int middle = (low + high) / 2;   
    shuttlesort(to, from, low, middle);   
    shuttlesort(to, from, middle, high);   
    int p = low;   
    int q = middle;   
    /* This is an optional short-cut; at each recursive call,  
        check to see if the elements in this subset are already  
        ordered.  If so, no further comparisons are needed; the  
        sub-array can just be copied.  The array must be copied rather  
        than assigned otherwise sister calls in the recursion might  
        get out of sinc.  When the number of elements is three they  
        are partitioned so that the first set, [low, mid), has one  
        element and and the second, [mid, high), has two. We skip the  
        optimisation when the number of elements is three or less as  
        the first compare in the normal merge will produce the same  
        sequence of steps. This optimisation seems to be worthwhile  
        for partially ordered lists but some analysis is needed to  
        find out how the performance drops to Nlog(N) as the initial  
        order diminishes - it may drop very quickly.  */   
    if(high - low >= 4 && compare(from[middle - 1], from[middle]) = 0) {   
      for(int i = low; i  high; i++) {   
        to[i] = from[i];   
      }   
      return;   
    }   
    // A normal merge.   
    for(int i = low; i  high; i++) {   
      if(q >= high || (p  middle && compare(from[p], from[q]) = 0)) {   
        to[i] = from[p++];   
      } else {   
        to[i] = from[q++];   
      }   
    }   
  }   
   
  private int compare(int row1, int row2) {   
    compares++;   
    for(int level = 0; level  sortingColumns.size(); level++) {   
      Integer column = (Integer)sortingColumns.elementAt(level);   
      int result = compareRowsByColumn(row1, row2, column.intValue());   
      if(result != 0) {   
        return ascending ? result : -result;   
      }   
    }   
    return 0;   
  }   
   
  private int compareRowsByColumn(int row1, int row2, int column) {   
    Class type = this.getColumnClass(column);   
    TableModel data = this;   
    // Check for nulls.   
    Object o1 = data.getValueAt(row1, column);   
    Object o2 = data.getValueAt(row2, column);   
    // If both values are null, return 0.   
    if (o1 == null && o2 == null) return 0;   
    // Define null less than everything.   
    else if(o1 == null)   
      return -1;   
    else if(o2 == null)   
      return 1;   
    /*  
     * We copy all returned values from the getValue call in case an  
     * optimised model is reusing one object to return many values.  
     * The Number subclasses in the JDK are immutable and so will not  
     * be used in this way but other subclasses of Number might want  
     * to do this to save space and avoid unnecessary heap allocation.  
     */   
    if(type.getSuperclass() == java.lang.Number.class) {   
      Number n1 = (Number)data.getValueAt(row1, column);   
      double d1 = n1.doubleValue();   
      Number n2 = (Number)data.getValueAt(row2, column);   
      double d2 = n2.doubleValue();   
      if(d1  d2)   
        return -1;   
      else if(d1 > d2)   
        return 1;   
      else   
        return 0;   
    }   
    else if(type == java.util.Date.class) {   
      Date d1 = (Date)data.getValueAt(row1, column);   
      long n1 = d1.getTime();   
      Date d2 = (Date)data.getValueAt(row2, column);   
      long n2 = d2.getTime();   
      if(n1  n2)   
        return -1;   
      else if(n1 > n2)   
        return 1;   
      else   
        return 0;   
    }   
    else if(type == String.class) {   
      String s1 = (String)data.getValueAt(row1, column);   
      String s2 = (String)data.getValueAt(row2, column);   
      int result = s1.compareTo(s2);   
      if(result  0)   
        return -1;   
      else if(result > 0)   
        return 1;   
      else   
        return 0;   
    }   
    else if(type == Boolean.class) {   
      Boolean bool1 = (Boolean)data.getValueAt(row1, column);   
      boolean b1 = bool1.booleanValue();   
      Boolean bool2 = (Boolean)data.getValueAt(row2, column);   
      boolean b2 = bool2.booleanValue();   
      if(b1 == b2)   
        return 0;   
      else if(b1)   
        return 1; // Define false  true   
      else   
        return -1;   
    }   
    else {   
      Object v1 = data.getValueAt(row1, column);   
      String s1 = v1.toString();   
      Object v2 = data.getValueAt(row2, column);   
      String s2 = v2.toString();   
      int result = s1.compareTo(s2);   
      if(result  0)   
        return -1;   
      else if(result > 0)   
        return 1;   
      else   
        return 0;   
    }   
  }   
}  


