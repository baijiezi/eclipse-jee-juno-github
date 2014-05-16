package com.sun309.gateway.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun309.gateway.dao.MasMoDao;
import com.sun309.gateway.dto.mas.MasMo;
import com.sun309.gateway.jdbc.MasDao;

public class MasMoDaoImpl extends MasDao implements MasMoDao
{
	public boolean delete(String smId, String interfaceName)
	{
		int r = this.delete(new StringBuffer("DELETE FROM api_mo_").append(interfaceName).append(" WHERE SM_ID IN (" + smId + ")").toString());
		if (r > 0)
			return true;
		return false;
	}
	
	public boolean deleteUnicom(String AUTOSN, String interfaceName)
	{
		int r = this.delete(new StringBuffer("DELETE FROM api_mo_").append(interfaceName).append(" WHERE AUTO_SN IN (").append(AUTOSN).append(")").toString());
		if (r > 0)
			return true;
		return false;
	}

	public ArrayList<MasMo> findMoByCondition(String condition, String interfaceName)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT AUTO_SN , SM_ID , MOBILE , CONTENT , MO_TIME, MSG_FMT FROM api_mo_");
		sql.append(interfaceName);
		sql.append(this.getSelectCondition(condition));
		List<Map<String, Object>> list = this.select(sql.toString());
		if(list == null || list.size() <= 0) return null;
		ArrayList<MasMo> _list = new ArrayList<MasMo>();
		for (Map<String, Object> map : list)
		{
			MasMo mo = new MasMo();
			mo.setAutoSn(Long.parseLong(map.get("AUTO_SN").toString()));
			mo.setContent( map.get("CONTENT").toString());
			mo.setMobile(map.get("MOBILE").toString());
			mo.setMoTime(Timestamp.valueOf(map.get("MO_TIME").toString()));
			mo.setMsgFmt(Integer.parseInt(map.get("MSG_FMT").toString()));
			mo.setSmId(Long.parseLong( map.get("SM_ID").toString()));
			_list.add(mo);
		}
		list = null;
		return _list;
	}
}
