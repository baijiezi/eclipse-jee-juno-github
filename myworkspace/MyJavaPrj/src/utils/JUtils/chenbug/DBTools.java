package utils.JUtils.chenbug;
   
import java.io.*;   
import java.sql.*;   
   
/**  
 * <p>Title: </p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: </p>  
 * @author chenbug  
 * @version 1.0  
 */   
   
public class DBTools {   
   
  public static byte[] blob2Byte(Blob blob) {   
    ByteArrayOutputStream bos = new ByteArrayOutputStream();   
    InputStream is = null;   
    try {   
      is = blob.getBinaryStream();   
      byte[] bRead = new byte[1024];   
      int iRead;   
      while ( (iRead = is.read(bRead, 0, 1024)) != -1) {   
        bos.write(bRead, 0, iRead);   
      }   
      return bos.toByteArray();   
    }   
    catch (Exception e) {   
      Tools.log.error("转换java.sql.Blob到BYTE异常：" + e.toString());   
      return bos.toByteArray();   
    }   
    finally {   
      try {   
        bos.close();   
      }   
      catch (IOException e) {}   
      try {   
        is.close();   
      }   
      catch (IOException e) {}   
    }   
   
  }   
   
  public static String clob2String(Clob clob) {   
    StringWriter writer = new StringWriter();   
    Reader reader = null;   
    try {   
      reader = clob.getCharacterStream();   
      char[] cRead = new char[1024];   
      int iRead;   
      while ( (iRead = reader.read(cRead, 0, 1024)) != -1) {   
        writer.write(cRead, 0, iRead);   
      }   
      return writer.getBuffer().toString();   
    }   
    catch (Exception e) {   
      Tools.log.error("转换java.sql.Clob到String异常：" + e.toString());   
      return "";   
    }   
    finally {   
      try {   
        writer.close();   
      }   
      catch (IOException e) {}   
      try {   
        reader.close();   
      }   
      catch (IOException e) {}   
    }   
   
  }   
   
   
}   


