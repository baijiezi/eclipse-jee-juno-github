package utils.JUtils.chenbug.chineseren.mytable;
  
import javax.swing.DefaultListSelectionModel;  
import javax.swing.event.ListSelectionListener;  
import javax.swing.event.ListSelectionEvent;  
import java.util.*;  
  
/** 
 * <p>Title: MyTableSelectionModel</p> 
 * <p>Description: 实现自己的SelectionModel</p> 
 * 继承原有DefaultListSelectionModel的功能 
 * 实现了TableModelListener接口,自己监听了自己 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: pubinfo</p> 
 * @author chineseren 
 * @version 1.0 
 */  
  
public class MyTableSelectionModel  
    extends DefaultListSelectionModel  
    implements ListSelectionListener {  
  
  public MyTableSelectionModel() {  
    //默认是single的模式  
    super();  
    this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);  
    this.addListSelectionListener(this);  
  }  
  
  /** 
   * 当SelectionModel为Single时有用 
   * @return 返回唯一选择的行号,默认返回-1 
   */  
  public int getSelectedIndex(){  
    return super.getMinSelectionIndex();  
  }  
  
  /** 
   * 当SelectionModel为Single时有用 
   * @param index 要选中的序号 
   * 选中index 
   */  
  public void setSelectedIndex(int index){  
    super.setSelectionInterval(index,index);  
  }  
  
  public void valueChanged(ListSelectionEvent e){  
    /**@todo: something*/  
  }  
}  


