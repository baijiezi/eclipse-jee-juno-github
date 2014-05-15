package hibernate.test.com.cache.secondlevelcache;


import hibernate.test.com.cache.secondlevelcache.dao.UserDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecondLevelCacheTest {
	  public static void main(String[] args) { 
	    	Log logger = LogFactory.getLog(SecondLevelCacheTest.class);
	        
	        UserDaoImpl userDao = new UserDaoImpl();
	        
	        //第一次查询
	        userDao.getSecondLevelCacheUser();
	        
	        //第二次查询
	        //二级缓存的生命周期和 SessionFactory 的生命周期一致,重新获取SessionFactory时，缓存不能再用
	        userDao.getSecondLevelCacheUser();
	        
	        
	        
	        
	        
//	        Transaction transaction = null; 
//	        try{ 
//	            //准备数据 
//	            UserModel userModel = new UserModel(); 
//	            userModel.setUserId(11); 
//	            userModel.setName("name1"); 
//	            userModel.setAge(10); 
//	            session = sessionFactory.openSession(); 
//	            transaction = session.beginTransaction(); 
//	            session.save(userModel); 
//	            transaction.commit(); 
//	        }catch(Exception err){ 
//	    	    transaction.rollback(); 
//	            err.printStackTrace(); 
//	        }finally{ 
//	        	session.close(); 
//	        } 
	    } 
}