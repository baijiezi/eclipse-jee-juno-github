package utils.JUtils.chenbug.chineseren.swing;
  
/** 
 * <p>Title: 组件风格类</p> 
 * <p>Description: 封装了组件的所有风格，包括字体，背景色，前景色，大小,位置等等</p> 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: pubinfo</p> 
 * @author chineseren 
 * @version 1.0 
 */  
  
public class ComponentStyle {  
  //维持一个默认的组件风格  
  public static final ComponentStyle DEFAULT_STYLE = new ComponentStyle();  
  //风格属性  
  private java.awt.Font font;  
  private java.awt.Color foreground;  
  private int sizeX; //按钮大小  
  private int sizeY;  
  private int x; //窗体位置  
  private int y;  
  private int width; //窗体大小  
  private int height;  
  private javax.swing.ImageIcon icon;//窗体icon  
  public ComponentStyle() {  
    this.font = new java.awt.Font("Dialog", 0, 14);  
    this.foreground = java.awt.Color.black;  
    this.sizeX = 30;  
    this.sizeY = 20;  
    this.x = 250;  
    this.y = 250;  
    this.icon = new javax.swing.ImageIcon("images/titleIcon.gif");  
  }  
  
  public java.awt.Font getFont() {  
    return font;  
  }  
  
  public java.awt.Color getForeground() {  
    return foreground;  
  }  
  
  public int getSizeX() {  
    return sizeX;  
  }  
  
  public int getSizeY() {  
    return sizeY;  
  }  
  
  public int getX() {  
    return x;  
  }  
  
  public int getY() {  
    return y;  
  }  
  
  public int getWidth() {  
    return width;  
  }  
  
  public int getHeight() {  
    return height;  
  }  
  
  public javax.swing.ImageIcon getIcon() {  
    return icon;  
  }  
  
  public java.awt.Image getImage() {  
    return icon.getImage();  
  }  
  
  public void setIcon(javax.swing.ImageIcon icon){  
    this.icon = icon;  
  }  
  
  public void setIcon(String iconFile){  
    this.icon = new javax.swing.ImageIcon(iconFile);  
  }  
}  



