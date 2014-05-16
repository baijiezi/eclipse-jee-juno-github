package utils.JUtils.chenbug.chineseren;
  
import org.apache.torque.util.Criteria;  
  
/** 
 * SimpleCriteria is a simple case of the more powerful Criteria 
 * Object. 
 */  
  
public class SimpleCrit  
    extends Criteria {  
  /** currently used as DEFAULT_CAPACITY in Criteria is private */  
  private static final int DEFAULT_CAPACITY = 10;  
  
  /* 
   * Constructor 
   */  
  public SimpleCrit() {  
    super(DEFAULT_CAPACITY);  
  }  
  public SimpleCrit(boolean isDistinc,boolean isIgnoreCase) {  
    super(DEFAULT_CAPACITY);  
    if(isDistinc){//select����а���DISTINCT  
      this.setDistinct();  
    }  
    if(isIgnoreCase){//select�����where�Ƿ���Դ�Сд  
      this.setIgnoreCase(isIgnoreCase);  
    }  
  }  
  
  //�ַ����Ƚ�ʱ�Ƿ���Դ�Сд  
  //criteria.getCriterion(InvoicePeer.TABLE_NAME, searchField).setIgnoreCase(true);  
  public SimpleCrit setIgnoreCase(String tablename, String columnname, boolean isIgnorCase) {  
    this.getCriterion(tablename, columnname).setIgnoreCase(isIgnorCase);  
    return this;  
  }  
  public SimpleCrit setIgnoreCase(String tablename, String columnname) {  
    return this.setIgnoreCase(tablename, columnname,true);  
  }  
  public SimpleCrit setIgnoreCase(String columnname, boolean isIgnorCase) {  
    this.getCriterion(columnname).setIgnoreCase(isIgnorCase);  
    return this;  
  }  
  public SimpleCrit setIgnoreCase(String columnname) {  
    return this.setIgnoreCase(columnname,true);  
  }  
  
  /* 
   * Represents the Greater Than in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   * @param columnvalue the column value to be compared against 
   */  
  public SimpleCrit addGreaterThan(String columnname, Object columnvalue) {  
    super.add(columnname, columnvalue, Criteria.GREATER_THAN);  
    return this;  
  }  
  public SimpleCrit addGreaterEqual(String columnname, Object columnvalue) {  
    super.add(columnname, columnvalue, Criteria.GREATER_EQUAL);  
    return this;  
  }  
  
  /* 
   * Represents the Less Than in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   * @param columnvalue the column value to be compared against 
   */  
  public SimpleCrit addLessThan(String columnname, Object columnvalue) {  
    super.add(columnname, columnvalue, Criteria.LESS_THAN);  
    return this;  
  }  
  public SimpleCrit addLessEqual(String columnname, Object columnvalue) {  
    super.add(columnname, columnvalue, Criteria.LESS_EQUAL);  
    return this;  
  }  
  
  /* 
   * Represents the Not_Equal in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   * @param columnvalue the column value to be compared against 
   */  
  public SimpleCrit addNotEqual(String columnname, Object columnvalue) {  
    super.add(columnname, columnvalue, Criteria.NOT_EQUAL);  
    return this;  
  }  
  
  /* 
   * Represents the Is NULL in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   */  
  public SimpleCrit addIsNull(String columnname) {  
    super.add(columnname, (Object)(columnname + " is NULL"), Criteria.CUSTOM);  
    return this;  
  }  
  public SimpleCrit addIsNotNull(String columnname) {  
    super.add(columnname, (Object)(columnname + " is not NULL"), Criteria.CUSTOM);  
    return this;  
  }  
  
  /* 
   * Represents the Between(min,max) in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   */  
  public SimpleCrit addBetween(String columnname, Object min, Object max) {  
    super.add(columnname, min, Criteria.GREATER_THAN);  
    Criterion criterion = this.getCriterion(columnname);  
    criterion.and(super.getNewCriterion(columnname,max,Criteria.LESS_EQUAL));  
    return this;  
  }  
  
  /* 
   * Represents the Like in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   */  
  public SimpleCrit addLike(String columnname, String condition,  
      String delimiter) {  
    this.add(columnname, (Object)(SimpleCrit.genLikeSQL(condition, delimiter)),  
        Criteria.LIKE);  
    return this;  
  }  
  public SimpleCrit addLike(String columnname, String condition){  
    return this.addLike(columnname, condition,SimpleCrit.DELIMITER_SPACE);  
  }  
  //�ؼ��ʲ�ѯʱ�����õ��ķָ���,Ĭ���ǿո�" "  
  public static final String DELIMITER_SPACE = " ";  
  public static final String DELIMITER_COMMA = ",";  
  public static final String DELIMITER_SEMI = ";";  
  public static final String DELIMITER_AMP = "&";  
  public static final String DELIMITER_WELL = "#";  
  
  /** 
   * ����ѯ����condition���տո�ָ��γ�like��� 
   * @param condition Sring ����:"a  b c " 
   * @param delimiter String ����:" " 
   * @return String Like SQL ����:"%a%b%c%" 
   */  
  public static String genLikeSQL(String condition, String delimiter) {  
    String like = "%";  
    String like2 = "_";  
    if(null == condition || "".equals(condition)) {  
      return like;  
    }  
  
    if(null == delimiter){  
      delimiter = SimpleCrit.DELIMITER_SPACE;  
    }  
    Debug.debug("SimpleCrit.genLikeSQL() condition is: " + condition);  
    //�滻condition�е�"%"��"_"����  
    condition = StringUtils.replace(condition,like,"");  
    condition = StringUtils.replace(condition,like2,"");  
    //��ʼ�ָ�  
    StringBuffer likeSql = new StringBuffer();  
    likeSql.append(like); //"%"  
    String[] strArray = StringUtils.split(condition.trim(), delimiter);  
    for(int i = 0; i  strArray.length; i++) {  
      String tmp = strArray[i];  
      if(null != tmp && !"".equals(tmp) && !delimiter.equals(tmp)) {  
        likeSql.append(tmp); //"a"  
        likeSql.append(like); //"%"  
      }  
    }  
  
    String ret = likeSql.toString();  
    Debug.debug("SimpleCrit.genLikeSQL() likeSql is: " + ret);  
    return ret;  
  }  
  
  /* 
   * Represents the Custom in the WHERE 
   * clause of an SQL Statement 
   * 
   * @param columnname the column name 
   */  
  public SimpleCrit addCustom(String columnname, String where) {  
    this.add(columnname, (Object)where,Criteria.CUSTOM);  
    return this;  
  }  
  
  /* 
   * Using Criterion to use a Column twice in a Criteria 
   * criteria.getNewCriterion( 
                             criterion.getTable(), 
                             criterion.getColumn(), 
                             new Integer(5000), 
                             Criteria.LESS_EQUAL ) 
 
   * @param columnname the column name 
   */  
  public SimpleCrit andColumn(String columnname, Criteria.Criterion newCriterion) {  
    Criteria.Criterion criterion = this.getCriterion(columnname);  
    criterion.and(newCriterion);  
    return this;  
  }  
}  


