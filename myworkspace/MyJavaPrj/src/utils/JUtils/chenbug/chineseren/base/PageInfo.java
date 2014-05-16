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
   *ȡ�����м�¼������  
   * */   
  public int getTotalRecords() {   
    return this.totalRecord;   
  }   
   
  /**  
   *ȡ�����е�ҳ��  
   * */   
  public int getToalPages() {   
    return (totalRecord + pageSize - 1) / this.pageSize;   
  }   
   
  /**  
   *ȡ�õ�ǰҳ����  
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
   *ȡ����һҳ�ĵ�һ����¼  
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
   *ȡ����ҳ�ĵ�һ����¼  
   * */   
  public int getFirstPage() {   
    return 1;   
  }   
   
  /**  
   *ȡ����һҳ�ĵ�һ����¼  
   * */   
  public int getNextPage() {   
    return this.startIndex + this.pageSize;   
  }   
   
  /**  
   �õ���ǰ����  
   */   
  public int getCurrentIndex() {   
    return this.startIndex;   
  }   
   
  /**  
   *ȡ�����ҳ�ĵ�һ����¼  
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
   *�Ƿ�����һҳ  
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
   *�Ƿ�����һҳ  
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
    sb.append("��").append(this.getTotalRecords()).append("����¼ ��").append(this.   
        getCurrentPage()).append("/").append(this.getToalPages()).append("ҳ(ÿҳ").   
        append(this.getPageSize()).append("��)");   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getFirstPage()).append("\"> ��ҳ </a>");   
    }   
    else {   
      sb.append(" ��ҳ ");   
    }   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getPreviousPage()).append("\"> ��ҳ </a>");   
    }else {   
      sb.append(" ��ҳ ");   
    }   
    if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getNextPage()).append("\"> ��ҳ </a>");   
    }else {   
      sb.append(" ��ҳ ");   
    }if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getLastPage()).append("\"> ���ҳ </a>");   
    }   
    else {   
      sb.append(" ���ҳ ");   
    }   
    return sb.toString();   
   
  }   
  /**  
   * url���ӣ�"BuyHandler?act=1";  
   * @param url  
   * @param rd  
   * @return  
   */   
  public String getPageHtml(String url,RunData rd) {//url���ӣ�"BuyHandler?act=1";   
    url += rd.getPageUrl();   
    StringBuffer sb = new StringBuffer();   
    sb.append("��").append(this.getTotalRecords()).append("����¼ ��").append(this.   
        getCurrentPage()).append("/").append(this.getToalPages()).append("ҳ(ÿҳ").   
        append(this.getPageSize()).append("��)");   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getFirstPage()).append("\"> ��ҳ </a>");   
    }   
    else {   
      sb.append(" ��ҳ ");   
    }   
   
    if (this.hasPreviousPage()) {   
      sb.append("<a href=\"").append(JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getPreviousPage()).append("\"> ��ҳ </a>");   
    }   
    else {   
      sb.append(" ��ҳ ");   
    }   
    if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getNextPage()).append("\"> ��ҳ </a>");   
    }   
    else {   
      sb.append(" ��ҳ ");   
    }   
    if (this.hasNextPage()) {   
      sb.append("<a href=\"" + JSPNames.ROOT_URI + url).append("&index=").   
          append(this.getLastPage()).append("\"> ���ҳ </a>");   
    }   
    else {   
      sb.append(" ���ҳ ");   
    }   
    return sb.toString();   
  }   
   
}  



