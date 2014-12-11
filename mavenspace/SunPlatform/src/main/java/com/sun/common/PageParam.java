package com.sun.common;

import java.io.Serializable;

/**
 * ����:��ҳ�������ݹ�����
 * ����:caolei
 * ����:2014-03-14
 * @author Administrator
 *
 */
public class PageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int pagenum;	//��ǰҳ��
	public int numPerpage;//ÿҳ��¼��
	
	public PageParam(int pageNum,int numPerPage){
		super();
		this.pagenum=pageNum;
		this.numPerpage=numPerPage;
	}
	
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getNumPerpage() {
		return numPerpage;
	}
	public void setNumPerpage(int numPerpage) {
		this.numPerpage = numPerpage;
	}
}
