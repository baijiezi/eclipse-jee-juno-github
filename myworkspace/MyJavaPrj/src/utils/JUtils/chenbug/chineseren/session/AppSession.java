package utils.JUtils.chenbug.chineseren.session;
   
/**  
 *  功能: 系统中的session管理器.  
 *  Session中存放着已经登录的用户TblUser的user对象，通过AppSession可以获得登录用户的所有信息  
 *  通过Right判断用户是否登录或者有相应的权限。  
 *  同理，后台管理员登录后也把TblAdmin放入session中。  
 *  通过session中的"login_type"值进行判断当前登录的用户的类型:"user"||"admin"  
 *  开发者：chineseren  
 *  创建时间：2004-11-15  
 *  最后修改时间：2004-11-15  
 */   
import javax.servlet.*;   
import javax.servlet.http.*;   
import java.util.*;   
   
import cn.com.pubinfo.util.*;   
import cn.com.pubinfo.eshop.torque.*;   
import org.apache.torque.*;   
   
public class AppSession {   
  //向session中放入一个对象   
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
   
  //向session中存放一个int   
  public static void put(RunData rd, String key, int value) {   
    rd.getSession().setAttribute(key,String.valueOf(value));   
  }   
  public static int put(HttpServletRequest req, int value, String key) {   
    return put(req, String.valueOf(value), key);   
  }   
   
  //向session中存放一个long   
  public static void put(RunData rd, String key, long value) {   
    rd.getSession().setAttribute(key,String.valueOf(value));   
  }   
  public static int put(HttpServletRequest req, long value, String key) {   
    return put(req, String.valueOf(value), key);   
  }   
   
  //向session中存放一个boolean   
  public static void put(RunData rd, String key, boolean value) {   
    rd.getSession().setAttribute(key,String.valueOf(value));   
  }   
  public static int put(HttpServletRequest req, boolean value, String key) {   
    return put(req, String.valueOf(value), key);   
  }   
   
  //从session中取得一个对象Object   
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
   
  //从session取得一个字符串   
  public static String getString(RunData rd, String key) {   
    return (String)AppSession.get(rd,key);   
  }   
  public static String getString(HttpServletRequest req, String key) {   
    return (String)AppSession.get(req,key);   
  }   
   
  //从session取得一个int   
  public static int getInt(RunData rd, String key) {   
    return Integer.parseInt(AppSession.getString(rd,key));   
  }   
  public static int getInt(HttpServletRequest req, String key) {   
    return Integer.parseInt(AppSession.getString(req,key));   
  }   
   
  //从session取得一个long   
  public static long getLong(RunData rd, String key) {   
    return Long.parseLong(AppSession.getString(rd,key));   
  }   
  public static long getLong(HttpServletRequest req, String key) {   
    return Long.parseLong(AppSession.getString(req,key));   
  }   
   
  //从session取得一个boolean   
  public static boolean getBoolean(RunData rd, String key) {   
    return Boolean.valueOf(AppSession.getString(rd,key)).booleanValue();   
  }   
  public static boolean getBoolean(HttpServletRequest req, String key) {   
    return Boolean.valueOf(AppSession.getString(req,key)).booleanValue();   
  }   
   
  //从session中去掉某个属性值   
  public static void removeAttribute(RunData rd, String key) {   
    rd.getSession().removeAttribute(key);   
  }   
  public static void removeAttribute(HttpServletRequest req, String key) {   
    HttpSession session = req.getSession();   
    session.removeAttribute(key);   
  }   
   
  //使用户session失效   
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
   
  //获得登录用户的TblUser对象   
  public static TblUser getUser(RunData rd){   
    return (TblUser)AppSession.get(rd,AppSystem.KEY_LOGIN_USER);   
  }   
  public static TblUser getUser(HttpServletRequest req){   
    return (TblUser)AppSession.get(req,AppSystem.KEY_LOGIN_USER);   
  }   
   
  //获得登录用户的类型:前台用户 || 后台管理员   
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
   
  //当管理员或者用户修改了user信息之后要同步更新session中的user对象   
  public static void setUser(RunData rd,TblUser user){   
    AppSession.put(rd,AppSystem.KEY_LOGIN_USER,user);   
  }   
  public static void setUser(HttpServletRequest req,TblUser user){   
    AppSession.put(req,user,AppSystem.KEY_LOGIN_USER);   
  }   
   
  //从session中取得用户ID   
  public static String getUserID(RunData rd) {   
    return AppSession.getUser(rd).getId();   
  }   
  public static String getUserID(HttpServletRequest req) {   
    return AppSession.getUser(req).getId();   
  }   
   
  //从session中取得互联星空用户ID   
  public static String getVnetID(RunData rd) {   
    return AppSession.getUser(rd).getVnetID();   
  }   
  public static String getVnetID(HttpServletRequest req) {   
    return AppSession.getUser(req).getVnetID();   
  }   
   
  //从session中取得用户名userName   
  public static String getUserName(RunData rd) {   
    return AppSession.getUser(rd).getUserName();   
  }   
  public static String getUserName(HttpServletRequest req) {   
    return AppSession.getUser(req).getUserName();   
  }   
   
  //从session中取得用户名realName   
  public static String getRealName(RunData rd) {   
    return AppSession.getUser(rd).getRealName();   
  }   
  public static String getRealName(HttpServletRequest req) {   
    return AppSession.getUser(req).getRealName();   
  }   
   
  //从session中取得密码password   
  public static String getPassword(RunData rd) {   
    return AppSession.getUser(rd).getPassword();   
  }   
  public static String getPassword(HttpServletRequest req) {   
    return AppSession.getUser(req).getPassword();   
  }   
   
  //从session中取得认证状态isAuthen   
  public static String getIsAuthen(RunData rd) {   
    return AppSession.getUser(rd).getIsAuthen();   
  }   
  public static String getIsAuthen(HttpServletRequest req) {   
    return AppSession.getUser(req).getIsAuthen();   
  }   
   
  //当前用户是否通过了实名认证   
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
   
  //从session中取得用户积分integral   
  public static int getIntegral(RunData rd) {   
    return AppSession.getUser(rd).getIntegral();   
  }   
  public static int getIntegral(HttpServletRequest req) {   
    return AppSession.getUser(req).getIntegral();   
  }   
   
  //从session中取得星级level   
  public static int getLevel(RunData rd) {   
    return AppSession.getUser(rd).getLevel();   
  }   
  public static int getLevel(HttpServletRequest req) {   
    return AppSession.getUser(req).getLevel();   
  }   
   
   
  //从session中取得星级bbsright   
  public static String getBbsRight(HttpServletRequest req) {   
    return AppSession.getUser(req).getBbsRight();   
  }   
   
  //获得登录用户的TblAdmin对象   
  public static TblAdmin getAdmin(RunData rd){   
    return (TblAdmin)AppSession.get(rd,AppSystem.KEY_LOGIN_ADMIN);   
  }   
  public static TblAdmin getAdmin(HttpServletRequest req){   
    return (TblAdmin)AppSession.get(req,AppSystem.KEY_LOGIN_ADMIN);   
  }   
   
  //当管理员修改了admin信息之后要同步更新session中的admin对象   
  public static void setAdmin(RunData rd,TblAdmin admin){   
    AppSession.put(rd,AppSystem.KEY_LOGIN_ADMIN,admin);   
  }   
  public static void setAdmin(HttpServletRequest req,TblAdmin admin){   
    AppSession.put(req,admin,AppSystem.KEY_LOGIN_ADMIN);   
  }   
   
  //从session中取得管理员ID   
  public static String getAdminID(RunData rd) {   
    return AppSession.getAdmin(rd).getId();   
  }   
  public static String getAdminID(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getId();   
  }   
   
  //从session中取得用户名userName   
  public static String getAdminName(RunData rd) {   
    return AppSession.getAdmin(rd).getUserName();   
  }   
  public static String getAdminName(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getUserName();   
  }   
   
  //从session中取得真实姓名realName   
  public static String getAdminRealName(RunData rd) {   
    return AppSession.getAdmin(rd).getRealName();   
  }   
  public static String getAdminRealName(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getRealName();   
  }   
   
  //从session中取得密码password   
  public static String getAdminPassword(RunData rd) {   
    return AppSession.getAdmin(rd).getPassword();   
  }   
  public static String getAdminPassword(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getPassword();   
  }   
   
  //从session中取得角色roleName   
  public static String getAdminRoleName(RunData rd) {   
    return AppSession.getAdmin(rd).getRoleName();   
  }   
  public static String getAdminRoleName(HttpServletRequest req) {   
    return AppSession.getAdmin(req).getRoleName();   
  }   
   
  //从session中取得权限permission   
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
   
  //用于testlogin.jsp,调试使用,模拟登录,根据req中的'id'和'type'='0'user || '1'admin   
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
    } catch(TorqueException ex) {//出错情况   
      Debug.error("AppSession.debug_login(HttpServletRequest req) ERROR!",ex);   
      return false;   
    }   
  }   
}   


