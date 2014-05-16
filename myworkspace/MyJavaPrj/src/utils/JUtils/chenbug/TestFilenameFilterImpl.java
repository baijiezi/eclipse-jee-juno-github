package utils.JUtils.chenbug;
   
import junit.framework.*;   
import java.io.*;   
   
public class TestFilenameFilterImpl extends TestCase {   
   
   
  protected void setUp() throws Exception {   
    super.setUp();   
    /**@todo verify the constructors*/   
  }   
   
  protected void tearDown() throws Exception {   
    super.tearDown();   
  }   
   
  public void testAccept() {   
   
    FilenameFilter filter;   
    filter = FilenameFilterImpl.getFilter("a*.xml|b*.xml");   
    File dir = null;   
    String filename = "tt.xml";   
    boolean expectedReturn = false;   
    boolean actualReturn = filter.accept(dir, filename);   
    assertEquals("return value", expectedReturn, actualReturn);   
   
    filter = FilenameFilterImpl.getFilter("a*.xml|b*.xml");   
    filename = "abc.xml";   
    expectedReturn = true;   
    actualReturn = filter.accept(dir, filename);   
    assertEquals("return value", expectedReturn, actualReturn);   
   
    filter = FilenameFilterImpl.getFilter("a*.xml|b*.xml");   
    filename = "bac.xml";   
    expectedReturn = true;   
    actualReturn = filter.accept(dir, filename);   
    assertEquals("return value", expectedReturn, actualReturn);   
   
    filter = FilenameFilterImpl.getFilter("a*.xml|b*.xml");   
    filename = "abc.xsl";   
    expectedReturn = false;   
    actualReturn = filter.accept(dir, filename);   
    assertEquals("return value", expectedReturn, actualReturn);   
   
    filter = FilenameFilterImpl.getFilter("a*.xml|b*.xml");   
    filename = "bac.xsl";   
    expectedReturn = false;   
    actualReturn = filter.accept(dir, filename);   
    assertEquals("return value", expectedReturn, actualReturn);   
   
    /**@todo fill in the test code*/   
  }   
   
}   


