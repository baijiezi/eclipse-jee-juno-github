package utils.JUtils.chenbug.chineseren.base;
   
/**  
 * Title:ID生成器(32位的数字和字母组合的随机字符串)  
 * Description:  
 * Copyright: chineseren Copyright (c) 2002  
 * Company:  
 * @author chineseren  
 * @version 1.0  
 */   
import java.sql.*;   
   
public class IdGenor {   
  public static String getNextID(){   
    return org.apache.commons.lang.RandomStringUtils.randomAlphanumeric(32);   
  }   
}  



