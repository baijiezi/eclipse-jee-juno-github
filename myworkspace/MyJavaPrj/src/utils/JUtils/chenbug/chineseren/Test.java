package utils.JUtils.chenbug.chineseren;
   
/**  
 * <p>Title: </p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2004</p>  
 * <p>Company: </p>  
 * @author not attributable  
 * @version 1.0  
 */   
import java.util.*;   
import org.apache.commons.beanutils.*;   
import org.apache.commons.lang.*;   
import org.apache.commons.lang.builder.*;   
import org.apache.torque.*;   
import org.apache.torque.util.*;   
import com.workingdogs.village.*;   
   
import cn.com.pubinfo.control.AppInitializer;   
import cn.com.pubinfo.eshop.torque.*;   
import cn.com.pubinfo.dsfactory.*;   
   
public class Test {   
  //init Torque   
  public static void init() {   
    try {   
      Torque.init("E:/eshop/eshop/webapps/WEB-INF/Torque.properties");   
      new AppInitializer().init();   
    } catch(TorqueException ex) {   
      Debug.debug(ex, ex);   
    } catch(ServletException ex) {   
      Debug.debug(ex, ex);   
    }   
  }   
   
  //destroy Torque   
  public static void destroy() {   
    Torque.shutdown();   
    new AppInitializer().destroy();   
  }   
   
  public static void main(String[] args) throws Exception {   
  { //获得资源,不要删除   
      Test.init();   
    }   
   
    //开始测试   
    {   
      //Test For TblCategory   
      /*TblCategory cate;  
      String id = "0";  
      cate = TblCategoryPeer.getCate(id);  
      Debug.debug(cate.toString());  
      List list = cate.getChildCates();  
      ToolUtils.printList(list);  
      id = "1411";  
      cate = TblCategoryPeer.getCate(id);  
      Debug.debug(cate.toString());  
      list = cate.getChildCates();  
      ToolUtils.printList(list);  
      id = "254004";  
      cate = TblCategoryPeer.getCate(id);  
      Debug.debug(cate.toString());  
      list = cate.getChildCates();  
      ToolUtils.printList(list);  
      id = "1501";  
      Debug.debug("" + TblCategoryPeer.isEmpty(id));  
      id = "0";  
      Debug.debug("" + TblCategoryPeer.getComdCount(id));  
      id = "5011";  
      Debug.debug("" + TblCategoryPeer.getComdCount(id));  
      id = "501101";  
      Debug.debug("" + TblCategoryPeer.getComdCount(id));*/   
      //测试crit.CUSTOM   
      Criteria crit = new Criteria();   
      String where = "TblCategory.ID not in (select id from TblCategory where id <> '11')";   
      crit.add(TblCategoryPeer.ID,(Object)where,crit.CUSTOM);   
      List list = TblCategoryPeer.doSelect(crit);   
      Debug.debug("Test for crit.CUSTOM!\nList size is: " + list.size());   
    }   
   
    { //销毁资源,不要删除   
      Test.destroy();   
    }   
    System.exit(0);   
  }   
}  



