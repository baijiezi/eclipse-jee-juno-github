package utils.JUtils.chenbug;

import java.io.*;   
import java.util.*;   
   
import org.apache.commons.lang.*;   
import org.apache.oro.text.regex.*;   
import com.langtong.exception.*;   
import com.langtong.utils.*;   
   
/**  
 *  
 * <p>Title: FilenameFilterImpl</p>  
 * <p>Description: �ļ���������</p>  
 * <p>Copyright: Copyright (c) 2005</p>  
 * <p>Company: </p>  
 * @author  
 * @version 1.0  
 */   
public class FilenameFilterImpl   
    implements FilenameFilter {   
  /**  
   * ���˹���  
   */   
  private String regexp;   
   
  /**  
   * ӳ��writeͬ����  
   */   
  private static Object lock = new Object();   
   
  /**  
   * ӳ��  
   */   
  private static Map filters = new HashMap();   
   
  /**  
   * ������ʽƥ����  
   */   
  private static PatternMatcher patternMatcher = new Perl5Matcher();   
   
  /**  
   * ������ʽ������  
   */   
  private static PatternCompiler compiler = new Perl5Compiler();   
   
  /**  
   * ƥ��ģʽ  
   */   
  Pattern[] patterns = null;   
   
  /**  
   * ���˹������ӷ�  
   */   
  public static final String REG_SEPARATOR = "|";   
   
  /**  
   * ˽�й��캯������ֹ�û���ʽ����  
   * @param regexp ��String ���˹�����*.xml, abc*abc*.xml��ʽ  
   */   
  private FilenameFilterImpl(String regexp) {   
   
    this.regexp = regexp;   
   
    /**  
     * �����˹���򵥷����������ʽ  
     */   
    String[] realRegexp = translateRegExp(regexp);   
   
    /**  
     * ��ʼ��ƥ��ģʽ  
     */   
    patterns = new Pattern[realRegexp.length];   
    for (int i = 0; i  realRegexp.length; i++) {   
      try {   
        patterns[i] = compiler.compile(realRegexp[i],   
                                       Perl5Compiler.CASE_INSENSITIVE_MASK);   
      }   
      catch (Exception e) {   
        throw new BaseRuntimeException("������ʽ��֤�쳣��" + e.toString());   
      }   
    }   
  }   
   
  /**  
   * ��ȡ�����������ӳ���������Ѿ����ڣ���ֱ�ӷ��أ������ȹ���  
   * @param regexp String �ļ�����ʽ��String *.xml, abc*abc*.xml��ʽ  
   * @return FilenameFilter ���˹�����*.xml, abc*abc*.xml��ʽ  
   */   
  public static FilenameFilter getFilter(String regexp) {   
   
    /**  
     * ���ӳ�����Ѿ����ڹ����������ȡ  
     */   
    if (filters.containsKey(regexp)) {   
      Tools.log.info("�ҵ���������������ʽ��" + regexp + "��ֱ�ӷ���");   
      return (FilenameFilter) filters.get(regexp);   
    }   
   
    /**  
     * ��������ڣ���ͬ������ӳ��  
     */   
    synchronized (lock) {   
      /**  
       * ˫�ؼ�⣬��������ڣ��򴴽�  
       */   
      if (!filters.containsKey(regexp)) {   
        Tools.log.info("�޷��ҵ���������������ʽ��" + regexp + "�������¹�����");   
        FilenameFilterImpl impl = new FilenameFilterImpl(regexp);   
        filters.put(regexp, impl);   
        return impl;   
      }   
      else {   
        return (FilenameFilter) filters.get(regexp);   
      }   
    }   
  }   
   
  /**  
   * �����˹���򵥷����������ʽ  
   * @param regexp String ƥ�����  
   * @return String ������ʽ  
   */   
  private static String[] translateRegExp(String regexp) {   
   
    String[] regexpAry = StringUtils.split(regexp, REG_SEPARATOR);   
   
    for (int i = 0; i  regexpAry.length; i++) {   
      regexpAry[i] = StringUtils.replace(regexpAry[i], ".", "\\.");   
      regexpAry[i] = StringUtils.replace(regexpAry[i], "*", ".*");   
    }   
    return regexpAry;   
  }   
   
  /**  
   * FilenameFilter�ӿ�ʵ�ֺ�����ȷ����Щ�ļ������Ϲ��˹���  
   * @param dir File �ļ�����·��  
   * @param filename String �ļ���  
   * @return boolean  
   */   
  public boolean accept(File dir, String filename) {   
    /**@todo Implement this java.io.FileFilter method*/   
    for (int i = 0; i  patterns.length; i++) {   
      if (patternMatcher.matches(filename, patterns[i])) {   
        return true;   
      }   
    }   
    return false;   
  }   
   
  public String getRegexp() {   
    return regexp;   
  }   
}   


