package utils.JUtils.chenbug.chineseren.base;
   
/**  
 * Title:ID������(32λ�����ֺ���ĸ��ϵ�����ַ���)  
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



