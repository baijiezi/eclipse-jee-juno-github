package utils.JUtils.chenbug;

public class GetClassPath {
    public static String CONFIG_PATH;   
    
    static {   
      try {   
        URL url = Constant.class.getResource("/");   
        CONFIG_PATH = URLDecoder.decode(url.toString(), "GBK");   
        if (CONFIG_PATH.toUpperCase().endsWith("CLASSES/") || CONFIG_PATH.toUpperCase().endsWith("CLASSES\\")) {   
          int i = CONFIG_PATH.toUpperCase().lastIndexOf("CLASSES\\");   
          if (i == -1) {   
            i = CONFIG_PATH.toUpperCase().lastIndexOf("CLASSES/");   
          }   
          CONFIG_PATH = CONFIG_PATH.substring(0, i);   
        }   
        if (CONFIG_PATH.toUpperCase().startsWith("FILE:")) {   
          CONFIG_PATH = CONFIG_PATH.substring(CONFIG_PATH.toUpperCase().indexOf("FILE:")+6, CONFIG_PATH.length());   
        }   
      }   
      catch (Exception ex) {   
        throw new RuntimeException("…Ë÷√WebService≈‰÷√¬∑æ∂“Ï≥££∫"+ex.toString());   
      }   
    }   
}
