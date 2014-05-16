package utils.JUtils.other;

public class HibernateUtil {   
   
  private static Log log = LogFactory.getLog(HibernateUtil.class);   
   
  private static SessionFactory sessionFactory;   
  private static final ThreadLocal threadSession = new ThreadLocal();   
  private static final ThreadLocal threadTransaction = new ThreadLocal();   
   
  public static SessionFactory getSessionFactory(){   
    if(sessionFactory == null){   
      try {   
        // Create the SessionFactory   
        sessionFactory = new Configuration().configure().buildSessionFactory();   
      } catch (HibernateException ex) {   
        ex.printStackTrace();   
        throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);   
      }   
    }   
    return sessionFactory;   
  }   
   
  public static Session currentSession() throws HibernateException {   
    Session s = (Session) threadSession.get();   
    // Open a new Session, if this Thread has none yet   
    if (s == null) {   
      s = getSessionFactory().openSession();   
      log.debug("###Opening new Session for this thread:" + s);   
      threadSession.set(s);   
    }else{   
      log.debug("###Session was existed:" + s);   
    }   
    return s;   
  }   
   
  public static void closeSession() throws HibernateException {   
    Session s = (Session) threadSession.get();   
    threadSession.set(null);   
    if (s != null){   
      log.debug("###Closing Session of this thread. " + s);   
      s.close();   
    }   
  }   
   
  public static void beginTransaction()   
    throws HibernateException {   
    Transaction tx = (Transaction) threadTransaction.get();   
    try {   
      if (tx == null) {   
        tx = currentSession().beginTransaction();   
        log.debug("###Starting new database transaction in this thread:" + tx);   
        threadTransaction.set(tx);   
      }else{   
        log.debug("###Tx was existed:" + tx);   
      }   
    } catch (HibernateException ex) {   
      throw ex;   
    }   
  }   
   
  public static void commitTransaction()   
    throws HibernateException {   
    Transaction tx = (Transaction) threadTransaction.get();   
    try {   
      if ( tx != null && !tx.wasCommitted()   
              && !tx.wasRolledBack() ) {   
        log.debug("###Committing database transaction of this thread.");   
        tx.commit();   
      }   
      threadTransaction.set(null);   
    } catch (HibernateException ex) {   
      rollbackTransaction();   
      throw ex;   
    }   
  }   
   
  public static void rollbackTransaction()   
    throws HibernateException {   
    Transaction tx = (Transaction) threadTransaction.get();   
    try {   
      threadTransaction.set(null);   
      if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {   
        log.debug("###Tyring to rollback database transaction of this thread.");   
        tx.rollback();   
      }   
    } catch (HibernateException ex) {   
      throw ex;   
    } finally {   
      closeSession();   
    }   
  }   
   
}   



