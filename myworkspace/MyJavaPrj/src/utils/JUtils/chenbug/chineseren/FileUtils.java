package utils.JUtils.chenbug.chineseren;
   
/**  
 * <p>Title: FileUtils</p>  
 * <p>Description: FileUtils</p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: pubinfo</p>  
 * @author chineseren  
 * @version 1.0  
 */   
import java.io.*;   
import java.util.*;   
   
public class FileUtils {   
    //��ȡ��classpath��Ŀ¼��ʼ��ȡ�ļ�ע��ת��������   
    public static String getCPFile(String path) {   
        URL url = FileUtils.class.getClassLoader().getResource(path);   
        String filepath = url.getFile();   
        File file = new File(filepath);   
        byte[] retBuffer = new byte[(int)file.length()];   
        try {   
          FileInputStream fis = new FileInputStream(filepath);   
          fis.read(retBuffer);   
          fis.close();   
          return new String(retBuffer,"GBK");   
        } catch(IOException e) {   
          Debug.error("FileUtils.getCPFile��ȡ�ļ��쳣��" + e.toString());   
          return null;   
        }   
    }   
   
  /**  
   * ��ȡϵͳ��class�ļ���������Ը�·��,����: E:\eshop\eshop\classes  
   */   
  public static String CLASS_PATH;   
  static {   
    try {   
      URL url = FileUtils.class.getResource("/");   
      CLASS_PATH = URLDecoder.decode(url.toString(), "GBK");   
      if (CLASS_PATH.toUpperCase().endsWith("CLASSES/") || CLASS_PATH.toUpperCase().endsWith("CLASSES\\")) {   
        int i = CLASS_PATH.toUpperCase().lastIndexOf("CLASSES\\");   
        if (i == -1) {   
          i = CLASS_PATH.toUpperCase().lastIndexOf("CLASSES/");   
        }   
        CLASS_PATH = CLASS_PATH.substring(0, i);   
      }   
      if (CLASS_PATH.toUpperCase().startsWith("FILE:")) {   
        CLASS_PATH = CLASS_PATH.substring(CLASS_PATH.toUpperCase().indexOf("FILE:")+6, CLASS_PATH.length());   
      }   
    }   
    catch (Exception ex) {   
      throw new RuntimeException("��ȡϵͳ��class�ļ���������Ը�·���쳣��"+ex.toString());   
    }   
  }   
   
  /**  
   * ����java���ؿ����ļ����ļ���,���ʵ���ļ��ж��ļ��еĿ�����?����ļ����ﻹ���ļ�����ô����?  
   * @param objDir Ŀ���ļ���  
   * @param srcDir Դ���ļ���  
   * @throws IOException  
   */   
  public static void copyDirectiory(String objDir,   
      String srcDir) throws IOException {   
    (new File(objDir)).mkdirs();   
    File[] file = (new File(srcDir)).listFiles();   
    for(int i = 0; i  file.length; i++) {   
      if(file[i].isFile()) {   
        FileInputStream input = new FileInputStream(file[i]);   
        FileOutputStream output = new FileOutputStream(objDir + "/" +   
            file[i].getName());   
        byte[] b = new byte[1024 * 5];   
        int len;   
        while((len = input.read(b)) != -1) {   
          output.write(b, 0, len);   
        }   
        output.flush();   
        output.close();   
        input.close();   
      }   
      if(file[i].isDirectory()) {   
        copyDirectiory(objDir + "/" + file[i].getName(),   
            srcDir + "/" + file[i].getName());   
      }   
    }   
  }   
   
  /**  
   * ��һ���ļ�inName����������һ���ļ�outName��  
   * @param inName Դ�ļ�·��  
   * @param outName Ŀ���ļ�·��  
   * @throws FileNotFoundException  
   * @throws IOException  
   */   
  public static void copyFile(String inName,   
      String outName) throws FileNotFoundException, IOException {   
    BufferedInputStream is =   
        new BufferedInputStream(new FileInputStream(inName));   
    BufferedOutputStream os =   
        new BufferedOutputStream(new FileOutputStream(outName));   
    copyFile(is, os, true);   
  }   
   
  /**  
   * Copy a file from an opened InputStream to opened OutputStream  
   * @param is source InputStream  
   * @param os target OutputStream  
   * @param close д��֮���Ƿ���Ҫ�ر�OutputStream  
   * @throws IOException  
   */   
  public static void copyFile(InputStream is, OutputStream os,   
      boolean close) throws IOException {   
    int b;   
    while((b = is.read()) != -1) {   
      os.write(b);   
    }   
    is.close();   
    if(close)   
      os.close();   
  }   
   
  public static void copyFile(Reader is, Writer os,   
      boolean close) throws IOException {   
    int b;   
    while((b = is.read()) != -1) {   
      os.write(b);   
    }   
    is.close();   
    if(close)   
      os.close();   
  }   
   
  public static void copyFile(String inName, PrintWriter pw,   
      boolean close) throws FileNotFoundException, IOException {   
    BufferedReader is = new BufferedReader(new FileReader(inName));   
    copyFile(is, pw, close);   
  }   
   
  /**  
   * ���ļ�inName�ж�ȡ��һ�е�����  
   * @param inName Դ�ļ�·��  
   * @return ��һ�е�����  
   * @throws FileNotFoundException  
   * @throws IOException  
   */   
  public static String readLine(String inName) throws FileNotFoundException,   
      IOException {   
    BufferedReader is = new BufferedReader(new FileReader(inName));   
    String line = null;   
    line = is.readLine();   
    is.close();   
    return line;   
  }   
   
  /**  
   * default buffer size  
   */   
  private static final int BLKSIZ = 8192;   
   
  public static void copyFileBuffered(String inName, String outName) throws   
      FileNotFoundException, IOException {   
    InputStream is = new FileInputStream(inName);   
    OutputStream os = new FileOutputStream(outName);   
    int count = 0;   
    byte b[] = new byte[BLKSIZ];   
    while((count = is.read(b)) != -1) {   
      os.write(b, 0, count);   
    }   
    is.close();   
    os.close();   
  }   
   
  /**  
   * ��String����ı��ļ�  
   * @param text ԴString  
   * @param fileName Ŀ���ļ�·��  
   * @throws IOException  
   */   
  public static void stringToFile(String text,   
      String fileName) throws IOException {   
    BufferedWriter os = new BufferedWriter(new FileWriter(fileName));   
    os.write(text);   
    os.flush();   
    os.close();   
  }   
   
  /**  
   * ���ļ����BufferedReader  
   * @param fileName Ŀ���ļ�·��  
   * @return BufferedReader  
   * @throws IOException  
   */   
  public static BufferedReader openFile(String fileName) throws IOException {   
    return new BufferedReader(new FileReader(fileName));   
  }   
   
  /**  
   * ��ȡ�ļ�filePath���ֽڱ���byte[]  
   * @param filePath �ļ�ȫ·��  
   * @return �ļ����ݵ��ֽڱ���  
   * @roseuid 3FBE26DE027D  
   */   
  public static byte[] fileToBytes(String filePath) {   
    if(filePath == null) {   
      Debug.info("·��Ϊ�գ�");   
      return null;   
    }   
   
    File tmpFile = new File(filePath);   
    if(tmpFile == null) {   
      Debug.info("�޷��ҵ��ļ���" + filePath);   
      return null;   
    }   
   
    byte[] retBuffer = new byte[(int)tmpFile.length()];   
    FileInputStream fis = null;   
    try {   
      fis = new FileInputStream(filePath);   
      fis.read(retBuffer);   
      fis.close();   
      return retBuffer;   
    } catch(IOException e) {   
      Debug.error("��ȡ�ļ��쳣��" + e.toString());   
      return null;   
    }   
  }   
   
  /**  
   * ��byte[]ת�����ļ�fullFilePath  
   * @param fullFilePath �ļ�ȫ·��  
   * @param content Դbyte[]  
   */   
  public static void bytesToFile(String fullFilePath, byte[] content) {   
    if(fullFilePath == null || content == null) {   
      return;   
    }   
   
    //������Ӧ��Ŀ¼   
    File f = new File(getDir(fullFilePath));   
    if(f == null || !f.exists()) {   
      f.mkdirs();   
    }   
   
    try {   
      FileOutputStream fos = new FileOutputStream(fullFilePath);   
      fos.write(content);   
      fos.close();   
    } catch(Exception e) {   
      Debug.error("д���ļ��쳣:" + e.toString());   
    }   
  }   
   
  /**  
   * ���ݴ�����ļ�ȫ·���������ļ�����·��  
   * @param fullPath �ļ�ȫ·��  
   * @return �ļ�����·��  
   */   
  public static String getDir(String fullPath) {   
    int iPos1 = fullPath.lastIndexOf("/");   
    int iPos2 = fullPath.lastIndexOf("\\");   
    iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);   
    return fullPath.substring(0, iPos1 + 1);   
  }   
   
  /**  
   * ���ݴ�����ļ�ȫ·���������ļ�ȫ����������׺����  
   * @param fullPath �ļ�ȫ·��  
   * @return �ļ�ȫ����������׺����  
   */   
  public static String getFileName(String fullPath) {   
    int iPos1 = fullPath.lastIndexOf("/");   
    int iPos2 = fullPath.lastIndexOf("\\");   
    iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);   
    return fullPath.substring(iPos1 + 1);   
  }   
   
  /**  
   * ����ļ���fileName�еĺ�׺��  
   * @param fileName Դ�ļ���  
   * @return String ��׺��  
   */   
  public static String getFileSuffix(String fileName) {   
    return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());   
  }   
   
  /**  
   * ���ݴ�����ļ�ȫ����������׺���������ļ�ȫ·�������ļ�����û�к�׺����  
   * @param fullPath �ļ�ȫ����������׺���������ļ�ȫ·��  
   * @return �ļ�����û�к�׺����  
   */   
  public static String getPureFileName(String fullPath) {   
    String fileFullName = getFileName(fullPath);   
    return fileFullName.substring(0, fileFullName.lastIndexOf("."));   
  }   
   
  /**  
   * ת���ļ�·���е�\\Ϊ/  
   * @param filePath Ҫת�����ļ�·��  
   * @return String  
   */   
  public static String wrapFilePath(String filePath) {   
    filePath.replace('\\', '/');   
    if (filePath.charAt(filePath.length() - 1) != '/') {   
      filePath += "/";   
    }   
    return filePath;   
  }   
   
  /**  
   * ɾ������Ŀ¼path,������Ŀ¼�����е���Ŀ¼���ļ�  
   * @param path  
   */   
  public static void deleteDirs(String path) {   
    File rootFile = new File(path);   
    if(rootFile == null) {   
      return;   
    }   
    File[] files = rootFile.listFiles();   
    if(files == null) {   
      return;   
    }   
    for(int i = 0; i<files.length; i++) {   
      File file = files[i];   
      if(file.isDirectory()) {   
        deleteDirs(file.getPath());   
      } else {   
        file.delete();   
      }   
    }   
    rootFile.delete();   
  }   
}   
   
//��λ�ȡ��һ��Jar���ڵ�����������   
JarFile jfile = new JarFile("E:/some.jar");    
Enumeration files = jfile.entries();    
while(files.hasMoreElements())    
{    
JarEntry entry = (JarEntry)files.nextElement();    
if(entry.getName().startsWith("some/some/some"))    
System.out.println(entry.getName());    
}    
   
//��λ�ȡ��һ��Package���ڵ�����������   
public static Class[] getClassFromPackage(String entirePackagePath) {    
    Class thisClazz = ClassUtils.class;    
    String thisName = thisClazz.getName();    
    String thisFileName = thisName.substring(thisName.lastIndexOf('.') +    
                                             1) + ".class";    
    URL url = thisClazz.getResource(thisFileName);    
    String strUrl = url.toString();    
   
   
    boolean isInJar = false;    
   
    //get classes dictionary or get jar file who contains the classes    
    if(strUrl.substring(0 , 4).equals("jar:")) {    
        strUrl = strUrl.substring(10);    
        int index = strUrl.indexOf(".jar");    
        strUrl = strUrl.substring(0 , index + 4);    
        isInJar = true;    
    }    
    else {    
        //use in the develop process,    
        //in the run time the class must in the jar    
        strUrl = strUrl.substring(6);    
        int index = strUrl.lastIndexOf("classes");    
        strUrl = strUrl.substring(0 , index + 7);    
    }    
   
    List classes = new ArrayList();    
   
    sun.tools.java.ClassPath classPath = new ClassPath(strUrl);    
    Identifier iden = Identifier.lookup(entirePackagePath);    
    Class stateClass;    
    try {    
        sun.tools.java.Package pkg = new sun.tools.java.Package(classPath ,    
                iden);    
        java.util.Enumeration e = pkg.getBinaryFiles();    
        String classFileName;    
        int classIndex;    
        String className;    
        for(; e.hasMoreElements(); ) {    
            classFileName = ((ClassFile)e.nextElement()).getName();    
   
            if(isInJar) {    
                String tmp = classFileName.substring(entirePackagePath.    
                        length() + 1);    
                //��jar�������ǡ���ݹ�ȡ�ð��µ�Class    
                //��������ʱ�򲻻�    
                if(tmp.indexOf('/') != -1) {    
                    continue;    
                }    
            }    
   
            classIndex = classFileName.lastIndexOf('.');    
            classFileName = classFileName.substring(0 , classIndex);    
   
            //��jar������ʱ��ȡ�õ�classFileNameΪXXX/XXX/...../ActivityState    
            //�ڿ�������ʱ �� ActivityState    
            if(isInJar) {    
                className = classFileName.replace('/' , '.');    
            }    
            else {    
                className = entirePackagePath + "." + classFileName;    
            }    
   
            stateClass = Class.forName(className);    
            classes.add(stateClass);    
        }    
    }    
    catch(IOException ex) {    
        System.out.println("δ�ҵ���?��?");    
        ex.printStackTrace();    
    }    
    catch(ClassNotFoundException ex) {    
        System.out.println("δ�ҵ���Ҫ���ص���");    
        ex.printStackTrace();    
    }    
   
    Class[] result = new Class[classes.size()];    
    ...........    
   
    return result;    
}    



