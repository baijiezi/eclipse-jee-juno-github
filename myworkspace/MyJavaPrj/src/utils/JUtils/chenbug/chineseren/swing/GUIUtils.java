package utils.JUtils.chenbug.chineseren.swing;
   
/**  
 * <p>Title: GUIUtils</p>  
 * <p>Description: �й�GUI��һЩ�򵥹��߷���</p>  
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
  static{//����ϵͳ�Ľ�����,��Ϊmeϲ�������ʽ   
    try {   
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());   
    } catch(Exception ex) {   
      Debug.debug("GUIUtils cant set UI Look&&Feel!", ex);   
    }   
  }   
  //ΪJMenuItem or JMenu��ӿ�ݼ�(Ҫ��ALT��)   
  /**  
   * <p>Description: ΪJMenuItem or JMenu��ӿ�ݼ�(Ҫ��ALT��)</p>  
   * @param KeyEvent_code ��ݼ�  
   * @param description ����  
   * @param jMenuItem Ŀ��JMenuItem or JMenu  
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
   * <p>Description: ��JPanel from���뵽JPanel to��</p>  
   * @param from ԴJPanel  
   * @param to Ŀ��JPanel  
   * @author chineseren  
   * @version 1.0  
   */   
  public static void setJPanel(JPanel to, JPanel from) {   
    to.removeAll();   
    to.add(from);   
    to.updateUI();   
  }   
   
  /**  
   * �������������  
   * @param component Ŀ�����  
   * @param font Ҫ�������  
   * @param isAll �Ƿ���Ҫ��������µ����������������Ҳ���ó�Ĭ������,Ĭ����false  
   */   
  public static void setFont(java.awt.Component component,java.awt.Font font,boolean isAll) {   
    component.setFont(font);   
    component.setForeground(ComponentStyle.DEFAULT_STYLE.getForeground());   
    if(!isAll){   
      return;   
    } else { //ֻ��java.awt.Container����getComponents()   
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
   * �������������  
   * @param component Component  
   * @param isAll �Ƿ���Ҫ��������µ����������������Ҳ���ó�Ĭ������,Ĭ����false  
   */   
  public static void setFont(java.awt.Component component,boolean isAll) {   
    setFont(component,ComponentStyle.DEFAULT_STYLE.getFont(),false);   
  }   
  public static void setFont(java.awt.Component component) {   
    setFont(component,false);   
  }   
   
  /**  
   * ��������ñ���ɫ  
   * @param component Ŀ�����  
   * @param color Ҫ��ı�����ɫ  
   */   
  public static void setBgColor(java.awt.Component component,   
      java.awt.Color color) {   
    component.setBackground(color);   
  }   
   
  /**  
   * ���������ǰ��ɫ  
   * @param component Ŀ�����  
   * @param color Ҫ���ǰ����ɫ  
   */   
  public static void setFgColor(java.awt.Component component,   
      java.awt.Color color) {   
    component.setForeground(color);   
  }   
   
  /**  
   * ��������Ĵ�С  
   * @param component Ŀ�����  
   * @param width Ҫ���������  
   * @param height Ҫ�������߶�  
   */   
  public static void setComponentSize(java.awt.Component component, int width,   
      int height) {   
    component.setSize(width, height);   
  }   
   
  public static Image getImage(String imageFile){   
    return Toolkit.getDefaultToolkit().createImage(imageFile);   
  }   
   
  /**  
   * ��Ŀ�괰������ͼ�꣨Ĭ�ϣ�  
   * @param frame Ŀ�괰��  
   */   
  public static void setIcon(JFrame frame) {   
    frame.setIconImage(ComponentStyle.DEFAULT_STYLE.getImage());   
  }   
   
  /**  
   * ��Ŀ�괰������ͼ�꣨����ͼ��·����  
   * @param frame Ŀ�괰��  
   * @param imagePath ͼ��·��  
   */   
  public static void setIcon(JFrame frame, String imagePath) {   
    frame.setIconImage(Toolkit.getDefaultToolkit().createImage(imagePath));   
  }   
   
  /**  
   * ��Ŀ�괰������λ��  
   * @param component Ŀ�����  
   * @param x x����  
   * @param y y����  
   */   
  public static void setLocation(Component component, int x, int y) {   
    component.setLocation(x, y);   
  }   
   
  //����Frame || Dialog������ʾ   
  public static void setCenter(Component component) {   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
    component.setBounds((screenSize.width - component.getWidth()) / 2,   
        (screenSize.height - component.getHeight()) / 2,   
        component.getWidth(), component.getHeight());   
  }   
   
  /**  
   * ����ϵͳ�����д����������  
   * @param componentStyle ������  
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
   * �������(���壬�Ի������)�ķ��  
   * @param frame Ŀ�괰�����(Frame || Dialog)  
   * @param componentStyle Ҫ���������  
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


