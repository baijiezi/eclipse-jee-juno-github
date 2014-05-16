package utils.JUtils.chenbug.chineseren;
   
/**  
 * Title:  
 * Description:  
 * Copyright:    Copyright (c) 2003  
 * Company:  
 * @author Yuanlong Li  
 * @version 1.0  
 */   
   
import java.text.*;   
import java.util.*;   
   
public class IpUtils {   
  //二进制32位为全1的整数值   
  public static final long ALL32ONE = 4294967295L;   
  //2的32次方整数值   
  public static final long TOW32POWER = 4294967296L;   
   
  /**计算数字是2的几次方，如果不是2的整数次,返回-1  
   * @param  
   * @return  
   */   
  public static int twoPower(long num) {   
   
    if(num  1 || num > TOW32POWER) {   
      return -1;   
    }   
    int i = 0;   
    int power = 0;   
    for(i = 0; i  32; i++) {   
      if((num & 1) == 1) {   
        break;   
      } else {   
        power++;   
        num = num >> 1;   
      }   
    }   
    if((num >> 1) == 0) {   
      return power;   
    } else {   
      return -1;   
    }   
  }   
   
  /**单个IP地址字符串到数字的转换，不做验证  
   * @param  
   * @return  
   */   
  public static final long ipStringToLong(String source) {   
    long temp = 0;   
    int pos = 0;   
    String cur = "";   
    pos = source.indexOf(".", 0);   
    while(pos != -1) {   
      cur = source.substring(0, pos);   
      source = source.substring(pos + 1, source.length());   
      temp = (temp < 8) | Long.parseLong(cur);   
      pos = source.indexOf(".", 0);   
    }   
    temp = (temp < 8) | Long.parseLong(source);   
    return temp;   
  }   
   
  /**单个IP地址数字到字符串的转换，不做验证  
   * @param  
   * @return  
   */   
  public static final String ipLongToString(long source) {   
    long Mask = 255;   
    long result = 0;   
    result = source & Mask;   
    String temp = String.valueOf(result);   
    for(int i = 0; i  3; i++) {   
      source = source >> 8;   
      result = source & Mask;   
      temp = String.valueOf(result) + "." + temp;   
    }   
    return temp.toString();   
  }   
   
  /**验证IP地址段合法性  
   * @param 开始、结束地址  
   * @return  
   */   
  public static final boolean isValidSegString(String start, String end) {   
    long s = ipStringToLong(start);   
    long e = ipStringToLong(end);   
    return isValidSegLong(s, e);   
  }   
   
  /**验证IP地址段合法性  
   * 开始、结束地址数值小于ALLONE大于0；开始地址小于等于结束地址  
   * （开始地址 – 结束地址 + 1）是2的整数次方n  
   * 开始地址>>>n = 结束地址>>>n  
   * @param 开始、结束地址  
   * @return  
   */   
  public static final boolean isValidSegLong(long start, long end) {   
    if((start > end) ||   
        (start > IpUtils.ALL32ONE) || (end > IpUtils.ALL32ONE) ||   
        (end  0) || (start  0)) {   
      return false;   
    }   
    int n = 0;   
    n = IpUtils.twoPower(end - start + 1);   
    if( -1 == n) {   
      return false;   
    }   
    if((start >> n) == (end >> n)) {   
      return true;   
    } else {   
      return false;   
    }   
  }   
   
  /**  
   * 循环分割  
   * @param  
   * @return  
   */   
  private static void repeatSplit(long sip, long eip, long low, long high,   
      Vector vc) {   
    long[] temp = new long[2];   
    //控制最小的分割地址段大小，原来是4，目前改为0，即可以分一个地址   
    if((high - low) == 0 || ((sip == low) && (eip == high))) {   
      temp[0] = low;   
      temp[1] = high;   
      vc.addElement(temp);   
      return;   
    }   
    long mid = (low + high - 1) / 2;   
   
    if(mid  sip) {   
      temp[0] = low;   
      temp[1] = mid;   
      vc.addElement(temp);   
      repeatSplit(sip, eip, mid + 1, high, vc);   
    } else if(mid >= eip) {   
      temp[0] = mid + 1;   
      temp[1] = high;   
      vc.addElement(temp);   
      repeatSplit(sip, eip, low, mid, vc);   
    } else {   
      vc = null;   
      return;   
    }   
  }   
   
  /**  
   * 地址拆分  
   * 输入合法地址段，但还需要验证包含关系,输出升序  
   * @param 子段开始地址，子段结束地址，基段开始地址，基段结束地址，是否排序  
   * @return long[] 地址段的 Vector  
   */   
  public static Vector split(long sip, long eip, long low, long high,   
      boolean sort) {   
    // 修改人：林静 修改日期：2004-4-6   
    // 使能分配单个地址，添加了恒等于   
    //  if ( !((low=sip && sip<high)&&(low<eip && eip=high)) ){   
    if(!((low = sip && sip = high) && (low = eip && eip = high))) {   
      return null;   
    }   
    Vector vc = new Vector();   
    repeatSplit(sip, eip, low, high, vc);   
    if(vc == null) {   
      return null;   
    } else if(!sort) {   
      return vc;   
    } else {   
      int size = vc.size();   
      int j = 0;   
      long[] temp = new long[size * 2];   
      long[] tempr = new long[2];   
   
      for(j = 0; j  size; j++) {   
        tempr = (long[])vc.elementAt(j);   
        temp[2 * j] = tempr[0];   
        temp[2 * j + 1] = tempr[1];   
      }   
      vc = null;   
      vc = new Vector();   
      Arrays.sort(temp);   
      for(j = 0; j  size; j++) {   
        tempr = new long[2];   
        tempr[0] = temp[2 * j];   
        tempr[1] = temp[2 * j + 1];   
        vc.addElement(tempr);   
      }   
      return vc;   
    }   
  }   
   
  /**  
   * 地址合并,检查是否连续，检查合并后是否合法  
   * @param 合法的地址段VECTOR  
   * @return  
   */   
  public static long[] unit(Vector vc) {   
    if(vc == null) {   
      return null;   
    }   
    int size = vc.size();   
    int n = size * 2;   
    long[] ips = new long[n];   
    long[] temp = null;   
    int i = 0;   
    for(i = 0; i  size; i++) {   
      temp = (long[])vc.elementAt(i);   
      if(temp == null) {   
        return null;   
      }   
      ips[2 * i] = temp[0];   
      ips[2 * i + 1] = temp[1];   
    }   
    Arrays.sort(ips);   
    n--;   
    for(i = 1; i  n; i = i + 2) {   
      if(ips[i] + 1 != ips[i + 1]) {   
        return null;   
      }   
    }   
    temp[0] = ips[0];   
    temp[1] = ips[n];   
    ips = null;   
    if(IpUtils.isValidSegLong(temp[0], temp[1])) {   
      return temp;   
    } else {   
      return null;   
    }   
  }   
   
  /**  
   * 输出是否已经指定的字符串  
   */   
  public static String getAssign(String in) {   
    if("0".equals(in)) {   
      return "未指定";   
    } else if("1".equals(in)) {   
      return "已指定";   
    } else {   
      return null;   
    }   
  }   
   
  /**  
   * 输出管理员帐号状态的字符串  
   */   
  public static String getAdminStates(String in) {   
    if("0".equals(in)) {   
      return "锁定";   
    } else if("1".equals(in)) {   
      return "正常";   
    } else if("2".equals(in)) {   
      return "销户";   
    } else {   
      return null;   
    }   
  }   
   
  /**  
   * 验证单个IP地址是否合法,保证输入是“.”和数字  
   */   
  public static boolean isValidIp(String ip) {   
    StringTokenizer st = new StringTokenizer(ip, ".");   
    int i = 0;   
    for(i = 0; st.hasMoreTokens(); i++) {   
      int n = Integer.parseInt(st.nextToken());   
      if(n > 255 || n  0) {   
        return false;   
      }   
    }   
    if(i != 4) {   
      return false;   
    } else {   
      return true;   
    }   
  }   
   
  /**  
   * 输入LONG型地址段，返回地址段字符串，用 - 连接。要求地址段合法。只用于从数据库中取值转换  
   */   
  public static String getSegString(long startip, long endip) {   
    return(IpUtils.ipLongToString(startip) + " - " +   
        IpUtils.ipLongToString(endip));   
  }   
   
  /**  
   * 输入STRING型地址段，返回地址段字符串，用 - 连接。要求地址段合法。只用于从数据库中取值转换  
   */   
  public static String getSegString(String startip, String endip) {   
    return(startip + " - " + endip);   
  }   
   
  /**  
   * 检查字符串是否全由数字组成  
   */   
  public static boolean isDigit(String str) {   
    int size = str.length();   
    for(int i = 0; i  size; i++) {   
      if(!Character.isDigit(str.charAt(i))) {   
        return false;   
      }   
    }   
    return true;   
  }   
   
  /**  
   * 验证比例字符串的正确性  
   * @param 比例字符串,被拆分的地址段(long)  
   * @return Vector 正确返回被拆分结果地址段Vector，否则空  
   */   
  public static Vector splitScale(String scale, long sip, long eip) {   
    StringTokenizer st = new StringTokenizer(scale, ":");   
    int size = st.countTokens();   
    int[] scales = new int[size];   
    int sum = 0;   
    for(int i = 0; i  size; i++) {   
      String temp = st.nextToken();   
      if(IpUtils.isDigit(temp)) {   
        scales[i] = Integer.parseInt(temp);   
        sum += scales[i];   
      } else {   
        return null;   
      }   
    }   
    //找到最小的数字和它的位置   
    int num = scales[0];   
    int pos = 0;   
    for(int i = 0; i  size; i++) {   
      if(num > scales[i]) {   
        num = scales[i];   
        pos = i;   
      }   
    }   
    //比例和数/比例数 必须是2的整数次并且无余数   
    for(int i = 0; i  size; i++) {   
      if(sum % scales[i] != 0) {   
        return null;   
      }   
      if( -1 == IpUtils.twoPower(sum / scales[i])) {   
        return null;   
      }   
    }   
   
    long share = 0;   
    if((eip - sip + 1) % sum == 0) {   
      share = (eip - sip + 1) / sum;   
    } else {   
      return null;   
    }   
    //分段验证   
    Vector vc = new Vector();   
    long s = sip;   
    long e = sip - 1;   
    for(int i = 0; i  size; i++) {   
      s = e + 1;   
      e = s + share * scales[i] - 1;   
      if(IpUtils.isValidSegLong(s, e)) {   
        long[] ips = new long[2];   
        ips[0] = s;   
        ips[1] = e;   
        vc.addElement(ips);   
      } else {   
        return null;   
      }   
    }   
    return vc;   
  }   
   
  /**  
   * 输入被拆地址段，子地址段,合法地址段  
   * 返回拆分结果  
   */   
  public static Vector splitSubIps(long sip, long eip, long subsip, long subeip) {   
    if((sip > subsip) || (sip > subeip) || (eip  subsip) || (eip  subeip)) {   
      return null;   
    }   
    return IpUtils.split(subsip, subeip, sip, eip, true);   
  }   
   
  /**  
   * 关联网络权限，0为国内权限 1为国际权限  
   */   
  public static String getNetStatus(String net) {   
    String netStatus = null;   
    if(net.equals("1")) {   
      netStatus = "国际权限";   
    } else {   
      netStatus = "国内权限";   
    }   
    return netStatus;   
  }   
   
  /**  
   * 关联是否路由，0为否，1为是  
   */   
  public static String getRouterStatus(String router) {   
    String routerStatus = null;   
    if(router.equals("1")) {   
      routerStatus = "是";   
    } else {   
      routerStatus = "否";   
    }   
    return routerStatus;   
  }   
   
  /**  
   * 注册 状态  
   */   
  public static String getRegisterStates(String str) {   
    String states = null;   
    if("1".equals(str)) {   
      states = "注册完成";   
    } else if("2".equals(str)) {   
      states = "等待注册";   
    } else if("3".equals(str)) {   
      states = "注册失败";   
    } else if("4".equals(str)) {   
      states = "等待回复";   
    } else {   
      states = "不确定错误";   
    }   
    return states;   
  }   
   
  /**  
   * 专用于从日历选择中的日期转换，所有日历选择的地方都用该函数，方便更换日期显示方式  
   */   
  public static Date convertDate(String dd) {   
    DateFormat df = null;   
    try {   
      df = new SimpleDateFormat("yyyyMMdd");   
      return df.parse(dd);   
    } catch(Exception e) {   
      e.printStackTrace();   
    }   
    return null;   
  }   
   
  /**  
   * 得到申请类型  
   */   
  public static String getAppType(String str) {   
    String states = null;   
    if("1".equals(str)) {   
      states = "开户申请";   
    } else if("2".equals(str)) {   
      states = "销户申请";   
    } else if("3".equals(str)) {   
      states = "地址更改申请";   
    } else if("4".equals(str)) {   
      states = "资料更改申请";   
    } else {   
      states = "错误申请";   
    }   
    return states;   
  }   
   
  /**  
   * 得到申请状态  
   */   
  public static String getAppState(String str) {   
    String states = null;   
    if("1".equals(str)) {   
      states = "等待业务审核";   
    } else if("2".equals(str)) {   
      states = "等待审核分配";   
    } else if("3".equals(str)) {   
      states = "等待窗口审核";   
    } else if("5".equals(str)) {   
      states = "竣工归档";   
    } else if("6".equals(str)) {   
      states = "退单归档";   
    } else if("7".equals(str)) {   
      states = "竣工归档";   
    } else if("10".equals(str)) {   
      states = "业务审核退单";   
    } else if("20".equals(str)) {   
      states = "审核分配退单";   
    } else {   
      states = "错误状态";   
    }   
    return states;   
  }   
}  


