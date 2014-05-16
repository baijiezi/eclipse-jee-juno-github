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
  //������32λΪȫ1������ֵ   
  public static final long ALL32ONE = 4294967295L;   
  //2��32�η�����ֵ   
  public static final long TOW32POWER = 4294967296L;   
   
  /**����������2�ļ��η����������2��������,����-1  
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
   
  /**����IP��ַ�ַ��������ֵ�ת����������֤  
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
   
  /**����IP��ַ���ֵ��ַ�����ת����������֤  
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
   
  /**��֤IP��ַ�κϷ���  
   * @param ��ʼ��������ַ  
   * @return  
   */   
  public static final boolean isValidSegString(String start, String end) {   
    long s = ipStringToLong(start);   
    long e = ipStringToLong(end);   
    return isValidSegLong(s, e);   
  }   
   
  /**��֤IP��ַ�κϷ���  
   * ��ʼ��������ַ��ֵС��ALLONE����0����ʼ��ַС�ڵ��ڽ�����ַ  
   * ����ʼ��ַ �C ������ַ + 1����2�������η�n  
   * ��ʼ��ַ>>>n = ������ַ>>>n  
   * @param ��ʼ��������ַ  
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
   * ѭ���ָ�  
   * @param  
   * @return  
   */   
  private static void repeatSplit(long sip, long eip, long low, long high,   
      Vector vc) {   
    long[] temp = new long[2];   
    //������С�ķָ��ַ�δ�С��ԭ����4��Ŀǰ��Ϊ0�������Է�һ����ַ   
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
   * ��ַ���  
   * ����Ϸ���ַ�Σ�������Ҫ��֤������ϵ,�������  
   * @param �Ӷο�ʼ��ַ���Ӷν�����ַ�����ο�ʼ��ַ�����ν�����ַ���Ƿ�����  
   * @return long[] ��ַ�ε� Vector  
   */   
  public static Vector split(long sip, long eip, long low, long high,   
      boolean sort) {   
    // �޸��ˣ��־� �޸����ڣ�2004-4-6   
    // ʹ�ܷ��䵥����ַ������˺����   
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
   * ��ַ�ϲ�,����Ƿ����������ϲ����Ƿ�Ϸ�  
   * @param �Ϸ��ĵ�ַ��VECTOR  
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
   * ����Ƿ��Ѿ�ָ�����ַ���  
   */   
  public static String getAssign(String in) {   
    if("0".equals(in)) {   
      return "δָ��";   
    } else if("1".equals(in)) {   
      return "��ָ��";   
    } else {   
      return null;   
    }   
  }   
   
  /**  
   * �������Ա�ʺ�״̬���ַ���  
   */   
  public static String getAdminStates(String in) {   
    if("0".equals(in)) {   
      return "����";   
    } else if("1".equals(in)) {   
      return "����";   
    } else if("2".equals(in)) {   
      return "����";   
    } else {   
      return null;   
    }   
  }   
   
  /**  
   * ��֤����IP��ַ�Ƿ�Ϸ�,��֤�����ǡ�.��������  
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
   * ����LONG�͵�ַ�Σ����ص�ַ���ַ������� - ���ӡ�Ҫ���ַ�κϷ���ֻ���ڴ����ݿ���ȡֵת��  
   */   
  public static String getSegString(long startip, long endip) {   
    return(IpUtils.ipLongToString(startip) + " - " +   
        IpUtils.ipLongToString(endip));   
  }   
   
  /**  
   * ����STRING�͵�ַ�Σ����ص�ַ���ַ������� - ���ӡ�Ҫ���ַ�κϷ���ֻ���ڴ����ݿ���ȡֵת��  
   */   
  public static String getSegString(String startip, String endip) {   
    return(startip + " - " + endip);   
  }   
   
  /**  
   * ����ַ����Ƿ�ȫ���������  
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
   * ��֤�����ַ�������ȷ��  
   * @param �����ַ���,����ֵĵ�ַ��(long)  
   * @return Vector ��ȷ���ر���ֽ����ַ��Vector�������  
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
    //�ҵ���С�����ֺ�����λ��   
    int num = scales[0];   
    int pos = 0;   
    for(int i = 0; i  size; i++) {   
      if(num > scales[i]) {   
        num = scales[i];   
        pos = i;   
      }   
    }   
    //��������/������ ������2�������β���������   
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
    //�ֶ���֤   
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
   * ���뱻���ַ�Σ��ӵ�ַ��,�Ϸ���ַ��  
   * ���ز�ֽ��  
   */   
  public static Vector splitSubIps(long sip, long eip, long subsip, long subeip) {   
    if((sip > subsip) || (sip > subeip) || (eip  subsip) || (eip  subeip)) {   
      return null;   
    }   
    return IpUtils.split(subsip, subeip, sip, eip, true);   
  }   
   
  /**  
   * ��������Ȩ�ޣ�0Ϊ����Ȩ�� 1Ϊ����Ȩ��  
   */   
  public static String getNetStatus(String net) {   
    String netStatus = null;   
    if(net.equals("1")) {   
      netStatus = "����Ȩ��";   
    } else {   
      netStatus = "����Ȩ��";   
    }   
    return netStatus;   
  }   
   
  /**  
   * �����Ƿ�·�ɣ�0Ϊ��1Ϊ��  
   */   
  public static String getRouterStatus(String router) {   
    String routerStatus = null;   
    if(router.equals("1")) {   
      routerStatus = "��";   
    } else {   
      routerStatus = "��";   
    }   
    return routerStatus;   
  }   
   
  /**  
   * ע�� ״̬  
   */   
  public static String getRegisterStates(String str) {   
    String states = null;   
    if("1".equals(str)) {   
      states = "ע�����";   
    } else if("2".equals(str)) {   
      states = "�ȴ�ע��";   
    } else if("3".equals(str)) {   
      states = "ע��ʧ��";   
    } else if("4".equals(str)) {   
      states = "�ȴ��ظ�";   
    } else {   
      states = "��ȷ������";   
    }   
    return states;   
  }   
   
  /**  
   * ר���ڴ�����ѡ���е�����ת������������ѡ��ĵط����øú������������������ʾ��ʽ  
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
   * �õ���������  
   */   
  public static String getAppType(String str) {   
    String states = null;   
    if("1".equals(str)) {   
      states = "��������";   
    } else if("2".equals(str)) {   
      states = "��������";   
    } else if("3".equals(str)) {   
      states = "��ַ��������";   
    } else if("4".equals(str)) {   
      states = "���ϸ�������";   
    } else {   
      states = "��������";   
    }   
    return states;   
  }   
   
  /**  
   * �õ�����״̬  
   */   
  public static String getAppState(String str) {   
    String states = null;   
    if("1".equals(str)) {   
      states = "�ȴ�ҵ�����";   
    } else if("2".equals(str)) {   
      states = "�ȴ���˷���";   
    } else if("3".equals(str)) {   
      states = "�ȴ��������";   
    } else if("5".equals(str)) {   
      states = "�����鵵";   
    } else if("6".equals(str)) {   
      states = "�˵��鵵";   
    } else if("7".equals(str)) {   
      states = "�����鵵";   
    } else if("10".equals(str)) {   
      states = "ҵ������˵�";   
    } else if("20".equals(str)) {   
      states = "��˷����˵�";   
    } else {   
      states = "����״̬";   
    }   
    return states;   
  }   
}  


