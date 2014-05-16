package utils.JUtils.chenbug;
   
import org.apache.oro.text.regex.*;   
import org.apache.oro.text.*;   
import org.apache.oro.text.perl.*;   
   
public class RegExTools {   
   
  private static final String variableRegular = "[a-z|A-Z]{1}[a-z|A-Z|\\d|_]*";   
   
  public static boolean isVariable(String variable) {   
    PatternCompiler compiler = new Perl5Compiler();   
    Pattern pattern = null;   
    try {   
      pattern = compiler.compile(variableRegular, Perl5Compiler.CASE_INSENSITIVE_MASK);   
    } catch (Exception e) {   
      Tools.log.error("正则表单式验证异常："+e.toString());   
      return false;   
    }   
    PatternMatcher patternMatcher = new Perl5Matcher();   
    return patternMatcher.matches(variable, pattern);   
  }   
}   



