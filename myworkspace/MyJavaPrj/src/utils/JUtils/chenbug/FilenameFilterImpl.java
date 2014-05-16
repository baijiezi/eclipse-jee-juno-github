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
 * <p>Description: 文件名过滤器</p>  
 * <p>Copyright: Copyright (c) 2005</p>  
 * <p>Company: </p>  
 * @author  
 * @version 1.0  
 */   
public class FilenameFilterImpl   
    implements FilenameFilter {   
  /**  
   * 过滤规则  
   */   
  private String regexp;   
   
  /**  
   * 映射write同步锁  
   */   
  private static Object lock = new Object();   
   
  /**  
   * 映射  
   */   
  private static Map filters = new HashMap();   
   
  /**  
   * 正则表达式匹配器  
   */   
  private static PatternMatcher patternMatcher = new Perl5Matcher();   
   
  /**  
   * 正则表达式编译器  
   */   
  private static PatternCompiler compiler = new Perl5Compiler();   
   
  /**  
   * 匹配模式  
   */   
  Pattern[] patterns = null;   
   
  /**  
   * 过滤规则连接符  
   */   
  public static final String REG_SEPARATOR = "|";   
   
  /**  
   * 私有构造函数，防止用户显式创建  
   * @param regexp ：String 过滤规则，如*.xml, abc*abc*.xml格式  
   */   
  private FilenameFilterImpl(String regexp) {   
   
    this.regexp = regexp;   
   
    /**  
     * 将过滤规则简单翻译成正则表达式  
     */   
    String[] realRegexp = translateRegExp(regexp);   
   
    /**  
     * 初始化匹配模式  
     */   
    patterns = new Pattern[realRegexp.length];   
    for (int i = 0; i  realRegexp.length; i++) {   
      try {   
        patterns[i] = compiler.compile(realRegexp[i],   
                                       Perl5Compiler.CASE_INSENSITIVE_MASK);   
      }   
      catch (Exception e) {   
        throw new BaseRuntimeException("正则表达式验证异常：" + e.toString());   
      }   
    }   
  }   
   
  /**  
   * 获取过滤器，如果映射配置中已经存在，则直接返回，否则先构造  
   * @param regexp String 文件名格式：String *.xml, abc*abc*.xml格式  
   * @return FilenameFilter 过滤规则，如*.xml, abc*abc*.xml格式  
   */   
  public static FilenameFilter getFilter(String regexp) {   
   
    /**  
     * 如果映射中已经存在过滤器，则获取  
     */   
    if (filters.containsKey(regexp)) {   
      Tools.log.info("找到过滤器，正则表达式：" + regexp + "，直接返回");   
      return (FilenameFilter) filters.get(regexp);   
    }   
   
    /**  
     * 如果不存在，则同步访问映射  
     */   
    synchronized (lock) {   
      /**  
       * 双重检测，如果不存在，则创建  
       */   
      if (!filters.containsKey(regexp)) {   
        Tools.log.info("无法找到过滤器，正则表达式：" + regexp + "，创建新过滤器");   
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
   * 将过滤规则简单翻译成正则表达式  
   * @param regexp String 匹配规则  
   * @return String 正则表达式  
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
   * FilenameFilter接口实现函数，确认哪些文件名符合过滤规则  
   * @param dir File 文件所在路径  
   * @param filename String 文件名  
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


