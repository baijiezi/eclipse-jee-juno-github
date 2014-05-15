package hibernate.test.com.cache.secondlevelcache.dao;


import hibernate.test.com.cache.secondlevelcache.model.UserModel;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl {
	public void insertUser(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
        Session session = null; 
        Transaction transaction = null; 
 
        try{ 
            //准备数据 
            UserModel userModel = new UserModel(); 
            userModel.setUserId(11); 
            userModel.setName("name2"); 
            userModel.setAge(10); 
            session = sessionFactory.openSession(); 
            transaction = session.beginTransaction(); 
            session.save(userModel); 
            transaction.commit(); 
       }catch(Exception err){ 
    	    transaction.rollback(); 
            err.printStackTrace(); 
      }finally{ 
            session.close(); 
      } 
	}
	
	
	public void getUser(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
        Session session = sessionFactory.openSession();
        try{ 
            List<UserModel> list = session.createCriteria(UserModel.class)
            .add(Restrictions.eq("name", "name1"))
            .list();
            if(list != null){
            	for(UserModel user : list){
            		System.out.println(user.getName());
            	}
            }
       }catch(Exception e){ 
            e.printStackTrace(); 
      }finally{ 
            session.close(); 
      } 
	}
	
	public void getSecondLevelCacheUser(){
		
		//二级缓存的生命周期和 SessionFactory 的生命周期一致
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
        Session session = sessionFactory.openSession();
        try{ 
        	session.beginTransaction();
        	
        	System.out.println("第一次查询，发出SQL语句");
        	UserModel user = (UserModel)session.get(UserModel.class, 2);
            System.out.println(user.getName());
            
            session.getTransaction().commit();
            session.close(); 
            
            
            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
        	System.out.println("第二次查询，从缓存中取，不发出SQL语句");
        	UserModel user2 = (UserModel)session2.get(UserModel.class, 2);
            System.out.println(user2.getName());
            
            session2.getTransaction().commit();
            session2.close(); 
            
       }catch(Exception e){ 
            e.printStackTrace(); 
      }finally{ 
            
      } 
	}
	
}
