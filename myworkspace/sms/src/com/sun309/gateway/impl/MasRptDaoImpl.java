package com.sun309.gateway.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.MasRptDao;
import com.sun309.gateway.dto.mas.MasRpt;
import com.sun309.gateway.jdbc.MasDao;

public class MasRptDaoImpl extends MasDao implements MasRptDao
{
	public boolean deleteRpt(String ids, String interfaceName)
	{
		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer();
		sql.append("DELETE FROM api_rpt_");
		sql.append(interfaceName);
		sql.append(" WHERE SM_ID IN (");
		sql.append(ids);
		sql.append(")");
		if(this.delete(sql.toString()) > 0)
			return true;
		return false;
	}
	
	
	/**
	 * 通过条件获取记录
	 * 
	 * @param interfaceName
	 * @param condition
	 * 
	 * @return
	 */
	public ArrayList<MasRpt> findRptByCondition(String condition, String interfaceName)
	{
		ArrayList<MasRpt> _list = new ArrayList<MasRpt>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SM_ID, MOBILE, RPT_CODE, RPT_DESC, RPT_TIME  FROM api_rpt_");
		sql.append(interfaceName);
		sql.append(this.getSelectCondition(condition));
		List<Map<String, Object>> list = this.select(sql.toString());
		if(list == null || list.size() <= 0) return null;
		for (Map<String, Object> map : list)
		{
			MasRpt rpt = new MasRpt();
			rpt.setSmId(Long.parseLong(map.get("SM_ID").toString()));
			rpt.setMobile(map.get("MOBILE").toString());
			rpt.setRptCode(Short.parseShort(map.get("RPT_CODE").toString()));
			rpt.setRptDesc(map.get("RPT_DESC").toString());
			rpt.setRptTime(Timestamp.valueOf(map.get("RPT_TIME").toString()));
			_list.add(rpt);
		}
		list = null;
		return _list;
	}

}
