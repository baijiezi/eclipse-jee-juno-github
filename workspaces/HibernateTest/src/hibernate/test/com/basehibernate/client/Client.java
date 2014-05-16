package hibernate.test.com.basehibernate.client;

import hibernate.test.com.basehibernate.dao.UserDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 运行本类时，注意将hibernate.cfg.xml文件的 使用二级缓存时 mapping配置注释掉，
 * 将 不使用二级缓存时 的mapping配置开启
 * @author BaiJiezi
 *
 */
public class Client { 
    public static void main(String[] args) { 
    	Log logger = LogFactory.getLog(Client.class);
    	logger.info("=================");
    	//这句话的意思是读取hibernate.cfg.xml，创建Session工厂
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
        Session session = null; 
        
        
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.getUser();
        
        
        
        
        
        
        
        
//        Transaction transaction = null; 
//        try{ 
//            //准备数据 
//            UserModel userModel = new UserModel(); 
//            userModel.setUserId(11); 
//            userModel.setName("name1"); 
//            userModel.setAge(10); 
//            session = sessionFactory.openSession(); 
//            transaction = session.beginTransaction(); 
//            session.save(userModel); 
//            transaction.commit(); 
//        }catch(Exception err){ 
//    	    transaction.rollback(); 
//            err.printStackTrace(); 
//        }finally{ 
//        	session.close(); 
//        } 
    } 
} 
