package utils.JUtils.chenbug.chineseren.mytable;
  
import javax.swing.DefaultListSelectionModel;  
import javax.swing.event.ListSelectionListener;  
import javax.swing.event.ListSelectionEvent;  
import java.util.*;  
  
/** 
 * <p>Title: MyTableSelectionModel</p> 
 * <p>Description: ʵ���Լ���SelectionModel</p> 
 * �̳�ԭ��DefaultListSelectionModel�Ĺ��� 
 * ʵ����TableModelListener�ӿ�,�Լ��������Լ� 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: pubinfo</p> 
 * @author chineseren 
 * @version 1.0 
 */  
  
public class MyTableSelectionModel  
    extends DefaultListSelectionModel  
    implements ListSelectionListener {  
  
  public MyTableSelectionModel() {  
    //Ĭ����single��ģʽ  
    super();  
    this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);  
    this.addListSelectionListener(this);  
  }  
  
  /** 
   * ��SelectionModelΪSingleʱ���� 
   * @return ����Ψһѡ����к�,Ĭ�Ϸ���-1 
   */  
  public int getSelectedIndex(){  
    return super.getMinSelectionIndex();  
  }  
  
  /** 
   * ��SelectionModelΪSingleʱ���� 
   * @param index Ҫѡ�е���� 
   * ѡ��index 
   */  
  public void setSelectedIndex(int index){  
    super.setSelectionInterval(index,index);  
  }  
  
  public void valueChanged(ListSelectionEvent e){  
    /**@todo: something*/  
  }  
}  


