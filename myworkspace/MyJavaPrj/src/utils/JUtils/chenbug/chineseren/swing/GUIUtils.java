package utils.JUtils.chenbug.chineseren.swing;
   
/**  
 * <p>Title: GUIUtils</p>  
 * <p>Description: 有关GUI的一些简单工具方法</p>  
 * <p>Copyright: PubInfo Copyright (c) 2004</p>  
 * <p>Company: www.pubinfo.com.cn</p>  
 * @author chineseren  
 * @version 1.0  
 */   
import java.util.*;   
   
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   
import javax.swing.event.*;   
   
import com.reason.util.*;   
   
public class GUIUtils {   
  static{//设置系统的界面风格,因为me喜欢这个样式   
    try {   
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());   
    } catch(Exception ex) {   
      Debug.debug("GUIUtils cant set UI Look&&Feel!", ex);   
    }   
  }   
  //为JMenuItem or JMenu添加快捷键(要求按ALT键)   
  /**  
   * <p>Description: 为JMenuItem or JMenu添加快捷键(要求按ALT键)</p>  
   * @param KeyEvent_code 快捷键  
   * @param description 描述  
   * @param jMenuItem 目标JMenuItem or JMenu  
   * @author chineseren  
   * @version 1.0  
   */   
  public static void setHotkey(int KeyEvent_code, String description,   
      JMenuItem jMenuItem) {   
    //jMenuItem.setMnemonic(KeyEvent.VK_A);   
    jMenuItem.setMnemonic(KeyEvent_code);   
    jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent_code,   
        ActionEvent.ALT_MASK));   
    jMenuItem.getAccessibleContext().setAccessibleDescription(description);   
  }   
   
  public static void setHotkey(int KeyEvent_code, String description,   
      JMenu jMenu) {   
    //jMenu.setMnemonic(KeyEvent.VK_A);   
    jMenu.setMnemonic(KeyEvent_code);   
    jMenu.getAccessibleContext().setAccessibleDescription(description);   
  }   
   
  /**  
   * <p>Description: 将JPanel from加入到JPanel to中</p>  
   * @param from 源JPanel  
   * @param to 目标JPanel  
   * @author chineseren  
   * @version 1.0  
   */   
  public static void setJPanel(JPanel to, JPanel from) {   
    to.removeAll();   
    to.add(from);   
    to.updateUI();   
  }   
   
  /**  
   * 给组件设置字体  
   * @param component 目标组件  
   * @param font 要求的字体  
   * @param isAll 是否需要将该组件下的所有子组件的字体也设置成默认字体,默认是false  
   */   
  public static void setFont(java.awt.Component component,java.awt.Font font,boolean isAll) {   
    component.setFont(font);   
    component.setForeground(ComponentStyle.DEFAULT_STYLE.getForeground());   
    if(!isAll){   
      return;   
    } else { //只有java.awt.Container才有getComponents()   
      if(component instanceof java.awt.Container) {   
        Component[] childs;   
        Component child;   
        int nCount;   
   
        childs = ((java.awt.Container)component).getComponents();   
        nCount = childs.length;   
        for(int i = 0; i  nCount; i++) {   
          child = childs[i];   
          child.setFont(font);   
          child.setForeground(ComponentStyle.DEFAULT_STYLE.getForeground());   
        }   
      }   
    }   
  }   
  public static void setFont(java.awt.Component component,java.awt.Font font) {   
    setFont(component,font,false);   
  }   
   
  /**  
   * 给组件设置字体  
   * @param component Component  
   * @param isAll 是否需要将该组件下的所有子组件的字体也设置成默认字体,默认是false  
   */   
  public static void setFont(java.awt.Component component,boolean isAll) {   
    setFont(component,ComponentStyle.DEFAULT_STYLE.getFont(),false);   
  }   
  public static void setFont(java.awt.Component component) {   
    setFont(component,false);   
  }   
   
  /**  
   * 给组件设置背景色  
   * @param component 目标组件  
   * @param color 要求的背景颜色  
   */   
  public static void setBgColor(java.awt.Component component,   
      java.awt.Color color) {   
    component.setBackground(color);   
  }   
   
  /**  
   * 给组件设置前景色  
   * @param component 目标组件  
   * @param color 要求的前景颜色  
   */   
  public static void setFgColor(java.awt.Component component,   
      java.awt.Color color) {   
    component.setForeground(color);   
  }   
   
  /**  
   * 设置组件的大小  
   * @param component 目标组件  
   * @param width 要求的组件宽度  
   * @param height 要求的组件高度  
   */   
  public static void setComponentSize(java.awt.Component component, int width,   
      int height) {   
    component.setSize(width, height);   
  }   
   
  public static Image getImage(String imageFile){   
    return Toolkit.getDefaultToolkit().createImage(imageFile);   
  }   
   
  /**  
   * 给目标窗体设置图标（默认）  
   * @param frame 目标窗体  
   */   
  public static void setIcon(JFrame frame) {   
    frame.setIconImage(ComponentStyle.DEFAULT_STYLE.getImage());   
  }   
   
  /**  
   * 给目标窗体设置图标（给定图标路径）  
   * @param frame 目标窗体  
   * @param imagePath 图标路径  
   */   
  public static void setIcon(JFrame frame, String imagePath) {   
    frame.setIconImage(Toolkit.getDefaultToolkit().createImage(imagePath));   
  }   
   
  /**  
   * 给目标窗体设置位置  
   * @param component 目标组件  
   * @param x x坐标  
   * @param y y坐标  
   */   
  public static void setLocation(Component component, int x, int y) {   
    component.setLocation(x, y);   
  }   
   
  //设置Frame || Dialog居中显示   
  public static void setCenter(Component component) {   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
    component.setBounds((screenSize.width - component.getWidth()) / 2,   
        (screenSize.height - component.getHeight()) / 2,   
        component.getWidth(), component.getHeight());   
  }   
   
  /**  
   * 设置系统中所有窗体的组件风格  
   * @param componentStyle 组件风格  
   */   
  public static void setAllComponentStyle(ComponentStyle componentStyle){   
    Frame[] frames = Frame.getFrames();   
    Frame frame;   
    for (int i = 0; i  frames.length; i++) {   
      frame = frames[i];   
      setComponentStyle(frame,componentStyle);   
    }   
  }   
  public static void setAllComponentStyle(){   
    setAllComponentStyle(ComponentStyle.DEFAULT_STYLE);   
  }   
   
  /**  
   * 设置组件(窗体，对话框，面板)的风格  
   * @param frame 目标窗体组件(Frame || Dialog)  
   * @param componentStyle 要求的组件风格  
   */   
  public static void setComponentStyle(java.awt.Frame frame,ComponentStyle componentStyle) {   
    frame.setFont(componentStyle.getFont());   
    frame.setForeground(componentStyle.getForeground());   
    frame.setIconImage(componentStyle.getImage());   
    setCenter(frame);   
  }   
  public static void setComponentStyle(java.awt.Frame frame) {   
    setComponentStyle(frame,ComponentStyle.DEFAULT_STYLE);   
  }   
  public static void setComponentStyle(java.awt.Dialog dialog,   
      ComponentStyle componentStyle) {   
    dialog.setFont(componentStyle.getFont());   
    dialog.setForeground(componentStyle.getForeground());   
    setCenter(dialog);   
  }   
  public static void setComponentStyle(java.awt.Dialog dialog) {   
    setComponentStyle(dialog, ComponentStyle.DEFAULT_STYLE);   
  }   
   
  public static void setAllComponentStyle(JFrame frame) {   
    setComponentStyle(frame,ComponentStyle.DEFAULT_STYLE);   
    Component[] components = frame.getContentPane().getComponents();   
    setAllComponentStyle(components);   
  }   
   
  public static void setAllComponentStyle(JDialog dialog) {   
    setComponentStyle(dialog, ComponentStyle.DEFAULT_STYLE);   
    Component[] components = dialog.getContentPane().getComponents();   
    setAllComponentStyle(components);   
  }   
   
  public static void setAllComponentStyle(JPanel jPanel) {   
    Component[] components = jPanel.getComponents();   
    setAllComponentStyle(components);   
  }   
   
  public static void setAllComponentStyle(Component[] components) {   
    java.awt.Component component;   
    int nCount = components.length;   
    for(int i = 0; i  nCount; i++) {   
      component = components[i];   
      component.setFont(ComponentStyle.DEFAULT_STYLE.getFont());   
      component.setForeground(ComponentStyle.DEFAULT_STYLE.getForeground());   
    }   
  }   
}  


