package utils.JUtils.chenbug.chineseren.base;
   
/**  
 * ��չ��HttpServlet�࣬�����ڵ�����servlet���Ǽ̳���RootHandler��  
 * doBusiness����ʵ���������߼��Ŀ���,����act�Ĳ�ͬ���ò�ͬ�ĺ�����ʵ�ֲ�ͬ�Ĺ���  
 */   
import javax.servlet.http.*;   
import javax.servlet.*;   
import java.io.*;   
import java.util.*;   
   
import cn.com.pubinfo.util.*;   
import cn.com.pubinfo.session.*;   
   
public abstract class RootHandler   
    extends HttpServlet {   
  //ʵ�������߼�����   
  //public abstract void doBusiness(RunData rd);//����rundata���ҵ��ʵ�ַ���   
  public void doBusiness(RunData rd) {}   
  public void doBusiness(HttpServletResponse m_response,HttpServletRequest m_request){}   
  //public abstract void doBusiness(HttpServletResponse m_response,HttpServletRequest m_request);   
  //����˳��:servlet==>service()==>doBefore()==>checkPermission()==>doBusiness()==>doAfter()   
  public void doBefore(RunData rd){}   
  public void doAfter(RunData rd){}   
  //�����û��Ƿ���Ȩ�޽����servlet����(Servlet������doBusinessִ��): isLogin && hasPermission   
  //subServlet�����ظ÷���,�����false,���ڲ�������Ӧ��ҳ����ת   
  //����ÿ��Handler������һ��Ȩ�޼��checkPermission,�����г�ͻʱ��ò�ֳɶ��Handler   
  public boolean checkPermission(RunData rd){   
    return true;   
  }   
  //û�е�¼ʱҪ��ת����ҳ��   
  public static void dispatch_noLogin_user(RunData rd){   
    dispatch(rd,JSPNames.LOGIN_PAGE_USER);   
  }   
  public static void dispatch_noLogin_admin(RunData rd){   
    dispatch(rd,JSPNames.LOGIN_PAGE_ADMIN);   
  }   
  //û����ӦȨ��ʱҪ��ת��ҳ��,��ʱ�û�Ӧ���Ѿ���¼   
  public static void dispatch_noPermission(RunData rd){   
    if(AppSession.isUserLoginType(rd.getRequest())){   
      dispatch(rd,JSPNames.NOPERMISSION_PAGE_USER);   
    }else{   
      dispatch(rd,JSPNames.NOPERMISSION_PAGE_ADMIN);   
    }   
  }   
   
  //����ҳ��ָ��   
  public void messageDispatch(RunData rd){   
    messageDispatch(rd.getResponse(),rd.getRequest());   
  }   
  public void messageDispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request) {   
    messageDispatch(m_response, m_request, Msgs.MSGTYPE_SYS, Msgs.MSG_SYSBUSY,   
        "", "");   
  }   
   
  //��ת����Ϣҳ��   
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
   * ��ת����Ϣҳ��  
   * @param rd RunData  
   * @param msgType ��Ϣ����: user || admin  
   * @param msgStr Ҫ��ʾ����Ϣ  
   * @param forwordPage �����ť��Ҫ��ת����һ��ҳ��  
   * @param buttonName ��ť��ʾ������  
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
   
  //rootServlet==>service()==>doBusiness()(���������ַ�ʽ,����subServletֻ��ʵ�����е�һ��)   
  //����˳��:servlet==>service()==>doBefore()==>checkPermission()==>doBusiness()==>doAfter()   
  public void service(HttpServletRequest request,   
      HttpServletResponse response) throws ServletException, IOException {   
    //����ThreadLocal rd   
    RunData rd = RunData.getInstance(request, response);   
    doBefore(rd);   
    //û��Ȩ�޾�Ҫ��ת,service()�ͱ��뷵��   
    if(!checkPermission(rd)){   
      return;   
    }   
    rd.flush();   
    doBusiness(rd);   
   
    doBusiness(response, request);   
    doAfter(rd);   
  }   
   
  //ҳ���ض�����   
  public static void dispatch(RunData rd, String url) {   
    dispatch(rd.getResponse(),rd.getRequest(),url);   
  }   
  public static void dispatch(HttpServletResponse m_response,   
      HttpServletRequest m_request, String url) {   
    try {   
      RequestDispatcher dipatcher = m_request.getRequestDispatcher("/" +url);   
      Debug.debug("App will forward to: /" + url);   
      //ʹIE��CacheʧЧ,��ֹ�û�����   
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
   
  //ȡ���ύ��action�Ĳ���ת��Ϊ����,����:act='5'   
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
   
  //���µ�Properties getRequest��������RunData rd������   
  public Properties getRequest(HttpServletResponse m_response,   
      HttpServletRequest m_request) {   
    //-----�ӱ�ȡ��ϸ��Ϣ----   
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
   * �ӱ�ȡ�����е�parameters,���ҷ���properties��,һ��paramName��Ӧ��һ��String[]��ֵ  
   * @param isArray �ж����е�parameterȡ�����Ƿ�Ϊ����,������һ��<input name="txtName">  
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
   
      for(int i = 0; i  count; i++) { //ȡ����ת��,�ٷ���properties   
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


