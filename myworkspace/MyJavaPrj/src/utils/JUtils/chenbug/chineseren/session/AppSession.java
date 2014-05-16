package utils.JUtils.chenbug.chineseren.session;
   
/**  
 *  ����: ϵͳ�е�session������.  
 *  Session�д�����Ѿ���¼���û�TblUser��user����ͨ��AppSession���Ի�õ�¼�û���������Ϣ  
 *  ͨ��Right�ж��û��Ƿ��¼��������Ӧ��Ȩ�ޡ�  
 *  ͬ����̨����Ա��¼��Ҳ��TblAdmin����session�С�  
 *  ͨ��session�е�"login_type"ֵ�����жϵ�ǰ��¼���û�������:"user"||"admin"  
 *  �����ߣ�chineseren  
 *  ����ʱ�䣺2004-11-15  
 *  ����޸�ʱ�䣺2004-11-15  
 */   
import javax.servlet.*;   
import javax.servlet.http.*;   
import java.util.*;   
   
import cn.com.pubinfo.util.*;   
import cn.com.pubinfo.eshop.torque.*;   
import org.apache.torque.*;   
   
public class AppSession {   
  //��session�з���һ������   
  public static void put(RunData rd, String key, Object value) {   
    rd.getSession().setAttribute(key,value);   
  }   
  public static int put(HttpServletRequest req, Object value, String key) {   
    try {   
      if (value != null) {   
        HttpSession session = req.getSession();   
        session.setAttribute(key, value);   
      }   
      return 0;   
    }   
    catch (Exception ex) {   
      Debug.error("AppSession.putObject(HttpServletRequest req, Object sinfo, String key) ERROR!",ex);   
      return -1;   
    }   
  }   
   
  //��session�д��һ��int   
  public static void put(RunData rd, String key, int value) {   
    rd.getSession().setAttribute(key,String.valueOf(value));   
  }   
  public static int put(HttpServletRequest req, int value, String key) {   
    return put(req, String.valueOf(value), key);   
  }   
   
  //��session�д��һ��long   
  public static void put(RunData rd, String key, long value) {   
    rd.getSession().setAttribute(key,String.valueOf(value));   
  }   
  public static int put(HttpServletRequest req, long value, String key) {   
    return put(req, String.valueOf(value), key);   
  }   
   
  //��session�д��һ��boolean   
  public static void put(RunData rd, String key, boolean value) {   
    rd.getSession().setAttribute(key,String.valueOf(value));   
  }   
  public static int put(HttpServletRequest req, boolean value, String key) {   
    return put(req, String.valueOf(value), key);   
  }   
   
  //��session��ȡ��һ������Object   
  public static Object get(RunData rd, String key) {   
    return rd.getSession().getAttribute(key);   
  }   
  public static Object get(HttpServletRequest req, String key) {   
    try {   
      HttpSession session = req.getSession();   
      return session.getAttribute(key);   
    }   
    catch (Exception ex) {   
      Debug.error("AppSession.get(HttpServletRequest req, String key) ERROR!",ex);   
      return null;   
    }   
  }   
   
  //��sessionȡ��һ���ַ���   
  public static String getString(RunData rd, String key) {   
    return (String)AppSession.get(rd,key);   
  }   
  public static String getString(HttpServletRequest req, String key) {   
    return (String)AppSession.get(req,key);   
  }   
   
  //��sessionȡ��һ��int   
  public static int getInt(RunData rd, String key) {   
    return Integer.parseInt(AppSession.getString(rd,key));   
  }   
  public static int getInt(HttpServletRequest req, String key) {   
    return Integer.parseInt(AppSession.getString(req,key));   
  }   
   
  //��sessionȡ��һ��long   
  public static long getLong(RunData rd, String key) {   
    return Long.parseLong(AppSession.getString(rd,key));   
  }   
  public static long getLong(HttpServletRequest req, String key) {   
    return Long.parseLong(AppSession.getString(req,key));   
  }   
   
  //��sessionȡ��һ��boolean   
  public static boolean getBoolean(RunData rd, String key) {   
    return Boolean.valueOf(AppSession.getString(rd,key)).booleanValue();   
  }   
  public static boolean getBoolean(HttpServletRequest req, String key) {   
    return Boolean.valueOf(AppSession.getString(req,key)).booleanValue();   
  }   
   
  //��session��ȥ��ĳ������ֵ   
  public static void removeAttribute(RunData rd, String key) {   
    rd.getSession().removeAttribute(key);   
  }   
  public static void removeAttribute(HttpServletRequest req, String key) {   
    HttpSession session = req.getSession();   
    session.removeAttribute(key);   
  }   
   
  //ʹ�û�sessionʧЧ   
  public static void invalidate(RunData rd) {   
    rd.getSession().invalidate();   
  }   
  public static boolean invalidate(HttpServletRequest req) {   
    try {   
      HttpSession session = req.getSession();   
      session.invalidate();   
      return true;   
    }   
    catch (Exception ex) {   
      Debug.error("AppSession.invalidate(HttpServletRequest req) ERROR!",ex);   
      return false;   
    }   
  }   
   
  //��õ�¼�û���TblUser����   
  public static TblUser getUser(RunData rd){   
    return (TblUser)AppSession.get(rd,AppSystem.KEY_LOGIN_USER);   
  }   
  public static TblUser getUser(HttpServletRequest req){   
    return (TblUser)AppSession.get(req,AppSystem.KEY_LOGIN_USER);   
  }   
   
  //��õ�¼�û�������:ǰ̨�û� || ��̨����Ա   
  public static String getLoginType(RunData rd){   
    return AppSession.getString(rd,AppSystem.KEY_LOGIN_TYPE);   
  }   
  public static String getLoginType(HttpServletRequest req){   
    String ret = AppSession.getString(req,AppSystem.KEY_LOGIN_TYPE);   
    //Debug.debug("AppSession.getLoginType() is: " +ret );   
    return ret;   
  }   
  //isUserLoginType   
  public static boolean isUserLoginType(RunData rd){   
    return AppSession.isUserLoginType(rd.getRequest());   
  }   
  public static boolean isUserLoginType(HttpServletRequest req){   
    boolean ret;   
    String loginType = AppSession.getLoginType(req);   
    if(AppSystem.LOGIN_TYPE_USER.equals(loginType)){   
      ret = true;   
    }else{   
      ret = false;   
    }   
    return ret;   
  }   
  //isAdminLoginType   
  public static boolean isAdminLoginType(RunData rd){   
    return AppSession.isAdminLoginType(rd.getRequest());   
  }   
  public static boolean isAdminLoginType(HttpServletRequest req){   
    boolean ret;   
    String loginType = AppSession.getLoginType(req);   
    if(AppSystem.LOGIN_TYPE_ADMIN.equals(loginType)){   
      ret = true;   
    }else{   
      ret = false;   
    }   
    return ret;   
  }   
   
  //������Ա�����û��޸���user��Ϣ֮��Ҫͬ������session�е�user����   
  public static void setUser(RunData rd,TblUser user){   
    AppSession.put(rd,AppSystem.KEY_LOGIN_USER,user);   
  }   
  public static void setUser(HttpServletRequest req,TblUser user){   
    AppSession.put(req,user,AppSystem.KEY_LOGIN_USER);   
  }   
   
  //��session��ȡ���û�ID   
  public static String getUserID(RunData rd) {   
    return AppSession.getUser(rd).getId();   
  }   
  public static String getUserID(HttpServletRequest req) {   
    return AppSession.getUser(req).getId();   
  }   
   
  //��session��ȡ�û����ǿ��û�ID   
  public static String getVnetID(RunData rd) {   
    return AppSession.getUser(rd).getVnetID();   
  }   
  public static String getVnetID(HttpServletRequest req) {   
    return AppSession.getUser(req).getVnetID();   
  }   
   
  //��session��ȡ���û���userName   
  public static String getUserName(RunData rd) {   
    return AppSession.getUser(rd).getUserName();   
  }   
  public static String getUserName(HttpServletRequest req) {   
    return AppSession.getUser(req).getUserName();   
  }   
   
  //��session��ȡ���û���realName   
  public static String getRealName(RunData rd) {   
    return AppSession.getUser(rd).getRealName();   
  }   
  public static String getRealName(HttpServletRequest req) {   
    return AppSession.getUser(req).getRealName();   
  }   
   
  //��session��ȡ������password   
  public static String getPassword(RunData rd) {   
    return AppSession.getUser(rd).getPassword();   
  }   
  public static String getPassword(HttpServletRequest req) {   
    return AppSession.getUser(req).getPassword();   
  }   
   
  //��session��ȡ����֤״̬isAuthen   
  public static String getIsAuthen(RunData rd) {   
    return AppSession.getUser(rd).getIsAuthen();   
  }   
  public static String getIsAuthen(HttpServletRequest req) {   
    return AppSession.getUser(req).getIsAuthen();   
  }   
   
  //��ǰ�û��Ƿ�ͨ����ʵ����֤   
  public static boolean isAuthen(RunData rd) {   
    return AppSession.isAuthen(rd.getRequest());   
  }   
  public static boolean isAuthen(HttpServletRequest req) {   
    boolean ret;   
    String isAuthen = AppSession.getIsAuthen(req);   
    if(TblUser.ISAUTHEN_YES.equals(isAuthen)){   
      ret = true;   
    }else{   
      ret = false;   
    }   
   
    return ret;   
  }   
   
  //��session��ȡ���û�����integral   
  public static int getIntegral(RunData rd) {   
    return AppSession.getUser(rd).getIntegral();   
  }   
  public static int getIntegral(HttpServletRequest req) {   
    return AppSession.getUser(req).getIntegral();   
  }   
   
  //��session��ȡ���Ǽ�level   
  public static int getLevel(RunData rd) {   
    return AppSession.getUser(rd).getLevel();   
  }   
  public static int getLevel(HttpServletRequest req) {   
    return AppSession.getUser(req).getLevel();   
  }   
   
   
  //��session��ȡ���Ǽ�bbsright   
  public static String getBbsRight(HttpServletRequest req) {   
    return AppSession.getUser(req).getBbsRight();   
  }   
   
  //��õ�¼�û���TblAdmin����   
  public static TblAdmin getAdmin(RunData rd){   
    return (TblAdmin)AppSession.get(rd,AppSystem.KEY_LOGIN_ADMIN);   
  }   
  public static TblAdmin getAdmin(HttpServletRequest req){   
    return (TblAdmin)AppSession.get(req,AppSystem.KEY_LOGIN_ADMIN);   
  }   
   
  //������Ա�޸���admin��Ϣ֮��Ҫͬ������session�е�admin����   
  public static void setAdmin(RunData rd,TblAdmin admin){   
    AppSession.put(rd,AppSystem.KEY_LOGIN_ADMIN,admin);   
  }   
  public static void setAdmin(HttpServletRequest req,TblAdmin admin){   
    AppSession.put(req,admin,AppSystem.KEY_LOGIN_ADMIN);   
  }   
   
  //��session��ȡ�ù���ԱID   
  public static String getAdminID(RunData rd) {   
    return AppSession.getAdmin(rd).getId();   
  }   
  public static String getAdminID(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getId();   
  }   
   
  //��session��ȡ���û���userName   
  public static String getAdminName(RunData rd) {   
    return AppSession.getAdmin(rd).getUserName();   
  }   
  public static String getAdminName(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getUserName();   
  }   
   
  //��session��ȡ����ʵ����realName   
  public static String getAdminRealName(RunData rd) {   
    return AppSession.getAdmin(rd).getRealName();   
  }   
  public static String getAdminRealName(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getRealName();   
  }   
   
  //��session��ȡ������password   
  public static String getAdminPassword(RunData rd) {   
    return AppSession.getAdmin(rd).getPassword();   
  }   
  public static String getAdminPassword(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getPassword();   
  }   
   
  //��session��ȡ�ý�ɫroleName   
  public static String getAdminRoleName(RunData rd) {   
    return AppSession.getAdmin(rd).getRoleName();   
  }   
  public static String getAdminRoleName(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getRoleName();   
  }   
   
  //��session��ȡ��Ȩ��permission   
  public static String getAdminPermission(RunData rd) {   
    return AppSession.getAdminPermission(rd.getRequest());   
  }   
  public static String getAdminPermission(HttpServletRequest req) {   
    TblAdmin admin = AppSession.getAdmin(req);   
    String permission = null;   
    try {   
      permission = TblAdminRolePeer.getPermissionByID(admin.getRoleID());   
    } catch(Exception ex) {   
      Debug.error("AppSession.getAdminPermission(HttpServletRequest req) ERROR!",ex);   
    }   
    return permission;   
  }   
   
  //����testlogin.jsp,����ʹ��,ģ���¼,����req�е�'id'��'type'='0'user || '1'admin   
  public static boolean debug_login(HttpServletRequest req){   
    try {   
      String id = (String)req.getAttribute("id");   
      String type = (String)req.getAttribute("type");   
      if(null == type || "".equals(type) || "0".equals(type)) { //user   
        if(null == id || "".equals(id)) {   
          id = InitSession.USER_ID;   
        }   
        TblUser user = TblUserPeer.retrieveByPK(id);   
        AppSession.setUser(req, user);   
        AppSession.put(req, AppSystem.ISLOGIN_TRUE, AppSystem.KEY_ISLOGIN);   
        AppSession.put(req, AppSystem.LOGIN_TYPE_USER, AppSystem.KEY_LOGIN_TYPE);   
        return true;   
      } else { //admin   
        if(null == id || "".equals(id)) {   
          id = InitSession.ADMIN_ID;   
        }   
        TblAdmin admin = TblAdminPeer.retrieveByPK(id);   
        AppSession.setAdmin(req, admin);   
        AppSession.put(req, AppSystem.ISLOGIN_TRUE, AppSystem.KEY_ISLOGIN);   
        AppSession.put(req, AppSystem.LOGIN_TYPE_ADMIN,AppSystem.KEY_LOGIN_TYPE);   
        return true;   
      }   
    } catch(TorqueException ex) {//�������   
      Debug.error("AppSession.debug_login(HttpServletRequest req) ERROR!",ex);   
      return false;   
    }   
  }   
}   


