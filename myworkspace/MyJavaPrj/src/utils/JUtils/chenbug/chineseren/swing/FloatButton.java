package utils.JUtils.chenbug.chineseren.swing;
  
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.event.*;  
import javax.swing.border.*;   
import com.reason.util.*;  
  
/** 
 * <p>Title: FloatButton</p> 
 * <p>Description: 浮动按钮,可以作为ToolBar的按钮,按钮之间用JSeperator隔开</p> 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: pubinfo</p> 
 * @author chineseren 
 * @version 1.0 
 */  
  
public class FloatButton  
    extends JButton {  
  private Border borderRaised = BorderFactory.createBevelBorder(BevelBorder.  
      RAISED, Color.white, Color.white, new Color(103, 101, 98), new Color(148,  
      145, 140));  
  ;  
  private Border borderLowed = BorderFactory.createBevelBorder(BevelBorder.  
      LOWERED, Color.white, Color.white, new Color(103, 101, 98), new Color(148,  
      145, 140));  
  ;  
  private Dimension size = new Dimension(25, 25);  
  private static boolean hadPressed; //是否有按钮已经按下？  
  /*public static boolean getHadPressed(){ 
    return FloatButton.hadPressed; 
  } 
  public static void setHadPressed(boolean hadPressed){ 
    FloatButton.hadPressed = hadPressed; 
  }*/  
  
  public FloatButton() {  
    super();  
    try {  
      jbInit();  
    }  
    catch(Exception e) {  
      Debug.error("new FloatButton() ERROR!",e);  
    }  
  }  
  
  public FloatButton(ImageIcon imageIcon) {  
    super(imageIcon);  
    try {  
      jbInit();  
    }  
    catch(Exception e) {  
      Debug.error("new FloatButton(ImageIcon imageIcon) ERROR!",e);  
    }  
  }  
  
  private void jbInit() throws Exception {  
    this.setFont(new java.awt.Font("Dialog", 0, 14));  
    this.setMaximumSize(size);  
    this.setMinimumSize(size);  
    this.setPreferredSize(size);  
    this.setBorderPainted(true);  
    this.setBorder(null);  
    this.setRolloverEnabled(true);  
    this.addActionListener(new java.awt.event.ActionListener() {  
      public void actionPerformed(ActionEvent e) {  
        onAction(e);  
      }  
    });  
    this.addMouseListener(new java.awt.event.MouseAdapter() {  
      public void mouseEntered(MouseEvent e) {  
        this_mouseEntered(e);  
      }  
  
      public void mouseExited(MouseEvent e) {  
        this_mouseExited(e);  
      }  
  
      public void mousePressed(MouseEvent e) {  
        this_mousePressed(e);  
      }  
  
      public void mouseReleased(MouseEvent e) {  
        this_mouseReleased(e);  
      }  
    });  
  }  
  
  void this_mouseEntered(MouseEvent e) {  
    if(this.hadPressed) {  
      this.setBorder(this.borderLowed);  
      return;  
    }  
  
    this.setBorder(this.borderRaised);  
  }  
  
  void this_mouseExited(MouseEvent e) {  
    this.setBorder(null);  
  }  
  
  void this_mousePressed(MouseEvent e) {  
    this.setBorder(this.borderLowed);  
    this.hadPressed = true;  
  }  
  
  void this_mouseReleased(MouseEvent e) {  
    this.setBorder(null);  
    this.hadPressed = false;  
  }  
  
  public void onAction(ActionEvent e) {  
    /**@todo: 按钮按下之后执行动作*/  
  }  
}  


