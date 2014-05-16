package utils.JUtils.chenbug.chineseren.base;
   
public class PageInfo {   
  private int startIndex;   
  private int totalRecord;   
  //private int currentPage;   
  // private int totalPage;   
  private int pageSize;   
   
  public PageInfo() {   
  }   
   
  public PageInfo(int index) {   
    this.startIndex = index;   
  }   
   
  public void setStartIndex(int startIndex) {   
    this.startIndex = startIndex;   
  }   
   
  public void setTotalRecord(int totalRecord) {   
    this.totalRecord = totalRecord;   
  }   
   
  public void setPageSize(int pageSize) {   
    this.pageSize = pageSize;   
  }   
   
  /**  
   *取得所有记录的条数  
   * */   
  public int getTotalRecords() {   
    return this.totalRecord;   
  }   
   
  /**  
   *取得所有的页数  
   * */   
  public int getToalPages() {   
    return (totalRecord + pageSize - 1) / this.pageSize;   
  }   
   
  /**  
   *取得当前页码数  
   * */   
  public int getCurrentPage() {   
    if (this.totalRecord == 0) {   
      return 0;   
    }   
    else {   
      return (startIndex + pageSize - 1) / this.pageSize;   
    }   
  }   
   
  /**  
   *取得上一页的第一条记录  
   * */   
  public int getPreviousPage() {   
    if ( (this.startIndex - this.pageSize) >= 1) {   
      return (this.startIndex - this.pageSize);   
    }   
    else {   
      return 1;   
    }   
  }   
   
  /**  
   *取得首页的第一条记录  
   * */   
  public int getFirstPage() {   
    return 1;   
  }   
   
  /**  
   *取得下一页的第一条记录  
   * */   
  public int getNextPage() {   
    return this.startIndex + this.pageSize;   
  }   
   
  /**  
   得到当前索引  
   */   
  public int getCurrentIndex() {   
    return this.startIndex;   
  }   
   
  /**  
   *取得最后页的第一条记录  
   * */   
  public int getLastPage() {   
    if (this.totalRecord % this.pageSize == 0) {   
      return this.totalRecord - this.pageSize + 1;   
    }   
    else {   
      return this.totalRecord - (this.totalRecord % this.pageSize) + 1;   
    }   
  }   
   
  /**  
   *是否有上一页  
   *  
   **/   
   
  public boolean hasPreviousPage() {   
    if (this.startIndex - this.pageSize = 0) {   
      return false;   
    }   
    else {   
      return true;   
    }   
  }   
   
  /**  
   *是否有下一页  
   * */   
   
  public boolean hasNextPage() {   
    if (this.startIndex + this.pageSize - 1 >= this.totalRecord) {   
      return false;   
    }   
    else {   
      return true;   
    }   
   
  }   
   
  public int getPageSize() {   
    return this.pageSize;   
  }   
   
  public String getPageHtml(String url) {   
    String result = "";   
    StringBuffer sb = new StringBuffer();   
    sb.append("共").append(this.getTotalRecords()).append("条记录 第").append(this.   
        getCurrentPage()).append("/").append(this.getToalPages()).append("页(每页").   
        append(this.getPageSize()).append("条)");   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getFirstPage()).append("\"> 首页 </a>");   
    }   
    else {   
      sb.append(" 首页 ");   
    }   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getPreviousPage()).append("\"> 上页 </a>");   
    }else {   
      sb.append(" 上页 ");   
    }   
    if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getNextPage()).append("\"> 下页 </a>");   
    }else {   
      sb.append(" 下页 ");   
    }if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getLastPage()).append("\"> 最后页 </a>");   
    }   
    else {   
      sb.append(" 最后页 ");   
    }   
    return sb.toString();   
   
  }   
  /**  
   * url例子："BuyHandler?act=1";  
   * @param url  
   * @param rd  
   * @return  
   */   
  public String getPageHtml(String url,RunData rd) {//url例子："BuyHandler?act=1";   
    url += rd.getPageUrl();   
    StringBuffer sb = new StringBuffer();   
    sb.append("共").append(this.getTotalRecords()).append("条记录 第").append(this.   
        getCurrentPage()).append("/").append(this.getToalPages()).append("页(每页").   
        append(this.getPageSize()).append("条)");   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getFirstPage()).append("\"> 首页 </a>");   
    }   
    else {   
      sb.append(" 首页 ");   
    }   
   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getPreviousPage()).append("\"> 上页 </a>");   
    }   
    else {   
      sb.append(" 上页 ");   
    }   
    if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getNextPage()).append("\"> 下页 </a>");   
    }   
    else {   
      sb.append(" 下页 ");   
    }   
    if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getLastPage()).append("\"> 最后页 </a>");   
    }   
    else {   
      sb.append(" 最后页 ");   
    }   
    return sb.toString();   
  }   
   
}  



