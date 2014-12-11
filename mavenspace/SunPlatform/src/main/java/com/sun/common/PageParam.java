package com.sun.common;

import java.io.Serializable;

/**
 * 作用:分页参数传递工具类
 * 作者:caolei
 * 日期:2014-03-14
 * @author Administrator
 *
 */
public class PageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int pagenum;	//当前页数
	public int numPerpage;//每页记录数
	
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
