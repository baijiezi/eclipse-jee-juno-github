package com.sun309.jdbc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sun309.util.HttpService;

public class Pager
{
	private Logger log = Logger.getLogger(Pager.class);
	private String pageVar = null; // 页的参数名称
	private int perPageRecord = 0; // 每页的记录数
	private int showPageNumber = 0; // 一次工具条上显示多少页的码数
	private int currentPage = 0; // 当前页
	private int totalRecord = 0; // 总记录数
	private String tool = ""; // 工具条
	private int totalPage = 0; // 总页数
	private List<Map<String, Object>> list = null;
	private int key;
	
	public String pager(String sql, HttpServletRequest request)
	{
		// 工具条
		String toolBar = "";

		// 设置默认值
		if (pageVar == null || "".equals(pageVar))
		{
			pageVar = "page";
		}
		if (perPageRecord == 0)
		{
			perPageRecord = 15;
		}
		if (showPageNumber == 0)
		{
			showPageNumber = 5;
		}

		// 设置页码(小于0)
		int page = 0;
		try
		{
			if (request.getParameter(pageVar) != null && Integer.parseInt(request.getParameter(pageVar)) > 1)
			{
				page = Integer.parseInt(request.getParameter(pageVar));
			}
			else
			{
				page = 1;
			}
		}
		catch (Exception e)
		{
			page = 1;
		}
		setCurrentPage(page);

		// 统计总记录数
		JdbcDaoService service = JdbcDaoFactory.create();
		List<Map<String, Object>> data = service.select(sql.toString());
		setTotalRecord(data.size());

		// 设置总页数
		// int temp = (int) (Math.floor(getTotalRecord() / perPageRecord));
		if ((getTotalRecord() % perPageRecord == 0) && (getTotalRecord() > 0))
		{
			setTotalPage((int) (Math.floor(getTotalRecord() / perPageRecord)));
		}
		else
		{
			setTotalPage((int) (Math.floor(getTotalRecord() / perPageRecord)) + 1);
		}

		// 设置页码 (大于最大页)
		try
		{
			if (Integer.parseInt(request.getParameter(pageVar)) >= getTotalPage())
			{
				page = getTotalPage();
			}
		}
		catch (Exception e)
		{
			page = 1;
		}
		key = (page - 1) * perPageRecord;
		sql = sql + " limit " + key + ", " + perPageRecord;

		String url = "?";
		try
		{
			url += (!"".equals(HttpService.getUrlParam(request, pageVar))) ? HttpService.getUrlParam(request, pageVar) + "&" : "";
		}
		catch (Exception e)
		{
			url += "";
		}
		String path = request.getRequestURL().toString() + url;

		toolBar += "共<b>" + getTotalRecord() + "</b>条记录&nbsp;&nbsp;每页<b>" + perPageRecord + "</b>条&nbsp;&nbsp;第<b>" + page + "/" + getTotalPage() + "</b>页&nbsp;&nbsp;";
		toolBar += (page > 1) ? "<a href='" + path + pageVar + "=1" + "'>首页</a>&nbsp;" : "首页&nbsp;";
		toolBar += (page > 1) ? "<a href='" + path + pageVar + "=" + (page - 1) + "'>上一页</a>&nbsp;" : "上一页&nbsp;";
		toolBar += (page < getTotalPage()) ? "<a href='" + path + pageVar + "=" + (page + 1) + "'>下一页</a>&nbsp;" : "下一页&nbsp;";
		toolBar += (page < getTotalPage()) ? "<a href='" + path + pageVar + "=" + getTotalPage() + "'>末页</a>" : "末页";
		toolBar += "&nbsp;&nbsp;跳到<input type='text' name='page' size='5' value='" + page + "' id='page' />页&nbsp;<input type=\"button\" value=\"GO...\" onclick=\"javascript:window.location.href='" + path + pageVar + "=' + document.getElementById(\'page\').value\"  />";
		setTool(toolBar);
		this.showData(data);
		return sql;
	}
	
	private void showData(List<Map<String, Object>> data)
	{
        for(int i = key; i <= (key + getPerPageRecord()-1); i++)
        {
            Map<String, Object> map = data.get(i);
            log.debug(map);
            list.add(map);
        }
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public String getPageVar()
	{
		return pageVar;
	}

	public void setPageVar(String pageVar)
	{
		this.pageVar = pageVar;
	}

	public int getPerPageRecord()
	{
		return perPageRecord;
	}

	public void setPerPageRecord(int perPageRecord)
	{
		this.perPageRecord = perPageRecord;
	}

	public int getShowPageNumber()
	{
		return showPageNumber;
	}

	public void setShowPageNumber(int showPageNumber)
	{
		this.showPageNumber = showPageNumber;
	}

	public String getTool()
	{
		return tool;
	}

	public void setTool(String tool)
	{
		this.tool = tool;
	}

	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public int getTotalRecord()
	{
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord)
	{
		this.totalRecord = totalRecord;
	}

	public List<Map<String, Object>> getList()
	{
		return list;
	}

	public void setList(List<Map<String, Object>> list)
	{
		this.list = list;
	}

}
