package hibernate.test.com.basehibernate.dao;

import hibernate.test.com.basehibernate.model.UserModel;

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
}