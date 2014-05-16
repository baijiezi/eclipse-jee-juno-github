package utils.JUtils.chenbug.chineseren.swing;
  
/** 
 * <p>Title: ��������</p> 
 * <p>Description: ��װ����������з�񣬰������壬����ɫ��ǰ��ɫ����С,λ�õȵ�</p> 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: pubinfo</p> 
 * @author chineseren 
 * @version 1.0 
 */  
  
public class ComponentStyle {  
  //ά��һ��Ĭ�ϵ�������  
  public static final ComponentStyle DEFAULT_STYLE = new ComponentStyle();  
  //�������  
  private java.awt.Font font;  
  private java.awt.Color foreground;  
  private int sizeX; //��ť��С  
  private int sizeY;  
  private int x; //����λ��  
  private int y;  
  private int width; //�����С  
  private int height;  
  private javax.swing.ImageIcon icon;//����icon  
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



