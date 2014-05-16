package utils.JUtils.chenbug.chineseren.base;
   
/**  
 * 扩展了HttpServlet类，工程内的所有servlet都是继承了RootHandler。  
 * doBusiness函数实现了商务逻辑的控制,根据act的不同调用不同的函数，实现不同的功能  
 */   
import javax.servlet.http.*;   
import javax.servlet.*;   
import java.io.*;   
import java.util.*;   
   
import cn.com.pubinfo.util.*;   
import cn.com.pubinfo.session.*;   
   
public abstract class RootHandler   
    extends HttpServlet {   
  //实现商务逻辑控制   
  //public abstract void doBusiness(RunData rd);//引用rundata后的业务实现方法   
  public void doBusiness(RunData rd) {}   
  public void doBusiness(HttpServletResponse m_response,HttpServletRequest m_request){}   
  //public abstract void doBusiness(HttpServletResponse m_response,HttpServletRequest m_request);   
  //调用顺序:servlet==>service()==>doBefore()==>checkPermission()==>doBusiness()==>doAfter()   
  public void doBefore(RunData rd){}   
  public void doAfter(RunData rd){}   
  //检测该用户是否有权限进入该servlet操作(Servlet中先于doBusiness执行): isLogin && hasPermission   
  //subServlet中重载该方法,如果是false,则内部进行相应的页面跳转   
  //由于每个Handler都共用一个权限检测checkPermission,所以有冲突时最好拆分成多个Handler   
  public boolean checkPermission(RunData rd){   
    return true;   
  }   
  //没有登录时要跳转到的页面   
  public static void dispatch_noLogin_user(RunData rd){   
    dispatch(rd,JSPNames.LOGIN_PAGE_USER);   
  }   
  public static void dispatch_noLogin_admin(RunData rd){   
    dispatch(rd,JSPNames.LOGIN_PAGE_ADMIN);   
  }   
  //没有相应权限时要跳转的页面,这时用户应该已经登录   
  public static void dispatch_noPermission(RunData rd){   
    if(AppSession.isUserLoginType(rd.getRequest())){   
      dispatch(rd,JSPNames.NOPERMISSION_PAGE_USER);   
    }else{   
      dispatch(rd,JSPNames.NOPERMISSION_PAGE_ADMIN);   
    }   
  }   
   
  //错误页面指向   
  public void messageDispatch(RunData rd){   
    messageDispatch(rd.getResponse(),rd.getRequest());   
  }   
  public void messageDispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request) {   
    messageDispatch(m_response, m_request, Msgs.MSGTYPE_SYS, Msgs.MSG_SYSBUSY,   
        "", "");   
  }   
   
  //跳转到消息页面   
  public void messageDispatch(RunData rd, int msgType, String msgStr,   
      String forwordPage){   
    messageDispatch(rd.getResponse(),rd.getRequest(),msgType,msgStr,forwordPage);   
  }   
  public void messageDispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request, int msgType, String msgStr,   
      String forwordPage) {   
    messageDispatch(m_response, m_request, msgType, msgStr, forwordPage, "");   
  }   
   
  public void messageDispatch(RunData rd, int msgType, String msgStr){   
    messageDispatch(rd.getResponse(),rd.getRequest(),msgType,msgStr,Button.BACK_BUTTON);   
  }   
  public void messageDispatch(RunData rd, int msgType, String msgStr, Button button){   
    messageDispatch(rd.getResponse(),rd.getRequest(),msgType,msgStr,button);   
  }   
  public void messageDispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request, int msgType, String msgStr, Button button) {   
    m_request.setAttribute("button", button);   
    m_request.setAttribute("message", msgStr);   
    if(msgType == Msgs.MSGTYPE_SYS) {   
      this.dispatch(m_response, m_request, JSPNames.ADMIN_MSGPAGE);   
    } else {   
      this.dispatch(m_response, m_request, JSPNames.USER_MSGPAGE);   
    }   
  }   
   
  /**  
   * 跳转到消息页面  
   * @param rd RunData  
   * @param msgType 消息类型: user || admin  
   * @param msgStr 要显示的消息  
   * @param forwordPage 点击按钮后要跳转的下一个页面  
   * @param buttonName 按钮显示的名称  
   */   
  public void messageDispatch(RunData rd, int msgType, String msgStr,   
      String forwordPage, String buttonName){   
    messageDispatch(rd.getResponse(), rd.getRequest(), msgType, msgStr,   
        forwordPage, buttonName);   
  }   
  public void messageDispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request, int msgType, String msgStr,   
      String forwordPage, String buttonName) {   
    Button button = new Button();   
    if(buttonName.equals("")) {   
      button.add(Button.BTN_BACK, JSPNames.ROOT_URI + forwordPage);   
    } else {   
      button.add(buttonName, JSPNames.ROOT_URI + forwordPage);   
    }   
    m_request.setAttribute("button", button);   
    m_request.setAttribute("message", msgStr);   
    if(msgType == Msgs.MSGTYPE_SYS) {   
      this.dispatch(m_response, m_request, JSPNames.ADMIN_MSGPAGE);   
    } else {   
      this.dispatch(m_response, m_request, JSPNames.USER_MSGPAGE);   
    }   
  }   
   
  //rootServlet==>service()==>doBusiness()(现在有两种方式,但是subServlet只能实现其中的一个)   
  //调用顺序:servlet==>service()==>doBefore()==>checkPermission()==>doBusiness()==>doAfter()   
  public void service(HttpServletRequest request,   
      HttpServletResponse response) throws ServletException, IOException {   
    //引入ThreadLocal rd   
    RunData rd = RunData.getInstance(request, response);   
    doBefore(rd);   
    //没有权限就要跳转,service()就必须返回   
    if(!checkPermission(rd)){   
      return;   
    }   
    rd.flush();   
    doBusiness(rd);   
   
    doBusiness(response, request);   
    doAfter(rd);   
  }   
   
  //页面重定向函数   
  public static void dispatch(RunData rd, String url) {   
    dispatch(rd.getResponse(),rd.getRequest(),url);   
  }   
  public static void dispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request, String url) {   
    try {   
      RequestDispatcher dipatcher = m_request.getRequestDispatcher("/" +url);   
      Debug.debug("App will forward to: /" + url);   
      //使IE的Cache失效,防止用户回退   
      m_response.setHeader("Pragma", "No-cache");   
      m_response.setHeader("Cache-Control", "no-cache");   
      m_response.setHeader("Cache-Control", "must-revalidate");   
      m_response.setDateHeader("Expires",0);   
      dipatcher.forward(m_request, m_response);   
    } catch(Exception e) {   
      Debug.debug("RootHandler.dispatch() ERROR!",e);   
       Debug.println(e.toString());   
    }   
  }   
   
  //取得提交的action的参数转换为整形,比如:act='5'   
  public int getAction(RunData rd){   
    return getAction(rd.getResponse(),rd.getRequest());   
  }   
  public int getAction(HttpServletResponse m_response,   
      HttpServletRequest m_request) {   
    String strAction = m_request.getParameter(AppSystem.KEY_ACT);   
    if(strAction == null)   
      return( -1);   
    try {   
      return(new Integer(strAction).intValue());   
    } catch(Exception e) {   
      Debug.debug("ServletHandler getAction() Error! The 'act' = " + strAction.toString());   
      return( -1);   
    }   
  }   
   
  //以下的Properties getRequest都可以用RunData rd代替了   
  public Properties getRequest(HttpServletResponse m_response,   
      HttpServletRequest m_request) {   
    //-----从表单取详细信息----   
    Properties formInfo = new Properties();   
    Enumeration fieldNames = m_request.getParameterNames();   
    while(fieldNames.hasMoreElements()) {   
      String paramName = (String)fieldNames.nextElement();   
      String paramValue = (String)m_request.getParameterValues(paramName)[0];   
      //formInfo.put(paramName, Encode.ISO8859ToGBK(Encode.convertNull(paramValue)));   
      formInfo.put(paramName, Encode.convertNull(paramValue));   
    }   
    return(formInfo);   
  }   
   
  /**  
   * 从表单取出所有的parameters,并且放在properties中,一个paramName对应了一个String[]的值  
   * @param isArray 判断其中的parameter取出后是否为数组,比如有一组<input name="txtName">  
   * @return Properties  
   */   
  public Properties getRequest(HttpServletResponse m_response,   
      HttpServletRequest m_request, boolean isArray) {   
    if(!isArray) {   
      return this.getRequest(m_response, m_request);   
    }   
   
    Properties formInfo = new Properties();   
    Enumeration fieldNames = m_request.getParameterNames();   
    while(fieldNames.hasMoreElements()) {   
      String paramName = (String)fieldNames.nextElement();   
      String[] paramValues = m_request.getParameterValues(paramName);   
      int count = paramValues.length;   
   
      for(int i = 0; i  count; i++) { //取出来转化,再放入properties   
        String paramValue = paramValues[i];   
        paramValues[i] = Encode.ISO8859ToGBK(Encode.convertNull(paramValue));   
      }   
      formInfo.put(paramName, paramValues);   
    }   
    return(formInfo);   
  }   
   
  public Properties getRequest(com.jspsmart.upload.Request m_request) {   
    Properties formInfo = new Properties();   
    Enumeration fieldNames = m_request.getParameterNames();   
    while(fieldNames.hasMoreElements()) {   
      String paramName = (String)fieldNames.nextElement();   
      String paramValue = (String)m_request.getParameterValues(paramName)[0];   
      formInfo.put(paramName, Encode.ISO8859ToGBK(Encode.convertNull(paramValue)));   
    }   
    return(formInfo);   
  }   
   
}   


